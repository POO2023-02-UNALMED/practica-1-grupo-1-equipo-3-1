package basedatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import administracion.Sucursal;
import transportes.Transporte;
import personas.Persona;
import administracion.CuentaBancaria;

public class Serializador {
	private static File rutaTemp = new File("C:/Users/Tomás Gómez/Documents/Github/practica-1-grupo-1-equipo-3-1/practicaGrupo3/src/basedatos/temp");

	/*
 	Este metodo es el encargado de serializar las listas
	que estan creadas en la clases.
	*/
	public static void serializar() {
		FileOutputStream rutaArchivo;
		ObjectOutputStream fichero_objeto;
		File[] ficheros = rutaTemp.listFiles();
		PrintWriter pw;
		// CON PRINTWRITER BORRAMOS EL CONTENIDO PARA EVITAR SOBREESCRITURAS
				for (File archivo : ficheros) {
					try {
						//BORRA LO QUE HAY EN EL ARCHIVO QUE LE PASAMOS COMO PARAMETRO
						pw = new PrintWriter(archivo);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}

				//RECORREMOS LOS ARCHIVOS QUE ESTAN EN LA LISTA ficheros
				for (File archivo1 : ficheros) {

					//VERIFICA SI LA RUTA DEL ARCHIVO CONTIENE LA PALABRA SUCURSALES(DONDE ALMACENAREMOS LA LISTA DE SUCURSALES Y TODA SU INFO)
					if (archivo1.getAbsolutePath().contains("Sucursales")) {
						try {
							//APORTA LA INFORMACION PARA IDENTIFICAR EL FICHERO
							rutaArchivo = new FileOutputStream(archivo1);
							//PROCESA OBJETOS JAVA Y SE VINCULA A UN OBJETO DE LA CLASE FileOutputStream
							fichero_objeto = new ObjectOutputStream(rutaArchivo);
							//CODIFICA LA LISTA QUE CONTIENE LAS SUCURSALES Y LAS GUARDA EN EL ARCHIVO AL QUE ESTA VINCULADO fichero_objeto
							fichero_objeto.writeObject(Sucursal.getTodasLasSucursales());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block e.printStackTrace();
							e.printStackTrace();
						}

					//VERIFICA SI LA RUTA DEL ARCHIVO CONTIENE LA PALABRA TRANSPORTES(DONDE ALMACENAREMOS LA LISTA DE TRANSPORTES Y TODA SU INFO)
					//SE COMPORTA DE IGUAL FORMA QUE EL ANTERIOR, PERO SERIALIZANDO UNA LISTA DE TRANSPORTES
					}else if(archivo1.getAbsolutePath().contains("Transportes")) {
						try {
							rutaArchivo = new FileOutputStream(archivo1);
							fichero_objeto = new ObjectOutputStream(rutaArchivo);
							fichero_objeto.writeObject(Transporte.getTodosLosTransportes());
						}catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block e.printStackTrace();
							e.printStackTrace();
						}

					}else if(archivo1.getAbsolutePath().contains("Personas")) {
						try {
							rutaArchivo = new FileOutputStream(archivo1);
							fichero_objeto = new ObjectOutputStream(rutaArchivo);
							fichero_objeto.writeObject(Persona.getTodasLasPersonas());
						}catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block e.printStackTrace();
							e.printStackTrace();

						}
					}else if(archivo1.getAbsolutePath().contains("CuentasBancarias")) {
						try {
							rutaArchivo = new FileOutputStream(archivo1);
							fichero_objeto = new ObjectOutputStream(rutaArchivo);
							fichero_objeto.writeObject(CuentaBancaria.getTodasLasCuentas());
						}catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block e.printStackTrace();
							e.printStackTrace();

						}
					}
				}
	}
}


