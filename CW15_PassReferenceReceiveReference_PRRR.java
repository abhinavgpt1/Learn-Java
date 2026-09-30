class BANK {
	int SB, CB;

	void setBal(int S, int C) {
		SB = S;
		CB = C;
	}

	void Party(BANK ramanRef) {
		SB = SB + ramanRef.SB;
		CB = CB + ramanRef.CB;
	}

	void show() {
		System.out.println(SB + "" + CB);
	}

	BANK Party2(BANK amanRef) {
		BANK pitaji = new BANK();
		pitaji.SB = SB + amanRef.SB;
		pitaji.CB = CB + amanRef.CB;
		return pitaji;
	}
};

class CW15_PassReferenceReceiveReference_PRRR {
	public static void main(String[] args) {
		BANK aman = new BANK();
		aman.setBal(1, 2);
		BANK raman = new BANK();
		raman.setBal(3, 4);
		aman.Party(raman);
		BANK chmn = raman.Party2(aman);
		chmn.show();
	}
}
