package personas;
// HECHO POR: TOMAS MURILLO ARISTIZABAL
import administracion.CuentaBancaria;
// CLASE DESTINATARIO
// EN ESTA CLASE SE ALMACENA LOS DATOS DEL DESTINATARIO SE DIFERENCIA DE LA CLASE CLIENTE YA QUE ESTA NO TIENE MEMBRESIA
// ADEMAS ESTA CLASE HEREDA DE PERSONA Y TAMBIEN HEREDA SU METODO ABSTRACTO

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
