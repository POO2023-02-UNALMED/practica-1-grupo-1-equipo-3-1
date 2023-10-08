package persona;
import administracion.Membresia;
import administracion.cuentaBancaria;

//Cabecera clase:

public class Cliente extends Persona{
	private Membresia membresia;
	
	public Cliente() {}
	public Cliente(String nombre, int cedula, cuentaBancaria cuentaBancaria, int telefono, Membresia membresia){
		
		super(nombre, cedula, cuentaBancaria, telefono);
		this.membresia = membresia;
		
	}
	
	public Membresia getMembresia() {
		return membresia;
	}

}
