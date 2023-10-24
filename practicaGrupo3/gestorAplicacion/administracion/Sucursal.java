package administracion;
//HECHA POR TOMÁS GÓMEZ Y KEVIN RAMOS
import java.io.Serializable;
import java.util.*;

import administracion.Guia;
import personas.*;
import productos.*;
import transportes.*;


public class Sucursal implements Serializable {

    private String nombre;
    private String ciudad;
    private int capacidadVolumen; 
    private int capacidadPeso; 
    private int latitud; 
    private int longitud; 
    private ArrayList<Producto> inventario = new ArrayList<>(); 
    private static ArrayList<Sucursal> todasLasSucursales = new ArrayList<>(); 
    private ArrayList<Camion> camionesEnSucursal = new ArrayList<>();
    private ArrayList<Avion> avionesEnSucursal = new ArrayList<>();
    private static CuentaBancaria correminas = new CuentaBancaria(new Cliente(),123456789,333,"07/05",1000000000);


    private int cantidadJaulasPequenas;
    private int cantidadJaulasMedianas;
    private int cantidadJaulasGrandes;

    private Opinion opinionSucursal;

    //EL IDENTIFICADOR PARA EL DESERIALIZADOR
    private static final long serialVersionUID = 1L; //

    //constructor
    public Sucursal(String nombre, int capacidadVolumen, int capacidadPeso, int longitud, int latitud, ArrayList<Camion> camionesEnSucursal, ArrayList<Avion> avionesEnSucursal) {
        this.nombre = nombre;
        String[] palabra = nombre.split(" ");
        ciudad = palabra[0];

        this.capacidadVolumen = capacidadVolumen;
        this.capacidadPeso = capacidadPeso;
        this.longitud = longitud;
        this.latitud = latitud;
        this.camionesEnSucursal = camionesEnSucursal;
        this.avionesEnSucursal = avionesEnSucursal;

        cantidadJaulasPequenas = 5;
        cantidadJaulasMedianas = 3;
        cantidadJaulasGrandes = 2;

        Sucursal.todasLasSucursales.add(this);

    }

    //Métodos de clase
    
    public boolean disponibilidadJaulas(Animal animal) { 
        boolean disponibilidad = false;

        switch (animal.getTamano()) {
            case PEQUENO:
                if (cantidadJaulasPequenas > 0) {
                    disponibilidad = true;
                    break;
                }
            case MEDIANO:
                if (cantidadJaulasMedianas > 0) {
                    disponibilidad = true;
                    break;
                }
            case GRANDE:
                if (cantidadJaulasGrandes > 0) {
                    disponibilidad = true;
                    break;
                }
        }

        return disponibilidad;
    }

    public void agregarCamion(Camion camion) {
        camionesEnSucursal.add(camion);
    }

    public void agregarAvion(Avion avion) {
        avionesEnSucursal.add(avion);
    }

    public void removerCamion(Camion camion) {
        camionesEnSucursal.remove(camion);
    }

    public void removerAvion(Avion avion) {
        avionesEnSucursal.remove(avion);
    }

    public void agregarProducto(Producto nuevoProducto) { //Se usa este metodo cuando se hace el envío o llega un paquete de otra sucursal
        boolean seAgrega = false;

        if (nuevoProducto instanceof Animal) { //Verfica si este producto es animal
            Animal nuevoAnimal = (Animal) nuevoProducto; //Fundicion de producto a animal de forma explicita

            if (disponibilidadJaulas(nuevoAnimal)) { //Verifica si hay jaulas
                if (capacidadVolumen > nuevoAnimal.getVolumen()) { //Verifica si hay espacio en la sucursal
                    if (capacidadPeso > nuevoAnimal.getPeso()) { //Verifica el peso
                        inventario.add(nuevoAnimal); //Si se cumple todo lo agrega y resta las distintas capacidades

                        capacidadVolumen -= nuevoAnimal.getVolumen();
                        capacidadPeso -= nuevoAnimal.getPeso();
                        seAgrega = true; //Para arrojar el mensaje al final

                        switch (nuevoAnimal.getTamano()) {
                            case PEQUENO:
                                cantidadJaulasPequenas--;
                                break;
                            case MEDIANO:
                                cantidadJaulasMedianas--;
                                break;
                            case GRANDE:
                                cantidadJaulasGrandes--;
                                break;
                        }
                    }
                }
            }
        } else { //Sino es animal, no verifica las jaulas sino los otros dos parametros

            if (capacidadVolumen > nuevoProducto.getVolumen()) {
                if (capacidadPeso > nuevoProducto.getPeso()) {
                    inventario.add(nuevoProducto);

                    capacidadVolumen -= nuevoProducto.getVolumen();
                    capacidadPeso -= nuevoProducto.getPeso();
                    seAgrega = true; //Para arrojar el mensaje al final

                }
            }
        }
    }

    public String verificarProductoCliente(Producto producto) { //verifica para ser recogido por el cliente
        if (inventario.contains(producto)) {
            return "El paquete con código " + producto.getCodigo() + " se encuentra en la sucursal y está listo para ser recogido.";
        } else {
            return "Lo sentimos, paquete con código " + producto.getCodigo() + " no está en la sucursal.";
        }
    }

    public boolean verificarProducto(Producto producto) { //Verifica para ser recogido por los transportes para llevarlo a otra sucursal
        if (inventario.contains(producto)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean verificarDisponibilidad(Producto producto) {
        if (capacidadVolumen > producto.getVolumen()) {
            if (capacidadPeso > producto.getPeso()) {
                return true;
            }
        }
        return false;
    }

    public String ubicar(Producto producto) {
        Sucursal sucursal = null;
        boolean estaEnSucursal = false;
        for (Sucursal sucursal1 : producto.getGuia().getRuta()) {
            if (sucursal1.verificarProducto(producto)) {
                estaEnSucursal = true;
                sucursal = sucursal1;
                break;
            }
        }

        if (estaEnSucursal) {
            return sucursal.getNombre();
        } else {
            return "El producto está en reparto, se encuentra entre " + producto.getGuia().getVehiculo();
        }

    }

    //GET Y SET

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getLatitud() {
        return this.latitud;
    }

    public int getLongitud() {
        return this.longitud;
    }

    public static ArrayList<Sucursal> getTodasLasSucursales() {
        return Sucursal.todasLasSucursales;
    }

    public static void setTodasLasSucursales(ArrayList<Sucursal> lista) {
        todasLasSucursales = lista;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public static CuentaBancaria getCorreminas() {
        return correminas;
    }

    public ArrayList<Camion> getCamionesEnSucursal() {
        return camionesEnSucursal;
    }

    public ArrayList<Avion> getAvionesEnSucursal() {
        return avionesEnSucursal;
    }

    public int getCapacidadVolumen() {
        return capacidadVolumen;
    }

    public int getCapacidadPeso() {
        return capacidadPeso;
    }

    public void setCapacidadPeso(int numero) {
        this.capacidadPeso = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Opinion getOpinionSucursal() {
        return opinionSucursal;
    }

    public void setOpinionSucursal(Opinion opinionSucursal) {
        this.opinionSucursal = opinionSucursal;
    }
}
