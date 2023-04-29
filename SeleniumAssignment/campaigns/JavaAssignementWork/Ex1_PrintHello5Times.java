package JavaAssignementWork;

import java.util.Scanner;

public class Ex1_PrintHello5Times {
	
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		int numberOfTimesToPrint= scan.nextInt();
		String textToBePrinted=scan.nextLine();
		
		for(int i=0;i<numberOfTimesToPrint;i++)
		{
			System.out.println(textToBePrinted);
		}
	}

}
