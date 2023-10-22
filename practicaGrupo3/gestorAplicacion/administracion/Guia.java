package administracion;



import personas.*;

//import sucursal.* No hace falta ya que estan en el mismo paquete

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
import java.lang.Math;
import java.util.Random;
import java.time.format.DateTimeFormatter;


import productos.*;
import transportes.*;

public class Guia implements Serializable{
	// atributos de clase
	private Transporte vehiculo;
	private float tiempo;
	private Producto producto;
	private Sucursal sucursalOrigen; //la clase sucursal debe tener método destino
	private Sucursal sucursalLlegada; //la clase sucursal debe tener método destino
	private ArrayList<Sucursal> ruta = new ArrayList<>(); /*Lista de sucursales por las que va a pasar incluyendo
	la ciudad de salida y la de destino*/
	// pago está en sucursal, ¿qué hacemos acá?
	private Cliente remitente;
	private Destinatario destinatario; 
	private double precioTotal;
	private double pagoPendiente;
	private LocalDateTime fecha;
	private String fechaDeEnvio;
	private static ArrayList<Guia> todasLasGuias = new ArrayList<>();

	//private LocalDateTime fechaDeLlegada;
	private estado estado;
	private tipoDePago tipoDePago;
	private static final long serialVersionUID = 1L;
	
	public enum tipoDePago {
		REMITENTE,
		FRACCIONADO,
		DESTINATARIO
	}

	public enum estado{
		ENTRANSITO,
		ENESPERA,
		ENTREGADO
	} 

	//Constructor
	public Guia(Producto producto,  Cliente remitente, Destinatario destinatario,  Sucursal sucursalOrigen, Sucursal sucursalLlegada, tipoDePago tipoDePago, Transporte vehiculo) {
		this.producto = producto;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalLlegada = sucursalLlegada;
		this.tipoDePago = tipoDePago;
		this.vehiculo = vehiculo;
		this.producto.setGuia(this);

		fecha = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

		fechaDeEnvio = fecha.format(formatter);
		asignarRuta();
		asignarPrecio();
		aplicarDescuento();
		pagoPendiente = precioTotal;
	}
	
	//métodos
	// Método para calcular la distancia entre dos puntos
	//FUNCIONA
	public static double calcularDistancia(Sucursal origen, Sucursal destino) {
		double x = destino.getLongitud() - origen.getLongitud();
		double y = destino.getLatitud() - origen.getLatitud();
		double magnitud = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return magnitud;
	}

	public double avancePedido() {
		switch (estado) {
			case ENESPERA:
				return 100;
			case ENTREGADO:
				return 100;
            case ENTRANSITO:
				if (vehiculo instanceof Camion) {
					double escalas = 100.0 / (ruta.size() - 1);
					Camion camion = (Camion)vehiculo;
					if (camion.getUbicacionActual() != null) {
						return escalas * ruta.indexOf(camion.getUbicacionActual());
					} else {
						return (escalas * ruta.indexOf(camion.getUbicacionAnterior())) + (escalas / 2);
					}
				} else {
					return 50;
				}
			default:
				return 0;
		}
    }

	public void asignarPrecio() {
		int cantidadDeSucursales = ruta.size() - 1;
		int costoTransporte = 0;
		if (vehiculo instanceof Camion) {
			costoTransporte =  3000;
		} else if (vehiculo instanceof Avion){
			costoTransporte = 7000;
		}

		precioTotal = producto.costoDelPedido + cantidadDeSucursales * costoTransporte;
	}

	public void aplicarDescuento() {
		switch (getRemitente().getMembresia().getBeneficio()) {
			case PLATINUM:
				precioTotal = precioTotal * 0.5; //Descuento del 50%
				break;
			case GOLD:
				precioTotal = precioTotal * 0.75;; //Descuento del 25%
				break;
			case SILVER:
				precioTotal = precioTotal * 0.9;; //Descuento del 10%
				break;
			case DEFAULT:
				precioTotal = precioTotal * 1;
				break;
		}


	}
	
	//Antihoraria
	//FUNCIONA
	public void asignarRuta() {
		if (vehiculo instanceof Camion) {
			ArrayList<Sucursal> sucursales = Sucursal.getTodasLasSucursales(); //La lista sería [Medellin, Cali, Pasto, Florencia, Bogotá]
			//Si quiero ir de Pasto a Bogotá el atributo ruta sería = [Pasto, Florencia, Bogotá]
			//Si es de Cali a Medellin = [Cali, Pasto, Florencia, Bogota, Medellin]
			//¿Como hacemos este Metodo?
			for (int i = 0; i < sucursales.size(); i++) { //Recorre la lista de sucursales
				if (sucursales.get(i) == sucursalOrigen) { //Para en la sucursal que es igual a la de origen
					if (i < sucursales.indexOf(sucursalLlegada)) { //Si la sucursal de llegada está después de la de origen en la lista
						//Ejemplo Pasto a Bogotá [Medellin, Cali, (Pasto), Florencia, (Bogotá)]
						for (int j = i; j < sucursales.indexOf(sucursalLlegada) + 1; j++) {//Agrega a ruta desde la sucursal de origen hasta la de llegada
							ruta.add(sucursales.get(j));
						}
						//Resultado esperado [Pasto, Florencia, Bogotá]
					} else { //Si la sucursal de llegada está antes en la lista que la de origen (paila está más difícil)
						//Ejemplo Cali a medellin [(Medellin), (Cali), Pasto, Florencia, Bogotá]
						for (int j = i; j < sucursales.size(); j++) { //Se agregan la sucursal origen y las que le siguen en la lista ruta
							ruta.add(sucursales.get(j)); //[Cali, Pasto, Florencia, Bogotá]
						}

						for (int k = 0; k < sucursales.indexOf(sucursalLlegada) + 1; k++) { //Termina agregando desde el comienzo de la lista sucursales hasta la sucursal de llegada (incluyendola)
							ruta.add(sucursales.get(k)); //[Medellin]
						}
						//Resultado esperado [Cali, Pasto, Florencia, Bogota, Medellin]
					}
				}
			}
		} else if (vehiculo instanceof Avion) {
			ruta.add(sucursalOrigen);
			ruta.add(sucursalLlegada);
		}

	} 


	public String toString() {
		String format = "| %-18s | %-18s |\n";
		StringBuilder tabla = new StringBuilder();
		tabla.append("--------------------GUIA-------------------\n");
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Tipo de Producto", String.valueOf(producto.getClass().getSimpleName())));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Código Paquete",  String.valueOf(producto.getCodigo())));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Ciudad Origen", String.valueOf(sucursalOrigen.getNombre())));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Ciudad Destino", String.valueOf(sucursalLlegada.getNombre())));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Tipo de Pago", String.valueOf(tipoDePago).toLowerCase()));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Precio Total", String.valueOf(precioTotal) + "$"));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Vehículo", String.valueOf(vehiculo.getClass().getSimpleName())));
		tabla.append("+--------------------+--------------------+\n");
		tabla.append(String.format(format, "Fecha de envío", fechaDeEnvio));
		tabla.append("+--------------------+--------------------+\n");
		return  tabla.toString();
	}

	//Metodos get
	public Transporte getVehiculo() {
		return vehiculo;
	}

	public float getTiempo() {
		return tiempo;
	}

	public Producto getProducto() {
		return producto;
	}

	public Sucursal getSucursalOrigen() {
		return sucursalOrigen;
	}

	public Sucursal getSucursalLlegada() {
		return sucursalLlegada;
	}

    public ArrayList<Sucursal> getRuta() {
        return ruta;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

	public double getPagoPendiente() {
		return pagoPendiente;
	}

    public double getPrecioTotal() {
        return precioTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

	public String getFechaDeEnvio() {
		return fechaDeEnvio;
	}

	public estado getEstado() {
        return estado;
    }

	public Guia.tipoDePago getTipoDePago() {
		return tipoDePago;
	}

	//Metodos set
	public void setVehiculo(Transporte vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public void setSucursalLlegada(Sucursal sucursalLlegada) {
		this.sucursalLlegada = sucursalLlegada;
	}

    public void setRuta(ArrayList<Sucursal> ruta) {
        this.ruta = ruta;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

	public void setPagoPendiente(double pagoPendiente) {
		this.pagoPendiente = pagoPendiente;
	}

	public void setEstado(estado estado) {
        this.estado = estado;
    }

	public void setTipoDePago(tipoDePago tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public static ArrayList<Guia> getTodasLasGuias() {
		return todasLasGuias;
	}

	public static void setTodasLasGuias(ArrayList<Guia> todasLasGuias) {
		Guia.todasLasGuias = todasLasGuias;
	}
}