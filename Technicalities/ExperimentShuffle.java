/**
 * For test cases
 * ---------------TC1-------------
 * 20
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
 * 10
 * ---------------TC2-------------
 * 10
 * 0 1 2 3 4 5 6 7 8 9
 * 5
 * Result is Technique 1/2 is better - both almost same for tries at 100
 */
import java.util.Scanner;

public class Sample {
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
            int tech1SimilarityMatch = shuffleAndReturnSimilarityIndexTech1(arr.clone(), arr);
            int tech2SimilarityMatch = shuffleAndReturnSimilarityIndexTech2(arr.clone(), arr);
//            System.out.println("tech1: " + tech1SimilarityMatch + " tech2: " + tech2SimilarityMatch); // -6
            if (tech2SimilarityMatch < tech1SimilarityMatch)
                score--; // tech2 wins
            else if(tech1SimilarityMatch < tech2SimilarityMatch)
                score++; // tech1 wins

            tries--;
        }
        System.out.println("score: " + score);
        System.out.println("Tech " + (score > 0 ? "1" : score < 0 ? "2" : "none") + " is better");
        cin.close();
    }

    static int shuffleAndReturnSimilarityIndexTech1(int[] arr, int[] orgArr) {
        // this is not good, as the chances of same number getting fixated at the same
        // from the end of array till [0] is high
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * (arr.length - i));
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        return getSimilarityIndex(arr, orgArr);
    }

    static int shuffleAndReturnSimilarityIndexTech2(int[] arr, int[] orgArr) {
        // this is better as it pushes/assigns a random number to the end of array,
        // and never picks it during randomIndex generation
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
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == orgArr[i])
                similarityIndex++;
        }
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