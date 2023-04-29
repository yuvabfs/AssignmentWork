package JavaAssignementWork;

import java.util.Scanner;

public class Ex1_DefineIntegerSum {
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		int sum=0;
	
		int integerToFindSumOfItsDigits=scan.nextInt();
		
		for(int i=integerToFindSumOfItsDigits; i>0;i=i/10)
		{
			sum=sum+i%10;
			
		}
		System.out.println(sum);
	}

}

