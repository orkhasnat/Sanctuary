import java.util.Scanner;

public class Bedroom extends Room
{
	Boolean lightsource, almirah;

	Bedroom(int FlatID)
	{
		super();
		id += 100000;
		name = "Bedroom";

		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println("Open Light Source:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lightsource = (temp%2 != 0);

		System.out.println("Attached Almirah:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		almirah = (temp%2 != 0);

		// INSERT INTO Bedroom (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 1, area, tiles, FlatID);
	}

	double value()
	{
		double temp = super.value();
		if(lightsource) temp *= 1.1;
		if(almirah) temp *= 1.02;

        return temp;
	}

	void display()
	{
		super.display();

		System.out.print("Open Light Source:"); if(lightsource) System.out.println('o'); else System.out.println('x');
		System.out.print("Attached Almirah: "); if(almirah) System.out.println('o'); else System.out.println('x');
	}
}