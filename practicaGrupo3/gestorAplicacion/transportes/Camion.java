package transportes;

import administracion.Guia;
import administracion.Sucursal;
import productos.Producto;

import java.io.Serializable;
import java.util.ArrayList;

public class Camion extends Transporte implements Serializable {
    private static final long serialVersionUID = 1L;

    public Camion(Sucursal sucursalOrigen, int capacidadVolumen, int capacidadPeso, String matricula) {
        super(sucursalOrigen, capacidadVolumen, capacidadPeso, matricula);
        //Sucursales por las que va a pasar el transporte
        //EJEMPLO si el transporte es de Pasto va a tener una ruta = [Pasto, Florencia, Bogotá, Medellín, Cali, Pasto]
        //Esto es único de Camion debido a que las motos son dentro de las ciudades y los aviones hacen envíos directos
    }

    @Override
    public void asignarRuta() {
        ArrayList<Sucursal> sucursales = Sucursal.getTodasLasSucursales();

        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i) == getSucursalOrigen()) {
                for (int j = i; j < sucursales.size(); j++) {
                    ruta.add(sucursales.get(j));
                }

                for (int k = 0; k < i + 1; k++) {
                    ruta.add(sucursales.get(k));
                }
            }
        }
    }

    @Override
    public void entrarASucursal(Sucursal sucursal) {
        //System.out.println("Estoy en " + sucursal.getNombre());
        ubicacionActual = sucursal;
        sucursal.agregarCamion(this);
        for (Producto producto : inventario) { //Busca en el inventario los producto que tiene como llegada está sucursal
            if (producto.getGuia().getSucursalLlegada() == sucursal) {
                if (sucursal.getCapacidadVolumen() > producto.getVolumen()) { //Verifica si hay capacidad para guardar el producto
                    if (sucursal.getCapacidadPeso() > producto.getPeso()) { //Verifica si hay capacidad para guardar el producto
                        sucursal.getInventario().add(producto); //Agrega el producto al inventario de la sucursal
                        producto.getGuia().setEstado(Guia.estado.ENESPERA);
                        //System.out.println("Dejé el paquete" + producto.getClass() + " en la sucursal " + sucursal.getNombre());
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

    @Override
    public void salirDeSucursal(Sucursal sucursal) {
        ubicacionAnterior = ubicacionActual;
        ubicacionActual = null;
        for (int i = 0; i < ruta.size() - 1; i++) {
            if (ruta.get(i) == ubicacionAnterior) {
                ubicacionSiguiente = ruta.get(i + 1);
            }
        }
        sucursal.removerCamion(this);
        this.enSucursal = false;
        //System.out.println("Salí de " + sucursal.getNombre());
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
            for (int i = 1; i < ruta.size() - 1; i++) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Sale de una sucursal a otra se demora 5 segundos

                entrarASucursal(ruta.get(i));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Espera en esa sucursal 5 segundos
                salirDeSucursal(ruta.get(i));
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            entrarASucursal(ruta.get(ruta.size() - 1)); // Finaliza el recorrido y vuelve a la sucursal propia

        });

        simulacionThread.start();
    }

    public String ubicarTransporte() {
        if (enSucursal) {
            return "El Camión de matrícula " + matricula + "\n" +
                    "que contiene su pedido en este momento se encuentra en la sucursal " + ubicacionActual.getNombre();
        } else {
            return "El Camión de matrícula " + matricula + " que contiene su pedido " + "\n" +
                    "en este momento se encuentra entre la sucursal de " + "\n" +
                    ubicacionAnterior.getNombre() + " y la sucursal de " + ubicacionSiguiente.getNombre();
        }
    }

}

