package productos;
import java.util.Random;
import administracion.*;

public abstract class Producto {
    protected double peso;
    protected int codigo;
    protected double volumen;
    protected double costoDelPedido; /*Este no es el costo definitvo del producto,
    se le suma despues el del transporte y los descuentos de membresias*/
    private static int contadorProductos;
    //El volumen y el peso es el espacio que ocupará el producto de cada transporte y sucursal
    private Guia guia;
    
    public Producto() {
        Producto.contadorProductos ++;
    }
    
    public abstract String toString();

    public abstract void asignarCostoDelPedido();

    public void asignarCodigo() { //Genera código de 5 digitos y lo asigna al atributo
        Random random = new Random();
        int codigoAleatorio = random.nextInt(90000) + 10000;
        codigo = codigoAleatorio;
    }
    
    public double getPeso() {
        return peso;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getVolumen() {
        return volumen;
    }

    public double getCostoDelPedido() {
        return costoDelPedido;
    }

    public static int getContadorProductos() {
        return contadorProductos;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public void setCostoDelPedido(double costoDelPedido) {
        this.costoDelPedido = costoDelPedido;
    }

    public void setCostoPedido(double costoDelPedido) {
        this.costoDelPedido = costoDelPedido;
    }

    public static void setContadorProductos(int contadorProductos) {
        Producto.contadorProductos = contadorProductos;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

}