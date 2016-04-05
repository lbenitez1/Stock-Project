package application;
public class ourStrategy implements Strategy{
	//function to retrieve advice from stock software
	public String getAdvice(double last, double close, String s){
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
