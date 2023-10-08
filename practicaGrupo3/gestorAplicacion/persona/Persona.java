package persona;
import administracion.cuentaBancaria;


public abstract class Persona {
	protected String nombre;
	protected int cedula;
	protected cuentaBancaria cuentaBancaria;
	protected int telefono;
	
	protected Persona() {}
	protected Persona(String nombre, int cedula, cuentaBancaria cuentaBancaria, int telefono){
		this.nombre = nombre;
		this.cedula = cedula;
		this.cuentaBancaria = cuentaBancaria;
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}	
	public cuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(cuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}	

	
}
