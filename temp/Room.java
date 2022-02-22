import java.util.Scanner;

abstract class Room
{
	int id;
	String name;
	double area, tiles;

	Room()
	{
		int id = Global.random(0, 99999);

		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.print("Area in square feet: ");
		area=scan.nextDouble();

		System.out.println("Tiles:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		tiles = temp%2;
		tiles *= 1.15;

		if(tiles==0)
		{
			System.out.println("Mosaic:");
			System.out.println("1. Yes.");
			System.out.println("2. No.");
			temp = scan.nextInt();
			tiles = temp%2;
			if(tiles == 0) tiles = 1;
			else tiles *= 1.05;
		}
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
}