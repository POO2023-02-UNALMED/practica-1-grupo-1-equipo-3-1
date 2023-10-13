package administracion;
import java.util.ArrayList;

import productos.Producto;
 
//Las listas van a almacenar todas las opiniones, para eso cree
// en la clase sucursal un nuevo atributo que es de tipo opinion
// para guardar ahi las opiniones de cada sucursal

public class Opinion {
	private  ArrayList<Integer> opinionPuntualidad = new ArrayList<>();
	private ArrayList<Integer> opinionIntegridad = new ArrayList<>();
	
	public Opinion() {}
	public Opinion(int opinionPunt, int opinionInt) {
		opinionIntegridad.add(opinionInt);
		opinionPuntualidad.add(opinionPunt);
	}
	
	public void agregarPuntuacionOpinion(int opinion) {
		
		
		
	}
	// Este metodo todavia no se como va hacer pero este va mirar cuales son
	// el promedio de las opiniones y si es menor que 2, en el caso
	// de puntulidad, solo se imprime por pantalla que se va implementar un nuevo
	// modo para mejorar la puntualidad de la sucursal y como medida
	// se le restringue a esa sucursal el inventario que puede almacenar
	// y la mas grave seria integridad que si es por debajo de 2 se cierra la sucursal
	public void Analisis() {
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
	
	

}
