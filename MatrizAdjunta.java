
    import java.io.*;
import java.util.*;

public class MatrizAdjunta {

    // Determinante por recursión (cofactores)
    public static double determinante(double[][] A) {
        int n = A.length;

        if (n == 1) return A[0][0];

        if (n == 2) {
            return A[0][0]*A[1][1] - A[0][1]*A[1][0];
        }

        double det = 0;
        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * A[0][col] * determinante(subMatriz(A, 0, col));
        }
        return det;
    }

    // Submatriz eliminando fila r y columna c
    public static double[][] subMatriz(double[][] A, int r, int c) {
        int n = A.length;
        double[][] M = new double[n-1][n-1];

        int rr = 0;
        for (int i = 0; i < n; i++) {
            if (i == r) continue;

            int cc = 0;
            for (int j = 0; j < n; j++) {
                if (j == c) continue;
                M[rr][cc] = A[i][j];
                cc++;
            }
            rr++;
        }
        return M;
    }

    // Matriz de cofactores
    public static double[][] cofactores(double[][] A) {
        int n = A.length;
        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = Math.pow(-1, i + j) * determinante(subMatriz(A, i, j));
            }
        }
        return C;
    }

    // Transpuesta
    public static double[][] transpuesta(double[][] A) {
        int n = A.length;
        double[][] T = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                T[j][i] = A[i][j];
            }
        }

        return T;
    }

    // Adjunta = transpuesta de cofactores
    public static double[][] adjunta(double[][] A) {
        return transpuesta(cofactores(A));
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             PrintWriter pw = new PrintWriter("output.txt")) {

            int n = Integer.parseInt(br.readLine().trim());
            double[][] A = new double[n][n];

            for (int i = 0; i < n; i++) {
                String[] parts = br.readLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    A[i][j] = Double.parseDouble(parts[j]);
                }
            }

            double[][] adj = adjunta(A);

            // Escribir matriz adjunta en output.txt
            Locale.setDefault(Locale.US);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j > 0) pw.print(" ");
                    pw.printf("%.6f", adj[i][j]);
                }
                pw.println();
            }

            System.out.println("Matriz adjunta calculada.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
