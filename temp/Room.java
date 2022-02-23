import java.util.Scanner;

abstract class Room
{
	int id;
	String name;
	double area, tiles;

	void getArea()
	{
		Scanner scan = new Scanner(System.in);

		System.out.print("Area in square feet: ");
		area = scan.nextDouble();
	}

	void updateName()
	{
		Scanner scan = new Scanner(System.in);

		System.out.print("Name: ");
		name = scan.nextLine();
	}

	boolean getAns(String str)
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println(str);
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();

		return (temp%2 != 0);
	}

	void updateTiles()
	{
		if(getAns("Tiles:")) tiles = 1.15;
		else if(getAns("Mosaic:")) tiles = 1.05;
		else tiles = 1;
	}

	double value()
	{
		return area*tiles;
	}

	void display()
	{
		System.out.println("Name: " + name);
		// Display Flat Info
		System.out.println("Area " + area);
		System.out.print("Floor: "); if(tiles == 1) System.out.println("Plain"); else if(tiles == 1.05) System.out.println("Mosaic"); else System.out.println("Tiles");
	}

	void view()
	{
		display();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println("");
			System.out.println("1. Edit Room.");
			System.out.println("2. Delete Room.");
			System.out.println("3. Done.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 3;

			if(choice == 1) edit();

			else if(choice == 2)
			{
				delete();
				return;
			}
		} while(choice != 0);
	}

	abstract void edit();

	void delete()
	{
		Global.AllRooms.remove(id);

		// DELETE FROM Room -- This should delete from the respective table
		// WHERE RoomID = id;

		name = "Deleted Room";
	}
}