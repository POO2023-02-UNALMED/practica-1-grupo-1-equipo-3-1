package productos;



public class Animal extends Producto {
    private String nombre; /*Para que aparezca su nombre si se murió o ya llegó al lugar de destino a la hora de rastrear el paquete
    "Toby ya llegó" o "Toby falleció, lo sentimos" */
    private int edad; //Entre mas viejo, más probabilidad tiene de morirse en la trayectoria
    private boolean vivo; //Si el animal muere en el trayecto se cambia este valor
    private boolean peligroso; //Si es peligroso, aumenta el precio en una cuarta parte
    private tipoAnimal tipo; //Si es un perro o un gato etc
    private tamanoAnimal tamano; /*Solo hay tres tamaños preestablecidos, si quieres enviar un gato la opcion es "PEQUENO",
    cada tamano tiene un volumen distinto que va a ocupar en la sucursal y en el camion.*/

    public enum tipoAnimal {
        PERRO,
        GATO,
        CABALLO,
        VACA,
        LORO,
        HAMSTER,
    }

    public enum tamanoAnimal {
        PEQUENO,
        MEDIANO,
        GRANDE,
    }

    public Animal(String nombre, int edad, double peso, tipoAnimal tipo) {
        super(generarCodigo());
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.tipo = tipo;

        vivo = true;

        asignarPeligro();
        asignarTamano();
        asignarVolumen();
        asignarCostoDelPedido();
    }

    public String toString() {
        return "Tipo de producto: Animal\n" +
        "Codigo de pedido: " + codigo + "\n" +
        "Nombre: " + nombre + "\n" +
        "Edad: " + edad + "\n" +
        "Peso: " + peso + "\n" +
        "Tamaño: " + tamano.toString().toLowerCase() + "\n";
    }

    public void asignarPeligro() {
        switch (tipo) {
            case PERRO:
                peligroso = true;
                break;
            case GATO:
                peligroso = false;
                break;
            case CABALLO:
                peligroso = true;
                break;
            case VACA:
                peligroso = true;
                break;
            case LORO:
                peligroso = false;
                break;
            case HAMSTER:
                peligroso = false;
                break;
        }
    }

    public void asignarTamano() {
        switch (tipo) {
            case PERRO:
                tamano = tamanoAnimal.MEDIANO;
                break;
            case GATO:
                tamano = tamanoAnimal.PEQUENO;
                break;
            case CABALLO:
                tamano = tamanoAnimal.GRANDE;
                break;
            case VACA:
                tamano = tamanoAnimal.GRANDE;
                break;
            case LORO:
                tamano = tamanoAnimal.PEQUENO;
                break;
            case HAMSTER:
                tamano = tamanoAnimal.PEQUENO;
                break;
        }
    }

    public void asignarVolumen() {
        switch (tamano) {
            case PEQUENO:
                volumen = 1;
                break;
            case MEDIANO:
                volumen = 3;
                break;
            case GRANDE:
                volumen = 6;
                break;
        }
    }

    public void asignarCostoDelPedido() {
        switch (tamano) {
            case PEQUENO:
                costoDelPedido = 200000;
                break;
            case MEDIANO:
                costoDelPedido = 350000;
                break;
            case GRANDE:
                costoDelPedido = 500000;
                break;
        }

        if (peligroso) {
            costoDelPedido *= 1.25; //Si el animal es peligroso, el valor del pedido aumenta en una cuarta parte
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isVivo() {
        return vivo;
    }

    public boolean isPeligroso() {
        return peligroso;
    }

    public tipoAnimal getTipo() {
        return tipo;
    }

    public tamanoAnimal getTamano() {
        return tamano;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setPeligroso(boolean peligroso) {
        this.peligroso = peligroso;
    }

    public void setTipo(tipoAnimal tipo) {
        this.tipo = tipo;
    }

    public void setTamano(tamanoAnimal tamano) {
        this.tamano = tamano;
    }
}
