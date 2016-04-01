package application;
import java.util.Date;
public class randomStrategy implements strategy{
	//function to retrieve advice from stock software
	public String getAdvice(int last, int close, String s){
		Date d = new Date(System.currentTimeMillis());
		System.out.println(d.toString().substring(11, 19));
		String hour = d.toString().substring(11, 13);
		String minute = d.toString().substring(14, 16);
		System.out.println(hour + " > "+ minute);
        Integer h = Integer.parseInt(hour.trim());
        Integer m = Integer.parseInt(minute.trim());	
		if(s.length() == 4){
			return "BUY";
		}
		else if(h>13 && (h<15 && m<22)){
			return "SELL";
		}
		else{
			return "HOLD";
		}
	}
}
