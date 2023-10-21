package personas;
// HEHCA POR: TOMAS MURILLO ARISTIZABAL
import administracion.*;

import productos.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
// CLASE PERSONA
// EN ESTA CLASE SE ALAMACENA TODA LA INFORMACION RECORRIDA DE LAS PERSONAS QUE INTERACTUAN EN EL PROGRAMA
// YA SEAN DESTINATRIO O EL CLIENTE, Y SUS RESPECTIVOS METODOS PARA ACCEDER ESA INFORMACION Y SOBREESCRIBIRLA


// CLASE ABSTRACTA
public abstract class Persona implements Serializable {
    protected String nombre;
    protected long cedula;
    protected CuentaBancaria cuentaBancaria;
    protected long telefono;
	protected Producto producto;
	private static ArrayList<Persona> todasLasPersonas = new ArrayList<>();
    private int reputacion;



    protected Persona() {
    }

    protected Persona(String nombre, long cedula, long telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;

		todasLasPersonas.add(this);
    }
    
    public static ArrayList<Persona> getTodasLasPersonas(){
    	return todasLasPersonas;
    }
    public static void setTodasLasPersonas(ArrayList<Persona> list) {
    	todasLasPersonas = list;
    }
 
    public void subirReputacion() {
        reputacion++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCedula() {
        return cedula;
    }

    public int getReputacion() {
        return reputacion;
    }

    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    // METODO ABSTRACTO QUE TODAS LAS SUBCLASES TIENEN QUE HERDAR
    public abstract String toString();
/*
	public String pagar(int entrada) {
		if (this instanceof Cliente) {
			asignarTipoDePago();

		} else {

		}
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


	public void asignarTipoDePago(int entrada) {
		Guia guia = null;
		for (Producto producto : Producto.getTodosLosProductos()) {
			if (producto.getGuia().getRemitente() == this) {
				guia = producto.getGuia();
				break;
			}
		}

		switch (entrada) {
			case 1: //Paga todo
				guia.setTipoDePago(Guia.tipoDePago.REMITENTE);
			case 2: //Pago fraccionado
				guia.setTipoDePago(Guia.tipoDePago.FRACCIONADO);
			case 3:
				guia.setTipoDePago(Guia.tipoDePago.REMITENTE);

		}
	}

	//Revisar Scanner
	//FUNCIONA
	public String pagarTarjeta(String titular, long numero, int cvv, String fechaExpiracion) {
		CuentaBancaria cuentaCliente = null;
		Guia guia = null;
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

							if (this instanceof Cliente) {
								for (Producto producto : Producto.getTodosLosProductos()) {
									if (producto.getGuia().getRemitente() == this) {
										guia = producto.getGuia();
										break;
									}
								}
							} else {
								for (Producto producto : Producto.getTodosLosProductos()) {
									if (producto.getGuia().getDestinatario() == this) {
										guia = producto.getGuia();
										break;
									}
								}
							}

							if (guia.getTipoDePago() == ) {
								Scanner scanner = new Scanner(System.in);
								int entrada = scanner.nextInt();
								scanner.close();
								if (Guia.confirmarPago(entrada)) {
									if (cuentaCliente.descontarSaldo(guia.getPrecioTotal())) {
										Sucursal.getCorreminas().aumentarSaldo(guia.getPrecioTotal());
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

*/
}
