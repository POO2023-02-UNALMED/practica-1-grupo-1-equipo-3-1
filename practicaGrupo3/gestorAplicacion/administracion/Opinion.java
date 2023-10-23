package administracion;
import java.io.Serializable;
import java.util.ArrayList;
import administracion.Sucursal;
import productos.Producto;
 
//Las listas van a almacenar todas las opiniones, para eso cree
// en la clase sucursal un nuevo atributo que es de tipo opinion
// para guardar ahi las opiniones de cada sucursal

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
	public void agregarOpinionPunt(Double opinion) {
		
		opinionPuntualidad.add(opinion);
	}
	public void agregarOpinionInt(Double opinion) {
		opinionIntegridad.add(opinion);
	}
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

/*	
	public String Analisis() {
		if (promedioPuntualidad() < 2) {
			this.sucursal.setCapacidadPeso(this.sucursal.getCapacidadPeso()-10);
			return "Sentimos los inconvenientes con esta sucursal, Aumentaremos la eficiencia implementando un plan de mejoramiento. Gracias por su critica constructiva"
		}
		if (promedioIntegridad() < 2 ) {
			for (Producto producto: getSucursal().getInventario()) {
				if (producto.getGuia().getSucursalLlegada() == //SI ES IGUAL A LA SIGIENTE TERMINAL EN LA RUTA){
					//PREPARAR UNOS O LOS TRANSPORTES QUE SEAN NECEARIO PARA LLEVAR LOS PAQUETES Y ENVIARLOS
						}
				else {
					if (//SI EN LA CIUDAD DE LA SUCURSAL SE ENCUENTRA TODAVIA ABIERTA LA OTRA SUCURSAL SE ENVIA EL RESTO DE LOS PAQUETES ALLA ) {
						
					}
					else {
						// SI LA SUCURSAL DE LA OTRA CIUDAD SE ENCUENTRA TAMBIEN CERRADA SE ENVIA A LA SIGUIENTE SUCURSAL DE LA RUTA
					}
				}
			}
			
			// LUEGO DE GESTIONAR TODOS LOS PAQUETES PARA ELLIMINAR LA SUCURSAL SE ELIMINA DE LA LISTA DE SUCURSALES, Y PARA ASEGURARNOS QUE NO SE ENVIAN MAS PAQUETES
			// LO ELIMINAMOS AUNQUE TODAVIA NO SE COMO
		}
	}
*/
	
	
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