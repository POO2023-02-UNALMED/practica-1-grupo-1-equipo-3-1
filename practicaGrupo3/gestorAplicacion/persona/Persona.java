package persona;

public abstract class Persona {
	private String nombre;
	private static int cedula;
	private CuentaBancaria cuentaBancaria;
	private int telefono;
	private Membresia membresia;
	
	protected Persona() {}
	protected Persona(String nombre, int cedula, CuentaBancaria cuentaBancaria, int telefono){
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
	public static int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}	
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}	

	public Membresia getMembresia() {
		return getMembresia();
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}
	protected abstract int getCedulaDestinatario();
	

}