
public class Function {

	int a;
	int b;
	int c;
	int d;
	
	int x;
	
	Function(int a, int b, int c, int d){
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;	
		
	}
	
	public int getValueForX(int x){
		return (int)((a*(int)(Math.pow(x, 3))+(b*(int)Math.pow(x, 2))+(c*x)+d));
	}
	
}