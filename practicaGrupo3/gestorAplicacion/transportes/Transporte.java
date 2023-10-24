package transportes;
//HECHO POR DANIELA SÁNCHEZ
import administracion.Sucursal;
import productos.Producto;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Transporte implements Serializable {
    protected Sucursal sucursalOrigen; //Sucursal a la que pertenece el transporte
    protected Sucursal sucursalDestino;
    protected int capacidadVolumen;
    protected int capacidadPeso;
    protected String matricula;
    protected Sucursal ubicacionActual;
    protected Sucursal ubicacionAnterior;
    protected Sucursal ubicacionSiguiente;
    protected boolean enSucursal;
    protected ArrayList<Sucursal> ruta = new ArrayList<>();
    protected ArrayList<Producto> inventario = new ArrayList<>(); //Cada vehiculo va a a tener su propio inventario

    private static ArrayList<Transporte> todosLosTransportes = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public Transporte(Sucursal sucursalOrigen, int capacidadVolumen, int capacidadPeso, String matricula) {
        this.sucursalOrigen = sucursalOrigen;
        this.capacidadVolumen = capacidadVolumen;
        this.capacidadPeso = capacidadPeso;
        this.matricula = matricula;
        ubicacionActual = sucursalOrigen;

        Transporte.todosLosTransportes.add(this);

        asignarRuta();
    }

    public Transporte(Sucursal sucursalOrigen, Sucursal sucursalDestino, int capacidadVolumen, int capacidadPeso, String matricula) {
        this.sucursalOrigen = sucursalOrigen;
        this.sucursalDestino = sucursalDestino;
        this.capacidadVolumen = capacidadVolumen;
        this.capacidadPeso = capacidadPeso;
        this.matricula = matricula;
        ubicacionActual = sucursalOrigen;

        Transporte.todosLosTransportes.add(this);

        asignarRuta();
    }

    public abstract void asignarRuta();

    public abstract void iniciarRecorrido();

    public abstract String ubicarTransporte();

    public abstract void entrarASucursal(Sucursal sucursal);

    public abstract void salirDeSucursal(Sucursal sucursal);

    public void agregarProductos() { //Agrega los productos que van a ser enviados, pasan del inventario de sucursal al del transporte
        for (Producto producto : sucursalOrigen.getInventario()) {
            if (producto.getGuia().getSucursalOrigen() == sucursalOrigen) { //Agrega SOLO los productos que vayan a salir a envio, no confundir con los que llegaron de otra sucursales
                if (producto.getGuia().getVehiculo() == this) {
                    inventario.add(producto);

                }
            }
        }

        for (Producto producto1 : inventario) {
            if (sucursalOrigen.getInventario().contains(producto1)) {
                sucursalOrigen.getInventario().remove(producto1);

            }
        }
    }

    public Sucursal getUbicacionAnterior() {
        return ubicacionAnterior;
    }

    public Sucursal getUbicacionActual() {
        return ubicacionActual;
    }

    public Sucursal getUbicacionSiguiente() {
        return ubicacionSiguiente;
    }

    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public ArrayList<Sucursal> getRuta() {
        return ruta;
    }

    public String getMatricula() {
        return matricula;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public int getCapacidadPeso() {
        return capacidadPeso;
    }

    public int getCapacidadVolumen() {
        return capacidadVolumen;
    }

    public static ArrayList<Transporte> getTodosLosTransportes() {
        return Transporte.todosLosTransportes;
    }

    public boolean isEnSucursal() {
        return enSucursal;
    }

    public static void setTodosLosTransportes(ArrayList<Transporte> lista) {
        todosLosTransportes = lista;
    }

    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }
}
