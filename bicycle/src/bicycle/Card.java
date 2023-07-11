package bicycle;

public class Card {
	String Suit;
	String Class;
	int value;
	int BaseValue;
	int HoldValue = 0;
	int pointValue = 0;
	
	Card(String A, String B, int C, int D){
		Suit = A;
		Class = B;
		value = C;
		BaseValue = D;
	}
	
	public String toString() {
		return this.Class + " of " + this.Suit + "'s";
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void Build(int A) {
		this.value = value + A;
	}
	
	public int getHoldValue() {
		return this.HoldValue;
	}
	
	public void Held() {
		this.HoldValue +=1;
	}
	public void PointValue(int A) {
		pointValue = A;
	}
	public int getPoints() {
		return this.pointValue;
	}	
}
