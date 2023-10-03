package productos;

public class Animal extends Producto {
    private String nombre; /*Para que aparezca su nombre si se murió o ya llegó al lugar de destino a la hora de rastrear el paquete
    "Toby ya llegó" o "Toby falleció, lo sentimos" */
    private int edad; //Entre mas viejo, más probabilidad tiene de morirse en la trayectoria
    private boolean acuatico; //Ni idea de que hacer con esto
    private boolean vivo = true; //Si el animal muere en el trayecto se cambia este valor
    private tamanoAnimal tamano; /*Solo hay tres tamaños preestablecidos, si quieres enviar un gato la opcion es "PEQUENO",
    cada tamano tiene un volumen distinto que va a ocupar en la sucursal y en el camion.
    No me termina de convencer este atributo este atributo */

    enum tamanoAnimal {
        PEQUENO,
        MEDIANO,
        GRANDE,
    }

    public Animal(int codigo, String nombre, int edad, double peso, boolean acuatico, tamanoAnimal tamano) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.acuatico = acuatico;

        if (tamano == tamanoAnimal.PEQUENO) {
            volumen = 1;
        } else if (tamano == tamanoAnimal.MEDIANO) {
            volumen = 5;
        } else {
            volumen = 10;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public tamanoAnimal getTamano() {
        return tamano;
    }
    
}
