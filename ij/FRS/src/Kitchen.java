import java.util.Scanner;

public class Kitchen extends Room
{
	private int stove;
	private boolean sink, cupboard, ventilator, gas;

	Kitchen(int FlatID, String _name, double _area, String _tiles, int _stove, boolean _sink, boolean _cupboard, boolean _gas, boolean _ventilator)
	{
		id = getnewID();

		updateName(_name);
		updateArea(_area);
		updateTiles(_tiles);

		stove = _stove;

		sink = _sink;
		cupboard = _cupboard;
		ventilator = _ventilator;
		gas = _gas;

		// INSERT INTO Kitchen (RoomID, Name, Type, Area, Tiles, FlatID, Stove, Sink, Cupboard, Ventilator, Gas)
		// VALUES (id, name, 4, area, tiles, FlatID, stove, sink, cupboard, ventilator, gas);
	}

	protected int getnewID()
	{
		int id = 400000;
		while(Global.AllRooms.containsKey(id)) id++;
		return id;
	}

	Kitchen(int FlatID)
	{
		id = getnewID();
		this.name = "Kitchen";
		updateName();
		updateArea();
		updateTiles();

		Scanner scan = new Scanner(System.in);

		System.out.print("Number of stove: ");
		this.stove = scan.nextInt();

		this.sink = getAns("Sink:");
		this.cupboard = getAns("Cupboard:");
		this.ventilator = getAns("Ventilator:");
		this.gas = getAns("Gas Supply:");

		// INSERT INTO Kitchen (RoomID, Name, Type, Area, Tiles, FlatID, Stove, Sink, Cupboard, Ventilator, Gas)
		// VALUES (id, name, 4, area, tiles, FlatID, stove, sink, cupboard, ventilator, gas);
	}

	double value()
	{
		double temp = super.value()*(0.9+0.1*stove);
		if(sink) temp *= 1.03;
		if(cupboard) temp*= 1.05;
		if(ventilator) temp *= 1.05;
		if(!gas) temp *= 0.1;

		return temp;
	}

	void display()
	{
		super.display();

		System.out.println("Number of Stove: "+stove);
		System.out.print("Sink:"); if(sink) System.out.println('o'); else System.out.println('x');
		System.out.print("Cupboard: "); if(cupboard) System.out.println('o'); else System.out.println('x');
		System.out.print("Ventilator: "); if(ventilator) System.out.println('o'); else System.out.println('x');
		System.out.print("Gas Supply: "); if(gas) System.out.println('o'); else System.out.println('x');
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
		System.out.println("4. Stove.");
		System.out.println("5. Sink.");
		System.out.println("6. Cupboard.");
		System.out.println("7. Ventilator.");
		System.out.println("8. Gas Supply.");
		System.out.println("9. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 9;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Kitchen
			// SET Name = name
			// WHERE RoomID = id;
		}
		
		else if(choice == 2)
		{
			updateArea();

			// UPDATE Kitchen
			// SET Area = area
			// WHERE RoomID = id;
		}
		
		else if(choice == 3)
		{
			updateTiles();

			// UPDATE Kitchen
			// SET Tiles = tiles
			// WHERE RoomID = id;
		}
		
		else if(choice == 4)
		{
			System.out.print("Number of stove: ");
			stove = scan.nextInt();

			// UPDATE Kitchen
			// SET Stove = stove
			// WHERE RoomID = id;
		}
		
		else if(choice == 5)
		{
			sink = getAns("Sink:");

			// UPDATE Kitchen
			// SET Sink = sink
			// WHERE RoomID = id;
		}
		
		else if(choice == 6)
		{
			cupboard = getAns("Cupboard:");

			// UPDATE Kitchen
			// SET Cupboard = cupboard
			// WHERE RoomID = id;
		}
		
		else if(choice == 7)
		{
			ventilator = getAns("Ventilator:");

			// UPDATE Kitchen
			// SET Ventilator = ventilator
			// WHERE RoomID = id;
		}
		
		else if(choice == 8)
		{
			gas = getAns("Gas Supply:");

			// UPDATE Kitchen
			// SET Gas = gas
			// WHERE RoomID = id;
		}
	}
}