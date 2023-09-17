/**
 * For test cases
 * 20
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
 * 10
 * and 
 * 10
 * 0 1 2 3 4 5 6 7 8 9
 * 5
 * Result is "Technique 1 is better"
 */
import java.util.Scanner;

public class ExperimentShuffle {
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        // take array input
        int n = cin.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = cin.nextInt();
        }
        // run this experiment "tries" times
        int tries, score = 0;
        tries = cin.nextInt();
        while (tries > 0) {
            int tech1 = shuffleAndReturnSimilarityIndexTech1(arr.clone(), arr);
            int tech2 = shuffleAndReturnSimilarityIndexTech2(arr.clone(), arr);
            if (tech1 > tech2)
                score--; // tech2 wins
            else
                score++; // tech1 wins

            tries--;
        }
        System.out.println("Tech " + (score > 0 ? "1" : score < 0 ? "2" : "none") + " is better");
        cin.close();
    }

    static int shuffleAndReturnSimilarityIndexTech1(int[] arr, int[] orgArr) {
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * (arr.length - i));
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        return getSimilarityIndex(arr, orgArr);
    }

    static int shuffleAndReturnSimilarityIndexTech2(int[] arr, int[] orgArr) {
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * (arr.length - i));
            int temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[index];
            arr[index] = temp;
        }
        return getSimilarityIndex(arr, orgArr);
    }

    static int getSimilarityIndex(int[] arr, int[] orgArr) {
        int similarityIndex = 0;
        // printArr(arr);
        // printArr(orgArr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == orgArr[i])
                similarityIndex++;
        }
        // System.out.println(similarityIndex);
        // printDashedLine();
        return similarityIndex;
    }

    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void printDashedLine() {
        for (int i = 0; i < 10; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}