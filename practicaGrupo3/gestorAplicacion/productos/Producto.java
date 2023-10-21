package productos;
import java.util.Random;
import java.util.ArrayList;
import administracion.*;

public abstract class Producto {
    protected double peso;
    protected final int codigo; //EL codigo de cada paquete es unico e inmutable
    protected double volumen;
    public double costoDelPedido; /*Este no es el costo definitvo del producto,
    se le suma despues el del transporte y los descuentos de membresias*/
    private static int contadorProductos;
    //El volumen y el peso es el espacio que ocupará el producto de cada transporte y sucursal
    private Guia guia;
    private static ArrayList<Producto> todosLosProductos = new ArrayList<>();

    public Producto(int codigo, double volumen, double peso) {
        this.codigo = codigo;
        this.volumen = volumen;
        this.peso = peso;

        Producto.todosLosProductos.add(this);
        Producto.contadorProductos ++;
    }

    public Producto(int codigo, double peso) {
        this.codigo = codigo;
        this.peso = peso;

        Producto.todosLosProductos.add(this);
        Producto.contadorProductos ++;
    }

    public abstract String toString();

    public abstract void asignarCostoDelPedido();

    public static int generarCodigo() { //Genera código de 5 digitos y lo asigna al atributo
        Random random = new Random();
        int codigoAleatorio = random.nextInt(90000) + 10000;
        return codigoAleatorio;
    }
    
    public double getPeso() {
        return peso;
    }

    public int getCodigo() {
        return codigo;
    }

    // Métodos get
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

    public static ArrayList<Producto> getTodosLosProductos() {
        return Producto.todosLosProductos;
    }

    // Métodos set
    public void setPeso(double peso) {
        this.peso = peso;
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