import java.util.Scanner;

public class LivingRoom extends Room
{
	boolean lightsource;

	LivingRoom(int FlatID)
	{
		id = Global.random(0, 99999);
		id += 300000;
		name = "Living Room";
		updateName();
		getArea();
		updateTiles();

		Scanner scan = new Scanner(System.in);
		int temp;

		lightsource = getAns("Open Light Source:");

		// INSERT INTO LivingRoom (RoomID, Name, Type, Area, Tiles, FlatID, LightSource)
		// VALUES (id, name, 3, area, tiles, FlatID, lightsource);
	}

	double value()
	{
		double temp = super.value();
		if(lightsource) temp *= 1.1;

		return temp;
	}

	void edit()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("Edit Room Information");
		System.out.println("---------------");
		System.out.println("1. Name.");
		System.out.println("2. Area.");
		System.out.println("3. Floor.");
		System.out.println("4. Open Light Source.");
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 5;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE LivingRoom
			// SET Name = name
			// WHERE RoomID = id;
		}
		
		else if(choice == 2)
		{
			getArea();

			// UPDATE LivingRoom
			// SET Area = area
			// WHERE RoomID = id;
		}
		
		else if(choice == 3)
		{
			updateTiles();

			// UPDATE LivingRoom
			// SET Tiles = tiles
			// WHERE RoomID = id;
		}

		else if(choice == 4)
		{
			lightsource = getAns("Open Light Source:");

			// UPDATE LivingRoom
			// SET LightSource = lightsource
			// WHERE RoomID = id;
		}
	}
}