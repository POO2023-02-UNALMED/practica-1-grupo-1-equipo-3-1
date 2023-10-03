//Cabecera Archivo:
// 
package productos;
//Cabecera Clase:
// La finalidad de esta clase es saber el beneficio de entrega
// y demas beneficios que incluye la membresia que cada cliente tiene.
public class Membresia{
	private Tipo beneficio;
	private int precio;
	private Cliente cliente;
	
	private enum Tipo{
		SILVER,
		GOLD,
		PLATINUM
	};// Estos seran los tipos de membresia disponibles
	public Membresia() {}
	public Membresia(Cliente cliente) {
		this.cliente = cliente;
		//Aqui en cada uno de los constructores estaria  
		// el codigo que haria que la membresia fuera aleatoria
	}
	
	public Tipo getBenficio() {
		return beneficio;
	}
	public void setBeneficio(Tipo beneficio) {
		this.beneficio = beneficio;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
