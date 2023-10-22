package uiMain;

import productos.*;
import personas.*;
import productos.Animal.tipoAnimal;
import administracion.Guia.tipoDePago;
import administracion.*;
import personas.Cliente;
import transportes.*;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import basedatos.Deserializador;
import basedatos.Serializador;

// Menu principal
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


/*
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

        Cliente guzman = new Cliente("Jaime Guzman", 123456789, 987654321);
        CuentaBancaria guzmanCuenta = new CuentaBancaria(guzman, 1010101010, 666, "09/27", 1000000);
        Destinatario david = new Destinatario("David", 55555, 666666);

        Producto documento = new Documento();
        Guia guiaDoc = new Guia(documento, guzman, david, medellinNorte, caliSur, tipoDePago.REMITENTE, camionesMN.get(0));

        medellinNorte.getInventario().add(documento);
        Camion camionmn = medellinNorte.getCamionesEnSucursal().get(0);

        camionmn.agregarProductos();
        camionmn.iniciarRecorrido();

        Serializador.serializar();



        //Deserializador.deserializar();


        /*

        for (Persona persona : Persona.getTodasLasPersonas()) {
            println(persona.getNombre());
        }
         for (CuentaBancaria cuenta : CuentaBancaria.getTodasLasCuentas()) {
            println(cuenta.getNumero());
            println("----");
        }

        for (Guia guia : Guia.getTodasLasGuias()) {
            println(guia);
        }
        
        for (Transporte vehiculo: Transporte.getTodosLosTransportes() ) {
        	println(vehiculo);
        	println(vehiculo.getSucursalOrigen());
        }
    


        println(Transporte.getTodosLosTransportes());



        println(documento.getCodigo());


<<<<<<< Updated upstream
        */
    	Deserializador.deserializar();
    	

        //Menu principal
        Main.menuPrincipal(Sucursal.getTodasLasSucursales().get(0));


    }

    public static void print(Object objeto) {
        System.out.print(objeto);
    }

    public static void println(Object objeto) {
        System.out.println(objeto);
    }

    public static void menuPrincipal(Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);
        

            println("--- BIENVENIDO AL SISTEMA DE ENVIOS CORREMINAS ---");
            println("¿En qué sucursal te encuentras?");
            println("1) Medellín.");
            println("2) Bogotá.");
            println("3) Cali.");
            print("Elija una opcion: ");
            int opcion1 = scanner.nextInt();
            	
            switch (opcion1) {
            	case 1: 
            		println("1) Medellin Norte.");
            		println("2) Medellin Sur.");
            		print("Elija una opcion: ");
            		int ciudad1 = scanner.nextInt();
            		ciudadOrigen(ciudad1);
            		break;
            		
            	case 2:
            		println("1) Bogotá Norte.");
            		println("2) Bogotá Sur.");
            		print("Elija una opcion: ");
            		int ciudad2 = scanner.nextInt();
            		ciudadOrigen(ciudad2);
            		break;
            		
            	case 3:
            		println("1) Cali Norte.");
            		println("2) Cali Sur.");
            		print("Elija una opcion: ");
            		int ciudad3 = scanner.nextInt();
            		ciudadOrigen(ciudad3);
            		break;
            		
            	default:
                     print("Número no válido. Inténtalo de nuevo: ");
                     scanner.nextLine();
            	}
   
            
        
            	
        while (true) {
            println("--- BIENVENIDO AL SISTEMA DE ENVIOS CORREMINAS SEDE ---");
            println("¿Qué operación deseas realizar?");
            println("1) Enviar paquete.");
            println("2) Pagar servicio.");
            println("3) Rastrear paquete.");
            println("4) Recoger paquete.");
            println("5) Reclamos.");
            println("6) Terminar.");
            print("Elige una opcion: ");

            try {
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        enviarPaquete(sucursal);

                        break;
                    case 2:
                        pagarServicio(sucursal);

                        break;
                    case 3:
                        rastrearPaquete(sucursal);

                        break;
                    case 4:
                        recogerPaquete(sucursal);

                        break;
                    case 5:
                        opcionesReclamo();

                        break;
                    case 6:
                        salirDelSistema();

                        break;
                    default:
                        print("Número no válido. Inténtalo de nuevo: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Dato no válido. Por favor inténtelo de nuevo: ");
                scanner.nextLine();
            }
        }
    }




	private static void ciudadOrigen(int ciudadOrigen) {
		
		
	}

	public static void enviarPaquete(Sucursal sucursalOrigen) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        do {
            try {
                println("---------------DATOS DEL PRODUCTO----------------");
                println("Ingrese el tipo de producto:");
                println("1) Paquete");
                println("2) Animal");
                println("3) Documento");
                print("Elige una opción: ");

                int tipoDeProducto = scanner.nextInt();
                scanner.nextLine();
                Producto producto = null;
                boolean numeroValido = false;

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
                                    print("Número no válido. Inténtalo de nuevo: ");
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
                                    print("Número no válido. Inténtalo de nuevo: ");
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
                        print("Número no válido. Inténtalo de nuevo: ");
                        break;
                }


                println("----------------DATOS DEL USUARIO----------------");

                print("Nombre del remitente: ");
                String nombreRemitente = scanner.nextLine();
                print("Cédula del remitente: ");
                long cedulaRemitente = scanner.nextLong();
                print("Telefono del remitente: ");
                long telefonoRemitente = scanner.nextLong();
                scanner.nextLine();

                Cliente remitente = new Cliente(nombreRemitente, cedulaRemitente, telefonoRemitente);

                println("-------------------------------------------------");
                print("Nombre del destinatario: ");
                String nombreDestinatario = scanner.nextLine();
                print("Cédula del destinatario: ");
                long cedulaDestinatario = scanner.nextLong();
                print("Telefono del destinatario: ");
                long telefonoDestinatario = scanner.nextLong();

                Destinatario destinatario = new Destinatario(nombreDestinatario, cedulaDestinatario, telefonoDestinatario);

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
                                        print("Número no válido. Inténtalo de nuevo: ");
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
                                        print("Número no válido. Inténtalo de nuevo: ");
                                }
                            }
                            numeroValido4 = true;
                            break;
                        case 3:
                            String sucursales2 = String.format("Seleccione la Sucursal de preferencia:\n" +
                                    "1) %s\n" +
                                    "2) %s", ciudadesDestino.get(4).getNombre(), ciudadesDestino.get(5).getNombre());

                            println("-------------------------------------------------");
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
                                        print("Número no válido. Inténtalo de nuevo: ");
                                }
                            }
                            numeroValido4 = true;
                            break;
                        default:
                            print("Número no válido. Inténtalo de nuevo: ");
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
                                    print("Número no válido. Inténtalo de nuevo: ");
                            }
                        }

                    } else {
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
                                guia = new Guia(producto, remitente, destinatario, sucursalOrigen, sucursalDestino, tipoDePago, vehiculo);
                                println(guia.toString());

                                println("Diríjase a la pestaña principal para pagar su servicio.");
                                println("");

                                print("1) Volver al menú principal: ");

                                boolean numerovalido7 = false;

                                while (!numerovalido7) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            Main.menuPrincipal(sucursalOrigen);
                                            numerovalido7 = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }

                                numeroValido6 = true;
                                break;
                            case 2:
                                tipoDePago = Guia.tipoDePago.FRACCIONADO;
                                guia = new Guia(producto, remitente, destinatario, sucursalOrigen, sucursalDestino, tipoDePago, vehiculo);
                                println(guia.toString());

                                println("Diríjase a la pestaña principal para pagar su servicio.");
                                println("");
                                print("1) Volver al menú principal: ");

                                boolean numerovalido8 = false;

                                while (!numerovalido8) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            Main.menuPrincipal(sucursalOrigen);
                                            numerovalido8 = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }

                                numeroValido6 = true;
                                break;
                            case 3:
                                tipoDePago = Guia.tipoDePago.DESTINATARIO;
                                guia = new Guia(producto, remitente, destinatario, sucursalOrigen, sucursalDestino, tipoDePago, vehiculo);
                                println(guia.toString());

                                sucursalOrigen.agregarProducto(producto);

                                Random random = new Random();
                                println("Muchas gracias por usar nuestro servicio, favor acerquese \na la caja #" +
                                        random.nextInt(5) + 1 + " para entregar el " + guia.getProducto().getClass().getSimpleName());
                                println("");
                                print("1) Volver al menú principal: ");

                                boolean numerovalido9 = false;

                                while (!numerovalido9) {
                                    int menuPrincipalEntrada = scanner.nextInt();
                                    switch (menuPrincipalEntrada) {
                                        case 1:
                                            Main.menuPrincipal(sucursalOrigen);
                                            numerovalido9 = true;
                                            break;
                                        default:
                                            print("Número no válido. Inténtalo de nuevo: ");
                                    }
                                }
                                numeroValido6 = true;
                                break;
                            default:
                                print("Número no válido. Inténtalo de nuevo: ");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ha ingresado un valor incorrecto. Por favor, ingrese un valor válido.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }

            System.out.println("¿Desea continuar (1 para continuar, cualquier otra tecla para cancelar)?: ");
            int choice = scanner.nextInt();
            if (choice != 1) {
                exit = true; // Salir del proceso de enviar paquetes
            }
        } while (!exit);

        scanner.close();
    }


    public static void pagarServicio(Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);
        println("----------------------PAGAR----------------------");
        print("Ingrese el código de la guía a pagar: ");
        int codigo = scanner.nextInt();

        Guia guia = null;
        for (Producto producto : Producto.getTodosLosProductos())
            if (producto.getCodigo() == codigo) {
                guia = producto.getGuia();
                break;
            }

        if (guia != null) {
            if (guia.getSucursalOrigen() == sucursal) { //¿El metodo ha sido accedido desde la sucursal de origen? Eso quiere decir que el que está pagando es el remitente
                if (guia.getTipoDePago() == tipoDePago.REMITENTE || guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
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
                                pagarEfectivo(guia, sucursal);
                                numeroValido = true;
                                break;
                            default:
                                print("Número no válido. Inténtalo de nuevo: ");
                        }
                    }
                } else {
                    println("El tipo de pago seleccionado fue contraentrega, el destinatario\n paga completamente el pedido");
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
                }
            } else { //Accedido por el destinatario
                if (guia.getTipoDePago() == tipoDePago.DESTINATARIO || guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
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
                                pagarEfectivo(guia, sucursal);

                                numeroValido = true;
                                break;
                            default:
                                print("Número no válido. Inténtalo de nuevo: ");
                        }
                    }
                } else {
                    println("El pedido ya está completamente pago.");
                    println("Diríjase a la pestaña principal para recoger su pedido.");
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
                }
            }
        } else {
            println("Lo sentimos, el código de la guía no coincide, intentalo de nuevo");
            println("");
            pagarServicio(sucursal);
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
                            confirmarPago(guia, cuentaCliente, sucursal);
                        } else {
                            println("Datos incorrectos, intente nuevamente");
                            println("");
                            pagarTarjeta(guia, sucursal);
                        }
                    } else {
                        println("Datos incorrectos, intente nuevamente");
                        println("");
                        pagarTarjeta(guia, sucursal);
                    }
                } else {
                    println("Datos incorrectos, intente nuevamente");
                    println("");
                    pagarTarjeta(guia, sucursal);
                }
            } else {
                println("Datos incorrectos, intente nuevamente");
                println("");
                pagarTarjeta(guia, sucursal);
            }
        } else {
            println("Lo sentimos, esta cuenta no existe, intentelo de nuevo");
            println("");
            pagarTarjeta(guia, sucursal);
        }
    }

    //FUNCIONA
    public static void pagarEfectivo(Guia guia, Sucursal sucursal) {
        if (guia.getSucursalOrigen() == sucursal) { //¿El metodo ha sido accedido desde la sucursal de origen? Eso quiere decir que el que está pagando es el remitente
            switch (guia.getTipoDePago()) {
                case REMITENTE:
                    guia.setPagoPendiente(guia.getPagoPendiente() * 0);
                    break;
                case FRACCIONADO:
                    guia.setPagoPendiente(guia.getPagoPendiente() / 2);
                    break;
            }
        } else { //Está pagando el destinatario
            switch (guia.getTipoDePago()) {
                case DESTINATARIO:
                    guia.setPagoPendiente(guia.getPagoPendiente() * 0);
                    break;
                case FRACCIONADO:
                    guia.setPagoPendiente(guia.getPagoPendiente() / 2);
                    break;
            }
        }

        Random random = new Random();
        int numeroAleatorio = random.nextInt(5) + 1;
        println("----------------PAGO EN EFECTIVO-----------------");
        println("Gracias por usar nuestro servicio, porfavor\nacerquese a la caja #" + numeroAleatorio + " para cancelar $" + (guia.getPrecioTotal() - guia.getPagoPendiente()));
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

        println("-----------------CONFIRMAR PAGO------------------");

        println("¿Desea confirmar el pago por $" + guia.getPrecioTotal() + "?");
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
                                guia.setPagoPendiente(guia.getPagoPendiente() * 0);
                                cuentaCliente.getTitular().subirReputacion();

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

                                println("-------------------------------------------------");

                                String format = "%-39s";
                                Random random = new Random();
                                println(String.format(format, "¡¡Transacción exitosa!!"));
                                println("Muchas gracias por usar nuestro servicio, favor acerquese a la caja #" + random.nextInt(5) + 1 + " para despachar el " + guia.getProducto().getClass().getSimpleName());
                                println("Recuerda que el destinatario dde pagar un total de $" + guia.getPagoPendiente());

                                println("-------------------------------------------------");
                            } else {
                                println("Lo sentimos, no hay suficiente dinero en la cuenta");
                                alternativa(guia, sucursal);
                            }
                        }
                    } else { //Está pagando el destinatario
                        if (guia.getTipoDePago() == tipoDePago.DESTINATARIO || guia.getTipoDePago() == tipoDePago.FRACCIONADO) {
                            if (cuentaCliente.descontarSaldo(guia.getPagoPendiente())) {
                                guia.setPagoPendiente(guia.getPagoPendiente() * 0);
                                cuentaCliente.getTitular().subirReputacion();

                                println("-------------------------------------------------");

                                Random random = new Random();
                                println("¡¡Transacción exitosa!!");
                                println("Muchas gracias por usar nuestro servicio, favor acerquese a la caja #" + random.nextInt(5) + 1 + " para despachar el " + guia.getProducto().getClass().getSimpleName());

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

            try {
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

            } catch (InputMismatchException e) {
                System.out.println("Error: Ha ingresado un valor incorrecto. Por favor, ingrese un valor válido.");
                scanner.nextLine();
            }
        }
    }

    public static void salirDelSistema() {
        System.out.println("Muchas gracias por usar nuestro servicio, Hasta la proxima...");
        Serializador.serializar();
        System.exit(0);

        // TODO Auto-generated method stub

    }

    public static void opcionesReclamo() {
        // TODO Auto-generated method stub

    }

    //Revisar
    //Recoger
    //Estaba en la clase Sucursal y no esta terminado, hayq eu cambiarle MUCHAS cosas
    // :(


    // Sirve a medias jasjasj ya lo corrijo KEVIN
    public static void recogerPaquete(Sucursal sucursal) { //Sucursal desde la cual se está recogiendo el paquete
        Scanner scanner = new Scanner(System.in);
        boolean datosValidos = false;
        while (!datosValidos) {
            try {
                println("-----------------RECOGER PRODUCTO----------------");
                print("Ingrese el código de la guía: ");
                int codigoPaquete = scanner.nextInt();
                scanner.nextLine();

                print("Ingrese su nombre: ");
                String nombreDestinatario = scanner.nextLine();

                print("Ingrese su cédula: ");
                int cedulaDestinatario = scanner.nextInt();

                datosValidos = true;

                //Encuentra el producto por el código de la guia
                Producto producto = encontrarProductoPorCodigo(codigoPaquete);

                //aas
                if (producto != null) {
                    //se verifican los datos del destinatario
                    if (verificarDatos(producto, nombreDestinatario, cedulaDestinatario, sucursal)) {
                        realizarEntrega(producto, sucursal);
                    } else {
                        System.out.println("Los datos que proporcionó no coinciden con los datos del destinatario.");
                    }
                } else {
                    System.out.println("Lo sentimos pero no se encontró un paquete asignado al código que proporcionó.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor intentelo de nuevo: ");
                scanner.nextLine();
            }

        }
    }

    //esto verificar con prints
    private static Producto encontrarProductoPorCodigo(int codigo) {
        for (Producto producto : Producto.getTodosLosProductos()) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null; // Producto no encontrado
    }

    private static boolean verificarDatos(Producto producto, String nombreDestinatario, int cedulaDestinatario, Sucursal sucursal) {
        return producto.getGuia().getSucursalLlegada() == sucursal &&
                producto.getGuia().getDestinatario().getNombre().equals(nombreDestinatario) &&
                producto.getGuia().getDestinatario().getCedula() == cedulaDestinatario;
    }

    private static void realizarEntrega(Producto producto, Sucursal sucursal) {
        Guia guia = producto.getGuia();

        if (guia.getEstado() == Guia.estado.ENESPERA && sucursal.getInventario().contains(producto)) {
            if (guia.getTipoDePago() == Guia.tipoDePago.DESTINATARIO) {
                println("Para retirar el producto tiene que cancelar el servicio por valor de $" + guia.getPrecioTotal());
                // Agregar lógica de pago
            } else {
                sucursal.getInventario().remove(producto);
                Random random = new Random();
                println("Operación realizada con éxito, favor acercarse a la caja0\n" +
                        random.nextInt(5) + " para retirar su paquete, muchas gracias por usar nuestro servicio");
            }
        } else if (guia.getEstado() == Guia.estado.ENTREGADO) {
            println("El paquete fue entregado el día " + guia.getFecha().getDayOfWeek() + " del mes " + guia.getFecha().getMonth());
        } else if (guia.getEstado() == Guia.estado.ENTRANSITO) {
            println("El paquete todavía no ha llegado");

        }

    }
        /*Producto producto = null;
        for (Producto productos : Producto.getTodosLosProductos()) { //Revisa en todos los productos creados
            int codigoPaquete = Producto.codigo;
			if (producto.getCodigo() == codigoPaquete) { //Encuentra el producto que coincida con el codigo
                producto = productos;
                break;
            }
        }

        if (producto != null) {
            if (producto.getGuia().getSucursalLlegada() == sucursal) { //Verifica si esa si es la sucursal de destino final
                String nombreRemitente = null;
				if (producto.getGuia().getDestinatario().getNombre().equals(nombreRemitente)) { //Verifica el nombre del destinatario
                    long cedulaRemitente = 0;
					if (producto.getGuia().getDestinatario().getCedula() == cedulaRemitente) { //Verifica la cedula del destinatario
                        if (producto.getGuia().isEntregaEnSucursal()) { //Creo que esto es redundante
                            if (producto.getGuia().getEstado() == Guia.estado.ENESPERA && sucursal.getInventario().contains(producto)) { //Tambien redundante
                                //if (producto.getGuia().)
                                if (producto.getGuia().getTipoDePago() == Guia.tipoDePago.DESTINATARIO) { //Verifica el pago contraentrega
                                    println("Para retirar el producto tiene que cancelar el servicio por valor de $" +
                                            producto.getGuia().getPrecioTotal());
                                    //Pagar
                                } else {
                                    sucursal.getInventario().remove(producto);
                                    Random random = new Random();
                                    println("Operación realizada con éxito, favor acercarce a la caja " +
                                            random.nextInt(5) + " para retirar su paquete, muchas gracias por usar nuestro servicio");
                                }
                            } else if (producto.getGuia().getEstado() == Guia.estado.ENTREGADO) {
                                println("El paquete fue entregado el dia# del mes #");
                            } else if (producto.getGuia().getEstado() == Guia.estado.ENTRANSITO) {
                                println("El paquete todavía no ha llegado");
                                //rastrear
                            }
                        } else {
                            println("Lo sentimos, el paquete fue programado para tener como destino la siguiente dirección" +
                                    producto.getGuia().getDireccion());
                        }
                    } else {
                        println("Datos incorrectos, intente nuevamente");
                    }
                } else {
                    println("Datos incorrectos, intente nuevamente");
                }
            } else {
                println("El paquete tiene como destino la ciudad de " + producto.getGuia().getSucursalLlegada().getNombre());
            }
        } else {
            println("Datos incorrectos, intente nuevamente");
        }
    }

    }*/


    //Revisar
    //Rastrear
    public static void rastrearPaquete(Sucursal sucursal) {
        Scanner scanner = new Scanner(System.in);
        println("----------------RASTREAR PRODUCTO----------------");
        print("Ingrese el código de la guía a rastrear: ");
        int codigo = scanner.nextInt();


        Guia guia = null;
        for (Producto producto : Producto.getTodosLosProductos())
            if (producto.getCodigo() == codigo) {
                guia = producto.getGuia();
                break;
            }

        if (guia != null) {
            switch (guia.getEstado()) {
                case ENTRANSITO:
                    if (guia.getVehiculo() instanceof Camion) {
                        Camion camion = (Camion)guia.getVehiculo();
                        println("-------------------------------------------------");
                        println(camion.ubicarTransporte());

                        StringBuilder barra = new StringBuilder();
                        barra.append("[");
                        for (int i = 0; i < (int)(guia.avancePedido()); i++) {
                            barra.append("+");
                        }
                        for (int j = 0; j < 100 - (int)(guia.avancePedido()); j++) {
                            barra.append("-");
                        }
                        barra.append("]");
                        println(barra);
                        println(guia.avancePedido());
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
                    break;
                case ENESPERA:
                    println("Tu " + guia.getProducto().getClass().getSimpleName() + " ya llegó a la sucursal de destino");
                    StringBuilder barra = new StringBuilder();
                    barra.append("[");
                    for (int i = 0; i < (int)(guia.avancePedido()); i++) {
                        barra.append("+");
                    }
                    for (int j = 0; j < 100 - (int)(guia.avancePedido()); j++) {
                        barra.append("-");
                    }
                    barra.append("]");
                    println(barra);
                    println(guia.avancePedido());
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
                    break;
                case ENTREGADO:
                    println("Tu paquete ya ha sido reclamado");
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
            }
        } else {
            println("Lo sentimos, el código de la guía no coincide, intentalo de nuevo");
            println("");
            rastrearPaquete(sucursal);
        }
        // TODO Auto-generated method stub

    }

    public static void verificarPaquete() {
        // TODO Auto-generated method stub
    }


    //kevin terminar

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

   
	



	
   
    



