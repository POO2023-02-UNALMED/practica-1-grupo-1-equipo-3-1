package productos;

public abstract class Producto {
    protected double peso;
    protected int codigo;
    protected double volumen;
    protected double costoDelPedido; /*Este no es el costo definitvo del producto,
    se le suma despues el del transporte y los descuentos de membresias*/
    //El volumen y el peso es el espacio que ocupará el producto de cada transporte y sucursal
    
    public Producto() {
    }
    
    public abstract String toString();

    public abstract void asignarCostoDelPedido();
    
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

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public void setCostoPedido(double costoDelPedido) {
        this.costoDelPedido = costoDelPedido;
    }
}