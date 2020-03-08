
public class SampleImpl implements SampleInterface {

	@Override
	public void printLines() {
		System.out.println("one");
		System.out.println("2");
		System.out.println("3");
		System.out.println("four");
	}
	
	public static void main(String[] args) {
		SampleInterface obj = new SampleImpl() ;
		obj.printLines();
	}

}
