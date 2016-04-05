package application;
public interface Strategy {
	//interface for retrieving info
	public String getAdvice(double last, double close, String tickSymbol);
}
