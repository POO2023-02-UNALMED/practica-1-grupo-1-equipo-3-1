package personas;
// HECHA POR: TOMAS MURILLO ARISTIZABAL
import administracion.Membresia;

import java.io.Serializable;

import administracion.CuentaBancaria;

import administracion.Membresia;
import administracion.CuentaBancaria;
// CLASE CLIENTE
// EN ESTA CLASE SE ALMACENA TODA LA INFORMACION DEL CLIENTE O REMITENTE, QUE ES LA PERSONA QUE ENVIA EL PAQUETE
// EN ESTA CLASE ADEMAS DE HEREDAR DE PERSONA TIENE UN ATRIBUTO SUYO PROPIO QUE ES MEMBRESIA

public class Cliente extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Membresia membresia;

	public Cliente() {
	}
	
	public Cliente(String nombre, long cedula, long telefono){
		super(nombre, cedula, telefono);
		membresia = new Membresia();
	}
	
	public Membresia getMembresia() {
		return membresia;
	}

	@Override
	public String toString() {
		String r = "El cliente identificado como " +  getNombre() + "\ncon cedula " + getCedula() + " cuenta con membresia " + getMembresia().getBeneficio();
		return r;
	}

	public String informacionMembresia() {
		return "El cliente identificado como " +  getNombre() + "\ncon cedula " + getCedula() + " cuenta con membresia " + getMembresia().getBeneficio();
	}
}
