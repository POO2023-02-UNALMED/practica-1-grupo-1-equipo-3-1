package transportes;

public class Camion extends Transporte {
    private static int cant_camiones;
    private static int cantidadCamionesDisponibles;
    private int matricula;

    public Camion(String ciudad_registro, float capacidad_carga, String fabricante, int matricula){
        super(ciudad_registro, capacidad_carga, fabricante, 20);
        this.matricula = matricula;
        Camion.cant_camiones++;
    }

    @Override
    public void mantenimiento(){
        this.setEstado(20);
    }
    public void recogerPaquete(){
        //Crear guia

    }
    public static int getCant_camiones() {
        return cant_camiones;
    }


}

