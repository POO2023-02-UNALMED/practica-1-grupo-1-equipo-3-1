package personas;
import administracion.Membresia;
import administracion.CuentaBancaria;

import administracion.Membresia;
import administracion.CuentaBancaria;
//Cabecera clase:

public class Cliente extends Persona{
	private Membresia membresia;
	private int reputacion;
	
	public Cliente() {
	}
	
	public Cliente(String nombre, long cedula, CuentaBancaria cuentaBancaria, long telefono){
		super(nombre, cedula, cuentaBancaria, telefono);
		membresia = new Membresia();
	}
	
	public Membresia getMembresia() {
		return membresia;
	}

	public int getReputacion() {
		return reputacion;
	}

	public void setReputacion(int reputacion) {
		this.reputacion = reputacion;
	}

	public String toString() {
		String r = "El cliente identificado como " +  getNombre() + " con cedula " + getCedula() + " y con membresia " + getMembresia().getBeneficio();
		return r;
	}
}
