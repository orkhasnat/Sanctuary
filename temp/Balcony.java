import java.util.Scanner;

public class Balcony extends Room
{
	Balcony(int FlatID)
	{
		id = Global.random(0, 99999);
		id += 600000;
		name = "Balcony";
		updateName();
		getArea();
		updateTiles();

		// INSERT INTO Balcony (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 6, area, tiles, FlatID);
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
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 4;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Balcony
			// SET Name = name
			// WHERE RoomID = id;
		}
		
		else if(choice == 2)
		{
			getArea();

			// UPDATE Balcony
			// SET Area = area
			// WHERE RoomID = id;
		}
		
		else if(choice == 3)
		{
			updateTiles();

			// UPDATE Balcony
			// SET Tiles = tiles
			// WHERE RoomID = id;
		}
	}
}