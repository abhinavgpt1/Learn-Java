class Professor {
	String name;
};

class Department {
	Professor HOD;

	void setHOD(Professor ref) {
		HOD = ref;
	}

	void showHOD() {
		System.out.println(HOD.name);
	}
};

class University {
	public static void main(String[] args) {
		Professor Prof = new Professor();
		Prof.name = "PKGPade";

		Department CSE = new Department();
		CSE.setHOD(Prof);
		CSE.showHOD();

		Department ECE = new Department();
		ECE.setHOD(Prof);
		ECE.showHOD();

		ECE = null; // department closed, but professor still exists so it's an aggregation
					// relationship
		CSE = null; // same as above
		System.out.println(Prof.name);

		/**
		 * Definitions:
		 * Aggregation: A relationship where one class (the whole) contains a reference
		 * to another class (the part), but the part can exist independently of the whole.
		 * 
		 * Composition: A relationship where one class (the whole) contains a reference
		 * to another class (the part), and the part cannot exist independently of the whole.
		 */
	}
}
