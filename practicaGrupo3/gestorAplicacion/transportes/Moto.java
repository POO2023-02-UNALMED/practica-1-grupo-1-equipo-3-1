package transportes;

import java.io.Serializable;

import administracion.Sucursal;

public class Moto extends Transporte implements Serializable{
    private static int cantMotos;
    private static int cantidadMotosDisponibles;
    private String matricula;
    private static final long serialVersionUID = 1L;
    
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
