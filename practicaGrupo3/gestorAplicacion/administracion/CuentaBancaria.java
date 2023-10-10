package administracion;
  /* KEVIN en esta clase deben de ir dos métodos que necesito para la parte de sucursal:
   * get.saldo() y descontarSaldo()
   * revise las lineas 304-313 para que pueda ver el uso que les voy a dar
   */

import java.util.Random; //Se importa esto ya que pues los datos seran random
import java.util.ArrayList;
import personas.*;

public class CuentaBancaria { //Los atributos de la clase (Viernes vemos si hay que agregar algun atributo)
	private Persona titular; // Importa Nombre y al final debe imprimirlo (AVANZAR).
    private long numero;
    private int cvv;
    private String fechaExpiracion;
    private double saldo;
    private static ArrayList<CuentaBancaria> todasLasCuentas = new ArrayList<>();
    
    // Un constructor que genera aleatoriamente los atributos
    public CuentaBancaria() {
        generarNumeroAleatorio();
        generarCVVAleatorio();
        generarFechaExpiracionAleatoria();
        generarSaldoAleatorio();

        CuentaBancaria.todasLasCuentas.add(this);
    }

    public CuentaBancaria(Persona titular, long numero, int cvv, String fechaExpiracion) {
        this.titular = titular;
        this.numero = numero;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;

        CuentaBancaria.todasLasCuentas.add(this);
    }
    
    // Generar un número aleatorio de 12 dígitos
    private void generarNumeroAleatorio() {
        Random random = new Random();
        numero = 100000000000L + random.nextInt(900000000); //Al superar el rango del "int" colocamos una "L" para especificar que es de tipo Long
    }
    // Generar un CVV aleatorio de 3 dígitos
    private void generarCVVAleatorio() {
        Random random = new Random();
        cvv = 100 + random.nextInt(999); //rango entre 0 y 999 
    }
    // Generar una fecha de expiración aleatoria (MM/YY)
    private void generarFechaExpiracionAleatoria() {
        Random random = new Random();
        int mes = 1 + random.nextInt(12);
        int año = 22 + random.nextInt(10);
        fechaExpiracion = String.format("%02d/%02d", mes, año);
    }
    // Generar saldo aleatorio de hasta 7 cifras
    private void generarSaldoAleatorio() {
        Random random = new Random();
        saldo = random.nextInt(10000000);
    }
    // Métodos públicos para obtener los atributos
    public Persona getTitular() {
        return titular;
    }
    
    public long getNumero() {
        return numero;
    }

    public int getCVV() {
        return cvv;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public double getSaldo() {
        return saldo;
    }

    public static ArrayList<CuentaBancaria> getTodasLasCuentas() {
        return CuentaBancaria.todasLasCuentas;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    // Método para imprimir la información de la cuenta
    public String imprimirInformacion() {
    	return "Los Datos De Tú Cuenta Bancaria Son Los Siguientes:\n"
         +"Número De Cuenta: " + numero +"\n"
         +"CVV: " + cvv +"\n"
        +"Fecha De Expiración: " + fechaExpiracion +"\n"
        +"Saldo: $" + saldo+"\n";
    }
    //Kevin - descontarSaldo
	public boolean descontarSaldo(double montoAPagar) {
		if (saldo < montoAPagar) {
			return false;
			
		}else {
			saldo = saldo-montoAPagar;
			return true;
		}
		
	}
	
}
