package administracion;

import java.util.Random;
<<<<<<< Updated upstream
=======
import personas.Cliente;
>>>>>>> Stashed changes
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
	
// Aqui estan los constructoes, el atributo beneficio que es donde se guarda el tipo de beneficio que se obtuvo aleatoriamente,
// por eso el metodo de crearMembresia que es precisamente el metodo que  hace el randomizer se llama en los constructores para cuando se cree de una se obtenga un tipo.
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

}
