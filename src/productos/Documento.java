package productos;

public class Documento extends Producto {
    public Documento(int codigo) {
        peso = 0.1;
        volumen = 0.1;
        
        asignarCodigo();
        asignarCostoDelPedido();
    }

    public String toString() {
        return "Tipo de producto: Documento\n" +
        "Codigo de pedido: " + codigo + "\n";
    }

    public void asignarCostoDelPedido() {
        costoDelPedido = 10000;
    }
}

//No estoy seguro de si es necesario esta subclase, no sé que mas atributos pueda tener