package laberintofake;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Raton {
    private char[][] laberinto;
    private ArrayList<String> movimientos;
    private boolean metaEncontrada;

    // Constructor
    public Raton(char[][] laberinto) {
        this.laberinto = laberinto;
        this.movimientos = new ArrayList<>();
        this.metaEncontrada = false;
    }

    // Resolución del laberinto
    public ArrayList<String> ejecutar() {
        try {
            leerLaberinto("laberinto.txt");
            resolverLaberinto();
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo");
        }
        return movimientos;
    }

    // Métodos:

    private void leerLaberinto(String archivo) throws IOException {
        ArrayList<char[]> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                filas.add(linea.toCharArray());
            }
        }
        laberinto = new char[filas.size()][];
        for (int i = 0; i < filas.size(); i++) {
            laberinto[i] = filas.get(i);
        }
    }

    private void resolverLaberinto() {
        int[] posicionInicial = encontrarPosicion('S');
        resolver(posicionInicial[0], posicionInicial[1]);
    }

    // Proceso de resolución
    private void resolver(int fila, int columna) {
        // Salida si ya se encontró la meta
        if (metaEncontrada) {
            return;
        }

        if (laberinto[fila][columna] == 'F') {
            movimientos.add(" M E T A ");
            metaEncontrada = true;
            return;
        }

        laberinto[fila][columna] = 'X';

     // Moverse
        if (movimientoValido(fila - 1, columna)) {
            movimientos.add("arriba");
            resolver(fila - 1, columna);
            if (metaEncontrada) return; 
            movimientos.remove(movimientos.size() - 1);
        }

        if (movimientoValido(fila + 1, columna)) {
            movimientos.add("abajo");
            resolver(fila + 1, columna);
            if (metaEncontrada) return; 
            movimientos.remove(movimientos.size() - 1); 
        }

        if (movimientoValido(fila, columna - 1)) {
            movimientos.add("izquierda");
            resolver(fila, columna - 1);
            if (metaEncontrada) return;
            movimientos.remove(movimientos.size() - 1); 
        }

        if (movimientoValido(fila, columna + 1)) {
            movimientos.add("derecha");
            resolver(fila, columna + 1);
            if (metaEncontrada) return; 
            movimientos.remove(movimientos.size() - 1); 
        }
    }

    private boolean movimientoValido(int fila, int columna) {
        if (fila >= 0 && fila < laberinto.length && columna >= 0 && columna < laberinto[0].length) {
            return laberinto[fila][columna] == '0' || laberinto[fila][columna] == 'F';
        }
        return false;
    }

    private int[] encontrarPosicion(char objetivo) {
        int[] posicion = new int[2];
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                if (laberinto[i][j] == objetivo) {
                    posicion[0] = i;
                    posicion[1] = j;
                    return posicion;
                }
            }
        }
        return null;
    }


}
