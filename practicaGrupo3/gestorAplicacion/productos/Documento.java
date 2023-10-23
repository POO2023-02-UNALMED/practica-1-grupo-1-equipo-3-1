package productos;
//HECHO POR TOMAS GOMEZ
import java.io.Serializable;

public class Documento extends Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	
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
