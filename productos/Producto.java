package productos;

public class Producto {
    protected double peso;
    protected int codigo;
    protected double volumen;

    //El volumen y el peso es el espacio que ocupará el producto de cada transporte y sucursal
    
    public Producto() {
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
}