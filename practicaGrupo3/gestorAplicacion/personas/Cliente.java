package personas;
import administracion.Membresia;
import administracion.CuentaBancaria;

import administracion.Membresia;
import administracion.CuentaBancaria;
//Cabecera clase:

public class Cliente extends Persona{
	private Membresia membresia;

	public Cliente() {
	}
	
	public Cliente(String nombre, long cedula, CuentaBancaria cuentaBancaria, long telefono){
		super(nombre, cedula, cuentaBancaria, telefono);
		membresia = new Membresia();
	}
	
	public Membresia getMembresia() {
		return membresia;
	}

	public String toString() {
		String r = "El cliente identificado como " +  getNombre() + " con cedula " + getCedula() + " y\ncon membresia " + getMembresia().getBeneficio();
		return r;
	}
}
