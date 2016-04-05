package application;
public class strategyOne implements Strategy {
	public static final double fiveP = 0.05;
	//function to retrieve advice from stock software
	@Override
	public String getAdvice(double last, double close, String s){
		if((last*fiveP)+last < close){
			return "BUY";
		}
		else if((last*fiveP)+last > close){
			return "SELL";
		}
		else{
			return "HOLD";
		}
	}
}
