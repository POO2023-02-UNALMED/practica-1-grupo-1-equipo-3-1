package administracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import persona.*;
import productos.*;
import transportes.*;


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
	private String ciudadDestino;
	private int capacidad;
	private int capacidadVolumen; //TOMAS alternativa a capacidad
	private int capacidadPeso; //TOMAS Las sucursales van a estar limitadas por el peso?
	private int latitud; //TOMAS
	private int longitud; //TOMAS
	private ArrayList<Producto> inventario = new ArrayList<>(); //TOMAS
	private static ArrayList<Sucursal> sucursales = new ArrayList<>(); //TOMAS
    private Map<Horario, String> horario;
	private int cantidadMotosDisponibles;
    private int cantidadCamionesDisponibles;
    private int cantidadAvionesDisponibles;
	private int cantidadJaulasPequeñas;
	private int cantidadJaulasMedianas;
	private int cantidadJaulasGrandes;

	
	//constructor
	public Sucursal(String ciudad, String ciudadDestino, int capacidadVolumen,int capacidadPeso, int latitud, int longitud) {
		//TOMASCorregí el tipo de horario e inventario
		//TOMASAgregue los atributos latitud y longitud y los combié a enteros (plano cartesiano)
		this.ciudad = ciudad;
		this.horario = horario;
		this.capacidad = capacidad;
		this.latitud = latitud;
		this.longitud = longitud;
		Sucursal.sucursales.add(this); //TOMAS
		//consultarHorario();
		
	}
	   
	//metodos
	public void asignarHorario() {
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
        } else if ("Pasto".equals(ciudad)) {
            // Horario para Pasto
            horario.put(Horario.LUNES, "7:30 AM - 4:00 PM");
            horario.put(Horario.MARTES, "7:30 AM - 4:00 PM");
            horario.put(Horario.MIERCOLES, "7:30 AM - 4:00 PM");
            horario.put(Horario.JUEVES, "7:30 AM - 4:00 PM");
            horario.put(Horario.VIERNES, "7:30 AM - 4:00 PM");
        } else if ("Florencia".equals(ciudad)) {
            // Horario para Florencia
            horario.put(Horario.LUNES, "11:00 AM - 8:00 PM");
            horario.put(Horario.MARTES, "11:00 AM - 8:00 PM");
            horario.put(Horario.MIERCOLES, "11:00 AM - 8:00 PM");
            horario.put(Horario.JUEVES, "11:00 AM - 8:00 PM");
            horario.put(Horario.VIERNES, "11:00 AM - 8:00 PM");
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
	/* public String consultarHorario() {
		 String nombre = ciudad;
	        return "Horario de la Sucursal " + nombre + ":";
	        for (Horario dia : Horario.values()) {
	            return horario.get(dia);
	        }
	    }*/
	 
	

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
						inventario.add(nuevoAnimal); //Si se cumple todo lo agrega y resta las distintas capacidades

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

	//TOMAS Cambie casi todo
	public String asignarTransporte(Producto producto, Transporte tipoTransporte) {
		boolean disponibilidad = false;
	
        if (tipoTransporte instanceof Moto) {
			if (cantidadMotosDisponibles > 0) {
	        	cantidadMotosDisponibles --;
	            producto.getGuia().setVehiculo(tipoTransporte);
				disponibilidad = true;
			}
        }else if (tipoTransporte instanceof Camion) {
			if (cantidadCamionesDisponibles > 0) {
	        	producto.getGuia().setVehiculo(tipoTransporte);
	        	cantidadCamionesDisponibles --;
				disponibilidad = true;
			}
	    }else if (tipoTransporte instanceof Avion){ 
			if (cantidadAvionesDisponibles > 0) {
	        	producto.getGuia().setVehiculo(tipoTransporte);
	        	cantidadAvionesDisponibles --;
				disponibilidad = true;
			}
	    }if (disponibilidad){
	    	return "Tenemos disponibilidad.";
	    }else {
	    	return "Lo sentimos, paquete con código " + producto.getCodigo() + " no está en la sucursal.";
	    }
	    }
	
	


	    // Método para agregar un paquete al inventario de la sucursal
	 public void agregarPaquete(Producto producto) {
	      inventario.add(producto); 
	 }

	    // Método para verificar si un paquete está en la sucursal
	 public String verificarPaquete(int producto) {
	     Producto codigoPaquete = inventario.get(producto);
		 if (inventario.contains(producto)) {
	            return "El paquete con código " + codigoPaquete + " se encuentra en la sucursal.";
	      } else {
	            return "El paquete con código " + codigoPaquete + " no está en la sucursal.";
	        }
	 }

	 
	

		//recoger
	 public String recoger(Producto producto, int remitente, int destinatario) {
		// Verificar si el paquete se encuentra en la sucursal
		 if (inventario.contains(producto)) {
			// Validar las cédulas del remitente y destinatario	
			 boolean esRemitente = validarCedulaRemi1(remitente);
			 boolean esDestinatario = validarCedulaDest(destinatario);
			 
			 /*entregar si se cumplen las condiciones anteriores*/
			 if(esRemitente) {
				 if(esDestinatario) {
					 inventario.remove(producto);
					 return "Se ha entregado el paquete con código" + producto.getCodigo()+" a "+destinatario;
				 }
			 }else {
				 return "Las cédulas del remitente y/o destinatio no son validas.\n";
			 }
		 }else {
			 return "El paquete no se encuentra en la Sucursal.";
		 }
	 }

	    // Método para validar una cédula 
		 
	 public boolean validarCedulaRemi1(int remitente) {
		 int cedulaRemi = Cliente.getCedula();
		 if (remitente == cedulaRemi) {
			 return true;
		 }else {
			 return false;
		 }
	 }
		 
	 public boolean validarCedulaDest(int destinatario) { 
	     int cedulaDesti = Destinatario.getCedula();
	     if (destinatario == cedulaDesti) {
	    	 return true;
	     }else {
	    	 return false;
	     }  
	 }
	 

	// Método para realizar el pago del envío
	 public String realizarPagoEnvio(double montoAPagar, CuentaBancaria cuentaCliente) {
		 	montoAPagar = Producto.costoDelPedido;
	 
	        if (cuentaCliente.getSaldo() >= montoAPagar) {
	            // Realizar el pago descontando el monto de la cuenta del cliente
	            cuentaCliente.descontarSaldo(montoAPagar);
	            return "Pago del envío realizado con éxito.";
	        } else {
	            // En caso de que no hayan los fondos suficientes en la cuenta
	        	return "La cuenta bancaria del cliente no tiene fondos suficientes para el pago del envío.";
	        }
	    }
	 // Método para obtener la ciudad destino del paquete
	 public String getCiudadDestino() {
		 return this.ciudadDestino;
	 }
	 public void setCiudadDestino(String ciudadDestino) {
		 this.ciudadDestino = ciudadDestino;
	 }
	 
	// Método para calcular la cantidad de escalas según la membresía del cliente
	 
	 //necesito que Tomás M. haga el switch con los tipos de membresia
	    private int calcularCantidadEscalas(Membresia membresia) {
	        switch (Membresia.crearMembresia()) {
	            case "silver":
	                return 4; // Hace 5 escalas
	            case "gold":
	                return 2; // Hace la mitad de las escalas de Silver 
	            case "platinum":
	                return 0; // No hace ninguna escala
	            default:
	                return 5; // Valor predeterminado para el cliente sin membresía 
	        }
	    }
}



	

