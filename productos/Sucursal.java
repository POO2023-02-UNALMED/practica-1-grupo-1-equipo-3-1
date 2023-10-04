package productos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import (paquete).Producto
//import (paquete).Guia

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
	private int latitud;
	private int longitud;
    private Map<String, String> inventario;
    private Map<Horario, String> horario;
	private int cantidadMotosDisponibles;
    private int cantidadCamionesDisponibles;
    private int cantidadAvionesDisponibles;
	private int cantidadJaulasPequeñas;
	private int cantidadJaulasMedianas;
	private int cantidadJaulasGrandes;

	
	//constructor
	public Sucursal(String ciudad, Map<Horario, String> horario, int capacidad, Map<String, String> inventario, int latitud, int longitud) {
		//TOMASCorregí el tipo de horario e inventario
		//TOMASAgregue los atributos latitud y longitud y los combié a enteros (plano cartesiano)
		this.ciudad = ciudad;
		this.horario = horario;
		this.capacidad = capacidad;
		this.inventario = new HashMap<>();
		this.horario = new HashMap<>();
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
    public Sucursal(String ciudad) { //TOMASPorqué dos constructores?
    	this.ciudad = ciudad;
        this.inventario = new HashMap<>();
        this.horario = new HashMap<>();
    
		asignarhorario(); //TOMASLo volví un método, como va a meter todo eso en un constructor ome cochino
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
        } else if ("Cali".equalsI(ciudad)) {
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
	
	//public String agregarPaquete(String codigoPaquete, String descripcion, String tipoTransporte) {
    //    if (tipoTransporte.equalsI("Moto") && cantidadMotosDisponibles > 0) {
    //       inventario.put(codigoPaquete, descripcion);
	//        cantidadMotosDisponibles =- 1;
	//        return "El paquete con código " + codigoPaquete + " ha llegado a la sucursal " + nombre + " y se ha agregado al inventario.";
	//    } else if (tipoTransporte.equals("Camion") && cantidadCamionesDisponibles > 0) {
	//        inventario.put(codigoPaquete, descripcion);
	//        cantidadCamionesDisponibles =- 1;
	//        return "El paquete con código " + codigoPaquete + " ha llegado a la sucursal " + nombre + " y se ha agregado al inventario.";
	//    } else if (tipoTransporte.equalsIgnoreCase("Avion") && cantidadAvionesDisponibles > 0) {
	//        inventario.put(codigoPaquete, descripcion);
	//        cantidadAvionesDisponibles =- 1;
	//        return "El paquete con código " + codigoPaquete + " ha llegado a la sucursal " + nombre + " y se ha agregado al inventario .";
	//    } else {
	//        return "No hay disponibilidad de transporte para el paquete con código " + codigoPaquete + " en la sucursal " + nombre + ".";
	//    }
	// }
	
	// Método para obtener la disponibilidad de cada tipo de transporte en la sucursal
	
	// public String obtenerDisponibilidadTransporte() {
	//    return "Disponibilidad de transporte en la Sucursal " + nombre + ":";
	//    return "Motos disponibles: " + cantidadMotosDisponibles;
	//    return "Camiones disponibles: " + cantidadCamionesDisponibles;
	//    return "Aviones disponibles: " + cantidadAvionesDisponibles;
	// }



	    // Método para agregar un paquete al inventario de la sucursal
	 public void agregarPaquete(String codigoPaquete, String descripcion) {
	      inventario.put(codigoPaquete, descripcion);
	 }

	    // Método para verificar si un paquete está en la sucursal
	 public String verificarPaquete(String codigoPaquete) {
	      if (inventario.containsKey(codigoPaquete)) {
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
	    
	 public String recoger(String codigoPaquete, String cedulaRemitente, String cedulaDestinatario) {
	      // Verificar si el paquete se encuentra en la sucursal
	      if (inventario.containsKey(codigoPaquete)) {
	          // Obtener la descripción del paquete
	    	  String descripcion = inventario.get(codigoPaquete);
	            return "El paquete con código " + codigoPaquete + " se encuentra en la sucursal.";
	            
	      // Validar las cédulas del remitente y destinatario	
	      if (validarCedula(cedulaRemitente) && validarCedula(cedulaDestinatario)) {
		      // se entrega y se actualiza el inventario
		      String descripcionPaquete = inventario.get(codigoPaquete);
		        return "Entregando el paquete con código " + codigoPaquete + " a " + cedulaDestinario; //quiero colocar el nombre asociado a esa cedula
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
	 public boolean compararCedula(String ceduladadaDest) {
	        return ceduladadaDest != null && ceduladadaDest.equals(cedula); // las cedulas la obtengo de la clase cliente y guia
	    }

}
