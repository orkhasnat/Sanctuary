import java.util.Scanner;

public class LivingRoom extends Room
{
	private boolean lightsource;

	LivingRoom(int FlatID, String _name, double _area, String _tiles, boolean _lightsource)
	{
		id = getnewID();

		updateName(_name);
		updateArea(_area);
		updateTiles(_tiles);

		lightsource = _lightsource;

		// INSERT INTO LivingRoom (RoomID, Name, Type, Area, Tiles, FlatID, LightSource)
		// VALUES (id, name, 3, area, tiles, FlatID, lightsource);
	}

	protected int getnewID()
	{
		int id = 300000;
		while(Global.AllRooms.containsKey(id)) id++;
		return id;
	}

	LivingRoom(int FlatID)
	{
		id = getnewID();
		name = "Living Room";
		updateName();
		updateArea();
		updateTiles();

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

	protected void edit()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println();
		System.out.println("Edit Room Information");
		System.out.println("---------------");
		System.out.println("1. Name.");
		System.out.println("2. Area.");
		System.out.println("3. Floor.");
		System.out.println("4. Open Light Source.");
		System.out.println("5. Back.");
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
			updateArea();

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