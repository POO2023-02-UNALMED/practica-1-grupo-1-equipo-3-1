package personas;
// HECHA POR: TOMAS MURILLO ARISTIZABAL
import administracion.Membresia;
import administracion.CuentaBancaria;

import administracion.Membresia;
import administracion.CuentaBancaria;
// CLASE CLIENTE
// EN ESTA CLASE SE ALMACENA TODA LA INFORMACION DEL CLIENTE O REMITENTE, QUE ES LA PERSONA QUE ENVIA EL PAQUETE
// EN ESTA CLASE ADEMAS DE HEREDAR DE PERSONA TIENE UN ATRIBUTO SUYO PROPIO QUE ES MEMBRESIA

public class Cliente extends Persona{
	private static Membresia membresia;

	public Cliente() {
	}
	
	public Cliente(String nombre, long cedula, CuentaBancaria cuentaBancaria, long telefono){
		super(nombre, cedula, cuentaBancaria, telefono);
		membresia = new Membresia();
	}
	
	public static Membresia getMembresia() {
		return membresia;
	}

	public String toString() {
		String r = "El cliente identificado como " +  getNombre() + "\ncon cedula " + getCedula() + " cuenta con membresia " + getMembresia().getBeneficio();
		return r;
	}
	public static String informacionMembresia() {
		return "El cliente identificado como " +  getNombre() + "\ncon cedula " + getCedula() + " cuenta con membresia " + getMembresia().getBeneficio();
	}
}
