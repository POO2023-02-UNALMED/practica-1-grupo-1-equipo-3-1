package administracion;
import java.util.Random;

import personas.*;

//import sucursal.* No hace falta ya que estan en el mismo paquete

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;


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
	private Cliente remitente;
	private Destinatario destinatario; 
	private String direccion;
	private boolean pagoContraentrega;
	private boolean entregaEnSucursal;
	private double precioTotal;
	private LocalDateTime fechaDeEnvio;
	//private LocalDateTime fechaDeLlegada;
	private estado estado;
	

	public enum estado{
		ENTRANSITO,
		ENESPERA,
		ENTREGADO
	} 
	//Constructor
	public Guia(Producto producto, LocalDateTime fechaDeEnvio, boolean pagoContraentrega, boolean entregaEnSucursal, Cliente remitente) {
		this.producto = producto;
		this.fechaDeEnvio = fechaDeEnvio;
		this.pagoContraentrega = pagoContraentrega;
		this.entregaEnSucursal = entregaEnSucursal;
		this.remitente = remitente;
		this.fechaDeEnvio = LocalDateTime.now();
		asignarRuta(remitente);
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
				return 3; // Hace la mitad de las escalas de Silver 
			case PLATINUM:
				return 2; // No hace ninguna escala
			default:
				return 5; // Valor predeterminado para el cliente sin membresía 
		}
	}
	// Método para determinar la siguiente sucursal basada en la distancia geográfica
	public static Sucursal determinarSiguienteSucursal(Sucursal origen) {
		Sucursal siguienteSucursal = null;
		double distanciaMinima = Double.MAX_VALUE;
		//recorreos cada sucursal
		for (Sucursal sucursal : Sucursal.getTodasLasSucursales()) {
			if (sucursal != origen) {
				double distancia = calcularDistancia(origen, sucursal);
				if (distancia < distanciaMinima) {
					distanciaMinima = distancia;
					siguienteSucursal = sucursal;
				}
			}
		}
			return siguienteSucursal;
	}
	//Sobrecarga
	public static Sucursal determinarSiguienteSucursal(Sucursal origen, ArrayList<Sucursal> sucursales) {
		Sucursal siguienteSucursal = null;
		double distanciaMinima = Double.MAX_VALUE;
		//recorreos cada sucursal
		for (Sucursal sucursal : sucursales) {
			if (sucursal != origen) {
				double distancia = calcularDistancia(origen, sucursal);
				if (distancia < distanciaMinima) {
					distanciaMinima = distancia;
					siguienteSucursal = sucursal;
				}
			}
		}
			return siguienteSucursal;
	}
	public void asignarRuta(Cliente remitente) { //Falta terminar
		switch (remitente.getMembresia().getBeneficio()) {
			case PLATINUM: //No hace ninguna escala
				ruta.add(sucursalOrigen);
				ruta.add(sucursalLlegada);
				break;
			case GOLD: //SOlo hace una escala
				ruta.add(sucursalOrigen);
				if (determinarSiguienteSucursal(sucursalOrigen) == sucursalLlegada) {/*Verifica si la sucursal mas cercana
					es la de destino */
					/*Si es así, tiene que encontrar otra sucursal cercana */
					ArrayList<Sucursal> rutaImprovisada = new ArrayList<>(ruta); /*Crea otra lista con la unica intencion de ser igual a ruta con la diferencia de que
					se elimina la sucursal de llegada para aplicar otra vez la funcion determinarsiguientesucursal*/
					rutaImprovisada.remove(rutaImprovisada.indexOf(sucursalLlegada));
					ruta.add(determinarSiguienteSucursal(sucursalOrigen, rutaImprovisada));
				} else {
					ruta.add(determinarSiguienteSucursal(sucursalOrigen, Sucursal.getTodasLasSucursales()));
				}
				ruta.add(sucursalLlegada);
				break;
			case SILVER:
				ruta.add(sucursalOrigen);
				while (ruta.size() < 4) {
					if (determinarSiguienteSucursal(sucursalOrigen) != sucursalLlegada) {
						if (determinarSiguienteSucursal(sucursalOrigen) != ruta.get(ruta.size() - 1)) {
						}
					
					}
				}
				ArrayList<Sucursal> rutaImprovisada = new ArrayList<>(ruta);
				while (ruta.size() < 4) {
					Sucursal siguienteSucursal = determinarSiguienteSucursal(ruta.get(ruta.size() - 1), ruta);
					if (siguienteSucursal != sucursalLlegada) {
						if (!ruta.contains(determinarSiguienteSucursal(siguienteSucursal, ruta))) {
							ruta.add(siguienteSucursal);
						}
					}
				}
				ruta.add(sucursalLlegada);
				break;
			case DEFAULT:
				ruta.add(sucursalOrigen);
				ruta.add(sucursalLlegada);		
				break;		
		}
		ruta.add(sucursalOrigen);
		ruta.add(determinarSiguienteSucursal(sucursalLlegada, Sucursal.getTodasLasSucursales()));
		ruta.add(sucursalLlegada);
	}
	public static boolean confirmarPago(int entrada) { //TOMAS REVISAR
		switch (entrada) {
			case 1:
				return true;
			case 2:
				return false;
			default:
				return false;
		}
	}
	
	public String pagar() {
		Scanner scanner = new Scanner(System.in);
		int entrada = scanner.nextInt();
		scanner.close();
		switch (entrada) {
			case 1:
				Scanner scanner1 = new Scanner(System.in);
				String titular = scanner1.nextLine();
				long numero = scanner1.nextLong();
				int cvv = scanner.nextInt();
				scanner1.nextLine();
				String fechaExpiracion = scanner.nextLine();
				scanner1.close();
				pagarTarjeta(titular, numero, cvv, fechaExpiracion);
				break;
			case 2:
				pagarEfectivo();
				break;
			default:
				return "Opción no válida. Introduce un número válido";
		}
		return "";
	}
	
	public String pagarTarjeta(String titular, long numero, int cvv, String fechaExpiracion) {
		CuentaBancaria cuentaCliente = null;
		for (CuentaBancaria cuenta : CuentaBancaria.getTodasLasCuentas()) {
			if (cuenta.getNumero() == numero) {
				cuentaCliente = cuenta;
				break;
			}
		}
		if (cuentaCliente != null) {	
			if (cuentaCliente.getTitular().getNombre().equals(titular)) {
				if (cuentaCliente.getNumero() == numero) {
					if (cuentaCliente.getCVV() == cvv) {
						if (cuentaCliente.getFechaExpiracion().equals(fechaExpiracion)) {
							if (this.isPagoContraentrega()) {
								Scanner scanner = new Scanner(System.in);
								int entrada = scanner.nextInt();
								scanner.close();
								if (confirmarPago(entrada)) {
									if (cuentaCliente.descontarSaldo(this.getPrecioTotal())) {
										return "Transacción exitosa";
									} else {
										return "Lo sentimos, no hay suficiente dinero en la cuenta";
									}
								} else {
									return "Servicio cancelado, vuelve pronto";
								}
							} else {
								return "Descuento membresia";
							}
						} else {
							return "Datos incorrectos, intente nuevamente";
						}
					} else {
						return "Datos incorrectos, intente nuevamente";
					}
				} else {
					return "Datos incorrectos, intente nuevamente";
				}
			} else {
				return "Datos incorrectos, intente nuevamente";
			}
		} else {
		return "Lo sentimos, esta cuenta no existe";
		}
	}

	public String pagarEfectivo() {
		Random random = new Random();
		return "Acerquese a la caja #" + random.nextInt(5) +
		" para cancelar";
	}

	//Metodos get
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

    public ArrayList<Sucursal> getRuta() {
        return ruta;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

	public boolean isPagoContraentrega() {
		return pagoContraentrega;
	}
	
	public boolean isEntregaEnSucursal() {
		return entregaEnSucursal;
	}

    public double getPrecioTotal() {
        return precioTotal;
    }

    public LocalDateTime getFechaDeEnvio() {
        return fechaDeEnvio;
    }

    public estado getEstado() {
        return estado;
    }

	//Metodos set
	public void setVehiculo(Transporte vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public void setSucursalLlegada(Sucursal sucursalLlegada) {
		this.sucursalLlegada = sucursalLlegada;
	}

    public void setRuta(ArrayList<Sucursal> ruta) {
        this.ruta = ruta;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPagoContraentrega(boolean pagoContraentrega) {
        this.pagoContraentrega = pagoContraentrega;
    }

    public void setEntregaEnSucursal(boolean entregaEnSucursal) {
        this.entregaEnSucursal = entregaEnSucursal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setEstado(estado estado) {
        this.estado = estado;
    }

}
	class EventosAleatorios {
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
