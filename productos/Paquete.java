package productos;

public class Paquete extends Producto {
    private boolean fragil; //Si este es fragil, el costo del envío es mayor y más probable es que sufra daños en el envío
    private double valorDeclarado; //Valor otorgado al producto, si se pierde el producto, le reembolsamos esa cantidad
    private double altura;
    private double ancho;
    private double largo;
    private String instruccionesEspeciales; //Cualquier instrucción especial para el manejo del paquete, como "manejar con cuidado" o "almacenar en lugar fresco" 

    public Paquete(int codigo, double peso, double altura, double ancho, double largo, boolean fragil,  double valor_declarado, String instruccionesEspeciales) {
        this.codigo = codigo;
        this.valorDeclarado = valor_declarado;
        this.peso = peso;
        this.altura = altura;
        this.ancho = ancho;
        this.largo = largo;
        this.fragil = fragil;
        this.volumen = altura * largo * ancho;
        this.instruccionesEspeciales = instruccionesEspeciales;
    }

    public boolean isFragil() {
        return fragil;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }    
}
