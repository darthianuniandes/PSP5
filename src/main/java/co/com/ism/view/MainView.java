package co.com.ism.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import co.com.ism.model.TableIntegrate;

/**
 * @author ian
 * Clase controladora del proceso de calculo de la integracion
 * Se encarga de obtener los datos del archivo de entrada, 
 * invocar el calculo de la integral e imprimir los resultados
 */
public class MainView {
	
	private SimpsonIntegrator simpsonIntegrator; 
	private List<TableIntegrate> table;
	private String numeros;
	
	/**
	 * @author ian
	 * Se obtiene la informacion del archivo dado
	 * Si no se tiene un archivo dado por el usuario se usa el archivo por defecto 
	 * @param archivo provee el directorio provisto por el usuario
	 */
	public void leerArchivo(String[] archivo) {
		if(archivo != null && archivo.length > 0){
			leerArchivo(archivo[0]);
		} else {
			leerArchivo("./numeros.txt");			
		}
	}
	
	private void leerArchivo(String archivo) {
		BufferedReader in = null;
		FileInputStream fis = null;
		StringBuilder lectura;

		try {
			fis = new FileInputStream(archivo);
			in = new BufferedReader(new InputStreamReader(fis));

			lectura = new StringBuilder();
			String sCurrentLine;

			while ((sCurrentLine = in.readLine()) != null) {
				lectura.append(sCurrentLine);
			}
			numeros = lectura.toString();
		} catch (IOException e) {
			System.out.println("\n---El directorio ingresado es invalido. Por favor valide la ubicacion del"
					+ " archivo numeros.txt segun lo indica el archivo README---\n");
			System.exit(0);
		} finally {
			try {
				if (in != null)
					in.close();
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				System.out.println("Hubo un error al intentar cerrar los recursos abiertos");
				System.exit(0);
			}
		}
	}
	
	/**
	 * @author ian
	 * Metodo que separa la informacion almacenada en el archivo dado para analizar
	 * Se hace la separacion de la informacion segun un formato especifico
	 * 
	 */
	public void obtenerValoresArchivo() {
		List<String> tests = Arrays.asList(numeros.split(";"));
		table = new LinkedList<TableIntegrate>(); 
		for (String testIterator : tests) {
			List<String> array = Arrays.asList(testIterator.split(","));
			TableIntegrate t = new TableIntegrate();
			try {
				t.setP(Float.valueOf(array.get(0).trim()));
				t.setDuff(Integer.valueOf(array.get(1).trim()));
			} catch (java.lang.NumberFormatException ne){
				t.setP((float) 0);
				t.setDuff(0);
				System.out.println("Se encontraron inconsistencias en el archivo de entrada. "
						+ "Por favor ingrese unicamente valores numericos siguiendo la estructura "
						+ "sugerida en el archivo README");
			}
			table.add(t);
		}
	}
	
	/**
	 * @author ian
	 * Metodo que hace el calculo de la integral para cada registro ingresado en el archivo de entrada
	 */
	public void integrarSimpsons(){
		for (TableIntegrate ti : table){
			simpsonIntegrator = new SimpsonIntegrator(ti.getP(), ti.getDuff());
			ti.setX(simpsonIntegrator.calcularSimpson());
		}
	}
	/**
	 * @author ian
	 * Metodo que imprime los resultados de la integral para cada registro del archivo de entrada
	 */
	public void imprimirResultados(){
		StringBuilder reporte = new StringBuilder();
		reporte.append("______________________________________________\n");
		reporte.append("|Test               |    Expected Values      |\n");
		reporte.append("______________________________________________\n");
		reporte.append("|   p    |   dof    |            x            |\n");
		reporte.append("______________________________________________\n");
		DecimalFormat five = new DecimalFormat("#0.00000");
		for (TableIntegrate ti : table){
			reporte.append("| "+five.format(ti.getP())+" |  "+five.format(ti.getDuff())+"  |       "+five.format(ti.getX())+"         |\n");
			reporte.append("_______________________________________________\n");
		}
		System.out.println(reporte.toString());
				
	}

}
