package administracion;
//import sucursal.* No hace falta ya que estan en el mismo paquete
import productos.*;
import transportes.*;

public class Guia {
	// atributos de clase
	private Transporte vehiculo;
	private float tiempo;
	private Producto producto;
	private Sucursal ciudadDestino; //la clase sucursal debe tener método destino
	private Sucursal ciudadEscala; //la clase sucursal debe tener método destino
	// pago está en sucursal, ¿qué hacemos acá?
	private String direccion;
	private boolean pagoContraentrega;
	private boolean entregaEnSucursal;
	private double precioTotal;
	
	
	//métodos
	public Transporte getVehiculo() {
		return vehiculo;
	}

	public float getTiempo() {
		return tiempo;
	}

	public Producto getProducto() {
		return producto;
	}

	public Sucursal getCiudadDestino() {
		return this.ciudadDestino;
	}

	public Sucursal getCiudadEscala() {
		return ciudadEscala;
	}

	public void setVehiculo(Transporte vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setCiudadDestino(Sucursal ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}


}
