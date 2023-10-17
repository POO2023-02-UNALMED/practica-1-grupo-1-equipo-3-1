package uiMain;

import productos.*;
import personas.*;
import productos.Animal.tipoAnimal;
import administracion.Guia.tipoDePago;
import administracion.*;
import administracion.EventosAleatorios;
import personas.Cliente;
import transportes.*;
import personas.Cliente;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

// Menu principal
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        ArrayList<Camion> camionesMN = new ArrayList<>();

        ArrayList<Moto> motosMN = new ArrayList<>();


        ArrayList<Avion> avionesMN = new ArrayList<>();

        Sucursal medellinNorte = new Sucursal("Medellin Norte", 100, 100, -6, 8, camionesMN, motosMN, avionesMN);
        Sucursal medellinSur = new Sucursal("Medellin Sur", 100, 100, -6, 6, camionesMN, motosMN, avionesMN);

        Sucursal caliNorte = new Sucursal("Cali Norte", 400, 200, -8, -4, camionesMN, motosMN, avionesMN);
        Sucursal caliSur = new Sucursal("Cali Sur", 400, 200, -8, -6, camionesMN, motosMN, avionesMN);

        Sucursal pastoNorte = new Sucursal("Pasto Norte", 200, 700, -12, -10, camionesMN, motosMN, avionesMN);
        Sucursal pastoSur = new Sucursal("Pasto Sur", 200, 700, -12, -12, camionesMN, motosMN, avionesMN);

        Sucursal bogotaNorte = new Sucursal("Bogotá Norte", 1000, 500, 4, 2, camionesMN, motosMN, avionesMN);
        Sucursal bogotaSur = new Sucursal("Bogotá Sur", 1000, 500, 4, 2, camionesMN, motosMN, avionesMN);


        camionesMN.add(new Camion(medellinNorte, 27, 300, "ABC109"));
        motosMN.add(new Moto(medellinNorte, 1, 30, "ABC123"));
        avionesMN.add(new Avion(medellinNorte, bogotaNorte, 200, 2000, "asdfg"));

        //Deserializador.deserializar();

        //Menu principal
        Main.menuPrincipal(medellinNorte);

    }

    public static void menuPrincipal(Sucursal sucursal) {
        println("--- BIENVENIDO AL SISTEMA DE ENVIOS CORREMINAS ---");
        println("¿Qué operación deseas realizar?");
        println("1) Enviar paquete.");
        println("2) Pagar servicio.");
        println("3) Verificar paquete.");
        println("4) Rastrear paquete.");
        println("5) Recoger paquete.");
        println("6) Reclamos.");
        println("7) Terminar.");
        print("Elige una opcion: ");

        boolean numeroValido = false;

        while (!numeroValido) {
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    enviarPaquete(sucursal);
                    numeroValido = true;
                    break;
                case 2:
                    pagarServicio(sucursal);
                    numeroValido = true;
                    break;
                case 3:
                    verificarPaquete();
                    numeroValido = true;
                    break;
                case 4:
                    rastrearPaquete();
                    numeroValido = true;
                    break;
                case 5:
                    recogerPaquete();
                    numeroValido = true;
                    break;
                case 6:
                    opcionesReclamo();
                    numeroValido = true;
                    break;
                case 7:
                    salirDelSistema();
                    numeroValido = true;
                    break;
                default:
                    println("Número no válido. Inténtalo de nuevo.");
            }
        }
    }

    public static void print(Object objeto) {
        System.out.print(objeto);
    }

    public static void println(Object objeto) {
        System.out.println(objeto);
    }


    public static void enviarPaquete(Sucursal sucursalOrigen) {
        Scanner scanner = new Scanner(System.in);

        println("---------------DATOS DEL PRODUCTO----------------");
        println("Ingrese el tipo de producto:");
        println("1) Paquete");
        println("2) Animal");
        println("3) Documento");
        print("Elige una opción: ");

        boolean numeroValido = false;
        Producto producto = null;

        while (!numeroValido) {
            int tipoDeProducto = scanner.nextInt();
            scanner.nextLine();
            switch (tipoDeProducto) {
                case 1: //Paquete
                    println("-------------------------------------------------");
                    print("Peso del paquete: ");
                    double peso = scanner.nextDouble();
                    print("Alto del paquete: ");
                    double alto = scanner.nextDouble();
                    print("Ancho del paquete: ");
                    double ancho = scanner.nextDouble();
                    print("Largo del paquete: ");
                    double largo = scanner.nextDouble();

                    println("-------------------------------------------------");
                    println("¿El paquete es fragil?");
                    println("1) Sí");
                    println("2) No");
                    print("Elige una opcion: ");

                    boolean numeroValido2 = false;
                    boolean fragil = false;

                    while (!numeroValido2) {
                        int fragilEntrada = scanner.nextInt();
                        switch (fragilEntrada) {
                            case 1:
                                fragil = true;
                                numeroValido2 = true;
                                break;
                            case 2:
                                fragil = false;
                                numeroValido2 = true;
                                break;
                            default:
                                println("Número no válido. Inténtalo de nuevo.");
                        }
                    }
                    println("-------------------------------------------------");
                    print("Valor declarado: ");
                    double valorDeclarado = scanner.nextDouble();
                    scanner.nextLine();

                    producto = new Paquete(peso, alto, ancho, largo, fragil, valorDeclarado);
                    Paquete paquete = (Paquete) producto;
                    println(producto);
                    numeroValido = true;
                    break;

                case 2: //Animal
                    println("-------------------------------------------------");
                    print("Nombre del animal: ");
                    String nombre = scanner.nextLine();
                    print("Edad del animal: ");
                    int edad = scanner.nextInt();
                    print("Peso del animal: ");
                    double peso1 = scanner.nextDouble();

                    println("-------------------------------------------------");
                    println("Ingrese el tipo del animal:");
                    println("1) Perro");
                    println("2) Gato");
                    println("3) Hamster");
                    println("4) Loro");
                    println("5) Caballo");
                    println("6) Vaca");
                    print("Elige una opción: ");

                    tipoAnimal tipoAnimal = null;
                    boolean numeroValido3 = false;

                    while (!numeroValido3) {
                        int tipoAnimalEntrada = scanner.nextInt();
                        scanner.nextLine();

                        switch (tipoAnimalEntrada) {
                            case 1: //Perro
                                tipoAnimal = Animal.tipoAnimal.PERRO;
                                numeroValido3 = true;
                                break;
                            case 2:
                                tipoAnimal = Animal.tipoAnimal.GATO;
                                numeroValido3 = true;
                                break;
                            case 3:
                                tipoAnimal = Animal.tipoAnimal.HAMSTER;
                                numeroValido3 = true;
                                break;
                            case 4:
                                tipoAnimal = Animal.tipoAnimal.LORO;
                                numeroValido3 = true;
                                break;
                            case 5:
                                tipoAnimal = Animal.tipoAnimal.CABALLO;
                                numeroValido3 = true;
                                break;
                            case 6:
                                tipoAnimal = Animal.tipoAnimal.VACA;
                                numeroValido3 = true;
                                break;
                            default:
                                println("Número no válido. Inténtalo de nuevo");
                        }
                    }

                    producto = new Animal(nombre, edad, peso1, tipoAnimal);
                    Animal animal = (Animal) producto;
                    println(animal);
                    numeroValido = true;
                    break;
                case 3:
                    producto = new Documento();
                    Documento documento = (Documento) producto;
                    numeroValido = true;
                    break;
                default:
                    println("Número no válido. Inténtalo de nuevo.");
            }

        }

        println("----------------DATOS DEL USUARIO----------------");

        print("Nombre del remitente: ");
        String nombreRemitente = scanner.nextLine();
        print("Cédula del remitente: ");
        long cedulaRemitente = scanner.nextLong();
        print("Telefono del remitente: ");
        long telefonoRemitente = scanner.nextLong();
        scanner.nextLine();

        Cliente remitente = new Cliente(nombreRemitente, cedulaRemitente, null, telefonoRemitente);
        remitente.getMembresia().setBeneficio(Membresia.tipo.PLATINUM);
        println(remitente);

        println("-------------------------------------------------");
        print("Nombre del destinatario: ");
        String nombreDestinatario = scanner.nextLine();
        print("Cédula del destinatario: ");
        long cedulaDestinatario = scanner.nextLong();
        print("Telefono del remitente: ");
        long telefonoDestinatario = scanner.nextLong();

        Destinatario destinatario = new Destinatario(nombreDestinatario, cedulaDestinatario, null, telefonoDestinatario);

        String[] palabra = sucursalOrigen.getNombre().split(" ");
        println("------------------DATOS DE ENVÍO-----------------");

        println("Ciudad de origen: " + sucursalOrigen.getCiudad());
        println("Sucursal: " + palabra[1]);

        ArrayList<Sucursal> ciudadesDestino = new ArrayList<>();

        for (Sucursal sucursal : Sucursal.getTodasLasSucursales()) {
            if (!Objects.equals(sucursal.getCiudad(), sucursalOrigen.getCiudad())) {
                ciudadesDestino.add(sucursal);
            }
        }

        String ciudades = String.format("Seleccione la ciudad de destino: \n" +
                "1) %s\n" +
                "2) %s\n" +
                "3) %s", ciudadesDestino.get(0).getCiudad(), ciudadesDestino.get(2).getCiudad(), ciudadesDestino.get(4).getCiudad());

        println("-------------------------------------------------");
        println(ciudades);
        print("Elige una opción: ");

        Sucursal sucursalDestino = null;
        boolean numeroValido4 = false;

        while (!numeroValido4) {
            int destinoEntrada = scanner.nextInt();

            switch (destinoEntrada) {
                case 1:
                    String sucursales = String.format("Seleccione la Sucursal de preferencia:\n" +
                            "1) %s\n" +
                            "2) %s", ciudadesDestino.get(0).getNombre(), ciudadesDestino.get(1).getNombre());

                    println("-------------------------------------------------");
                    println(sucursales);
                    print("Elige una opción: ");

                    boolean numeroValido5 = false;

                    while (!numeroValido5) {
                        int sucursalEntrada = scanner.nextInt();

                        switch (sucursalEntrada) {
                            case 1:
                                sucursalDestino = ciudadesDestino.get(0);
                                numeroValido5 = true;
                                break;
                            case 2:
                                sucursalDestino = ciudadesDestino.get(1);
                                numeroValido5 = true;
                                break;
                            default:
                                println("Número no válido. Inténtalo de nuevo.");
                        }
                    }
                    numeroValido4 = true;
                    break;
                case 2:
                    String sucursales1 = String.format("Seleccione la Sucursal de preferencia:\n" +
                            "1) %s\n" +
                            "2) %s", ciudadesDestino.get(2).getNombre(), ciudadesDestino.get(3).getNombre());

                    println("-------------------------------------------------");
                    println(sucursales1);
                    print("Elige una opción: ");

                    boolean numeroValido6 = false;

                    while (!numeroValido6) {
                        int sucursalEntrada = scanner.nextInt();

                        switch (sucursalEntrada) {
                            case 1:
                                sucursalDestino = ciudadesDestino.get(2);
                                numeroValido6 = true;
                                break;
                            case 2:
                                sucursalDestino = ciudadesDestino.get(3);
                                numeroValido6 = true;
                                break;
                            default:
                                println("Número no válido. Inténtalo de nuevo.");
                        }
                    }
                    numeroValido4 = true;
                    break;
                case 3:
                    String sucursales2 = String.format("Seleccione la Sucursal de preferencia:\n" +
                            "1) %s\n" +
                            "2) %s", ciudadesDestino.get(4).getNombre(), ciudadesDestino.get(5).getNombre());

                    println("--------------------------------");
                    println(sucursales2);
                    print("Elige una opción: ");

                    boolean numeroValido7 = false;

                    while (!numeroValido7) {
                        int sucursalEntrada = scanner.nextInt();

                        switch (sucursalEntrada) {
                            case 1:
                                sucursalDestino = ciudadesDestino.get(4);
                                numeroValido7 = true;
                                break;
                            case 2:
                                sucursalDestino = ciudadesDestino.get(5);
                                numeroValido7 = true;
                                break;
                            default:
                                println("Número no válido. Inténtalo de nuevo.");
                        }
                    }
                    numeroValido4 = true;
                    break;
                default:
                    println("Número no válido. Inténtalo de nuevo.");
            }
        }

        boolean disponibilidadSucursal = false;
        boolean disponibilidadTransporte = false;

        if (producto instanceof Paquete || producto instanceof Documento) {
            if (sucursalOrigen.verificarDisponibilidad(producto)) {
                disponibilidadSucursal = true;
            }
        } else if (producto instanceof Animal) {
            if (sucursalOrigen.verificarDisponibilidad(producto)) {
                if (sucursalOrigen.disponibilidadJaulas((Animal) producto)) {
                    disponibilidadSucursal = true;
                }
            }
        }

        Transporte vehiculo = null;

        if (disponibilidadSucursal) {

            if (remitente.getMembresia().getBeneficio() == Membresia.tipo.PLATINUM || remitente.getMembresia().getBeneficio() == Membresia.tipo.GOLD) {
                println("---------------DATOS DE TRANSPORTE---------------");
                println("Ingrese el tipo de tranporte de su preferencia:");
                println("1) Camión");
                println("2) Avión (Envío directo y más rápido)");
                print("Elige una opción: ");

                boolean numeroValido5 = false;

                while (!numeroValido5) {
                    int transporteEntrada = scanner.nextInt();

                    switch (transporteEntrada) {
                        case 1:
                            if (!sucursalOrigen.getCamionesEnSucursal().isEmpty()) {
                                vehiculo = sucursalOrigen.getCamionesEnSucursal().get(0);
                                numeroValido5 = true;
                                disponibilidadTransporte = true;
                                break;
                            } else {
                                println("Lo sentimos no tenemos disponibilidad de camiones en este momento");
                                numeroValido5 = true;
                                break;
                            }
                        case 2:
                            if (!sucursalOrigen.getAvionesEnSucursal().isEmpty()) {
                                for (Avion aviones : sucursalOrigen.getAvionesEnSucursal()) {
                                    if (aviones.getSucursalDestino() == sucursalDestino) {
                                        vehiculo = aviones;
                                        disponibilidadTransporte = true;
                                        break;
                                    }
                                    if (vehiculo == null) {
                                        println("Lo sentimos no tenemos disponibilidad de aviones que se dirigen a esa sucursal en este momento");
                                    }
                                }
                                numeroValido5 = true;
                                break;
                            } else {
                                println("Lo sentimos no tenemos disponibilidad de aviones en este momento");
                                numeroValido5 = true;
                                break;
                            }
                        default:
                            println("Número no válido. Inténtalo de nuevo.");
                    }
                }

            } else {
                println("Verificando camiones disponibles");
                if (!sucursalOrigen.getCamionesEnSucursal().isEmpty()) {
                    vehiculo = sucursalOrigen.getCamionesEnSucursal().get(0);
                    disponibilidadTransporte = true;

                } else {
                    println("Lo sentimos no tenemos disponibilidad de camiones en este momento");
                }

            }
        } else {
            println("Lo sentimos, no tenemos disponibilidad en la sucursal");
        }

        if (disponibilidadTransporte) {
            println("------------------FORMA DE PAGO------------------");
            println("Seleccione el pago de su preferencia para el pedido:");
            println("1) Pago total");
            println("2) Pago fraccionado");
            println("3) Pago contraentrega");
            print("Elige una opción: ");

            boolean numeroValido6 = false;

            tipoDePago tipoDePago = null;

            while (!numeroValido6) {
                int pagoEntrada = scanner.nextInt();

                Guia guia = null;

                switch (pagoEntrada) {
                    case 1:
                        tipoDePago = Guia.tipoDePago.REMITENTE;
                        guia = new Guia(producto, remitente, destinatario, sucursalOrigen, sucursalDestino, tipoDePago, true, vehiculo);
                        sucursalOrigen.agregarProducto(producto);
                        println(guia);
                        Main.menuPrincipal(sucursalOrigen);
                        numeroValido6 = true;
                        break;
                    case 2:
                        tipoDePago = Guia.tipoDePago.FRACCIONADO;
                        guia = new Guia(producto, remitente, destinatario, sucursalOrigen, sucursalDestino, tipoDePago, true, vehiculo);

                        println("LLama funcionalidad pagar para pagar la mitad");
                        numeroValido6 = true;
                        break;
                    case 3:
                        tipoDePago = Guia.tipoDePago.DESTINATARIO;
                        guia = new Guia(producto, remitente, destinatario, sucursalOrigen, sucursalDestino, tipoDePago, true, vehiculo);

                        Random random = new Random();
                        println("Muchas gracias por usar nuestro servicio, favor acerquese a la caja #" + random.nextInt(5) + 1 + " para despachar el " + guia.getProducto().getClass().getSimpleName());
                        numeroValido6 = true;
                        break;
                    default:
                        println("Número no válido. Inténtalo de nuevo.");
                }
            }

            sucursalOrigen.agregarProducto(producto);

        }
        scanner.close();
    }

    //Revisar scanner
    public static void pagarServicio(Guia guia, Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);

        println("-----------------MÉTODO DE PAGO------------------");
        println("Ingrese el método de pago:");
        println("1) Tarjeta Crédito o Débito\n");
        println("2) Efectivo");
        print("Elige una opción: ");

        boolean numeroValido = false;

        while (!numeroValido) {
            int metodoDePagoEntrada = scanner.nextInt();
            switch (metodoDePagoEntrada) {
                case 1:
                    pagarTarjeta(guia, sucursal);
                    numeroValido = true;
                    break;
                case 2:
                    pagarEfectivo();
                    numeroValido = true;
                    break;
                default:
                    println("Número no válido. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }

    public static void pagarServicio(Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);
        println("----------------------PAGAR----------------------");
        print("Ingrese el código de la guía a pagar: ");
        int codigo = scanner.nextInt();

        println(Producto.getTodosLosProductos());
        Guia guia = null;
        for (Producto producto : Producto.getTodosLosProductos())
            if (producto.getCodigo() == codigo) {
                guia = producto.getGuia();
                break;
            }

        if (guia != null) {
            println("-----------------MÉTODO DE PAGO------------------");
            println("Ingrese el método de pago:");
            println("1) Tarjeta Crédito o Débito");
            println("2) Efectivo");
            print("Elige una opción: ");

            boolean numeroValido = false;

            while (!numeroValido) {
                int metodoDePagoEntrada = scanner.nextInt();
                switch (metodoDePagoEntrada) {
                    case 1:
                        pagarTarjeta(guia, sucursal);
                        numeroValido = true;
                        break;
                    case 2:
                        pagarEfectivo();
                        numeroValido = true;
                        break;
                    default:
                        println("Número no válido. Inténtalo de nuevo.");
                }
            }
        } else {
            println("Lo sentimos, el código de la guía no coincide, intentalo de nuevo");
        }


        scanner.close();
    }

    //Revisar Scanner
    //FUNCIONA
    public static void pagarTarjeta(Guia guia, Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);

        println("-----------------PAGO POR TARJETA----------------");
        print("Nombre del Titular: ");
        String titular = scanner.nextLine();
        print("Número: ");
        long numero = scanner.nextLong();
        print("CVV: ");
        int cvv = scanner.nextInt();
        scanner.nextLine();
        print("Fecha de expiración: ");
        String fechaExpiracion = scanner.nextLine();

        println("-------------------------------------------------");

        CuentaBancaria cuentaCliente = null;
        for (CuentaBancaria cuenta : CuentaBancaria.getTodasLasCuentas()) {
            if (cuenta.getNumero() == numero) {
                cuentaCliente = cuenta;
                break;
            }
        }
        if (cuentaCliente != null) {
            if (cuentaCliente.getTitular().getNombre().equals(titular)) {
                if (cuentaCliente.getNumero() == numero) {
                    if (cuentaCliente.getCVV() == cvv) {
                        if (cuentaCliente.getFechaExpiracion().equals(fechaExpiracion)) {
                            if (guia.getTipoDePago() == tipoDePago.DESTINATARIO) {
                                int entrada = scanner.nextInt();
                                scanner.close();

                                confirmarPago(guia, cuentaCliente, sucursal);

                            } else {
                                println("Descuento membresia");
                            }
                        } else {
                            println("Datos incorrectos, intente nuevamente");
                        }
                    } else {
                        println("Datos incorrectos, intente nuevamente");
                    }
                } else {
                    println("Datos incorrectos, intente nuevamente");
                }
            } else {
                println("Datos incorrectos, intente nuevamente");
            }
        } else {
            println("Lo sentimos, esta cuenta no existe");
        }
    }

    //FUNCIONA
    public static String pagarEfectivo() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(5) + 1;
        return "Acerquese a la caja #" + numeroAleatorio +
                " para cancelar";
    }

    public static void confirmarPago(Guia guia, CuentaBancaria cuentaCliente, Sucursal sucursal) { //TOMAS REVISAR
        Scanner scanner = new Scanner(System.in);

        println("-----------------CONFIRMAR PAGO------------------");

        println("¿Desea confirmar el pago por " + guia.getPrecioTotal() + "$?");
        println("1) Sí");
        println("2) No");
        print("Elige una opción: ");

        boolean numeroValido = false;
        while (!numeroValido) {
            int entrada = scanner.nextInt();
            switch (entrada) {
                case 1:
                    if (guia.getSucursalLlegada() == sucursal) { //¿El metodo ha sido accedido desde la sucursal de origen?
                        if (guia.getTipoDePago() == tipoDePago.REMITENTE) {
                            if (cuentaCliente.descontarSaldo(guia.getPagoPendiente())) {
                                println("Transacción exitosa");
                                guia.setPagoPendiente(guia.getPagoPendiente() * 0);
                                cuentaCliente.getTitular().subirReputacion();

                            } else {
                                println("Lo sentimos, no hay suficiente dinero en la cuenta");
                            }
                        } else if (guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
                            if (cuentaCliente.descontarSaldo(guia.getPagoPendiente() / 2)) {
                                println("Transacción exitosa");
                                guia.setPagoPendiente(guia.getPagoPendiente() / 2);
                                cuentaCliente.getTitular().subirReputacion();

                            } else {
                                println("Lo sentimos, no hay suficiente dinero en la cuenta");
                            }
                        }
                    }
					numeroValido = true;
					break;
				case 2:
					println("Servicio cancelado, vuelve pronto");
					numeroValido = true;
					break;
				default:
					println("Número no válido. Inténtalo de nuevo.");

			}


        }
    }



    public static void salirDelSistema() {
        // TODO Auto-generated method stub

    }

    public static void opcionesReclamo() {
        // TODO Auto-generated method stub

    }

    public static void recogerPaquete() {
        // TODO Auto-generated method stub

    }

    public static void rastrearPaquete() {
        // TODO Auto-generated method stub

    }

    public static void verificarPaquete() {
        // TODO Auto-generated method stub
    }
}



/*
	//case 2 - solicitamos el código del paquete para así mostrar la factura asociada a dicho paquete
	private static void pagarServicio() {

		System.out.print("--- Es hora de pagar ---\n");
		System.out.print("¿Quién realizará el pago?\n");
			System.out.print("1. Remitente \n");
			System.out.print("2. Destinatario \n");
		System.out.print("Selecciona una opción: ");
		int quienPaga = readInt();

		if (quienPaga == 1) {
			// qué otras cosas se podrían añadir¿?
			System.out.print("Por favor digita los siguientes datos:\n");
			System.out.print("Nombre del remitente:");
			int nombreRemitente = readInt();
			System.out.print("CC del remitente:");
			long cedulaRemitente = readLong();
			System.out.print("Código asignado en tu factura:");
			int codigoPaquete = readInt();
		}
		if (quienPaga == 2) {
			System.out.print("Por favor digita los siguientes datos:\n");
			System.out.print("Nombre del destinatario:");
			int nombreRemitente = readInt();
			System.out.print("CC del destinatario:");
			long cedulaRemitente = readLong();
			System.out.print("Código asignado en tu factura:");
			int codigoPaquete = readInt();
		}




	}


	//Case 1 - se solicitara por pantalla cierta informacion necesaria para registar el envio
    // usaremos tablas para mostrar la info ¿?

    private static void enviarPaquete() {

    	//tengo problemas al momento de imprimir en la consola algunas cosas y el cómo se leen otras, pero va funcionando

    	System.out.print("--- Ahora debes llenar la siguiente informacion ---\n");
		System.out.print("Nombre del remitente: ");
		String nombreRemitente = readString();
		System.out.print("CC del remitente: ");
		long cedulaRemitente = readLong();
		System.out.print("Tipo del paquete a enviar: \n"); //deberá escribir animal o producto
		System.out.print("1. Animal \n");
		System.out.print("2. Producto \n");
		System.out.print("Selecciona una opción: ");
		int tipoPaquete = readInt();



		if (tipoPaquete == 1) {
			System.out.print("Nombre del animal: ");
			String nombreAnimal = readString();
			System.out.print("Edad del animal: ");
			long edadAnimal = readLong();
			System.out.print("¿Qué tipo de animal es?: \n");
				System.out.print("1. Perro \n");
				System.out.print("2. Gato \n");
				System.out.print("3. Caballo \n");
				System.out.print("4. Vaca \n");
				System.out.print("5. Loro \n");
				System.out.print("6. Hamster \n");
			System.out.print("Selecciona una opción: ");
			int tipoAnimal = readInt();
			//con la opcion que se escoja, si es 3 o 4 ya se sabe que es peligroso por ende afectaria al precio
			//las opciones 1,2,5 y 6 se tomarían como no peligrosos

			System.out.print("Tamaño del animal: ");
			long tamañoAnimal = readLong();

			//con lo que dije arriba esta opción debería quitarse
			System.out.print("¿Es peligroso?: \n");
				System.out.print("1. Si \n");
				System.out.print("2. No \n");
				System.out.print("Selecciona una opción: ");
			String peligroAnimal = readString();

		}

		if(tipoPaquete == 2) {
			//con esto hay que definir el tema de los precios
			System.out.print("¿Es fragil?: \n");
				System.out.print("1. Si \n");
				System.out.print("2. No \n");
				System.out.print("Selecciona una opción: ");
			int fragilidad = readInt();
			System.out.print("Valor del producto a enviar: ");
			double valorDeclarado = readDouble();
			System.out.print("Altura del paquete: ");
			double alturaPaquete = readDouble();
			System.out.print("Ancho del paquete: ");
			double anchoPaquete = readDouble();
			System.out.print("Largo del paquete: ");
			double largoPaquete = readDouble();

			//con esto hay que definir el tema de los precios
			System.out.print("¿Tiene alguna instrucción especial?: \n");
				System.out.print("1. Si \n");
				System.out.print("2. No \n");
				System.out.print("Selecciona una opción: ");
			int instrucciones = readInt();

			if (instrucciones == 1) {
				System.out.print("¿Qué instrucción es?: \n");
					System.out.print("1. Conservar en un lugar fresco. \n");
					System.out.print("2. Manejar con cuidado. \n");
					// mas opciones¿?
				int siInstrucciones = readInt();

			}

		}
		// Mi idea es que esta funcionalidad una vez terminada, imprima una especie de factura con todos los datos digitados
		// para esto debemos enviar estos datos a una clase o método que se encargue de realizar la factura
		//Cliente factura = new Cliente(parametros necesarios);
		//System.out.println(factura);
    }



	//metodos necesarios para leer lo que escribe el usuario
    static long readLong() {
		return sc.nextLong();
	}
	static String readString() {
		return sc.next();
	}
	static int readInt() {
		return sc.nextInt();
	}
	static double readDouble() {
		return sc.nextDouble();
	}


    // Mi idea es que esta funcionalidad una vez terminada, imprima una especie de factura con todos los datos digitados



}



 EventosAleatorios
public class Main {
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        EventosAleatorios eventos = new EventosAleatorios();
        String mensajeEvento = eventos.generarEventoAleatorio();

        if (mensajeEvento.contains("se ha roto") || mensajeEvento.contains("ha sido robado") || mensajeEvento.contains("se ha perdido") 
        		|| mensajeEvento.contains("La GUERRILLA") || mensajeEvento.contains("ha sido explotado") 
        		|| mensajeEvento.contains("se ha roto!") || mensajeEvento.contains("ha ESCAPADO.")
        		|| mensajeEvento.contains("ha MUERTO")) {
            System.out.println(mensajeEvento);
            System.out.println("¿Deseas presentar un reclamo?");
            System.out.println("1) Sí");
            System.out.println("2) No");
        } else {
            System.out.println(mensajeEvento);
        }
    }
}
*/

    
	



	
   
    



