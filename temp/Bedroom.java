import java.util.Scanner;

public class Bedroom extends Room
{
	boolean lightsource, almirah;

	Bedroom(int FlatID)
	{
		id = Global.random(0, 99999);
		id += 100000;
		name = "Bedroom";
		updateName();
		getArea();
		updateTiles();

		Scanner scan = new Scanner(System.in);
		int temp;

		lightsource = getAns("Open Light Source:");
		almirah = getAns("Attached Almirah:");

		// INSERT INTO Bedroom (RoomID, Name, Type, Area, Tiles, FlatID, LightSource, Almirah)
		// VALUES (id, name, 1, area, tiles, FlatID, lightsource, almirah);
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

		System.out.print("Open Light Source: "); if(lightsource) System.out.println('o'); else System.out.println('x');
		System.out.print("Attached Almirah: "); if(almirah) System.out.println('o'); else System.out.println('x');
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
		System.out.println("5. Attached Almirah.");
		System.out.println("6. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 6;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Bedroom
			// SET Name = name
			// WHERE RoomID = id;
		}
		
		else if(choice == 2)
		{
			getArea();

			// UPDATE Bedroom
			// SET Area = area
			// WHERE RoomID = id;
		}
		
		else if(choice == 3)
		{
			updateTiles();

			// UPDATE Bedroom
			// SET Tiles = tiles
			// WHERE RoomID = id;
		}

		else if(choice == 4)
		{
			lightsource = getAns("Open Light Source:");

			// UPDATE Bedroom
			// SET LightSource = lightsource
			// WHERE RoomID = id;
		}

		else if(choice == 5)
		{
			almirah = getAns("Attached Almirah:");

			// UPDATE Bedroom
			// SET Almirah = almirah
			// WHERE RoomID = id;
		}
	}
}