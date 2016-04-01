package application;
public class ourStrategy implements strategy{
	//function to retrieve advice from stock software
	public String getAdvice(int last, int close, String s){
		int random = (int)(Math.random()*2);
		if(random == 0){
			return "BUY";
		}
		else if(random == 1){
			return "SELL";
		}
		else{
			return "HOLD";
		}
	}
}
