package transportes;

import java.util.ArrayList;
import administracion.*;
import productos.*;

public abstract class Transporte {
    private static int cant_transporte = 0;
    private String ciudadRegistro;
    private String ciudadActual;
    private int capacidadVolumen;
    private int capacidadPeso;
    private String matricula;
    private int estado;
    private ArrayList<Producto> inventario = new ArrayList<>(); //TOMAS Cada vehiculo va a a tener su propio inventario
    private static ArrayList<Transporte> todosLosTransportes = new ArrayList<>();
    private Sucursal sucursal; //Sucursal a la que pertenece el transporte

    public Transporte(String ciudadRegistro, int capacidadVolumen, int capacidadPeso, String matricula, int estado){
        this.ciudadRegistro = ciudadRegistro;
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
    public String getCiudad_registro() {
        return ciudadRegistro;
    }

    public void setCiudad_registro(String ciudad_registro) {
        this.ciudadRegistro = ciudad_registro;
    }

    public String getCiudad_actual() {
        return ciudadActual;
    }

    public void setCiudad_actual(String ciudad_actual) {
        this.ciudadActual = ciudad_actual;
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

    public ArrayList<Transporte> getTodosLosTransportes() {
        return Transporte.todosLosTransportes;
    } 

    public int getEstado() {
        return estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

}
