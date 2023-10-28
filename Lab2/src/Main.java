import java.util.Scanner;
import java.util.Random;

/**
 * My lovely class (to see - CTRL+Q)
 * CTRL+Shift+A -> Javadoc
 */
public class Main {
    public static void main(String[] args){

        // Declare/Initialize
        Scanner myScanner = new Scanner(System.in);
        float MAX = 50;
        float MIN = -50;

        System.out.print("Enter number of rows in matrix A: ");
        int rowsA = myScanner.nextInt();

        System.out.print("Enter number of columns in matrix A " +
                            "and number of rows in matrix B: ");
        int crsAB = myScanner.nextInt();

        System.out.print("Enter number of rows in matrix B: ");
        int colsB = myScanner.nextInt();

        // Declare matrix
        float[][] matrixA = new float[rowsA][crsAB];
        float[][] matrixB = new float[crsAB][colsB];
        float[][] matrixC;

        //Fill matrix A
        fillMatrixRand(matrixA, MIN, MAX);
        fillMatrixRand(matrixB, MIN, MAX);

        //Print Matrix A
        printMatrix(matrixA, "A");

        //Print Matrix B
        printMatrix(matrixB, "B");

        //Multiply matrices C=AxB
        matrixC = multiplyMatrices(matrixA, matrixB);

        //Print Matrix C
        printMatrix(matrixC, "C");

        // Find Sum of min elements in each row
        sumMinRowElements(matrixC);

    }

    static void fillMatrixRand(float[][] matrix, float min, float max){
        int rows = matrix.length;
        int cols = matrix[0].length;
        Random myRandom = new Random();

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                matrix[i][j] = myRandom.nextFloat(max - min) + min;
            }
        }
    }

    static void printMatrix(float[][] matrix, String matrixName){
/*
  @params: matrix float[][]
 */
        System.out.println("\nMatrix" + matrixName + " : ");

        for (float[] floats : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                // System.out.printf(ints[j] + " ");
                System.out.printf("%" + 15 + ".4f", floats[j]);
            }
            System.out.print('\n');
        }
    }

    static float[][] multiplyMatrices(float[][] matrix1, float[][] matrix2){
        int rows1 = matrix1.length;
        int cols2 = matrix2[0].length;
        int cols1rows2 = (matrix1[0].length == matrix2.length) ? matrix1[0].length : 0;

        if (cols1rows2 == 0) {
            throw new IllegalArgumentException("Matrix 1 column number " +
                        "is not equal to row number of matrix 2");
        }
        float[][] matrix3 = new float[rows1][cols2];
        float tempSum;

        for (int i = 0; i < rows1; i++){
            for (int j = 0; j < cols2; j++){
                tempSum = 0;
                for (int k = 0; k < cols1rows2; k++){
                    tempSum += matrix1[i][k] * matrix2[k][j];
                }
                matrix3[i][j] = tempSum;
            }
        }
        return matrix3;
    }

    static void sumMinRowElements(float[][] matrix){

        float minElemSum = 0;
        float[] minElements = new float[matrix.length];

        for (int i = 0; i < matrix.length; i++){
            minElements[i] = matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++){
                if (matrix[i][j] < minElements[i]) {
                    minElements[i] = matrix[i][j];
                }
            }
        }

        System.out.print("\nMinimal elements in rows of Matrix C: [");
        for (float elem : minElements){
            minElemSum += elem;
            System.out.print(elem + " ");
        }
        System.out.print("]\n");

        System.out.println("Their Sum is " + minElemSum);

    }
    
}
