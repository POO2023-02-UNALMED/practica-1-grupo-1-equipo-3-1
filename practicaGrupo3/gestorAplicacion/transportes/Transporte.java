package transportes;

import java.io.Serializable;
import java.util.ArrayList;

import administracion.*;
import productos.*;

public abstract class Transporte implements Serializable {
    private static int cant_transporte = 0;
    protected Sucursal sucursalOrigen;
    protected int capacidadVolumen;
    protected int capacidadPeso;
    protected String matricula;
    protected int estado;
    protected ArrayList<Producto> inventario = new ArrayList<>(); //TOMAS Cada vehiculo va a a tener su propio inventario
    private static ArrayList<Transporte> todosLosTransportes = new ArrayList<>();
    private Sucursal sucursal; //Sucursal a la que pertenece el transporte
    private static final long serialVersionUID = 1L;

    public Transporte(Sucursal sucursalOrigen, int capacidadVolumen, int capacidadPeso, String matricula, int estado) {
        this.sucursalOrigen = sucursalOrigen;
        this.capacidadVolumen = capacidadVolumen;
        this.capacidadPeso = capacidadPeso;
        this.matricula = matricula;
        this.estado = estado;

        Transporte.cant_transporte++;
        Transporte.todosLosTransportes.add(this);
    }

    public abstract void mantenimiento();

    /*  TOMAS Lo comenté para verificarlo más adelante
        public String entregarPaquete(Guia guia){
            this.guia = guia;
            this.guia.lugar_actual = this.guia.destino;
            this.ciudad_actual = this.guia.lugar_actual;
            this.estado--;
            if(this.estado == 1){
                this.mantenimiento();
            }
            return "Entregado con éxito";
        }
    */
    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setCiudad_registro(Sucursal ciudadRegistro) {
        this.sucursalOrigen = ciudadRegistro;
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

    public static void setTodosLosTransportes(ArrayList<Transporte> lista) {
        todosLosTransportes = lista;
    }

    public int getEstado() {
        return estado;
    }

    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
