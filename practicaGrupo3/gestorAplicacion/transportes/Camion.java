package transportes;

import administracion.Sucursal;
import java.util.ArrayList;

public class Camion extends Transporte {
    private static int cant_camiones;
    private static int cantidadCamionesDisponibles;
    private ArrayList<Sucursal> ruta = new ArrayList<>();
    private Sucursal ubicacionActual;
    private Sucursal ubicacionAnterior;
    private Sucursal ubicacionSiguiente;
    private boolean enSucursal;
    

    public Camion(Sucursal ciudadRegistro,  int capacidadVolumen, int capacidadPeso, String matricula){
        super(ciudadRegistro, capacidadVolumen, capacidadPeso, matricula, 20);
        Camion.cant_camiones++;

        asignarRuta(); //Sucursales por las que va a pasar el transporte
        //EJEMPLO si el transporte es de Pasto va a tener una ruta = [Pasto, Florencia, Bogotá, Medellín, Cali, Pasto]
        //Esto es único de Camion debido a que las motos son dentro de las ciudades y los aviones hacen envíos directos
    }

    @Override
    public void mantenimiento(){
        this.setEstado(20);
    }

    public void asignarRuta() {
        ArrayList<Sucursal> sucursales = Sucursal.getTodasLasSucursales();

        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i) == getSucursalOrigen()) {
                for (int j = i; j < sucursales.size(); j++) {
                    ruta.add(sucursales.get(j));
                }

                for (int k = 0; k < i + 1; k++) {
                    ruta.add(sucursales.get(k));
                }
            }
        }
    }

    //Revisar
    public void entrarASucursal(Sucursal sucursal) {
        ubicacionActual = sucursal;
        sucursal.agregarCamion(this);
        enSucursal = true;
    }

    //Revisar
    public void salirDeSucursal(Sucursal sucursal) {
        ubicacionAnterior = ubicacionActual;
        ubicacionActual = null;
        for (int i = 0; i < ruta.size() - 1; i++) {
            if (ruta.get(i) == ubicacionAnterior) {
                ubicacionSiguiente = ruta.get(i + 1);
            }
        }
        sucursal.removerCamion(this);
        this.enSucursal = false;
    }

    //Revisar
    public String ubicarTransporte() {
        //Cómo lo hacemos?
        if (enSucursal == true) {
            return ubicacionActual.getNombre();
        } else {
            return "Entre la ciudad de " + ubicacionAnterior.getNombre() + " y la ciudad de " + ubicacionSiguiente.getNombre();
        }
    }

    //Revisar
    public void iniciarRecorrido() {
        for (int i = 1; i < ruta.size() - 1; i++) {
            entrarASucursal(ruta.get(i));
            //Espera en esa sucursal 3 horas
            salirDeSucursal(ruta.get(i));
            //Calcula el tiempo entre una sucursal y otra y eso es lo que se demora
        }

        entrarASucursal(ruta.get(ruta.size() - 1)); //Finaliza el recorrido y vuelve a la sucursal propia
    }

    public void recogerPaquete(){
        //Crear guia

    }
    public static int getCant_camiones() {
        return cant_camiones;
    }


}

