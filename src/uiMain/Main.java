package uiMain;

import productos.*;
import productos.Animal.tipoAnimal;
import administracion.*;
import transportes.*;
import administacion.Sucursal;

public class Main {
    public static void main(String[] args) {
        Animal Perrito = new Animal("Toby", 10, 30, tipoAnimal.PERRO);

        System.out.println(Perrito);
    
    
    
   
    Sucursal sucursal = new Sucursal();

    //Agregar paquetes al inventario
    sucursal.agregarPaquete("PAQ123", "Paquete 1");
    sucursal.agregarPaquete("PAQ456", "Paquete 2");
    sucursal.agregarPaquete("PAQ789", "Paquete 3");

    //Verificar si un paquete está en la sucursal
    sucursal.verificarPaquete("PAQ123"); // Debería mostrar "El paquete con código PAQ123 se encuentra en la sucursal."
    sucursal.verificarPaquete("PAQ999"); // Debería mostrar "El paquete con código PAQ999 no está en la sucursal."
    
    sucursal.entregarPaquete("PAQ123", "1234567890", "9876543210");
    System.out.println(sucursal);
}
