package basedatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


import java.io.PrintWriter;
 

// CLASES DE IMPORTACION PARA DESERIALIZAR
import administracion.Sucursal;
import transportes.Transporte;

public class Deserializador {
	
	private static File rutaTemp = new File("src\\basedatos\\temp");
	
	public static void deserializar() {
		
		File[] ficheros = rutaTemp.listFiles();
		
		FileInputStream archivo;
		ObjectInputStream guardado;
		
		//RECORREMOS LOS ARCHIVOS QUE ESTAN EN LA LISTA ficheros
		for (File file : ficheros) {
			
			//VERIFICA SI LA RUTA DEL ARCHIVO CONTIENE LA PALABRA Aerolineas(DE DONDE EXTRAEREMOS LA LISTA  DE AEROLINEAS Y TODA SU INFO)
			if (file.getAbsolutePath().contains("Sucursales")) {
				try {
					//SE LEE EL ARCHIVO Aerolineas.txt DE LA LISTA ficheros
					archivo = new FileInputStream(file);
					//PROCESA LOS DATOS CONTENIDOS EN EL OBJETO archivo Y SE VINCULA A EL
					guardado = new ObjectInputStream(archivo);
					//SE LEEN LOS OBJETOS EN EL MISMO ORDEN EN QUE HABIAN SIDO ESCRITOS Y 
					//SE HACE EL CASTEO DEL APUNTADOR OBJECT A ArrayList<Aerolinea>
					//ESTE ArrayList DE AEROLINEAS SE ASIGNA AL ATRIBUTO DE CLASE Aerolineas DE LA CLASE Aerolinea
					Sucursal.setTodasLasSucursales((ArrayList<Sucursal>) guardado.readObject());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();;
				}
				
			//VERIFICA SI LA RUTA DEL ARCHIVO CONTIENE LA PALABRA Alojamientos(DE DONDE EXTRAEREMOS LA LISTA DE ALOJAMIENTOS Y TODA SU INFO)
			//SE COMPORTA DE IGUAL FORMA QUE EL ANTERIOR, PERO DESERIALIZANDO UNA LISTA DE ALOJAMIENTOS
			}else if (file.getAbsolutePath().contains("Transportes")) {
				try {
				archivo = new FileInputStream(file);
				guardado = new ObjectInputStream(archivo);
				Transporte.setTodosLosTransportes((ArrayList<Transporte>) guardado.readObject());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();;
				}
			}
		}		
		
		
		
		
	}
}

