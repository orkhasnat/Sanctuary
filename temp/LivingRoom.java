import java.util.Scanner;

public class LivingRoom extends Room
{
	Boolean lightsource;

	LivingRoom(int FlatID)
	{
		super();
		id += 300000;
		name = "Living Room";

		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println("Open Light Source:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lightsource = (temp%2 != 0);

		// INSERT INTO LivingRoom (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 3, area, tiles, FlatID);
	}

	double value()
	{
		double temp = super.value();
		if(lightsource) temp *= 1.1;

		return temp;
	}
}