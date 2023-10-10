package transportes;

public class Moto extends Transporte {
    private static int cant_motos;
    private static int cantidadMotosDisponibles;
    private int matricula;

    public Moto(String ciudad_registro, float capacidad_carga, String fabricante, int matricula){
        super(ciudad_registro, capacidad_carga, fabricante, 20);
        this.matricula = matricula;
        Moto.cant_motos++;
    }

    @Override
    public void mantenimiento(){
        this.setEstado(20);
    }
    
    public void recogerPaquete(){
        //crear guia
    }

    public static int getCant_motos(){
        return cant_motos;
    }

}
