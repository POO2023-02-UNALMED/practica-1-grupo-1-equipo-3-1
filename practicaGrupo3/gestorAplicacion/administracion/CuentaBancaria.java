package administracion;

import java.util.Random; 
import java.util.ArrayList;
import java.io.Serializable;

import personas.*;

public class CuentaBancaria implements Serializable {
	//Atributos de clase
	private Persona titular; 
    private long numero;
    private int cvv;
    private String fechaExpiracion;
    private double saldo;
    private static ArrayList<CuentaBancaria> todasLasCuentas = new ArrayList<>();
    private static final long serialVersionUID = 1L;
   

    //Constructor
    public CuentaBancaria(Persona titular, long numero, int cvv, String fechaExpiracion, double saldo) {
        this.titular = titular;
        this.numero = numero;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;
        this.saldo = saldo;

        this.titular.setCuentaBancaria(this); //Le asigna el atributo cuenta al titular

        CuentaBancaria.todasLasCuentas.add(this);
    }
    

    //Métodos de clase
    
    private void generarNumeroAleatorio() {
        Random random = new Random();
        numero = 100000000000L + random.nextInt(900000000); 
    }

    private void generarCVVAleatorio() {
        Random random = new Random();
        cvv = 100 + random.nextInt(999); 
    }

    private void generarFechaExpiracionAleatoria() {
        Random random = new Random();
        int mes = 1 + random.nextInt(12);
        int año = 22 + random.nextInt(10);
        fechaExpiracion = String.format("%02d/%02d", mes, año);
    }

    private void generarSaldoAleatorio() {
        Random random = new Random();
        saldo = random.nextInt(10000000);
    }

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
        return todasLasCuentas;
    }
    public static void setTodasLasCuentas(ArrayList<CuentaBancaria> list) {
    	todasLasCuentas = list;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
    	return "--------------------------------\n" +
                "Titular: " + this.getTitular().getNombre()+"\n" +
    			"Número De Cuenta: " + numero +"\n" +
    			"CVV: " + cvv +"\n"+
    			"Fecha De Expiración: " + fechaExpiracion +"\n"+
                "Saldo: $" + saldo+"\n"+
                "--------------------------------";
    }
    
	public boolean descontarSaldo(double montoAPagar) {
		if (saldo < montoAPagar) {
			return false;
			
		} else {
			saldo = saldo-montoAPagar;
			return true;
		}
	}

    public void aumentarSaldo(double montoAPagar) {
        saldo += montoAPagar;
    }
	
}
