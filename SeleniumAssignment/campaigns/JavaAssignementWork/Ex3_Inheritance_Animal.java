package JavaAssignementWork;

 class Animal {


	String animalName;
	String animalType;

	Animal(String animalName,String animalType)
	{
		this.animalName=animalName;
		this.animalType=animalType;
	}
	void sound()
	{
		System.out.println("AnimalSound");
	}
	
	public void eat()
	{
		System.out.println("Animal food");
	}

}

class Dog extends Animal{
	Dog(String animalName, String animalType) {
		super(animalName, animalType);
	}

	public void sound()
	{
		System.out.println("Barks");
	}
	
}

class Tiger extends Animal{
	
	Tiger(String animalName, String animalType) {
		super(animalName, animalType);
		}

	public void sound()
	{
		System.out.println("Tiger Roars");
	}
}

class Lion extends Animal{
	Lion(String animalName, String animalType) {
		super(animalName, animalType);
		}

	public void sound()
	{
		System.out.println("Lion Roars");
	}
}

class Cat extends Animal{
	Cat(String animalName, String animalType) {
		super(animalName, animalType);
	}

	public void sound()
	{
		System.out.println("Meows");
	}
}


class mainClass{
	public static void main(String[] args)
	{
		Dog dogObject=new Dog("doggy", "Domestic");
		Lion lionObject=new Lion("leo", "Domestic");
		Tiger tigerObject=new Tiger("tiggu", "Domestic");
		Cat catObject=new Cat("catty", "Domestic");
		
	}
}