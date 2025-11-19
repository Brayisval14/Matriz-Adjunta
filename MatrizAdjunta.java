
    public class MatrizAdjunta {

    public static double[][] adjunta(double[][] m) {
        double[][] adj = new double[2][2];
        adj[0][0] = m[1][1];
        adj[1][1] = m[0][0];
        adj[0][1] = -m[0][1];
        adj[1][0] = -m[1][0];
        return adj;
    }

    public static void main(String[] args) {
        double[][] matriz = {{2, 1}, {5, 3}};
        double[][] adj = adjunta(matriz);

        for (double[] fila : adj) {
            for (double v : fila) System.out.print(v + " ");
            System.out.println();
        }
    }
}

