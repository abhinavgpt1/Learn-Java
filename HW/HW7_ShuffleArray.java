// Shuffling of array
public class HW7_ShuffleArray {
	public static void main(String[] args) {
		int a[] = { 1, 6, 21, 32, 55, 89 };
		shuffle(a);
		show(a);
	}

	static void shuffle(int[] ref) {

		for (int i = 0; i < ref.length; i++) {
			int index = (int) (Math.random() * (ref.length - i));
			int temp = ref[i];
			// Assign it to ref[ref.length - i - 1] for better shuffle
			// see Technicalities > tech14_ShuffleArrayTechniques_experiment.java
			ref[i] = ref[index];
			ref[index] = temp;
		}
	}

	static void show(int ref[]) {
		for (int i = 0; i < ref.length; i++)
			System.out.println(ref[i]);
	}
}
