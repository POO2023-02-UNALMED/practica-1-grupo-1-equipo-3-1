package persona;

//import tests.CuentaBancaria;
import administracion.cuentaBancaria;

public class Destinatario extends Persona{
	public Destinatario() {}
	public Destinatario(String nombre, int cedula, cuentaBancaria cuentaBancaria, int telefono) {
		super(nombre,cedula,cuentaBancaria,telefono);
		
	}
	// KEVIN - necesito esto xd
	public int getCedulaDestinatario() {
		// TODO Auto-generated method stub
		return this.cedula;
	}

}
