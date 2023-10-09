package uiMain;

import productos.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import personas.Cliente;
import transportes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Animal Perrito = new Animal("Toby", 10, 30, tipoAnimal.PERRO);

        System.out.println(Perrito.getTipo());
        Membresia membresia1 = new Membresia();
        System.out.println(membresia1.getBeneficio());

        Cliente guzman = new Cliente("Jaime Guzman", 1033487678, null, 350562901, membresia1);

        CuentaBancaria cuentaGuzman = new CuentaBancaria(guzman, 45068373, 123, "08/27");
        guzman.setCuentaBancaria(cuentaGuzman);
    }
    
   

}
