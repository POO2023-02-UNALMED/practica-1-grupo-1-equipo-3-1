package productos;

public class Documento extends Producto {
    public Documento() {
        super(generarCodigo(), 0.1, 0.1);
        asignarCostoDelPedido();
    }

    @Override
    public String toString() {
        return  "---------------------PRODUCTO--------------------\n" +
                "Tipo de producto: Documento\n" +
                "Codigo de pedido: " + codigo;
    }

    @Override
    public void asignarCostoDelPedido() {
        costoDelPedido = 10000;
    }
}

//No estoy seguro de si es necesario esta subclase, no sé que mas atributos pueda tener
