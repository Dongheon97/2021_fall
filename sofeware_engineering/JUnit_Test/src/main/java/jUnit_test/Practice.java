package jUnit_test;

public class Practice {
	
	public boolean isPrimeNumber(int givenNumber) {
		for(int i=2; i<givenNumber; i++) {
			if((givenNumber%i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int sum(int givenNumber) {
		int total = 0;
		int last = givenNumber;
		if(givenNumber<0) {
			last = -givenNumber;
		}
		for(int i=1; i<=last; i++) {
			total = total+i;
		}
		if(givenNumber<0) {
			total = -total;
		}
		return total;
	}
}
