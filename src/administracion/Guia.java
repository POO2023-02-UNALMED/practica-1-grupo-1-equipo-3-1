package administracion;
//import sucursal.* No hace falta ya que estan en el mismo paquete

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.Math;

import productos.*;
import transportes.*;

public class Guia {
	// atributos de clase
	private Transporte vehiculo;
	private float tiempo;
	private Producto producto;
	private Sucursal sucursalOrigen; //la clase sucursal debe tener método destino
	private Sucursal sucursalLlegada; //la clase sucursal debe tener método destino
	private ArrayList<Sucursal> ruta = new ArrayList<>(); /*Lista de sucursales por las que va a pasar incluyendo
	la ciudad de salida y la de destino*/
	// pago está en sucursal, ¿qué hacemos acá?
	private String direccion;
	private boolean pagoContraentrega;
	private boolean entregaEnSucursal;
	private double precioTotal;
	private final LocalDateTime fechaDeEnvio;
	private estado estado;
	
	public enum estado{
		ENTRANSITO,
		ENESPERA,
		ENTREGADO
	} 

	//Constructor
	public Guia(Producto producto, LocalDateTime fechaDeEnvio, boolean pagoContraentrega, boolean entregaEnSucursal) {
		this.producto = producto;
		this.fechaDeEnvio = fechaDeEnvio;
		this.pagoContraentrega = pagoContraentrega;
		this.entregaEnSucursal = entregaEnSucursal;

		asignarRuta();
	}
	
	//métodos
	// Método para calcular la distancia entre dos puntos 
	public static double calcularDistancia(Sucursal origen, Sucursal destino) {
		double x = destino.getLongitud() - origen.getLongitud();
		double y = destino.getLatitud() - origen.getLatitud();

		double magnitud = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return magnitud;
	}

	// Método para calcular la cantidad de escalas según la membresía del cliente
	public static int calcularCantidadEscalas(Cliente remitente) {
		switch (remitente.getMembresia().getBeneficio()) {
			case SILVER:
				return 4; // Hace 4 escalas
			case GOLD:
				return 2; // Hace la mitad de las escalas de Silver 
			case PLATINUM:
				return 0; // No hace ninguna escala
			default:
				return 5; // Valor predeterminado para el cliente sin membresía 
		}
	}

	// Método para determinar la siguiente sucursal basada en la distancia geográfica
	public static Sucursal determinarSiguienteSucursal(Sucursal origen, ArrayList<Sucursal> sucursales) {
			Sucursal siguienteSucursal = null;
			double distanciaMinima = Double.MAX_VALUE;
			//recorreos cada sucursal
			for (Sucursal sucursal : sucursales) {
				if (!sucursal.equals(origen)) {
					double distancia = calcularDistancia(origen, sucursal);
					if (distancia < distanciaMinima) {
						distanciaMinima = distancia;
						siguienteSucursal = sucursal;
					}
				}
			}

			return siguienteSucursal;
		}


	public void asignarRuta() { //Falta terminar
		ruta.add(sucursalOrigen);
		ruta.add(determinarSiguienteSucursal(sucursalLlegada, Sucursal.getTodasLasSucursales()));
		ruta.add(sucursalLlegada);

	}


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

	public void setVehiculo(Transporte vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public void setSucursalLlegada(Sucursal sucursalLlegada) {
		this.sucursalLlegada = sucursalLlegada;
	}

}
