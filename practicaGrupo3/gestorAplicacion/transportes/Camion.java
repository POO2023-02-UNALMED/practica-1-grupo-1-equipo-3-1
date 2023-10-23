package transportes;

import administracion.Guia;
import administracion.Sucursal;
import productos.Producto;

import java.io.Serializable;
import java.util.ArrayList;

public class Camion extends Transporte implements Serializable{
    private static int cant_camiones;
    private static int cantidadCamionesDisponibles;
    private ArrayList<Sucursal> ruta = new ArrayList<>();
    private Sucursal ubicacionActual;
    private Sucursal ubicacionAnterior;
    private Sucursal ubicacionSiguiente;
    private String ubicacion;
    private boolean enSucursal;
    private static final long serialVersionUID = 1L;

    public Camion(Sucursal ciudadRegistro, int capacidadVolumen, int capacidadPeso, String matricula) {
        super(ciudadRegistro, capacidadVolumen, capacidadPeso, matricula, 20);
        Camion.cant_camiones++;

        asignarRuta(); //Sucursales por las que va a pasar el transporte
        //EJEMPLO si el transporte es de Pasto va a tener una ruta = [Pasto, Florencia, Bogotá, Medellín, Cali, Pasto]
        //Esto es único de Camion debido a que las motos son dentro de las ciudades y los aviones hacen envíos directos
    }

    @Override
    public void mantenimiento() {
        this.setEstado(20);
    }

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

    //Revisar
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

    //Revisar
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

    public void agregarProductos() { //Agrega los productos que van a ser enviados, pasan del inventario de sucursal al del transporte
        for (Producto producto : sucursalOrigen.getInventario()) {
            if (producto.getGuia().getSucursalOrigen() == sucursalOrigen) { //Agrega SOLO los productos que vayan a salir a envio, no confundir con los que llegaron de otra sucursales
                inventario.add(producto);
            }
        }

        for (Producto producto1 : inventario) {
            sucursalOrigen.getInventario().remove(producto1);
        }
    }

    public void iniciarRecorrido() {
        for (Producto producto : inventario) {
            producto.getGuia().setEstado(Guia.estado.ENTRANSITO); //Cambia el estado de todos los productos del inventario
        }
        ubicacionAnterior = sucursalOrigen;
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

            entrarASucursal(ruta.get(ruta.size() - 1)); // Finaliza el recorrido y vuelve a la sucursal propia
        });

        simulacionThread.start();
    }

    //Revisar
    public String ubicarTransporte() {
        //Cómo lo hacemos?
        if (enSucursal) {
            return "El producto en este momento se encuentra en la sucursal " + ubicacionActual.getNombre();
        } else {
            return "El producto se encuentra entre la sucursal de " + ubicacionAnterior.getNombre() + " y la sursal de " + ubicacionSiguiente.getNombre();
        }
    }

    public void recogerPaquete() {
        //Crear guia

    }

    public boolean isEnSucursal() {
        return enSucursal;
    }

    public static int getCant_camiones() {
        return cant_camiones;
    }

    public ArrayList<Sucursal> getRuta() {
        return ruta;
    }

    public Sucursal getUbicacionActual() {
        return ubicacionActual;
    }

    public Sucursal getUbicacionAnterior() {
        return ubicacionAnterior;
    }

    public Sucursal getUbicacionSiguiente() {
        return ubicacionSiguiente;
    }
}

