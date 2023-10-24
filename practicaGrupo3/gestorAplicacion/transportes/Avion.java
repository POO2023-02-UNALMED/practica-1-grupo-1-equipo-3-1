package transportes;
//HECHO POR DANIELA SÁNCHEZ
import administracion.*;
import productos.Producto;

import java.io.Serializable;
import java.util.ArrayList;

public class Avion extends Transporte implements Serializable{
    private static final long serialVersionUID = 1L;

    public Avion(Sucursal sucursalOrigen, Sucursal sucursalDestino, int capacidadVolumen, int capacidadPeso, String matricula) {
        super(sucursalOrigen, sucursalDestino, capacidadVolumen, capacidadPeso, matricula);
        this.sucursalDestino = sucursalDestino;
    }

    public void asignarRuta() {
        ruta.add(sucursalOrigen);
        ruta.add(sucursalDestino);
        ruta.add(sucursalOrigen);
    }

    public void entrarASucursal(Sucursal sucursal) {
        
        ubicacionActual = sucursal;
        sucursal.agregarAvion(this);
        for (Producto producto : inventario) { //Busca en el inventario los producto que tiene como llegada está sucursal
            if (producto.getGuia().getSucursalLlegada() == sucursal) {
                if (sucursal.getCapacidadVolumen() > producto.getVolumen()) { //Verifica si hay capacidad para guardar el producto
                    if (sucursal.getCapacidadPeso() > producto.getPeso()) { //Verifica si hay capacidad para guardar el producto
                        sucursal.getInventario().add(producto); //Agrega el producto al inventario de la sucursal
                        producto.getGuia().setEstado(Guia.estado.ENESPERA);
                       
                    }
                }
            }
        }
        for (Producto producto : sucursal.getInventario()) {
            if (inventario.contains(producto)) {
                inventario.remove(producto); //Elimina el producto del inventario del transporte
            }
        }
        enSucursal = true;
    }

    public void salirDeSucursal(Sucursal sucursal) {
        ubicacionAnterior = ubicacionActual;
        ubicacionActual = null;
        for (int i = 0; i < ruta.size() - 1; i++) {
            if (ruta.get(i) == ubicacionAnterior) {
                ubicacionSiguiente = ruta.get(i + 1);
            }
        }
        sucursal.removerAvion(this);
        this.enSucursal = false;

    }

    @Override
    public void iniciarRecorrido() {
        for (Producto producto : inventario) {
            producto.getGuia().setEstado(Guia.estado.ENTRANSITO); //Cambia el estado de todos los productos del inventario
        }

        ubicacionAnterior = sucursalOrigen;
        ubicacionActual = null;
        ubicacionSiguiente = ruta.get(1);

        Thread simulacionThread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            entrarASucursal(ruta.get(1)); //Entra a la sucursal de destino

            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            salirDeSucursal(ruta.get(1)); //Sale de la sucursal de destino

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            entrarASucursal(ruta.get(2)); // Finaliza el recorrido y vuelve a la sucursal propia

        });

        simulacionThread.start();
    }

    @Override
    public String ubicarTransporte() {
        if (enSucursal) {
            return "El Avión de matrícula " + matricula + "\n" +
                    "que contiene su pedido en este momento se encuentra en la sucursal " + ubicacionActual.getNombre();
        } else {
            return "El Avión de matrícula " + matricula + " que contiene su pedido " + "\n" +
                    "en este momento se encuentra volando entre la sucursal de " + "\n" +
                    ubicacionAnterior.getNombre() + " y la sucursal de " + ubicacionSiguiente.getNombre();
        }
    }



    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }
}

