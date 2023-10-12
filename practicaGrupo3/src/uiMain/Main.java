package uiMain;

import productos.*;
import personas.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import personas.Cliente;
import transportes.*;
import personas.Cliente;


import java.util.Scanner;

public class Main {

    public static void print(Object objeto) {
        System.out.println(objeto);
    }

    public static void main(String[] args) {

        
        Animal Perrito = new Animal("Toby", 10, 30, tipoAnimal.PERRO);

        //System.out.println(Perrito.getTipo());
        Membresia membresia1 = new Membresia();
        //System.out.println(membresia1.getBeneficio());
        /*Destinatario destinatario1 = new Destinatario("Tomas",123456, new CuentaBancaria(), 316555 );
        Cliente cliente1 = new Cliente("Tomas",123456, new CuentaBancaria(), 316555, membresia1 );
        
        System.out.println(destinatario1.getNombre());
        System.out.println(destinatario1.getCedula());
        System.out.println(destinatario1.getCuentaBancaria());
        System.out.println(destinatario1.getTelefono());
        membresia1.setCliente(cliente1);
        System.out.println(membresia1);
        System.out.println(cliente1);
        System.out.println(destinatario1);
            }´ñ        */

        Cliente guzman = new Cliente("Jaime Guzman", 1033487678, null, 350562901, membresia1);

        CuentaBancaria cuentaGuzman = new CuentaBancaria(guzman, 45068373, 123, "08/27");
        guzman.setCuentaBancaria(cuentaGuzman);

        //Ejemplos
        Sucursal medellin = new Sucursal("Medellín", 500, 1000, -5, 5);
        Sucursal cali = new Sucursal("Cali", 500, 1000, -5, -2);
        Sucursal pasto = new Sucursal("Pasto", 500, 1000, -8, -5);
        Sucursal florencia = new Sucursal("Florencia", 500, 1000, 4, -1);
        Sucursal bogota = new Sucursal("Bogotá", 500, 1000, 5, 2);
        //System.out.println(Sucursal.getTodasLasSucursales());
        //System.out.println(new Paquete(20, 3, 4, 3, false, 3000, "Nada"));

        /*for (Sucursal sucursal : Sucursal.getTodasLasSucursales()) {
            System.out.println(sucursal.getCiudad());
        }*/

        Guia guiaToby = new Guia(Perrito, true, false, guzman, bogota, florencia);
        /*for (Sucursal sucursal : guiaToby.getRuta()) {
            print(sucursal.getCiudad());
        }*/

        //print(Guia.calcularDistancia(florencia, bogota));
        //print(guiaToby.pagarEfectivo());
        guzman.getCuentaBancaria().setSaldo(10000000);
        print(guiaToby.pagarTarjeta("Jaime Guzman", 45068373, 123, "08/27"));
        //print(guzman.getCuentaBancaria().getSaldo());
        //print(guiaToby.getPrecioTotal());
        }
    
   

}
