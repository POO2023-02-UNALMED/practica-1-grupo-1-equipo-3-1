package basedatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import administracion.Sucursal;

public class Serializador {
	private static File rutaTemp = new File("src\\basedatos\\temp");
	
	/* 
 	Este metodo es el encargado de serializar las listas 
	que estan creadas en la clases.
	*/
	private static void serializar(Sucursal scrsl) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		File[] docs = rutaTemp.listFiles();
		PrintWriter pw;
	}

}
