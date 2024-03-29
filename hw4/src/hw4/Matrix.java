
package hw4;

public class Matrix {
    private int[][] matrix;
    private int row;
    private int column;

    public Matrix(int row, int column) {
        this.row = row;
        this.column = column;
        this.matrix = new int[row][column];
    }

    public void Set(int row, int column, int value) {
        this.matrix[row][column] = value;
    }
    public int Get(int row, int column) {
        return this.matrix[row][column];
    }

    public void Print() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.printf("%d ", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void GaussianElimination() {
        for (int i = 0; i < this.row; i++) {
            // Find the pivot
            int pivot = -1;
            for (int j = 0; j < this.column; j++) {
                if (this.matrix[i][j] != 0) {
                    pivot = j;
                    break;
                }
            }
            if (pivot == -1) {
                continue;
            }

            // Make the pivot 1
            int pivotValue = this.matrix[i][pivot];
            for (int j = 0; j < this.column; j++) {
                this.matrix[i][j] /= pivotValue;
            }

            // Make the other elements in the column 0
            for (int j = 0; j < this.row; j++) {
                if (j == i) {
                    continue;
                }
                int factor = this.matrix[j][pivot];
                for (int k = 0; k < this.column; k++) {
                    this.matrix[j][k] -= factor * this.matrix[i][k];
                }
            }
        }
    }
}