package administracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import (paquete).Guia

import productos.*;

public class Sucursal {
	enum Horario {
		LUNES, 
		MARTES, 
		MIERCOLES,
		JUEVES, 
		VIERNES,
		SABADO,
		DOMINGO,
	}

	private String ciudad;
	private int capacidad;
	private int capacidadVolumen; //TOMAS alternativa a capacidad
	private int capacidadPeso; //TOMAS Las sucursales van a estar limitadas por el peso?
	private int latitud; //TOMAS
	private int longitud; //TOMAS
	private ArrayList<Producto> inventario = new ArrayList<>(); //TOMAS
    private Map<Horario, String> horario;
	private int cantidadMotosDisponibles;
    private int cantidadCamionesDisponibles;
    private int cantidadAvionesDisponibles;
	private int cantidadJaulasPequeñas;
	private int cantidadJaulasMedianas;
	private int cantidadJaulasGrandes;
}
	
	//constructor
	public Sucursal(String ciudad, int capacidad, int latitud, int longitud) {
		//TOMASCorregí el tipo de horario e inventario
		//TOMASAgregue los atributos latitud y longitud y los combié a enteros (plano cartesiano)
		this.ciudad = ciudad;
		this.horario = horario;
		this.capacidad = capacidad;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	   
	//metodos
	public void asignarhorario() {
        	//  horarios específicos para cada sucursal,
        	//en este caso solo se especificaron 3 ciudadades
        	//podemos modificar las horas a gusto
        if ("Medellin".equals(ciudad)) {
            // Horario para Medellin
            horario.put(Horario.LUNES, "9:00 AM - 6:00 PM");
            horario.put(Horario.MARTES, "9:00 AM - 6:00 PM");
            horario.put(Horario.MIERCOLES, "9:00 AM - 6:00 PM");
            horario.put(Horario.JUEVES, "9:00 AM - 6:00 PM");
            horario.put(Horario.VIERNES, "9:00 AM - 6:00 PM");
        } else if ("Bogota".equals(ciudad)) {
            // Horario para Bogotá
            horario.put(Horario.LUNES, "8:00 AM - 7:00 PM");
            horario.put(Horario.MARTES, "8:00 AM - 7:00 PM");
            horario.put(Horario.MIERCOLES, "8:00 AM - 7:00 PM");
            horario.put(Horario.JUEVES, "8:00 AM - 7:00 PM");
            horario.put(Horario.VIERNES, "8:00 AM - 7:00 PM");
        } else if ("Cali".equals(ciudad)) {
            // Horario para Cali
            horario.put(Horario.LUNES, "10:00 AM - 5:00 PM");
            horario.put(Horario.MARTES, "10:00 AM - 5:00 PM");
            horario.put(Horario.MIERCOLES, "10:00 AM - 5:00 PM");
            horario.put(Horario.JUEVES, "10:00 AM - 5:00 PM");
            horario.put(Horario.VIERNES, "10:00 AM - 5:00 PM");
        }

	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getCapacidad() {
		return this.capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	
	// horario. IDEA: Dar una opción para conocer la disponibilidad de la sucursal
	 public String consultarHorario() {
		 String nombre = ciudad;
	        return "Horario de la Sucursal " + nombre + ":";
	        for (Horario dia : Horario.values()) {
	            return dia + ": " + horario.get(dia);
	        }
	    }

	
	// IDEA: añadir un metodo que permita conocer la disponibilidad de tipos de transporte por sucursal
	// y así mostrarle al cliente las opciones con las cuales puede enviar el paquete
	// aqui podemos meter directamente la capacidad como parametro

	// los valores de estos atributos los podemos especificar directamente
	// lo siguiente es cómo se vería el método  agregar paquete si colocamos como parametro el tipo de transporte
	

	//TOMAS

	public boolean disponibilidadJaulas(Animal animal) { //Se ingresa el animal para saber su tamaño y verificar si hay jaulas para él
		boolean disponibilidad = false;

		switch (animal.getTamano()) {
			case PEQUENO:
				if (cantidadJaulasPequeñas > 0) {
					cantidadJaulasPequeñas --;
					disponibilidad = true;
					break;
				}
			case MEDIANO:
				if (cantidadJaulasMedianas > 0) {
					cantidadJaulasMedianas --;
					disponibilidad = true;
					break;
				}
			case GRANDE:
				if (cantidadJaulasGrandes > 0) {
					cantidadJaulasGrandes --;
					disponibilidad = true;
					break;
				}
		}

		return disponibilidad;
	}

	public String agregarProducto(Producto nuevoProducto) { //Se usa este metodo cuando se hace el envío o llega un paquete de otra sucursal
		boolean seAgrega = false;

		if (nuevoProducto instanceof Animal) { //Verfica si este producto es animal
			Animal nuevoAnimal = (Animal) nuevoProducto; //Fundicion de producto a animal de forma explicita

			if (disponibilidadJaulas(nuevoAnimal)) { //Verifica si hay jaulas
				if (capacidadVolumen > nuevoAnimal.getVolumen()) { //Verifica si hay espacio en la sucursal
					if (capacidadPeso > nuevoAnimal.getPeso()) { //Verifica el peso
						inventario1.add(nuevoAnimal); //Si se cumple todo lo agrega y resta las distintas capacidades

						capacidadVolumen -= nuevoAnimal.getVolumen();
						capacidadPeso -= nuevoAnimal.getPeso();

						seAgrega = true; //Para arrojar el mensaje al final
					}
				}
			}

		} else { //Sino es animal, no verifica las jaulas sino los otros dos parametros

			if (capacidadVolumen > nuevoProducto.getVolumen()) {
				if (capacidadPeso > nuevoProducto.getPeso()) {
					inventario.add(nuevoProducto);

					capacidadVolumen -= nuevoProducto.getVolumen();
					capacidadPeso -= nuevoProducto.getPeso();
					seAgrega = true;
				}
			}
		}

		if (seAgrega) {
			return "Tenemos disponibilidad";
		} else {
			return "Lo sentimos, nuestra sucursal no tiene disponibilidad en este momento";
		}
	}

	public String verificarProductoCliente(Producto producto) { //verifica para ser recogido por el cliente
		if (inventario.contains(producto)) {
	        return "El paquete con código " + producto.getCodigo() + " se encuentra en la sucursal y está listo para ser recogido.";
		} else {
	        return "Lo sentimos, paquete con código " + producto.getCodigo() + " no está en la sucursal.";
		}
	}

	public boolean verificarProducto(Producto producto) { //Verifica para ser recogido por los transportes para llevarlo a otra sucursal
		if (inventario.contains(producto)) {
			return true;
		} else {
			return false;
		}
	}

	public String asignarTransporte(Producto producto, String ciudad, String tipoTransporte) {
		String codigoP = inventario.get(producto);
		String nombre = ciudad;
	
        if (tipoTransporte.equalsI("Moto") && cantidadMotosDisponibles > 0) {
           Guia.vehiculo = "Moto";
	        cantidadMotosDisponibles =- 1;
	
	    } else if (tipoTransporte.equals("Camion") && cantidadCamionesDisponibles > 0) {
	        Guia.vehiculo = "Camion";
	       cantidadCamionesDisponibles =- 1;
	       
	    } else if (tipoTransporte.equals("Avion") && cantidadAvionesDisponibles > 0) {
	        Guia.vehiculo = "Avion";
	        cantidadAvionesDisponibles =- 1;
	       
	   } else {
	        return "No hay disponibilidad de transporte para el paquete con código " + codigoP + " en la sucursal " + nombre + ".";
	    }
	 }
	
	// Método para obtener la disponibilidad de cada tipo de transporte en la sucursal
	
	 public String obtenerDisponibilidadTransporte() {
		 if cantidadMotosDisponibles>0 || cantidadCamionesDisponibles>0 || cantidadAvionesDisponibles>0{
	
		 	return "Disponibilidad de transporte en la Sucursal " + nombre + ":";
		 	return "Motos disponibles: " + cantidadMotosDisponibles;
		 	return "Camiones disponibles: " + cantidadCamionesDisponibles;
		 	return "Aviones disponibles: " + cantidadAvionesDisponibles;
		 }
		 if cantidadMotosDisponibles<0 && cantidadCamionesDisponibles<0 && cantidadAvionesDisponibles<0{
			 return "No hay disponibilidad de transporte para el paquete.";
		 }



	    // Método para agregar un paquete al inventario de la sucursal
	 public void agregarPaquete(Producto producto) {
	      inventario.add(codigoPaquete, descripcion); 
	 }

	    // Método para verificar si un paquete está en la sucursal
	 public String verificarPaquete(Producto producto) {
	     String codigoPaquete = inventario.get(producto)
		 if (inventario.contains(producto)) {
	            return "El paquete con código " + codigoPaquete + " se encuentra en la sucursal.";
	      } else {
	            return "El paquete con código " + codigoPaquete + " no está en la sucursal.";
	        }
	 }

	    		//Esto iria en la clase main
	   /// public static void main(String[] args) {
	        //Sucursal sucursal = new Sucursal();

	        // Agregar paquetes al inventario
	        //sucursal.agregarPaquete("PAQ123", "Paquete 1");
	        //sucursal.agregarPaquete("PAQ456", "Paquete 2");
	        //sucursal.agregarPaquete("PAQ789", "Paquete 3");

	        // Verificar si un paquete está en la sucursal
	        //sucursal.verificarPaquete("PAQ123"); // Debería mostrar "El paquete con código PAQ123 se encuentra en la sucursal."
	       // sucursal.verificarPaquete("PAQ999"); // Debería mostrar "El paquete con código PAQ999 no está en la sucursal."
	    //}
	

		//recoger
	   
	 public String recoger(Producto producto, Cliente cedulaRemitente, Guia cedulaDestinatario) {
	      // Verificar si el paquete se encuentra en la sucursal
	      if (inventario.contains(producto)) {
	          // Obtener la descripción del paquete
	    	  String descripcion = inventario.get(codigoPaquete);
	            return "El paquete con código " + descripcion + " se encuentra en la sucursal.";   
	            // Validar las cédulas del remitente y destinatario	
	            if esRemitente && esDestinatario {
		     	// se entrega y se actualiza el inventario
	            	return "Entregando el paquete con código " + descripcion + " a " + cedulaDestinario; //quiero colocar el nombre asociado a esa cedula
		        // Eliminar el paquete del inventario
		        inventario.remove(codigoPaquete);  
		        return "El paquete se ha entregado y el inventario ha sido actualizado.";
	                         
	      } else {
	            return "Las cédulas del remitente y/o destinatario no son válidas.";
	      }
	      } else {
	            return "El paquete con código " + codigoPaquete + " no está en la sucursal.";
	        }
	    }
	   

	    // Método para validar una cédula 
	 public boolean validarCedulaRemi(int ceduladadaRemi) {
		 boolean esRemitente = false;
	        if ceduladadaRemi != null && ceduladadaRemi.equals(cedulaRemitente){
	        	esRemitente = true;
	    }
	 public boolean validarCedulaDesti(int ceduladadaDesti) { //aquí se pide digitar la cc y con eso se verifica
	     boolean esDestinatario = false;
		 	if ceduladadaDesti != null && ceduladadaDesti.equals(cedulaDestinatario){
		 		esDestinatario = true;
	        }
	    }

}
