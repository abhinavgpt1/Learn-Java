class BANK {
	int SB, CB;

	void setBal(int S, int C) {
		SB = S;
		CB = C;
	}

	void Party(BANK Rref) {
		SB = SB + Rref.SB;
		CB = CB + Rref.CB;
	}

	void show() {
		System.out.println(SB + "" + CB);
	}

	BANK Party2(BANK Aref) {
		BANK pitaji = new BANK();
		pitaji.SB = SB + Aref.SB;
		pitaji.CB = CB + Aref.CB;
		return pitaji;
	}
};

class PRRR15 {
	public static void main(String[] args) {
		BANK amn = new BANK();
		amn.setBal(1, 2);
		BANK rmn = new BANK();
		rmn.setBal(3, 4);
		amn.Party(rmn);
		BANK chmn = rmn.Party2(amn);
		chmn.show();
	}
}
