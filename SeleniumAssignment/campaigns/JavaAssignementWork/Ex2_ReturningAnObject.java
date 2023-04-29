package JavaAssignementWork;

public class Ex2_ReturningAnObject {

	public static void main(String[] args) {
		Ex2_ReturningAnObject object1=new Ex2_ReturningAnObject();
		
		Ex2_ReturningAnObject newObject=object1.methodReturnsObject();

		System.out.println(object1);
		System.out.println(newObject);
	}
	
	public Ex2_ReturningAnObject methodReturnsObject() {
		
		System.out.println("Method returning Object");
		
		return new Ex2_ReturningAnObject();
	}

}
