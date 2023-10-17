package transportes;
import administracion.*;

import java.util.ArrayList;

public class Avion extends Transporte {
    private static int cantAviones = 0;
    private int cantdadAvionesDisponibles = 0;
    private Sucursal sucursalDestino;
    private ArrayList<Sucursal> ruta = new ArrayList<>();


    private Guia guia;
    public Avion(Sucursal ciudadRegistro, Sucursal sucursalDestino, int capacidadVolumen, int capacidadPeso, String matricula) {
        super(ciudadRegistro, capacidadVolumen, capacidadPeso, matricula, 20);
        this.sucursalDestino = sucursalDestino;

        asignarRuta();
    }

    @Override
    public void mantenimiento(){
        this.setEstado(30);
    }


    public void asignarRuta() {
        ruta.add(sucursalOrigen);
        ruta.add(sucursalDestino);
        ruta.add(sucursalOrigen);
    }

    public Guia getGuia() {
        return guia;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

}

