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
import personas.Persona;
import administracion.CuentaBancaria;
import productos.Producto;
import administracion.Guia;

public class Deserializador {

	private static File rutaTemp = new File("src\\basedatos\\temp");

	public static void deserializar() {

		File[] ficheros = rutaTemp.listFiles();

		FileInputStream archivo;
		ObjectInputStream guardado;

		//RECORREMOS LOS ARCHIVOS QUE ESTAN EN LA LISTA ficheros
		for (File file : ficheros) {

			//VERIFICA SI LA RUTA DEL ARCHIVO CONTIENE LA PALABRA SUCURSALES (DE DONDE EXTRAEREMOS LA LISTA  DE SUCURSALES Y TODA SU INFO)
			if (file.getAbsolutePath().contains("Sucursales")) {
				try {
					//SE LEE EL ARCHIVO sucursales.txt DE LA LISTA ficheros
					archivo = new FileInputStream(file);
					//PROCESA LOS DATOS CONTENIDOS EN EL OBJETO archivo Y SE VINCULA A EL
					guardado = new ObjectInputStream(archivo);
					//SE LEEN LOS OBJETOS EN EL MISMO ORDEN EN QUE HABIAN SIDO ESCRITOS Y
					//SE HACE EL CASTEO DEL APUNTADOR OBJECT A ArrayList<Sucursal>
					//ESTE ArrayList DE Sucursales SE ASIGNA AL ATRIBUTO DE CLASE TodasLasSucursales DE LA CLASE Sucursal
					Sucursal.setTodasLasSucursales((ArrayList<Sucursal>) guardado.readObject());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();;
				}

			//VERIFICA SI LA RUTA DEL ARCHIVO CONTIENE LA PALABRA TRANSPORTES(DE DONDE EXTRAEREMOS LA LISTA DE TRANSPORTES Y TODA SU INFO)
			//SE COMPORTA DE IGUAL FORMA QUE EL ANTERIOR, PERO DESERIALIZANDO UNA LISTA DE TRANSPORTES
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

			}else if (file.getAbsolutePath().contains("Personas")) {
				try {
				archivo = new FileInputStream(file);
				guardado = new ObjectInputStream(archivo);
				Persona.setTodasLasPersonas((ArrayList<Persona>) guardado.readObject());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();;
				}

			}else if (file.getAbsolutePath().contains("CuentasBancarias")) {
				try {
				archivo = new FileInputStream(file);
				guardado = new ObjectInputStream(archivo);
				CuentaBancaria.setTodasLasCuentas((ArrayList<CuentaBancaria>) guardado.readObject());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();;
				}

			}else if (file.getAbsolutePath().contains("Productos")) {
				try {
				archivo = new FileInputStream(file);
				guardado = new ObjectInputStream(archivo);
				Producto.setTodosLosProductos((ArrayList<Producto>) guardado.readObject());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();;
				}
			}else if (file.getAbsolutePath().contains("Guias")) {
				try {
				archivo = new FileInputStream(file);
				guardado = new ObjectInputStream(archivo);
				Guia.setTodasLasGuias((ArrayList<Guia>) guardado.readObject());
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
