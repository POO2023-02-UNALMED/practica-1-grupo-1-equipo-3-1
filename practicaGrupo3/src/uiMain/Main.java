package uiMain;

import productos.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import transportes.*;

public class Main {
    public static void main(String[] args) {
        Animal Perrito = new Animal("Toby", 10, 30, tipoAnimal.PERRO);

        System.out.println(Perrito.getTipo());
        Membresia membresia1 = new Membresia();
        System.out.println(membresia1.getBeneficio());
    }
    
   

}
