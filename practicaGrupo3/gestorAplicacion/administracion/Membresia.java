package administracion;


import java.util.Random;
import personas.*;

//Cabecera Clase:
// La finalidad de esta clase es saber el beneficio de entrega
// y demas beneficios que incluye la membresia que cada cliente tiene.
public class Membresia {
	private tipo beneficio;
	private int precio;
	private Cliente cliente;
	
	/*KEVIN. ¿Podriamos colocar el caso en el que el cliente no tenga membresía? 
			leer el método calcularCantidadEscalas en la línea 322 de la clase sucursal donde dice default.*/
	enum tipo{
		DEFAULT(50),
		SILVER(40),
		GOLD(30),
		PLATINUM(20);
		
		private int probabilidad;
		
		private tipo(int probabilidad) {
			this.probabilidad = probabilidad;
			}
		
		public int getProbabilidad() {
			return probabilidad;
			}
		}
	

	public Membresia() {
	}

	public Membresia(Cliente cliente) {
		this.cliente = cliente;
		//Aqui en cada uno de los constructores estaria  
		// el codigo que haria que la membresia fuera aleatoria
	}
	
	public tipo crearMembresia() {
        Random random = new Random();
        int probabilidadTotal = 100;
        int numeroAleatorio = random.nextInt(probabilidadTotal) + 1; // Genera un número entre 1 y 100

        int acumulado = 0;
        for (tipo membresia : tipo.values()) {
            acumulado += membresia.getProbabilidad();
            if (numeroAleatorio <= acumulado) {
                return membresia;
            }
        }
        return tipo.DEFAULT;
     }

	
	public tipo getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(tipo beneficio) {
		this.beneficio = beneficio;
	}
	public int getPrecio() {
		return precio;
	}

	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
