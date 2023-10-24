package personas;
import java.io.Serializable;

// HECHO POR: TOMAS MURILLO ARISTIZABAL
import administracion.CuentaBancaria;
// CLASE DESTINATARIO
// EN ESTA CLASE SE ALMACENA LOS DATOS DEL DESTINATARIO SE DIFERENCIA DE LA CLASE CLIENTE YA QUE ESTA NO TIENE MEMBRESIA
// ADEMAS ESTA CLASE HEREDA DE PERSONA Y TAMBIEN HEREDA SU METODO ABSTRACTO

public class Destinatario extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Destinatario() {
		this("pepito",105678907L,3225678976L);

	}
	public Destinatario(String nombre, long cedula, long telefono) {
		super(nombre, cedula, telefono);
		
	}
	
	
	public String toString() {
		String r = "El destinatario identificado como " + getNombre() + " y cedula " + getCedula();
		return r;
		
	}

}
