package personas;

//Cabecera clase:
//La finalidad de esta clase es darle un nombre, y varios atributos necesarios
//para que las personas puedan acceder a las funcionalidades
//con esta clase Cliente puedes acceder a todas la informacion necesaria y guardada en esta.
public class Cliente extends Persona{
	private Membresia membresia;
	
	public Cliente() {}
	public Cliente(String nombre, int cedula, CuentaBancaria cuentaBancaria, int telefono, Membresia membresia){
		
		super(nombre, cedula, cuentaBancaria, telefono);
		this.membresia = membresia;
		
	}


}
