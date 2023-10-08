package transportes;
import administracion.*;

public class Avion extends Transporte {
    private static int cant_aviones = 0;
    private int matricula;
    private String ciudad_actual;
    private int cantdadAvionesDisponibles = 0;

    private Guia guia;
    public Avion(String ciudad_registro, float capacidad_carga, int matricula, String fabricante){
        super(ciudad_registro, capacidad_carga, fabricante, estado);
        this.matricula = matricula;
    }

    @Override
    public void mantenimiento(){
        this.estado = 30;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    public static int getCant_Aviones() {
        return cant_aviones;
    }
}

