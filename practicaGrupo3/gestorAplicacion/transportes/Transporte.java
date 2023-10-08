package transportes;

public abstract class Transporte {
    private static int cant_transporte = 0;
    private String ciudad_registro;
    private String ciudad_actual;
    private float capacidad_carga;
    private String fabricante;
    private int estado;

    public Transporte(String ciudad_registro, float capacidad_carga, String fabricante, int estado){
        this.ciudad_registro = ciudad_registro;
        this.capacidad_carga = capacidad_carga;
        this.estado = estado;
        Transporte.cant_transporte++;
    }

    public abstract void mantenimiento();


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

    public String getCiudad_registro() {
        return ciudad_registro;
    }

    public void setCiudad_registro(String ciudad_registro) {
        this.ciudad_registro = ciudad_registro;
    }

    public String getCiudad_actual() {
        return ciudad_actual;
    }

    public void setCiudad_actual(String ciudad_actual) {
        this.ciudad_actual = ciudad_actual;
    }

    public float getCapacidad_carga() {
        return capacidad_carga;
    }

    public void setCapacidad_carga(float capacidad_carga) {
        this.capacidad_carga = capacidad_carga;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
