package productos;
//Cabecera clase:
// La finalidad de esta clase es darle un nombre, y varios atributos necesarios
// para que las personas puedan acceder a las funcionalidades
// con esta clase Cliente puedes acceder a todas la informacion necesaria y guardada en esta.
public class Cliente{
	private String nombre;
	private int cedula;
	private CuentaBancaria cuentaBancaria;
	private int telefono;
	private Membresia premium; //esta atributo premium es de tipo de membresia, la cual es aleatoria para cada usuario
	
	public Cliente(String nombre, int cedula, CuentaBancaria cuentaBancaria
			int telefono, Membresia premium){
		
		this.nombre = nombre;
		this.cedula = cedula;
		this.cuentaBancaria = cuentaBancaria;
		this.telefono = telefono;
		this.premium = premium;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula
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
		this.telefono = telefono
	}	
	public Membresia getPremium() {
		return premium;
	}
	public void setPremium(Membresia premium) {
		this.premium = premium
	}	
}