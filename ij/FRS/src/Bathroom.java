import java.util.Scanner;

public class Bathroom extends Room
{
	private boolean shower, sink, tseat, tpan, spray, geaser, bathtub;

	Bathroom(int FlatID, String _name, double _area, String _tiles, boolean _shower, boolean _sink, boolean _tseat, boolean _tpan, boolean _spray, boolean _geaser, boolean _bathtub)
	{
		id = Global.random(0, 99999);
		id += 500000;

		updateName(_name);
		updateArea(_area);
		updateTiles(_tiles);

		shower = _shower;
		sink = _sink;
		tseat = _tseat;
		tpan = _tpan;
		spray = _spray;
		geaser = _geaser;
		bathtub = _bathtub;

		// INSERT INTO Bathroom (RoomID, Name, Type, Area, Tiles, FlatID, Shower, Sink, Tseat, Tpan, SprayWasher, Geaser, Bathtub)
		// VALUES (id, name, 5, area, tiles, FlatID, shower, sink, tseat, tpan, spray, geaser, bathtub);
	}

	Bathroom(int FlatID)
	{
		id = Global.random(0, 99999);
		id += 500000;
		this.name = "Bathroom";
		updateName();
		updateArea();
		updateTiles();

		this.shower = getAns("Shower:");
		this.sink = getAns("Sink:");
		this.tseat = getAns("Toilet Seat:");
		this.tpan = getAns("Toilet Pan:");
		this.spray = getAns("Spray Washer:");
		this.geaser = getAns("Geaser:");
		this.bathtub = getAns("Bathtub:");

		// INSERT INTO Bathroom (RoomID, Name, Type, Area, Tiles, FlatID, Shower, Sink, Tseat, Tpan, SprayWasher, Geaser, Bathtub)
		// VALUES (id, name, 5, area, tiles, FlatID, shower, sink, tseat, tpan, spraywasher, geaser, bathtub);
	}

	double value()
	{
		double temp = super.value();
		if(shower) temp *= 1.02;
		if(sink) temp *= 1.02;
		if(tseat) temp *= 1.05;
		if(tpan) temp *= 1.01;
		if(spray) temp *= 1.01;
		if(geaser) temp *= 1.04;
		if(bathtub) temp *= 1.07;

		return temp;
	}

	void display()
	{
		super.display();

		System.out.print("Shower:"); if(shower) System.out.println('o'); else System.out.println('x');
		System.out.print("Sink: "); if(sink) System.out.println('o'); else System.out.println('x');
		System.out.print("Toilet Seat: "); if(tseat) System.out.println('o'); else System.out.println('x');
		System.out.print("Toilet Pan: "); if(tpan) System.out.println('o'); else System.out.println('x');
		System.out.print("Spraywasher:"); if(spray) System.out.println('o'); else System.out.println('x');
		System.out.print("Geaser: "); if(geaser) System.out.println('o'); else System.out.println('x');
		System.out.print("Bathtub: "); if(bathtub) System.out.println('o'); else System.out.println('x');
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
		System.out.println("4. Shower.");
		System.out.println("5. Sink.");
		System.out.println("6. Toilet Seat.");
		System.out.println("7. Toilet Pan.");
		System.out.println("8. Spray Washer.");
		System.out.println("9. Geaser.");
		System.out.println("10. Bathtub.");
		System.out.println("11. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 11;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Bathroom
			// SET Name = name
			// WHERE RoomID = id;
		}
		
		else if(choice == 2)
		{
			updateArea();

			// UPDATE Bathroom
			// SET Area = area
			// WHERE RoomID = id;
		}
		
		else if(choice == 3)
		{
			updateTiles();

			// UPDATE Bathroom
			// SET Tiles = tiles
			// WHERE RoomID = id;
		}
		
		else if(choice == 4)
		{
			shower = getAns("Shower:");

			// UPDATE Bathroom
			// SET Shower = shower
			// WHERE RoomID = id;
		}
		
		else if(choice == 5)
		{
			sink = getAns("Sink:");

			// UPDATE Bathroom
			// SET Sink = sink
			// WHERE RoomID = id;
		}
		
		else if(choice == 6)
		{
			tseat = getAns("Toilet Seat:");

			// UPDATE Bathroom
			// SET Tseat = tseat
			// WHERE RoomID = id;
		}
		
		else if(choice == 7)
		{
			tpan = getAns("Toilet Pan:");

			// UPDATE Bathroom
			// SET Tpan = tpan
			// WHERE RoomID = id;
		}
		
		else if(choice == 8)
		{
			spray = getAns("Spray Washer:");

			// UPDATE Bathroom
			// SET SprayWasher = spraywasher
			// WHERE RoomID = id;
		}
		
		else if(choice == 9)
		{
			geaser = getAns("Geaser:");

			// UPDATE Bathroom
			// SET Geaser = geaser
			// WHERE RoomID = id;
		}
		
		else if(choice == 10)
		{
			bathtub = getAns("Bathtub:");

			// UPDATE Bathroom
			// SET Bathtub = bathtub
			// WHERE RoomID = id;
		}
	}
}