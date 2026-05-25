import java.util.Random;

public class NumeroPi {

    public static double generarNumeroPi(long pasos) {
        long aciertos = 0;
        Random rand = new Random();

        for (long i = 0; i < pasos; i++) {
            double x = -1 + 2 * rand.nextDouble(); // [-1, 1]
            double y = -1 + 2 * rand.nextDouble();

            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }

        return 4.0 * aciertos / pasos;
    }
}
