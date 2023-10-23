package administracion;

import java.util.Random;
import java.io.Serializable;
import personas.*;


// La finalidad de esta clase es saber el beneficio de entrega
// y demas beneficios que incluye la membresia que cada cliente tiene.
public class Membresia implements Serializable {
	private tipo beneficio;
	private int precio;
	private Cliente cliente;
	
	public enum tipo{
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
		this.beneficio = crearMembresia();
	}

	public Membresia(Cliente cliente) {
		this.cliente = cliente;
		this.beneficio = crearMembresia();
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

	public String toString() {
		return "El cliente tiene el beneficio " + String.valueOf(getBeneficio()).toLowerCase();
	}



}
