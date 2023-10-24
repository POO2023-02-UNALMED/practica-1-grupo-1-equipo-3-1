package productos;
//HECHO POR TOMAS GOMEZ
import java.io.Serializable;

public class Paquete extends Producto implements Serializable{
    private boolean fragil; //Si este es fragil, el costo del envío es mayor y más probable es que sufra daños en el envío
    private double valorDeclarado; //Valor otorgado al producto, si se pierde el producto, le reembolsamos esa cantidad
    private double alto;
    private double ancho;
    private double largo;
    private boolean roto;
    private static final long serialVersionUID = 1L;

    public Paquete(double peso, double alto, double ancho, double largo, boolean fragil,  double valorDeclarado) {
        super(generarCodigo(), alto * largo * ancho, peso);

        this.valorDeclarado = valorDeclarado;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.fragil = fragil;
        this.volumen = alto * largo * ancho;
        roto = false;

    }

    @Override
    public String toString() {
        return  "---------------------PRODUCTO--------------------\n" +
                "Tipo de producto: Paquete\n" +
                "Código de pedido: " + codigo + "\n" +
                "Peso: " + peso + "kg\n" +
                "Altura: " + alto + "m\n" +
                "Ancho: " + ancho + "m\n" +
                "Largo: " + largo + "m\n" +
                "Volumen: " + alto * ancho * largo + "m3\n" +
                "Fragil: " + (fragil ? "Sí" : "No") + "\n" +
                "Valor declarado: " + valorDeclarado;
    }

    @Override
    public void asignarCostoDelPedido() {
        double tarifaBasePorKg = 1000;
        double tarfifaBasePorMetroCubico = 4000;
        double tarifaAdicionalFragil = 1.25;
        double costoPedido = (tarfifaBasePorMetroCubico * volumen) + (tarifaBasePorKg * peso) + valorDeclarado * 0.3;
        if (!fragil) {
        	this.costoDelPedido = costoPedido;
        } else {
        	this.costoDelPedido = costoPedido * tarifaAdicionalFragil;
        }
    }

    public boolean isFragil() {
        return fragil;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }    

    public boolean isRoto() {
        return roto;
    }

    public void setRoto(boolean roto) {
        this.roto = roto;
    }
}
