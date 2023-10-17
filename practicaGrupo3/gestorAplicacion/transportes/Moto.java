package transportes;

import administracion.Sucursal;

public class Moto extends Transporte {
    private static int cantMotos;
    private static int cantidadMotosDisponibles;
    private String matricula;

    public Moto(Sucursal ciudadRegistro, int capacidadVolumen, int capacidadPeso, String matricula){
        super(ciudadRegistro, capacidadVolumen, capacidadPeso, matricula, 20);
        Moto.cantMotos++;
    }

    @Override
    public void mantenimiento(){
        this.setEstado(20);
    }
    
    public void recogerPaquete(){
        //crear guia
    }


}
