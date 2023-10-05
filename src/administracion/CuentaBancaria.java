package administracion;
  /* KEVIN en esta clase deben de ir dos métodos que necesito para la parte de sucursal:
   * get.saldo() y descontarSaldo()
   * revise las lineas 304-313 para que pueda ver el uso que les voy a dar
   */
}
import java.util.Random; //Se importa esto ya que pues los datos seran random

public class CuentaBancaria { //Los atributos de la clase (Viernes vemos si hay que agregar algun atributo)
    private long numero;
    private int cvv;
    private String fechaExpiracion;
    private int saldo;
    
    // Un constructor que genera aleatoriamente los atributos
    public CuentaBancaria() {
        generarNumeroAleatorio();
        generarCVVAleatorio();
        generarFechaExpiracionAleatoria();
        generarSaldoAleatorio();
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
    // Continuacion más tarde