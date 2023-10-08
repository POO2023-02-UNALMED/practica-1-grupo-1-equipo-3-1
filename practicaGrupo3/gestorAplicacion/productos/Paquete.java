package productos;

public class Paquete extends Producto {
    private boolean fragil; //Si este es fragil, el costo del envío es mayor y más probable es que sufra daños en el envío
    private double valorDeclarado; //Valor otorgado al producto, si se pierde el producto, le reembolsamos esa cantidad
    private double altura;
    private double ancho;
    private double largo;
    private String instruccionesEspeciales; //Cualquier instrucción especial para el manejo del paquete, como "manejar con cuidado" o "almacenar en lugar fresco" 
    private boolean roto;
    

    public Paquete(double peso, double altura, double ancho, double largo, boolean fragil,  double valor_declarado, String instruccionesEspeciales) {
        super(generarCodigo());

        this.valorDeclarado = valor_declarado;
        this.peso = peso;
        this.altura = altura;
        this.ancho = ancho;
        this.largo = largo;
        this.fragil = fragil;
        this.volumen = altura * largo * ancho;
        this.instruccionesEspeciales = instruccionesEspeciales;
        roto = false;

        asignarCostoDelPedido();
    }

    public String toString() {
        return "Tipo de producto: Paquete\n" +
        "Código de pedido: " + codigo + "\n" +
        "Peso: " + peso + "\n" +
        "Altura: " + altura + "\n" +
        "Ancho: " + ancho + "\n" +
        "Largo: " + largo + "\n" +
        "Fragil: " + (fragil ? "Sí" : "No") + "\n" +
        "Valor declarado: " + valorDeclarado + "\n" +
        "Instrucciones especiales: " + instruccionesEspeciales;
    }

    public double asignarCostoDelPedido() {
        double tarifaBasePorKg = 10000;
        double tarfifaBasePorMetroCubico = 5000;
        double tarifaAdicionalFragil = 1.25;
        double costoDelPedido = (tarfifaBasePorMetroCubico * volumen) + (tarifaBasePorKg * peso);
        if (!fragil) {
        	this.costoDelPedido = costoDelPedido;
        }else {
        	costoDelPedido *= tarifaAdicionalFragil;
        }
		return costoDelPedido;
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
