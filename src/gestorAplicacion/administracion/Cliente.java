package administracion;
//Cabecera clase:
// La finalidad de esta clase es darle un nombre, y varios atributos necesarios
// para que las personas puedan acceder a las funcionalidades
// con esta clase Cliente puedes acceder a todas la informacion necesaria y guardada en esta.
public class Cliente{
	private String nombre;
	private int cedula;
	private CuentaBancaria cuentaBancaria;
	private int telefono;
	private Membresia membresia; //esta atributo premium es de tipo de membresia, la cual es aleatoria para cada usuario
	
	public Cliente(String nombre, int cedula, CuentaBancaria cuentaBancaria, int telefono, Membresia membresia){
		
		this.nombre = nombre;
		this.cedula = cedula;
		this.cuentaBancaria = cuentaBancaria;
		this.telefono = telefono;
		this.membresia = membresia;
		
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
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}
	
}