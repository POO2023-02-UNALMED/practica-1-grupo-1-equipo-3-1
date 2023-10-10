package uiMain;

import productos.*;
import personas.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import transportes.*;
import personas.Cliente;


public class Main {
    public static void main(String[] args) {
        //Animal Perrito = new Animal("Toby", 10, 30, tipoAnimal.PERRO);

        //System.out.println(Perrito.getTipo());
        Membresia membresia1 = new Membresia();
        System.out.println(membresia1.getBeneficio());
        Destinatario destinatario1 = new Destinatario("Tomas",123456, new CuentaBancaria(), 316555 );
        Cliente cliente1 = new Cliente("Tomas",123456, new CuentaBancaria(), 316555, membresia1 );
        
        System.out.println(destinatario1.getNombre());
        System.out.println(destinatario1.getCedula());
        System.out.println(destinatario1.getCuentaBancaria());
        System.out.println(destinatario1.getTelefono());
        membresia1.setCliente(cliente1);
        System.out.println(membresia1);
        System.out.println(cliente1);
        System.out.println(destinatario1);
        
    }
    
   

}
