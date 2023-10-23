package administracion;
import java.io.Serializable;
import java.util.ArrayList;
import administracion.Sucursal;
import productos.Producto;
 
//HECHA POR: TOMAS MURILLO ARISTIZABAL, KEVIN LEANDRO RAMOS LUNA

//EN ESTA CLASE SE ALMACENA TODAS LAS OPINIONES DE LAS SUCURSALES, SE GURDAN EN LISTAS CADA OPINION QUE SE DEN EN LA SUCURSAL, Y HAY METODOS PARA CALCULAR SU PROMEDIO.
//ADEMAS LAS OPINIONES TENDRA SU REPERCUSION EN LAS SUCURSALES

public class Opinion implements Serializable {
	
	private  ArrayList<Double> opinionPuntualidad = new ArrayList<>();
	private ArrayList<Double> opinionIntegridad = new ArrayList<>();
	private Sucursal sucursal;
	private static final long serialVersionUID = 1L;
	private static ArrayList<Opinion> todasLasOpiniones = new ArrayList<>();
	
	public Opinion() {}
	public Opinion(Double opinionPunt, Double opinionInt, Sucursal sucursal) {
		opinionIntegridad.add(opinionInt);
		opinionPuntualidad.add(opinionPunt);
		this.sucursal = sucursal;
		
		this.sucursal.setOpinionSucursal(this);
		Opinion.todasLasOpiniones.add(this);
	}
	
	//ESTOS METODOS DAN EL PROMEDIO DE LAS OPINIONES EN LA LISTA, ESTO ES PARA QUE SOLO MUESTRE UN NUMERO EN VEZ TODA LA LISTA DE OPINIONES
	public Double promedioPuntualidad() {
		Double suma = 0.0;
		for (Double nota: opinionPuntualidad) {
			suma += nota;
			
		}
		return (double) (suma/ opinionPuntualidad.size());
	}
	public Double promedioIntegridad() {
		Double suma = 0.0;
		for (Double nota: opinionIntegridad) {
			suma += nota;
			
		}
		return (double) (suma/ opinionIntegridad.size());
	}
	
	// ESTOS METODOS HACEN POSIBLE AGREGAR OPINIONES A CADA LISTA RESPECTIVAMENTE
	public void agregarOpinionPunt(Double opinion) {
		
		opinionPuntualidad.add(opinion);
	}
	public void agregarOpinionInt(Double opinion) {
		opinionIntegridad.add(opinion);
	}
	
	// ESTE METODO LO QUE HACE ES QUE GENERAR LA TABLA DONDE SE VE CADA SUCURSAL Y SUS OPIONIONES
	public static String generarTablaSucursales() {
	    StringBuilder tabla = new StringBuilder();
	    tabla.append(String.format("%-20s %20s %20s%n", "Sucursales", "Punt. Opinion", "Punt. Integridad"));
	    tabla.append("------------------------------------------------------------\n");

	    for (int i = 0; i < Sucursal.getTodasLasSucursales().size(); i++) {
	        Sucursal sucursal = Sucursal.getTodasLasSucursales().get(i);
	        tabla.append(String.format("%-20s %15.2f %15.2f%n", 
	                sucursal.getNombre(), 
	                sucursal.getOpinionSucursal().promedioPuntualidad(),
	                sucursal.getOpinionSucursal().promedioIntegridad()));
	    }
	    tabla.append("------------------------------------------------------------\n");
	    return tabla.toString();
	}
	
	//METODOS GETTERS Y SETTERS:
	
	public ArrayList<Double> getOpinionPuntualidad() {
		return opinionPuntualidad;
	}
	
	public void setOpinionPuntualidad(ArrayList<Double> opinionPuntualidad) {
		this.opinionPuntualidad = opinionPuntualidad;
	}
	public ArrayList<Double> getOpinionIntegridad() {
		return opinionIntegridad;
	}
	public void setOpinionIntegridad(ArrayList<Double> opinionIntegridad) {
		this.opinionIntegridad = opinionIntegridad;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public static ArrayList<Opinion> getTodasLasOpiniones() {
		return todasLasOpiniones;
	}
	public static void setTodasLasOpiniones(ArrayList<Opinion> todasLasOpiniones) {
		Opinion.todasLasOpiniones = todasLasOpiniones;
	}
	
	

}