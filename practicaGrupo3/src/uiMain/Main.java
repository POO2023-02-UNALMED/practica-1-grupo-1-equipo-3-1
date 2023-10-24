
        } else {
            println("Lo sentimos, esta cuenta no existe, intentelo de nuevo");
            println("");
            println("1) Volver a intentar");
            println("2) Volver al menú principal");
            print("Elija una opcion: ");

            boolean numerovalido = false;

            while (!numerovalido) {
                int menuPrincipalEntrada = scanner.nextInt();
                switch (menuPrincipalEntrada) {
                    case 1:
                        pagarTarjeta(guia, sucursal);
                        numerovalido = true;
                        break;
                    case 2:
                        Main.menuPrincipal(sucursal);
                        numerovalido = true;
                        break;
                    default:
                        print("Número no válido. Inténtalo de nuevo: ");
                }
            }
        }
    }

    //FUNCIONA
    public static void pagarEfectivo(Guia guia, Sucursal sucursal) {
        double precio = 0;
        if (guia.getSucursalOrigen() == sucursal) { //¿El metodo ha sido accedido desde la sucursal de origen? Eso quiere decir que el que está pagando es el remitente
            switch (guia.getTipoDePago()) {
                case REMITENTE:
                    guia.setPagoPendiente(guia.getPagoPendiente() * 0);
                    precio = guia.getPrecioTotal();
                    break;
                case FRACCIONADO:
                    guia.setPagoPendiente(guia.getPagoPendiente() / 2);
                    precio = guia.getPrecioTotal() / 2;
                    break;
            }
        } else { //Está pagando el destinatario
            switch (guia.getTipoDePago()) {
                case DESTINATARIO:
                    guia.setPagoPendiente(0);
                    precio = guia.getPrecioTotal();
                    break;
                case FRACCIONADO:
                    guia.setPagoPendiente(0);
                    precio = guia.getPrecioTotal() / 2;
                    break;
            }
        }

        Random random = new Random();
        sucursal.agregarProducto(guia.getProducto());

        int numeroAleatorio = random.nextInt(5) + 1;
        println("----------------PAGO EN EFECTIVO-----------------");
        println("Gracias por usar nuestro servicio, porfavor\nacerquese a la caja #" + numeroAleatorio + " para cancelar $" + precio);
        println("");
        print("1) Volver al menú principal: ");

        boolean numerovalido = false;

        while (!numerovalido) {
            int menuPrincipalEntrada = scanner.nextInt();
            switch (menuPrincipalEntrada) {
                case 1:
                    Main.menuPrincipal(sucursal);
                    numerovalido = true;
                    break;
                default:
                    print("Número no válido. Inténtalo de nuevo: ");
            }
        }

    }


    public static void confirmarPago(Guia guia, CuentaBancaria cuentaCliente, Sucursal sucursal) { //TOMAS REVISAR
        Scanner scanner = new Scanner(System.in);
        double precio = guia.getPrecioTotal();

        if (guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
            precio = precio / 2;
        }

        println("-----------------CONFIRMAR PAGO------------------");

        println("¿Desea confirmar el pago por $" + precio + "?");
        println("1) Sí");
        println("2) No");
        println("3) Ver credenciales de la cuenta");
        print("Elige una opción: ");

        boolean numeroValido = false;
        while (!numeroValido) {
            int entrada = scanner.nextInt();
            switch (entrada) {
                case 1:
                    if (guia.getSucursalOrigen() == sucursal) { //¿El metodo ha sido accedido desde la sucursal de origen? Eso quiere decir que el que está pagando es el remitente
                        if (guia.getTipoDePago() == tipoDePago.REMITENTE) {
                            if (cuentaCliente.descontarSaldo(guia.getPagoPendiente())) {
                                guia.setPagoPendiente(0);
                                cuentaCliente.getTitular().subirReputacion();
                                sucursal.agregarProducto(guia.getProducto()); //Se agrega el producto al inventario de la sucursal


                                println("-------------------------------------------------");

                                String format = "%-39s";
                                Random random = new Random();
                                println(String.format(format, "¡¡Transacción exitosa!!"));
                                println("Muchas gracias por usar nuestro servicio,\n favor acerquese a la caja #" + random.nextInt(5) + 1 + " para despachar el " + guia.getProducto().getClass().getSimpleName());
                                println("");
                                print("1) Volver al menú principal: ");

                                boolean numerovalido3 = false;

                                while (!numerovalido3) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            Main.menuPrincipal(sucursal);
                                            numerovalido3 = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }

                            } else {
                                println("Lo sentimos, no hay suficiente dinero en la cuenta");
                                alternativa(guia, sucursal);
                            }
                        } else if (guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
                            if (cuentaCliente.descontarSaldo(guia.getPagoPendiente() / 2)) {
                                guia.setPagoPendiente(guia.getPagoPendiente() / 2);
                                cuentaCliente.getTitular().subirReputacion();
                                sucursal.agregarProducto(guia.getProducto()); //Se agrega el producto al inventario de la sucursal

                                println("-------------------------------------------------");

                                String format = "%-39s";
                                Random random = new Random();
                                println(String.format(format, "¡¡Transacción exitosa!!"));
                                println("Muchas gracias por usar nuestro servicio, favor acerquese a la caja #" + random.nextInt(5) + 1 + " para despachar el " + guia.getProducto().getClass().getSimpleName());
                                println("Recuerda que el destinatario debe pagar un total de $" + guia.getPagoPendiente());

                                println("");
                                print("1) Volver al menú principal: ");

                                boolean numerovalido3 = false;

                                while (!numerovalido3) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            Main.menuPrincipal(sucursal);
                                            numerovalido3 = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }
                            } else {
                                println("Lo sentimos, no hay suficiente dinero en la cuenta");
                                alternativa(guia, sucursal);
                            }
                        }
                    } else { //Está pagando el destinatario
                        if (guia.getTipoDePago() == tipoDePago.DESTINATARIO || guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
                            if (cuentaCliente.descontarSaldo(guia.getPagoPendiente())) {
                                guia.setPagoPendiente(0);
                                cuentaCliente.getTitular().subirReputacion();

                                println("-------------------------------------------------");

                                Random random = new Random();
                                println("¡¡Transacción exitosa!!");
                                println("Muchas gracias por usar nuestro servicio, favor acerquese a la caja #" + random.nextInt(5) + 1 + " para despachar el " + guia.getProducto().getClass().getSimpleName());
                                println("");
                                print("1) Volver al menú principal: ");

                                boolean numerovalido3 = false;

                                while (!numerovalido3) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            Main.menuPrincipal(sucursal);
                                            numerovalido3 = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }
                                println("-------------------------------------------------");

                                cuentaCliente.getTitular().subirReputacion();
                            } else {
                                println("Lo sentimos, no hay suficiente dinero en la cuenta");
                                alternativa(guia, sucursal);
                            }
                        }
                    }

                    numeroValido = true;
                    break;
                case 2:
                    println("Servicio cancelado, vuelve pronto");
                    println("");

                    print("1) Volver al menú principal: ");

                    boolean numerovalido2 = false;

                    while (!numerovalido2) {
                        int menuPrincipalEntrada = scanner.nextInt();
                        switch (menuPrincipalEntrada) {
                            case 1:
                                Main.menuPrincipal(sucursal);
                                numerovalido2 = true;
                                break;
                            default:
                                print("Número no válido. Inténtalo de nuevo: ");
                        }
                    }

                    numeroValido = true;
                    break;
                case 3:
                    credencialesUsuario(guia, cuentaCliente, sucursal);

                    numeroValido = true;
                    break;
                default:
                    print("Número no válido. Inténtalo de nuevo: ");
            }
        }
    }

    public static void alternativa(Guia guia, Sucursal sucursal) { //Si no tiene suficiente dinero en la cuenta puede pagar eefectivo
        println("¿Quieres pagar en efectivo?");
        println("1) Sí");
        println("2) No, cancelar compra");
        print("Elige una opción: ");

        boolean numeroValido = false;

        while (!numeroValido) {
            int entrada1 = scanner.nextInt();

            switch (entrada1) {
                case 1:
                    pagarEfectivo(guia, sucursal);

                    numeroValido = true;
                    break;
                case 2:
                    println("Servicio cancelado, vuelve pronto");
                    menuPrincipal(Sucursal.getTodasLasSucursales().get(0));
                    numeroValido = true;
                    break;
            }
        }
    }

    public static void credencialesUsuario(Guia guia, CuentaBancaria cuentaCliente, Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);
        println("-------------CREDENCIALES DEL USUARIO------------");
        println("Hola " + cuentaCliente.getTitular().getNombre() + "\n¿qué deseas conocer respecto a tu perfil?");
        println("1) Cuenta Bancaria");
        println("2) Membresía");
        println("3) Volver a confirmar pago");
        print("Elige una opción: ");

        boolean datosCorrectos = false;

        while (!datosCorrectos) {
            int opcionCliente = scanner.nextInt();
            scanner.nextLine();
            switch (opcionCliente) {

                case 1: // cuenta Bancaria
                    println(cuentaCliente);

                    println("1) Volver a Credenciales");

                    boolean numeroValido = false;

                    while (!numeroValido) {
                        int entrada = scanner.nextInt();
                        switch (entrada) {
                            case 1:
                                credencialesUsuario(guia, cuentaCliente, sucursal);

                                numeroValido = true;
                                break;
                        }
                    }

                    datosCorrectos = true;
                    break;
                case 2: //membresía
                    if (cuentaCliente.getTitular() instanceof Cliente) {
                        Cliente titular = (Cliente) cuentaCliente.getTitular();
                        println(titular.getMembresia());
                    } else {
                        println("No puede hacer uso de tu membresía si no eres el remitente"); //No me gusta el mensaje
                    }

                    print("1) Volver a Credenciales: ");

                    boolean numeroValido2 = false;

                    while (!numeroValido2) {
                        int entrada = scanner.nextInt();
                        switch (entrada) {
                            case 1:
                                credencialesUsuario(guia, cuentaCliente, sucursal);

                                numeroValido2 = true;
                                break;
                        }
                    }

                    datosCorrectos = true;
                    break;

                case 3: //volver al menu
                    confirmarPago(guia, cuentaCliente, sucursal);

                    datosCorrectos = true;
                    break;
            }

            datosCorrectos = true;
        }
    }

    public static void salirDelSistema() {
        System.out.println("Muchas gracias por usar nuestro servicio, Hasta la proxima...");
        Serializador.serializar();
        System.exit(0);

        // TODO Auto-generated method stub

    }

    public static void opcionesOpiniones() {
        Scanner scanner = new Scanner(System.in);
        println("-------------Opinion Sucursales------------");
        println("");
        println(Opinion.generarTablaSucursales());
        println("");

        println("Ingrese a que sucursal quiere ingresar una opinion:");
        println("1) Medellin Norte");
        println("2) Medellin Sur");
        println("3) Cali Norte");
        println("4) Cali Sur");
        println("5) Pasto Norte");
        println("6) Pasto Sur");
        println("7) Bogotá Norte");
        println("8) Bogotá Sur");
        print("Elige una opción: ");

        boolean numeroValido1 = false;
        Sucursal sucursalOpinion = null;

        while (!numeroValido1) {
            int OpinionEntrada = scanner.nextInt();
            switch (OpinionEntrada) {
                case 1:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(0);
                    numeroValido1 = true;
                    break;
                case 2:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(1);
                    numeroValido1 = true;
                    break;
                case 3:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(2);
                    numeroValido1 = true;
                    break;
                case 4:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(3);
                    numeroValido1 = true;
                    break;
                case 5:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(4);
                    numeroValido1 = true;
                    break;
                case 6:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(5);
                    numeroValido1 = true;
                    break;
                case 7:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(6);
                    numeroValido1 = true;
                    break;
                case 8:
                    sucursalOpinion = Sucursal.getTodasLasSucursales().get(7);
                    numeroValido1 = true;
                    break;
                default:
                    print("Número no válido. Inténtalo de nuevo: ");
            }
        }
        println("-----------------------------------------------------------");
        println("Usted escogio la sucursal: " + sucursalOpinion.getNombre());
        println("-----------------------------------------------------------");


        double numero;
        boolean esValido = false;
        while (!esValido) {
            println("Ingrese su opinion de puntualidad de la sucursal escogida");
            System.out.print("Ingresa un número entre 0 y 5: ");
            try {
                numero = scanner.nextDouble();
                if (numero >= 0.0 && numero <= 5.0) {
                    esValido = true;
                    println("Número válido: " + numero);
                    sucursalOpinion.getOpinionSucursal().agregarOpinionPunt(numero);
                } else {
                    println("Número fuera del rango. Inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                println("Entrada no válida. Inténtalo de nuevo.");
                scanner.next();
            } // Limpiar el búfer de entrada

        }

        println("Ingrese su opinion de integridad de la sucursal escogida");
        double numero2;
        boolean esValido1 = false;
        while (!esValido1) {
            print("Ingresa un número entre 0 y 5: ");
            try {
                numero2 = scanner.nextDouble();
                if (numero2 >= 0.0 && numero2 <= 5.0) {
                    esValido1 = true;
                    println("Número válido: " + numero2);
                    sucursalOpinion.getOpinionSucursal().agregarOpinionInt(numero2);
                    if (numero2 < 1.0) {
                        boolean encontrada = false;
                        do {
                            print("Ingrese su nombre: ");
                            String nombreBuscado = scanner.next();
                            print("Ingrese su cédula: ");
                            Long cedulaBuscada = scanner.nextLong();
                            print("Ingrese su telefono: ");
                            Long telefonoBuscada = scanner.nextLong();

                            encontrada = false;

                            for (Persona persona : Persona.getTodasLasPersonas()) {
                                if (persona.getNombre().equals(nombreBuscado) && persona.getCedula() == cedulaBuscada) {
                                    encontrada = true;
                                    if (persona instanceof Cliente) {
                                        println("La persona existe en la Base de datos y es un Cliente.");
                                        ((Cliente) persona).getMembresia().setBeneficio(Membresia.tipo.PLATINUM);
                                        println("-----------------------------------------------------------");
                                        println("Lamentamos los inconvenientes Sr/ Sra " + persona.getNombre() + " se le ha mejorado la membresia a Platinum como compensancion");
                                        println("-----------------------------------------------------------");
                                    } else {
                                        println("La persona existe en la Base de datos pero no es un Cliente.");
                                    }
                                    break;
                                }
                            }

                            if (!encontrada) {
                                // SE CREA UNA PERSONA CON LOS DATOS SUMINISTRADOS
                                Cliente x = new Cliente(nombreBuscado, cedulaBuscada, telefonoBuscada);
                                x.getMembresia().setBeneficio(Membresia.tipo.PLATINUM);
                                println("-----------------------------------------------------------");
                                println("Se le ha creado una cuenta con los siguientes datos: ");
                                println(x);
                                println("La proxima vez que use nuestro servicio utilice esta cuenta para acceder a grandiosos beneficios. ");
                                println("-----------------------------------------------------------");
                                break;
                            }
                        } while (!encontrada);

                    }
                } else {
                    println("Número fuera del rango. Inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                println("Entrada no válida. Inténtalo de nuevo.");
            }
        }


        if (sucursalOpinion.getOpinionSucursal().promedioPuntualidad() < 1.0) {
            sucursalOpinion.setCapacidadPeso(sucursalOpinion.getCapacidadPeso() - 10);
            println("Sentimos la molestia que pudimos haber causado, para el mejoramiento del servicio hemos implmentado en esta sucursal un plan de mejoramiento.");
        }
        println(Opinion.generarTablaSucursales());
        println("MUCHAS GRACIAS POR REGISTRAR SU OPINION, EN NUESTRA EMPRESA SU OPINION HACE LA DIFERENCIA :)");
        println("-----------------------------------------------------------");
        println("");
        Main.menuPrincipal(Sucursal.getTodasLasSucursales().get(0));

    }


    public static void recogerPaquete(Sucursal sucursal) { //Sucursal desde la cual se está recogiendo el paquete
        Scanner scanner = new Scanner(System.in);

        println("-----------------RECOGER PRODUCTO----------------");

        print("Ingrese el código de la guía: ");
        int codigoPaquete = scanner.nextInt();
        scanner.nextLine();

        print("Ingrese su nombre: ");
        String nombreDestinatario = scanner.nextLine();

        print("Ingrese su cédula: ");
        long cedulaDestinatario = scanner.nextLong();

        //Encuentra el producto por el código de la guia
        Producto producto = encontrarProductoPorCodigo(codigoPaquete);


        if (producto != null) {
            //Obtenemos la guia asociada al producto
            Guia guia = producto.getGuia();

            if (verificarDatos(producto, cedulaDestinatario)) {
                //Se verifica que el paquete llegó y que está en el inventario de la sucursal
                if (guia.getSucursalLlegada() == sucursal) {
                    if (sucursal.getInventario().contains(producto) && guia.getEstado() != Guia.estado.ENTREGADO) {

                        //si el saldo pendiente es 0
                        if (guia.getTipoDePago() == tipoDePago.REMITENTE) {
                            //se validan los datos

                            Random random = new Random();
                            LocalDateTime fechaEntrega = LocalDateTime.now();
                            println("");
                            println("************************************* *");
                            println("*  Operación realizada con éxito     *");
                            println("*  Favor acercarse a la caja No. " + random.nextInt(5) + "  *");
                            println("*  para retirar su paquete.          *");
                            println("*  ¡Muchas gracias por usar nuestro  *");
                            println("*  servicio!                         *");
                            println("**************************************");
                            //cambia el estado de la guia
                            guia.setEstado(Guia.estado.ENTREGADO);
                            sucursal.getInventario().remove(producto);

                            //volver al menu
                            println("");

                            print("1) Volver al menú principal: ");

                            boolean numerovalido = false;

                            while (!numerovalido) {
                                int menuPrincipalEntrada = scanner.nextInt();
                                switch (menuPrincipalEntrada) {
                                    case 1:
                                        menuPrincipal(sucursal);
                                        numerovalido = true;
                                        break;
                                    default:
                                        print("Número no válido. Inténtalo de nuevo: ");
                                }
                            }

                        } else {
                            if (guia.getPagoPendiente() != 0) {
                                println("-------------------------------------------------");
                                println("Para poder recoger este paquete debes pagar la deuda pendiente asociada a este.");
                                println("¿Desea pagar el envío?");
                                println("1) Sí");
                                println("2) Volver al menú principal");
                                print("Elige una opción: ");
                                boolean numeroValido1 = false;
                                while (!numeroValido1) {
                                    int confirmapago = scanner.nextInt();
                                    switch (confirmapago) {
                                        case 1:
                                            pagarServicio(sucursal, guia);
                                        case 2:
                                            //volver al menu
                                            Main.menuPrincipal(sucursal);
                                            break;

                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }
                            } else {
                                Random random = new Random();
                                LocalDateTime fechaEntrega = LocalDateTime.now();
                                println("");
                                println("************************************* *");
                                println("*  Operación realizada con éxito     *");
                                println("*  Favor acercarse a la caja No. " + random.nextInt(5) + "  *");
                                println("*  para retirar su paquete.          *");
                                println("*  ¡Muchas gracias por usar nuestro  *");
                                println("*  servicio!                         *");
                                println("**************************************");
                                //cambia el estado de la guia
                                guia.setEstado(Guia.estado.ENTREGADO);
                                sucursal.getInventario().remove(producto);

                                //volver al menu
                                println("");

                                print("1) Volver al menú principal: ");

                                boolean numerovalido = false;

                                while (!numerovalido) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            menuPrincipal(sucursal);
                                            numerovalido = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }
                            }
                        }
                    } else {
                        //Se verifica si el paquete no está en la sucursal
                        //verificamos si el paquete fue entregado
                        if (guia.getEstado() == Guia.estado.ENTREGADO) {
                            println("-------------------------------------------------");
                            println("El paquete que busca ya fue entregado.");

                            //volver al menu
                            println("");
                            print("1) Volver al menú principal: ");

                            boolean numerovalido = false;

                            while (!numerovalido) {
                                int menuPrincipalEntrada = scanner.nextInt();
                                switch (menuPrincipalEntrada) {
                                    case 1:
                                        menuPrincipal(sucursal);
                                        numerovalido = true;
                                        break;
                                    default:
                                        print("Número no válido. Inténtalo de nuevo: ");
                                }
                            }
                        } else {
                            println("-------------------------------------------------");
                            println("El envío no ha llegado");
                            println("¿Desea rastrear el envío?");
                            println("1) Sí");
                            println("2) Volver al menú principal");
                            print("Elige una opción: ");


                            boolean numeroValido = false;

                            while (!numeroValido) {
                                int entrada = scanner.nextInt();
                                switch (entrada) {
                                    case 1:
                                        rastrearPaquete(sucursal);

                                        numeroValido = true;
                                        break;
                                    case 2:
                                        menuPrincipal(sucursal);

                                        numeroValido = true;
                                        break;
                                    default:
                                        print("Número no válido. Inténtalo de nuevo: ");
                                }
                            }
                        }
                    }
                } else {
                    println("-------------------------------------------------");
                    println("EL envío tiene como destino la sucursal de " + guia.getSucursalLlegada().getNombre());
                    println("¿Desea rastrear el envío?");
                    println("1) Sí");
                    println("2) Volver al menú principal");
                    print("Elige una opción: ");


                    boolean numeroValido = false;

                    while (!numeroValido) {
                        int entrada = scanner.nextInt();
                        switch (entrada) {
                            case 1:
                                rastrearPaquete(sucursal);

                                numeroValido = true;
                                break;
                            case 2:
                                menuPrincipal(sucursal);

                                numeroValido = true;
                                break;
                            default:
                                print("Número no válido. Inténtalo de nuevo: ");
                        }
                    }
                }
            } else {
                println("-------------------------------------------------");
                println("Los datos ingresados no corresponden con los del remitente.");
                println("");
                print("1) Volver al menú principal: ");

                boolean numerovalido = false;

                while (!numerovalido) {
                    int menuPrincipalEntrada = scanner.nextInt();
                    switch (menuPrincipalEntrada) {
                        case 1:
                            Main.menuPrincipal(sucursal);
                            numerovalido = true;
                            break;
                        default:
                            print("Número no válido. Inténtalo de nuevo: ");
                    }
                }

            }
        } else {
            println("-------------------------------------------------");
            println("El paquete que intenta buscar no existe.");
            //volver al menu
            println("");
            print("1) Volver al menú principal: ");

            boolean numerovalido = false;

            while (!numerovalido) {
                int menuPrincipalEntrada = scanner.nextInt();
                switch (menuPrincipalEntrada) {
                    case 1:
                        menuPrincipal(sucursal);
                        numerovalido = true;
                        break;
                    default:
                        print("Número no válido. Inténtalo de nuevo: ");
                }
            }
        }


        if (producto.getGuia().getEstado() == Guia.estado.ENTREGADO) {
            if (sucursal.getInventario().contains(producto)) {
                sucursal.getInventario().remove(producto);
            }
        }
    }

    private static Producto encontrarProductoPorCodigo(int codigo) {
        for (Producto producto : Producto.getTodosLosProductos()) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null; // Producto no encontrado
    }

    private static boolean verificarDatos(Producto producto, long cedulaDestinatario) {
        Guia guia = producto.getGuia();
        Destinatario destinatario = (Destinatario) guia.getDestinatario();
        if (Long.valueOf(destinatario.getCedula()).equals(cedulaDestinatario)) {
            return true;
        }
        return false;// Destinatario diferente
    }


    public static void rastrearPaquete(Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);


        println("-----------------RASTREAR PRODUCTO---------------");
        print("Ingrese el código de la guía a rastrear: ");
        int codigo = scanner.nextInt();

        Guia guia = null;
        for (Producto producto : Producto.getTodosLosProductos()) {
            if (producto.getCodigo() == codigo) {
                guia = producto.getGuia();
                break;
            }
        }

        if (guia != null) {
            boolean enviado = false;

            if (guia.getTipoDePago() == tipoDePago.REMITENTE) {
                if (guia.getPagoPendiente() == 0) {
                    enviado = true;
                }
            } else if (guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
                if (guia.getPagoPendiente() == guia.getPrecioTotal() / 2) {
                    enviado = true;
                }
            } else {
                enviado = true;
            }

            if (enviado) {
                StringBuilder barra = new StringBuilder();
                barra.append("[");
                for (int i = 0; i < (int) (guia.avancePedido()); i++) {
                    barra.append("+");
                }
                for (int j = 0; j < 100 - (int) (guia.avancePedido()); j++) {
                    barra.append("-");
                }
                barra.append("]");

                println("-------------------------------------------------");

                switch (guia.getEstado()) {
                    case ENSUCURSALORIGEN:
                        println("El camión con tu pedido está preparándose para salir");
                        println(barra);
                        println(guia.avancePedido() + "%");

                        break;
                    case ENTRANSITO:
                        if (guia.getVehiculo() instanceof Camion) { //Está en un Camión
                            Camion camion = (Camion) guia.getVehiculo();
                            println(camion.ubicarTransporte());
                        } else { //Está en un Avión
                            Avion avion = (Avion) guia.getVehiculo();
                            println(avion.ubicarTransporte());
                        }
                        println(barra);
                        println(guia.avancePedido() + "%");
                        break;

                    case ENESPERA:
                        println("Tu " + guia.getProducto().getClass().getSimpleName() + " ya llegó a la sucursal de destino");
                        println(barra);
                        println(guia.avancePedido() + "%");
                        break;

                    case ENTREGADO:
                        println("Tu paquete ya ha sido reclamado");
                        println(barra);
                        println(guia.avancePedido() + "%");
                        break;

                }
                println("");

                print("1) Volver al menú principal: ");

                boolean numerovalido = false;

                while (!numerovalido) {
                    int menuPrincipalEntrada = scanner.nextInt();
                    switch (menuPrincipalEntrada) {
                        case 1:
                            Main.menuPrincipal(sucursal);
                            numerovalido = true;
                            break;
                        default:
                            print("Número no válido. Inténtalo de nuevo: ");
                    }
                }
            } else {
                println("-------------------------------------------------");
                println("Lo sentimos, completa el pago para finalizar el registro del envío");
                println("");

                print("1) Volver al menú principal: ");

                boolean numerovalido = false;

                while (!numerovalido) {
                    int menuPrincipalEntrada = scanner.nextInt();
                    switch (menuPrincipalEntrada) {
                        case 1:
                            Main.menuPrincipal(sucursal);
                            numerovalido = true;
                            break;
                        default:
                            print("Número no válido. Inténtalo de nuevo: ");
                    }
                }
            }
        } else {
            println("-------------------------------------------------");
            println("Lo sentimos, el código de la guía no coincide, intentelo de nuevo");
            println("");

            println("1) Volver a intentar");
            println("2) Volver al menú principal");
            print("Elija una opcion: ");

            boolean numerovalido = false;

            while (!numerovalido) {
                int menuPrincipalEntrada = scanner.nextInt();
                switch (menuPrincipalEntrada) {
                    case 1:
                        rastrearPaquete(sucursal);
                        numerovalido = true;
                        break;
                    case 2:
                        Main.menuPrincipal(sucursal);
                        numerovalido = true;
                        break;
                    default:
                        print("Número no válido. Inténtalo de nuevo: ");
                }
            }
        }
    }
}













