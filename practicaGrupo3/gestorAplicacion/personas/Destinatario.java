package personas;

//import tests.CuentaBancaria;
import administracion.CuentaBancaria;

public class Destinatario extends Persona{
	public Destinatario() {}
	public Destinatario(String nombre, long cedula, CuentaBancaria cuentaBancaria, long telefono) {
		super(nombre,cedula,cuentaBancaria,telefono);
		
	}
	
	
	public String toString() {
		String r = "El destinatario identificado como " + getNombre() + " y cedula " + getCedula();
		return r;
		
	}

}
