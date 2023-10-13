package uiMain;

import productos.*;
import personas.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import personas.Cliente;
import transportes.*;
import personas.Cliente;


import java.util.Scanner;
/*
public class Main {

    public static void print(Object objeto) {
        System.out.println(objeto);
    }

    public static void main(String[] args) {

        
        Animal Perrito = new Animal("Toby", 10, 30, tipoAnimal.PERRO);

        //System.out.println(Perrito.getTipo());
        Membresia membresia1 = new Membresia();
        //System.out.println(membresia1.getBeneficio());
        /*Destinatario destinatario1 = new Destinatario("Tomas",123456, new CuentaBancaria(), 316555 );
        Cliente cliente1 = new Cliente("Tomas",123456, new CuentaBancaria(), 316555, membresia1 );
        
        System.out.println(destinatario1.getNombre());
        System.out.println(destinatario1.getCedula());
        System.out.println(destinatario1.getCuentaBancaria());
        System.out.println(destinatario1.getTelefono());
        membresia1.setCliente(cliente1);
        System.out.println(membresia1);
        System.out.println(cliente1);
        System.out.println(destinatario1);
            }´ñ        */

      /*  Cliente guzman = new Cliente("Jaime Guzman", 1033487678, null, 350562901, membresia1);

        CuentaBancaria cuentaGuzman = new CuentaBancaria(guzman, 45068373, 123, "08/27");
        guzman.setCuentaBancaria(cuentaGuzman);

        //Ejemplos
        Sucursal medellin = new Sucursal("Medellín", 500, 1000, -5, 5);
        Sucursal cali = new Sucursal("Cali", 500, 1000, -5, -2);
        Sucursal pasto = new Sucursal("Pasto", 500, 1000, -8, -5);
        Sucursal florencia = new Sucursal("Florencia", 500, 1000, 4, -1);
        Sucursal bogota = new Sucursal("Bogotá", 500, 1000, 5, 2);
        //System.out.println(Sucursal.getTodasLasSucursales());
        //System.out.println(new Paquete(20, 3, 4, 3, false, 3000, "Nada"));

        /*for (Sucursal sucursal : Sucursal.getTodasLasSucursales()) {
            System.out.println(sucursal.getCiudad());
        }*/

     /*   Guia guiaToby = new Guia(Perrito, true, false, guzman, bogota, florencia);
        /*for (Sucursal sucursal : guiaToby.getRuta()) {
            print(sucursal.getCiudad());
        }*/

        //print(Guia.calcularDistancia(florencia, bogota));
        //print(guiaToby.pagarEfectivo());
      //  guzman.getCuentaBancaria().setSaldo(10000000);
       // print(guiaToby.pagarTarjeta("Jaime Guzman", 45068373, 123, "08/27"));
        //print(guzman.getCuentaBancaria().getSaldo());
        //print(guiaToby.getPrecioTotal());
        //}
    
   

//}

//Kevin 

import java.util.Scanner;
import productos.*;
import personas.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import personas.Cliente;
import transportes.*;
import personas.Cliente;


import java.util.Scanner;


// Menu principal
public class Main {
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    
    	//Deserializador.deserializar();
    	
    	//Menu principal
    	
    	int opcion;
    	do {
    		System.out.println("--- BIENVENIDO AL SISTEMA DE ENVIOS CORREMINAS ---");
    		System.out.println("¿Qué operación deseas realizar?");
    		System.out.println(" 1) Enviar paquete.");
    		System.out.println(" 2) Pagar servicio.");
    		System.out.println(" 3) Verificar paquete.");
    		System.out.println(" 4) Rastrear paquete.");
    		System.out.println(" 5) Recoger paquete.");
    		System.out.println(" 6) Reclamos.");
    		System.out.println(" 7) Terminar.");
    		System.out.println("Elige una opcion: ");
    		opcion = (int) sc.nextInt();
   	
    	switch (opcion) {
    		case 1: 
    			enviarPaquete();
    			break;
    		case 2:
    			pagarServicio();
    			break;
    		case 3:
    			verificarPaquete();
    			break;
    		case 4:
    			rastrearPaquete();
    			break;
    		case 5:
    			recogerPaquete();
    			break;
    		case 6:
    			opcionesReclamo();
    			break;
    		case 7:
    			salirDelSistema();
    			break;
    	}
    	} while(opcion != 7);
    }
    private static void salirDelSistema() {
		// TODO Auto-generated method stub
		
	}
	private static void opcionesReclamo() {
		// TODO Auto-generated method stub
		
	}
	private static void recogerPaquete() {
		// TODO Auto-generated method stub
		
	}
	private static void rastrearPaquete() {
		// TODO Auto-generated method stub
		
	}
	private static void verificarPaquete() {
		// TODO Auto-generated method stub
		
	}
	
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
    



	
   
    



