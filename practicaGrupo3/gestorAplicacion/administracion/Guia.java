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
	private boolean pagoMitad;
	private boolean entregaEnSucursal;
	private double precioTotal;
	private LocalDateTime fechaDeEnvio;
	//private LocalDateTime fechaDeLlegada;
	private estado estado;
	private tipoDePago tipoDePago;

	public enum tipoDePago {
		REMITENTE,
		FRACCIONADO,
		DESTINATARIO
	}

	public enum estado{
		ENTRANSITO,
		ENESPERA,
		ENTREGADO
	} 

	//Constructor
	public Guia(Producto producto,  Cliente remitente, Destinatario destinatario,  Sucursal sucursalOrigen, Sucursal sucursalLlegada, tipoDePago tipoDePago, boolean entregaEnSucursal) {
		this.producto = producto;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalLlegada = sucursalLlegada;
		this.tipoDePago = tipoDePago;
		this.entregaEnSucursal = entregaEnSucursal;
		this.fechaDeEnvio = LocalDateTime.now();

		precioTotal = producto.getCostoDelPedido();

		asignarRuta();
	}
	
	//métodos
	// Método para calcular la distancia entre dos puntos
	//FUNCIONA
	public static double calcularDistancia(Sucursal origen, Sucursal destino) {
		double x = destino.getLongitud() - origen.getLongitud();
		double y = destino.getLatitud() - origen.getLatitud();
		double magnitud = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return magnitud;
	}

	
	//Antihoraria
	//FUNCIONA
	public void asignarRuta() {
		ArrayList<Sucursal> sucursales = Sucursal.getTodasLasSucursales(); //La lista sería [Medellin, Cali, Pasto, Florencia, Bogotá]
		//Si quiero ir de Pasto a Bogotá el atributo ruta sería = [Pasto, Florencia, Bogotá]
		//Si es de Cali a Medellin = [Cali, Pasto, Florencia, Bogota, Medellin]
		//¿Como hacemos este Metodo?
		for (int i = 0; i < sucursales.size(); i++) { //Recorre la lista de sucursales
			if (sucursales.get(i) == sucursalOrigen) { //Para en la sucursal que es igual a la de origen
				if (i < sucursales.indexOf(sucursalLlegada)) { //Si la sucursal de llegada está después de la de origen en la lista 
					//Ejemplo Pasto a Bogotá [Medellin, Cali, (Pasto), Florencia, (Bogotá)]
					for (int j = i; j < sucursales.indexOf(sucursalLlegada) + 1; j++) {//Agrega a ruta desde la sucursal de origen hasta la de llegada
						ruta.add(sucursales.get(j));
					}
					//Resultado esperado [Pasto, Florencia, Bogotá]
				} else { //Si la sucursal de llegada está antes en la lista que la de origen (paila está más difícil)
					//Ejemplo Cali a medellin [(Medellin), (Cali), Pasto, Florencia, Bogotá]
					for (int j = i; j < sucursales.size(); j++) { //Se agregan la sucursal origen y las que le siguen en la lista ruta
						ruta.add(sucursales.get(j)); //[Cali, Pasto, Florencia, Bogotá]
					}

					for (int k = 0; k < sucursales.indexOf(sucursalLlegada) + 1; k++) { //Termina agregando desde el comienzo de la lista sucursales hasta la sucursal de llegada (incluyendola)
						ruta.add(sucursales.get(k)); //[Medellin]
					}
					//Resultado esperado [Cali, Pasto, Florencia, Bogota, Medellin]
				}
			}
		}
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
	
	//Revisar scanner
	public String pagar(int entrada) {
		switch (entrada) {
			case 1:
				Scanner scanner1 = new Scanner(System.in);
				String titular = scanner1.nextLine();
				long numero = scanner1.nextLong();
				int cvv = scanner1.nextInt();
				scanner1.nextLine();
				String fechaExpiracion = scanner1.nextLine();
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
	
	//Revisar Scanner
	//FUNCIONA
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

	//FUNCIONA
	public String pagarEfectivo() {
		Random random = new Random();
		int numeroAleatorio = random.nextInt(5) + 1;
		return "Acerquese a la caja #" + numeroAleatorio +
		" para cancelar";
	}

	/*public String toString() {
		String format = "| %-16s | %-18s | %-15s | %-16s | %-14s |\n";
		StringBuilder tabla = new StringBuilder();
		tabla.append("+------------------+--------------------+-----------------+------------------+----------------+\n");
		tabla.append("|  Código Paquete  |  Tipo de Producto  |  Ciudad Origen  |  Ciudad Destino  |  Precio Total  |\n");
		tabla.append("+------------------+--------------------+-----------------+------------------+----------------+\n");
		tabla.append(String.format(format, String.valueOf(producto.getCodigo()), String.valueOf(producto.getClass().getSimpleName()), String.valueOf(sucursalOrigen.getNombre()), String.valueOf(sucursalLlegada.getNombre()), String.valueOf(precioTotal) + "$"));
		tabla.append("+------------------+--------------------+-----------------+------------------+----------------+\n");
		return tabla.toString();
	}

	 */
	public String toString() {
		String format = "| %-18 | %-18 |\n";
		StringBuilder tabla = new StringBuilder();
		tabla.append("+--------------------+--------------------+");
		tabla.append(String.format(format, "Código Paquete"))
		tabla.append("+--------------------+--------------------+");
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

	public Guia.tipoDePago getTipoDePago() {
		return tipoDePago;
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

	public void setTipoDePago(tipoDePago tipoDePago) {
		this.tipoDePago = tipoDePago;
	}
}