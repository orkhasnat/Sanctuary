import java.util.Scanner;
import java.util.ArrayList;

public class Flat
{
	int id;
	String name;
	int level;

	Location location = new Location(23.94538493888004, 90.38274718424901);
	boolean lift = false, generator = false, vacant = true;

	ArrayList<Room> rooms = new ArrayList<>();
	int rent;

	static boolean initialized = false;
	static double[] coff = new double[Global.FACTORS];

	double[] value = new double[Global.FACTORS];

	Flat(String Owner)
	{
		Scanner scan = new Scanner(System.in);
		int i, temp;

		System.out.println("Flat Information");
		System.out.println("---------------");

		id = Global.random(0, 99999);
		id += 1000000;
		updateName();
		updateLocation();
		updateLevel();

		updateLiftInfo();
		updateGeneratorInfo();

        // INSERT INTO Flat (FlatID, Name, X, Y, Level, Owner, Lift, Generator)
		// VALUES (id, name, location.x, location.y, level, Owner, lift, generator);

		int choice;
		do
		{
			System.out.println("");
			System.out.println("1. Add a Room.");
			System.out.println("2. Done.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 2;

			if(choice == 1) addRoom();
		} while(choice != 0);

		setRent();

		// INSERT INTO Rent_Table (FlatID, Value, Rent)
		// VALUES (id, value, rent);
	}

	void updateName()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("");
		System.out.print("Name: ");
		name = scan.nextLine();
	}

	void updateLocation()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("");
		System.out.println("Flat Location");
		System.out.println("---------------");
		location.update();
	}

	void updateLevel()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("");
		System.out.print("Level (starts from 1): ");
		level = scan.nextInt();
	} 

	void updateLiftInfo()
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println("");
		System.out.println("Lift:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lift = (temp%2 != 0);
	}

	void updateGeneratorInfo()
	{
		Scanner scan = new Scanner(System.in);
		int temp;
		
		System.out.println("");
		System.out.println("Generator:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		generator = (temp%2 != 0);
	}

	void addRoom()
	{
		Scanner scan = new Scanner(System.in);
		int choice;
		Room r;

		System.out.println("");
		System.out.println("1. Bedroom.");
		System.out.println("2. Dining Room.");
		System.out.println("3. Living Room.");
		System.out.println("4. Kitchen.");
		System.out.println("5. Bathroom.");
		System.out.println("6. Balcony.");
		System.out.println("7. Store Room.");
		System.out.println("8. Extra Room.");
		System.out.println("9. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 9;
		
		if(choice == 1)
		{
			System.out.println("");
			System.out.println("Bedroom Information");
			System.out.println("---------------");

			r = new Bedroom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 2)
		{
			System.out.println("");
			System.out.println("Dining Room Information");
			System.out.println("---------------");
			
			r = new DiningRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 3)
		{
			System.out.println("");
			System.out.println("Living Room Information");
			System.out.println("---------------");
			
			r = new LivingRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 4)
		{
			System.out.println("");
			System.out.println("Kitchen Information");
			System.out.println("---------------");
			
			r = new Kitchen(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 5)
		{
			System.out.println("");
			System.out.println("Bathroom Information");
			System.out.println("---------------");
			
			r = new Bathroom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 6)
		{
			System.out.println("");
			System.out.println("Balcony Information");
			System.out.println("---------------");
			
			r = new Balcony(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 7)
		{
			System.out.println("");
			System.out.println("Store Room Information");
			System.out.println("---------------");
			
			r = new StoreRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
		
		else if(choice == 8)
		{
			System.out.println("");
			System.out.println("Extra Room Information");
			System.out.println("---------------");
			
			r = new XtraRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
	}

	void roomlist()
	{
		int i = 0;

		System.out.println("");
		for(Room room: rooms)
		{
			System.out.println((i+1)+". "+room.name);
			i++;
		}
	}

	void roomdisplay()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		roomlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= rooms.size()+1;

		if(choice != 0) rooms.get(choice-1).display();
	}

	void roomview()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		roomlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= rooms.size()+1;

		if(choice != 0) rooms.get(choice-1).view();
	}

	void setRent()
	{
		Scanner scan = new Scanner(System.in);
		int i;
		boolean flag = false;
		double temp;

		for(i = 0; i<Global.FACTORS; i++) value[i] = 0;
		init(); refresh();

		System.out.println("");
		System.out.println("Rent Information");
		System.out.println("---------------");

		for(Room room: rooms)
		{
			int t = room.id/100000-1;
			value[t]+= room.value();
		}

		if(generator) value[8] = 1;
		else value[8] = 0;

		if(!lift && level>3) value[9] = -1;
		else value[9] = 0;

		temp = 0;
		for(i = 0; i<Global.FACTORS; i++)
		{
			temp += value[i]*coff[i];
			if(coff[i] != 1) flag = true;
		}

		System.out.println("");
		if(flag) System.out.println("Standard Rent:\t\t" + temp);

		System.out.print("Rent:\t\t\t");
		rent = scan.nextInt();
		System.out.println("");
	}

	void info()
	{
		System.out.println("");
		System.out.println("Name: " + name);
		// Display Owner Info
		System.out.println("Rent: " + rent);

		// <a href = "https://www.google.com/maps/search/?api = 1&query = "+location.x+"%2C"+location.y>Location</a><br>
		System.out.println("Level: " + level);

		System.out.print("Lift:"); if(lift) System.out.println('o'); else System.out.println('x');
		System.out.print("Generator:"); if(generator) System.out.println('o'); else System.out.println('x');
	}

	void display()
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println("");
			System.out.println("1. Display Rooms.");
			System.out.println("2. Back.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 2;

			if(choice == 1) roomdisplay();
		} while(choice != 0);
	}

	void display(long StudentID)
	{
		display();

		if(vacant)
		{
			// Will You Request Reservation
		}
	}

	void view()
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println("");
			System.out.println("1. Edit Flat.");
			System.out.println("2. Delete Flat.");
			System.out.println("3. View Rooms.");
			System.out.println("4. Back.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 4;

			if(choice == 1) edit();

			else if(choice == 2)
			{
				delete();
				return;
			}

			else if(choice == 3) roomview();
		} while(choice != 0);
	}

	void edit()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("Edit Flat Information");
		System.out.println("---------------");
		System.out.println("1. Name.");
		System.out.println("2. Location");
		System.out.println("3. Level");
		System.out.println("4. Add Room.");
		System.out.println("5. Lift.");
		System.out.println("6. Generator.");
		System.out.println("7. Rent");
		System.out.println("8. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 8;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Flat
			// SET Name = name
			// WHERE FlatID = id;
		}
		
		else if(choice == 2)
		{
			updateLocation();

			// UPDATE Flat
			// SET X = location.x, Y = location.y
			// WHERE FlatID = id;
		}
		
		else if(choice == 3)
		{
			updateLevel();

			// UPDATE Flat
			// SET Level = level
			// WHERE FlatID = id;
		}

		else if(choice == 4) addRoom();

		else if(choice == 5)
		{
			updateLiftInfo();

			// UPDATE Flat
			// SET Lift = lift
			// WHERE FlatID = id;
		}

		else if(choice == 6)
		{
			updateGeneratorInfo();

			// UPDATE Flat
			// SET Generator = generator
			// WHERE FlatID = id;
		}

		else if(choice == 7)
		{
			if(vacant) setRent();

			// UPDATE Rent_Table
			// SET Rent = rent
			// WHERE FlatID = id;
		}
	}

	void delete()
	{
		Global.AllFlats.remove(id);

		// DELETE FROM Flat
		// WHERE FlatID = id;

		name = "Deleted Flat";
	}

	void save()
	{

	}

	void init()
	{
		if(initialized) return;
		initialized = true;

		for(int i = 0; i<Global.FACTORS; i++) coff[i] = 1;
	}

	void refresh()
	{
		
	}

	void findOutliers()
	{

	}
}