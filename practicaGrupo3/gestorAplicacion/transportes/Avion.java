package transportes;
import administracion.*;

public class Avion extends Transporte {
    private static int cantAviones = 0;
    private int cantdadAvionesDisponibles = 0;

    private Guia guia;
    public Avion(String ciudadRegistro, int capacidadVolumen, int capacidadPeso, String matricula) {
        super(ciudadRegistro, capacidadVolumen, capacidadPeso, matricula, 20);
    }

    @Override
    public void mantenimiento(){
        this.setEstado(30);
    }



    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

}

