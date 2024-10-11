package laberintofake;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Raton stuart = new Raton(null);
        ArrayList<String> movimientos = stuart.ejecutar();
        
        crearArchivo("movimientos.txt", movimientos);

        }
    
    	private static void crearArchivo (String archivo, ArrayList<String> movimientos) {
    		  try (FileWriter writer = new FileWriter(archivo)) {
    	            for (String movimiento : movimientos) {
    	                writer.write(movimiento + "\n");
    	            }
    	            System.out.println("Se ha creado un archivo con los movimientos para resolver el laberinto:  " + archivo );
    	            
    	        } catch (IOException e) {
    	            System.err.println("Error al guardar los movimientos en el archivo: ");
    	        }
    	}
    
}