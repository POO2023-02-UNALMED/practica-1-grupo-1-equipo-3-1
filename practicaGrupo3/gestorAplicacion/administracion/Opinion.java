package administracion;
import java.util.ArrayList;
import administracion.Sucursal;
import productos.Producto;
 
//Las listas van a almacenar todas las opiniones, para eso cree
// en la clase sucursal un nuevo atributo que es de tipo opinion
// para guardar ahi las opiniones de cada sucursal

public class Opinion {
	private  ArrayList<Integer> opinionPuntualidad = new ArrayList<>();
	private ArrayList<Integer> opinionIntegridad = new ArrayList<>();
	private Sucursal sucursal;
	
	public Opinion() {}
	public Opinion(int opinionPunt, int opinionInt, Sucursal sucursal) {
		opinionIntegridad.add(opinionInt);
		opinionPuntualidad.add(opinionPunt);
		this.sucursal = sucursal;
	}
	
	public Double promedioPuntualidad() {
		int suma = 0;
		for (int nota: opinionPuntualidad) {
			suma += nota;
			
		}
		return (double) (suma/ opinionPuntualidad.size());
	}
	public Double promedioIntegridad() {
		int suma = 0;
		for (int nota: opinionIntegridad) {
			suma += nota;
			
		}
		return (double) (suma/ opinionIntegridad.size());
	}
	public void agregarOpinionPunt(int opinion) {
		
		opinionPuntualidad.add(opinion);
	}
	public void agregarOpinionInt(int opinion) {
		opinionIntegridad.add(opinion);
	}

	
	public String Analisis() {
		if (promedioPuntualidad() < 2) {
			
		}
		if (promedioIntegridad() < 2 ) {
			for (Producto producto: getSucursal().getInventario()) {
				if (producto.getGuia().getSucursaLlegada() == /* SI ES IGUAL A LA SIGIENTE TERMINAL EN LA RUTA*/){
					//PREPARAR UNOS O LOS TRANSPORTES QUE SEAN NECEARIO PARA LLEVAR LOS PAQUETES Y ENVIARLOS
						}
				else {
					if (/* SI EN LA CIUDAD DE LA SUCURSAL SE ENCUENTRA TODAVIA ABIERTA LA OTRA SUCURSAL SE ENVIA EL RESTO DE LOS PAQUETES ALLA */) {
						
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Integer> getOpinionPuntualidad() {
		return opinionPuntualidad;
	}
	
	public void setOpinionPuntualidad(ArrayList<Integer> opinionPuntualidad) {
		this.opinionPuntualidad = opinionPuntualidad;
	}
	public ArrayList<Integer> getOpinionIntegridad() {
		return opinionIntegridad;
	}
	public void setOpinionIntegridad(ArrayList<Integer> opinionIntegridad) {
		this.opinionIntegridad = opinionIntegridad;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	

}
