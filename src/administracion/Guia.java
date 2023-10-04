package administracion;
import sucursal.*
public class Guia {
	// atributos de clase
	private String vehiculo;
	private float tiempo;
	private float velocidad;
	private int codigo;
	private Sucursal destino; //la clase sucursal debe tener método destino
	private Sucursal escala; //la clase sucursal debe tener método destino
	// pago está en sucursal, ¿qué hacemos acá?
	private String direccion;
	
	
	//métodos
	public Sucursal getCiudadDestino() {
		return this.destino;
	}
	public void setCiudadDestino(Sucursal destino) {
		this.destino = destino;
	}
}
