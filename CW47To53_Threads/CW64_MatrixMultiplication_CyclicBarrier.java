import java.util.concurrent.CyclicBarrier;

// Generated using AI
/**
 * Matrix multiplication using CyclicBarrier
 * Shows that CyclicBarrier CAN be used, though ThreadPool might be more convenient
 * 
 * Matrix A (2x3) * Matrix B (3x2) = Matrix C (2x2)
 * 
 * Issues with CyclicBarrier for matrix multiplication:
 * -----------------------------------------------------
 * The core issue is valid: CyclicBarrier requires knowing the exact number of threads upfront, which can be inflexible for matrix multiplication where thread count depends on matrix dimensions.
 * But CyclicBarrier CAN still be used: You can absolutely use CyclicBarrier if you pre-determine the thread count based on your matrix dimensions (e.g., one thread per result element, or per row).
 * ThreadPool is indeed better: A ThreadPool with submit() tasks and collecting Future objects is cleaner because you don't need to hardcode thread count—you just submit tasks and wait for them.
 */
public class CW64_MatrixMultiplication_CyclicBarrier {
    
    static class MatrixMultiplier implements Runnable {
        int[][] matrixA;
        int[][] matrixB;
        int[][] result;
        int row, col;
        CyclicBarrier barrier;
        
        public MatrixMultiplier(int[][] a, int[][] b, int[][] result, 
                               int row, int col, CyclicBarrier barrier) {
            this.matrixA = a;
            this.matrixB = b;
            this.result = result;
            this.row = row;
            this.col = col;
            this.barrier = barrier;
        }
        
        @Override
        public void run() {
            try {
                // Calculate the result[row][col]
                int sum = 0;
                int commonDimension = matrixA[0].length; // cols of A = rows of B
                
                for (int k = 0; k < commonDimension; k++) {
                    sum += matrixA[row][k] * matrixB[k][col];
                }
                
                result[row][col] = sum;
                System.out.println(Thread.currentThread().getName() + 
                                 " computed result[" + row + "][" + col + "] = " + sum);
                
                // Wait for all threads to complete their computation
                barrier.await();
                
                System.out.println(Thread.currentThread().getName() + 
                                 " passed the barrier, continuing...");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        // Matrix A: 2x3
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        
        // Matrix B: 3x2
        int[][] matrixB = {
            {7, 8},
            {9, 10},
            {11, 12}
        };
        
        // Result matrix C: 2x2
        int[][] result = new int[2][2];
        
        int rowsA = matrixA.length;      // 2
        int colsB = matrixB[0].length;   // 2
        int totalThreads = rowsA * colsB; // 4 threads
        
        // Create CyclicBarrier with known thread count
        CyclicBarrier barrier = new CyclicBarrier(totalThreads, () -> {
            System.out.println("\n=== All threads crossed the barrier ===\n");
        });
        
        System.out.println("Starting matrix multiplication with " + totalThreads + " threads\n");
        
        Thread[] threads = new Thread[totalThreads];
        int threadIndex = 0;
        
        // Create a thread for each result element
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                MatrixMultiplier multiplier = new MatrixMultiplier(
                    matrixA, matrixB, result, i, j, barrier
                );
                threads[threadIndex] = new Thread(multiplier, "T-" + (i + 1) + (j + 1));
                threads[threadIndex].start();
                threadIndex++;
            }
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        
        // Print result matrix
        System.out.println("\nResult Matrix (A * B):");
        printMatrix(result);
    }
    
    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
