package personas;

//import tests.CuentaBancaria;
import administracion.CuentaBancaria;

public class Destinatario extends Persona{
	public Destinatario() {}
	public Destinatario(String nombre, int cedula, CuentaBancaria cuentaBancaria, long telefono) {
		super(nombre,cedula,cuentaBancaria,telefono);
		
	}
	// KEVIN - necesito esto xd
	public int getCedulaDestinatario() {
		// TODO Auto-generated method stub
		return this.cedula;
	}
	
	
	public String toString() {
		String r = "El destinatario identificado como " + getNombre() + " y cedula " + getCedula();
		return r;
		
	}

}
