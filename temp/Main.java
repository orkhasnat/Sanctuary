import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		do
		{
			System.out.println("");
			System.out.println("1. Sign In.");
			System.out.println("2. Sign Up.");
			System.out.println("3. Search.");
			System.out.println("4. Exit.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 4;

			if(choice == 1) signin();
			else if(choice == 2) signup();
			else if(choice == 3) search();
		} while(choice != 0);
	}

	static void signin()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("1. As Student.");
		System.out.println("2. As Owner.");
		System.out.println("3. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 3;
		
		if(choice == 1)
		{
			Student p = Student.signin();
			if(p != null) p.view();
			else Global.notify("SIGN IN FAILED!");
		}
		
		else if(choice == 2)
		{
			Owner p = Owner.signin();
			if(p != null) p.view();
			else Global.notify("SIGN IN FAILED!");
		}
	}

	static void signup()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("1. As Student.");
		System.out.println("2. As Owner.");
		System.out.println("3. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 3;
		
		if(choice == 1)
		{
			System.out.println("");
			Student p = new Student();
			Global.AllStudents.put(p.id, p);
			p.view();
		}
		
		else if(choice == 2)
		{
			System.out.println("");
			Owner p = new Owner();
			Global.AllOwners.put(p.username, p);
			p.view();
		}
	}

	static void search()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("1. Search Flat.");
		System.out.println("2. Search Owner.");
		System.out.println("3. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 3;
		
		if(choice == 1)
		{
			Owner p;
			String user;

			System.out.println("");
			System.out.print("Username: ");
			user = scan.next();

			if(Global.AllOwners.containsKey(user))
			{
				p = Global.AllOwners.get(user);
				p.display();
			}

			else Global.notify("OWNER NOT FOUND!");
		}
		
		else if(choice == 2)
		{
			Flat f;

			int temp;

			System.out.println("");
			System.out.print("Flat ID: ");
			temp = scan.nextInt();

			if(Global.AllFlats.containsKey(temp))
			{
				f = Global.AllFlats.get(temp);
				f.display();
			}
			else Global.notify("FLAT NOT FOUND!");
		}
	}
}