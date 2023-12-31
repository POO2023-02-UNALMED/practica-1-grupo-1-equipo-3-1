package productos;
//HECHO POR TOMAS GOMEZ
import java.io.Serializable;

public class Animal extends Producto implements Serializable{
    private String nombre; 
    private int edad; //Entre mas viejo, el precio final será más costoso
    private boolean peligroso; //Si es peligroso, aumenta el precio en una cuarta parte
    private tipoAnimal tipo; //Si es un perro o un gato etc
    private tamanoAnimal tamano; /*Solo hay tres tamaños preestablecidos, si quieres enviar un gato la opcion es "PEQUENO",
    cada tamano tiene un volumen distinto que va a ocupar en la sucursal y en el camion.*/
    private static final long serialVersionUID = 1L;
    
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
        super(generarCodigo(), peso);
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;

        asignarTamano();
        asignarPeligro();
        asignarVolumen();
        asignarCostoDelPedido();
    }

    @Override
    public String toString() {
        return  "---------------------PRODUCTO--------------------\n" +
                "Tipo de producto: Animal\n" +
                "Codigo de pedido: " + codigo + "\n" +
                "Nombre: " + nombre + "\n" +
                "Edad: " + edad + " años\n" +
                "Peso: " + peso + "kg\n" +
                "Tamaño: " + tamano.toString().toLowerCase() + "\n" +
                "Peligroso: " + (peligroso ? "Sí" : "No");
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

    @Override
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
    
    //GET Y SET

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
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
