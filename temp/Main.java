import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice;
		Flat f;

		do
		{
			System.out.println("1. Add a Flat.");
			System.out.println("2. Exit.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 2;

			System.out.println("");
			if(choice == 1) f = new Flat();
		} while(choice == 1);
	}
}