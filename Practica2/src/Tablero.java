import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Tablero {

    private static int DIMENSION = 30;
    private int[][] estadoActual = new int[DIMENSION][DIMENSION];
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

    // Leer desde fichero "matriz"
    public void leerEstadoActual() {
        try (BufferedReader br = new BufferedReader(new FileReader("matriz"))) {
            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null && i < DIMENSION) {
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = linea.charAt(j) - '0';
                }
                i++;
            }

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo");
        }

        calcularSiguiente();
    }

    // Generar tablero aleatorio
    public void generarEstadoActualPorMontecarlo() {
        Random rand = new Random();

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = (rand.nextDouble() < 0.5) ? 1 : 0;
            }
        }

        calcularSiguiente();
    }

    // Contar vecinas vivas
    private int contarVecinas(int fila, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) continue;

                int ni = fila + i;
                int nj = col + j;

                if (ni >= 0 && ni < DIMENSION && nj >= 0 && nj < DIMENSION) {
                    count += estadoActual[ni][nj];
                }
            }
        }

        return count;
    }

    // Calcular siguiente estado
    private void calcularSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {

                int vecinas = contarVecinas(i, j);

                if (estadoActual[i][j] == 1 && (vecinas == 2 || vecinas == 3)) {
                    estadoSiguiente[i][j] = 1;
                } else if (estadoActual[i][j] == 0 && vecinas == 3) {
                    estadoSiguiente[i][j] = 1;
                } else {
                    estadoSiguiente[i][j] = 0;
                }
            }
        }
    }

    // Pasar al siguiente estado
    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = estadoSiguiente[i][j];
            }
        }

        calcularSiguiente();
    }

    // Mostrar tablero
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(estadoActual[i][j] == 1 ? "x " : "  ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
