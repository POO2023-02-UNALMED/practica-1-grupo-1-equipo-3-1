package persona;

import tests.CuentaBancaria;

public class Destinatario extends Persona{
	public Destinatario() {}
	public Destinatario(String nombre, int cedula, CuentaBancaria cuentaBancaria, int telefono) {
		super(nombre,cedula,cuentaBancaria,telefono);
	}
}