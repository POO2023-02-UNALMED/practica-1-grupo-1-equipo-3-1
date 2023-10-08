package administracion;
import java.util.Random;

import administracion.EventosAleatorios.EventoAnimal;
import administracion.EventosAleatorios.EventoPaquete;

//import sucursal.* No hace falta ya que estan en el mismo paquete

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.Math;

import productos.*;
import transportes.*;

	public class Guia {
		// atributos de clase
		private Transporte vehiculo;
		private float tiempo;
		private Producto producto;
		private Sucursal sucursalOrigen; //la clase sucursal debe tener método destino
		private Sucursal sucursalLlegada; //la clase sucursal debe tener método destino
		private ArrayList<Sucursal> ruta = new ArrayList<>(); /*Lista de sucursales por las que va a pasar incluyendo
		la ciudad de salida y la de destino*/
		// pago está en sucursal, ¿qué hacemos acá?
		private String direccion;
		private boolean pagoContraentrega;
		private boolean entregaEnSucursal;
		private double precioTotal;
		private final LocalDateTime fechaDeEnvio;
		private estado estado;
		
		public enum estado{
			ENTRANSITO,
			ENESPERA,
			ENTREGADO
		} 

		//Constructor
		public Guia(Producto producto, LocalDateTime fechaDeEnvio, boolean pagoContraentrega, boolean entregaEnSucursal) {
			this.producto = producto;
			this.fechaDeEnvio = fechaDeEnvio;
			this.pagoContraentrega = pagoContraentrega;
			this.entregaEnSucursal = entregaEnSucursal;

			asignarRuta();
		}
		
		//métodos
		// Método para calcular la distancia entre dos puntos 
		public static double calcularDistancia(Sucursal origen, Sucursal destino) {
			double x = destino.getLongitud() - origen.getLongitud();
			double y = destino.getLatitud() - origen.getLatitud();

			double magnitud = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			return magnitud;
		}

		// Método para calcular la cantidad de escalas según la membresía del cliente
		public static int calcularCantidadEscalas(Cliente remitente) {
			switch (remitente.getMembresia().getBeneficio()) {
				case SILVER:
					return 4; // Hace 4 escalas
				case GOLD:
					return 2; // Hace la mitad de las escalas de Silver 
				case PLATINUM:
					return 0; // No hace ninguna escala
				default:
					return 5; // Valor predeterminado para el cliente sin membresía 
			}
		}

		// Método para determinar la siguiente sucursal basada en la distancia geográfica
		public static Sucursal determinarSiguienteSucursal(Sucursal origen, ArrayList<Sucursal> sucursales) {
				Sucursal siguienteSucursal = null;
				double distanciaMinima = Double.MAX_VALUE;
				//recorreos cada sucursal
				for (Sucursal sucursal : sucursales) {
					if (!sucursal.equals(origen)) {
						double distancia = calcularDistancia(origen, sucursal);
						if (distancia < distanciaMinima) {
							distanciaMinima = distancia;
							siguienteSucursal = sucursal;
						}
					}
				}

				return siguienteSucursal;
			}


		public void asignarRuta() { //Falta terminar
			ruta.add(sucursalOrigen);
			ruta.add(determinarSiguienteSucursal(sucursalLlegada, Sucursal.getTodasLasSucursales()));
			ruta.add(sucursalLlegada);

		}


		public Transporte getVehiculo() {
			return vehiculo;
		}

		public float getTiempo() {
			return tiempo;
		}

		public Producto getProducto() {
			return producto;
		}

		public Sucursal getSucursalOrigen() {
			return sucursalOrigen;
		}

		public Sucursal getSucursalLlegada() {
			return sucursalLlegada;
		}

		public void setVehiculo(Transporte vehiculo) {
			this.vehiculo = vehiculo;
		}

		public void setSucursalOrigen(Sucursal sucursalOrigen) {
			this.sucursalOrigen = sucursalOrigen;
		}

		public void setSucursalLlegada(Sucursal sucursalLlegada) {
			this.sucursalLlegada = sucursalLlegada;
		}

}
	public class EventosAleatorios 
		// PAQUETE FRAGIL PUEDE ROMPERSE Y PUEDE QUE NO; ANIMAL DERRUMBE A SOBREVIVIDO ; LLORONA DISCUTIENDO
		// PEGAR A GUÍA
		    enum EventoPaquete {
		        DERRUMBE(50),
		        DILUVIO(50),
		        TRAFICO(50),
		        GUERRILLA(50),
		        LLORONA(50),
		        ATENTADO(50),
		        ATRACO(50),
		        DAÑO_EN_VEHICULO(50);

		        private int probabilidad;

		        private EventoPaquete(int probabilidad) {
		            this.probabilidad = probabilidad;
		        }

		        public int getProbabilidad() {
		            return probabilidad;
		        }
		    }

		    enum EventoAnimal {
		        ESCAPE(35),
		        MUERTO(20);

		        private int probabilidad;

		        private EventoAnimal(int probabilidad) {
		            this.probabilidad = probabilidad;
		        }

		        public int getProbabilidad() {
		            return probabilidad;
		        }
		    }

		    // Atributos
		    private int probabilidad;
		    private EventoPaquete eventoPaquete;
		    private EventoAnimal eventoAnimal;

		    // Constructor con valor predeterminado para la probabilidad y eventos aleatorios
		    public EventosAleatorios() {
		        probabilidad = 100;
		        eventoPaquete = EventoPaquete.values()[new Random().nextInt(EventoPaquete.values().length)];
		        eventoAnimal = EventoAnimal.values()[new Random().nextInt(EventoAnimal.values().length)];
		    }

		    // Métodos para obtener y establecer la probabilidad
		    public int getProbabilidad() {
		        return probabilidad;
		    }

		    public void setProbabilidad(int probabilidad) {
		        this.probabilidad = probabilidad;
		    }

		    // Métodos para obtener y establecer eventoPaquete
		    public EventoPaquete getEventoPaquete() {
		        return eventoPaquete;
		    }

		    public void setEventoPaquete(EventoPaquete eventoPaquete) {
		        this.eventoPaquete = eventoPaquete;
		    }

		    // Métodos para obtener y establecer eventoAnimal
		    public EventoAnimal getEventoAnimal() {
		        return eventoAnimal;
		    }

		    public void setEventoAnimal(EventoAnimal eventoAnimal) {
		        this.eventoAnimal = eventoAnimal;
		    }

		    // Método para generar un evento aleatorio
		    public void generarEventoAleatorio() {
		        Random random = new Random();
		        int numeroAleatorio = random.nextInt(100); // Rango entre 0 y 99

		        int probabilidadTotal = eventoPaquete.getProbabilidad() + eventoAnimal.getProbabilidad();

		        if (numeroAleatorio < eventoPaquete.getProbabilidad()) {
		            System.out.println("¡Ha ocurrido un evento de paquete: " + eventoPaquete + "!"); //Se cambiara el texto antes del evento si no les gusta (VIERNES)
		        } else if (numeroAleatorio < probabilidadTotal) {
		            System.out.println("¡Ha ocurrido un evento de animal: " + eventoAnimal + "!");
		        } else {
		            System.out.println("No ha ocurrido ningún evento.");
		        }
		    }
	}
