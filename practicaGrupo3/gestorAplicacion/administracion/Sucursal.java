package administracion;

import java.util.*;

import administracion.Guia.estado;
import personas.*;
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
    private int capacidadVolumen; //TOMAS alternativa a capacidad
    private int capacidadPeso; //TOMAS Las sucursales van a estar limitadas por el peso?
    private int latitud; //TOMAS
    private int longitud; //TOMAS
    private ArrayList<Producto> inventario = new ArrayList<>(); //TOMAS
    private static ArrayList<Sucursal> todasLasSucursales = new ArrayList<>(); //TOMAS
    private Map<Horario, String> horario;
    private ArrayList<Moto> motosEnSucursal = new ArrayList<>();
    private ArrayList<Camion> camionesEnSucursal = new ArrayList<>();
    private ArrayList<Avion> avionesEnSucursal = new ArrayList<>();
    private static CuentaBancaria correminas = new CuentaBancaria();

    private int cantidadMotosDisponibles;
    private int cantidadCamionesDisponibles;
    private int cantidadAvionesDisponibles;
    private int cantidadJaulasPequenas;
    private int cantidadJaulasMedianas;
    private int cantidadJaulasGrandes;

    private Opinion opinionSucursal;

    //constructor
    public Sucursal(String ciudad, int capacidadVolumen, int capacidadPeso, int longitud, int latitud) {
        //TOMASCorregí el tipo de horario e inventario
        //TOMASAgregue los atributos latitud y longitud y los combié a enteros (plano cartesiano)
        this.ciudad = ciudad;
        this.capacidadVolumen = capacidadVolumen;
        this.capacidadPeso = capacidadPeso;
        //this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
        Sucursal.todasLasSucursales.add(this); //TOMAS
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
                if (cantidadJaulasPequenas > 0) {
                    cantidadJaulasPequenas--;
                    disponibilidad = true;
                    break;
                }
            case MEDIANO:
                if (cantidadJaulasMedianas > 0) {
                    cantidadJaulasMedianas--;
                    disponibilidad = true;
                    break;
                }
            case GRANDE:
                if (cantidadJaulasGrandes > 0) {
                    cantidadJaulasGrandes--;
                    disponibilidad = true;
                    break;
                }
        }

        return disponibilidad;
    }

    public void agregarCamion(Camion camion) {
        camionesEnSucursal.add(camion);
    }

    public void removerCamion(Camion camion) {
        camionesEnSucursal.remove(camion);
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
                cantidadMotosDisponibles--;
                producto.getGuia().setVehiculo(tipoTransporte);
                disponibilidad = true;
            }
        } else if (tipoTransporte instanceof Camion) {
            if (cantidadCamionesDisponibles > 0) {
                producto.getGuia().setVehiculo(tipoTransporte);
                cantidadCamionesDisponibles--;
                disponibilidad = true;
            }
        } else if (tipoTransporte instanceof Avion) {
            if (cantidadAvionesDisponibles > 0) {
                producto.getGuia().setVehiculo(tipoTransporte);
                cantidadAvionesDisponibles--;
                disponibilidad = true;
            }
        }
        if (disponibilidad) {
            return "Tenemos disponibilidad.";
        } else {
            return "Lo sentimos, paquete con código " + producto.getCodigo() + " no está en la sucursal.";
        }
    }


    // Método para agregar un paquete al inventario de la sucursal
    public void agregarPaquete(Producto producto) {
        inventario.add(producto);
    }

    // Método para verificar si un paquete está en la sucursal
    public String verificarPaquete(Producto producto) {
        Producto codigoPaquete = inventario.get(inventario.indexOf(producto));
        if (inventario.contains(producto)) {
            return "El paquete con código " + codigoPaquete + " se encuentra en la sucursal.";
        } else {
            return "El paquete con código " + codigoPaquete + " no está en la sucursal.";
        }
    }





    public boolean verificarDisponibilidad(Producto producto) {
        if (capacidadVolumen > producto.getVolumen()) {
            if (capacidadPeso > producto.getPeso()) {
                return true;
            }
        }
        return false;
    }



    //Revisar
    //Rastrear
    public static void rastrear(int codigo) {
        Producto producto = null;
        for (Producto producto1 : Producto.getTodosLosProductos()) {
            if (producto1.getCodigo() == codigo) {
                producto = producto1;
                break;
            }
        }

        if (producto != null) {
            switch (producto.getGuia().getEstado()) {
                case ENTRANSITO:
                    String lugarActual;
                    boolean estaEnSucursal;
                    for (Sucursal sucursal : producto.getGuia().getRuta()) {
                        if (sucursal.verificarProducto(producto)) {
                            estaEnSucursal = true;
                            lugarActual = sucursal.getCiudad();
                            break;
                        }
                    }

                    //if (estaEnSucursal) {}

            }

        } else {

        }
    }


    //Revisar
    //Ubicar paquete
    public String ubicar(Producto producto) {
        Sucursal sucursal = null;
        boolean estaEnSucursal = false;
        for (Sucursal sucursal1 : producto.getGuia().getRuta()) {
            if (sucursal1.verificarProducto(producto)) {
                estaEnSucursal = true;
                sucursal = sucursal1;
                break;
            }
        }

        if (estaEnSucursal) {
            return sucursal.getCiudad();
        } else {
            return "El producto está en reparto, se encuentra entre " + producto.getGuia().getVehiculo();
        }

    }

    //recoger
    public String recoger(int codigoPaquete, String nombreRemitente, int cedulaRemitente) {
        Producto paquete = null;
        for (Producto producto : Producto.getTodosLosProductos()) { //Revisa en todos los productos creados
            if (producto.getCodigo() == codigoPaquete) { //Encuentra el producto que coincida con el codigo
                paquete = producto;
                break;
            }
        }

        if (paquete != null) {
            if (paquete.getGuia().getSucursalLlegada() == this) { //Verifica si esa si es la sucursal de destino final
                if (paquete.getGuia().getDestinatario().getNombre().equals(nombreRemitente)) { //Verifica el nombre del destinatario
                    if (paquete.getGuia().getDestinatario().getCedula() == cedulaRemitente) { //Verifica la cedula del destinatario
                        if (paquete.getGuia().isEntregaEnSucursal()) { //Creo que esto es redundante
                            if (paquete.getGuia().getEstado() == estado.ENESPERA && inventario.contains(paquete)) { //Tambien redundante
                                //if (producto.getGuia().)
                                if (paquete.getGuia().isPagoContraentrega()) { //Verifica el pago contraentrega
                                    return "Para retirar el producto tiene que cancelar el servicio por valor de $" +
                                            paquete.getGuia().getPrecioTotal();
                                    //Pagar
                                } else {
                                    inventario.remove(paquete);
                                    Random random = new Random();
                                    return "Operación realizada con éxito, favor acercarce a la caja " +
                                            random.nextInt(5) + " para retirar su paquete, muchas gracias por usar nuestro servicio";
                                }
                            } else if (paquete.getGuia().getEstado() == estado.ENTREGADO) {
                                return "El paquete fue entregado el dia# del mes #";
                            } else if (paquete.getGuia().getEstado() == estado.ENTRANSITO) {
                                return "El paquete todavía no ha llegado";
                                //rastrear
                            }
                        } else {
                            return "Lo sentimos, el paquete fue programado para tener como destino la siguiente dirección" +
                                    paquete.getGuia().getDireccion();
                        }
                    } else {
                        return "Datos incorrectos, intente nuevamente";
                    }
                } else {
                    return "Datos incorrectos, intente nuevamente";
                }
            } else {
                return "El paquete tiene como destino la ciudad de " + paquete.getGuia().getSucursalLlegada().getCiudad();
            }
        } else {
            return "Datos incorrectos, intente nuevamente";
        }
        return "";
    }
/*
		public String recoger(Producto producto, int remitente, int destinatario) {
			// Verificar si el paquete se encuentra en la sucursal
			 if (inventario.contains(producto)) {
				// Validar las cédulas del remitente y destinatario	
				boolean esRemitente = validarCedulaRemi1(remitente);
				boolean esDestinatario = validarCedulaDest(destinatario);
				 
				 /*entregar si se cumplen las condiciones anteriores
				if(esRemitente) {
					 if(esDestinatario) {
						 inventario.remove(producto);
						 return "Se ha entregado el paquete con código" + producto.getCodigo()+" a "+destinatario;
					}
				} else {
					 return "Las cédulas del remitente y/o destinatio no son validas.\n";
				}
				} else {
					return "El paquete no se encuentra en la Sucursal.";
				}
			}
				// Método para validar una cédula 
		 
	 public boolean validarCedulaRemitente(int remitente) {//TOMAS
		 int cedulaRemi = Cliente.getCedula();
		 if (remitente == cedulaRemi) {
			 return true;
		 }else {
			 return false;
		 }
	 }
		 
	 public boolean validarCedulaDest(int destinatario) { 
	     int cedulaDesti = .getCedula();
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
	*/
    // Método para calcular la cantidad de escalas según la membresía del cliente

    //necesito que Tomás M. haga el switch con los tipos de membresia
    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getLatitud() {
        return this.latitud;
    }

    public int getLongitud() {
        return this.longitud;
    }

    public static ArrayList<Sucursal> getTodasLasSucursales() {
        return Sucursal.todasLasSucursales;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public static CuentaBancaria getCorreminas() {
        return correminas;
    }

    public ArrayList<Camion> getCamionesEnSucursal() {
        return camionesEnSucursal;
    }

    public ArrayList<Moto> getMotosEnSucursal() {
        return motosEnSucursal;
    }

    public ArrayList<Avion> getAvionesEnSucursal() {
        return avionesEnSucursal;
    }

    public int getCantidadCamionesDisponibles() {
        return cantidadCamionesDisponibles;
    }

    public int getCantidadMotosDisponibles() {
        return cantidadMotosDisponibles;
    }

    public int getCantidadAvionesDisponibles() {
        return cantidadAvionesDisponibles;
    }

}
