import java.util.Scanner;
import java.util.ArrayList;

public class Flat
{
	int id;
	String name;
	int floor;

	Location location = new Location(23.94538493888004, 90.38274718424901);
	Boolean lift = false, generator = false;

	ArrayList<Room> rooms = new ArrayList<>();
	int rent;

	static Boolean initialized = false;
	static double[] coff = new double[Global.FACTORS];

	double[] value = new double[Global.FACTORS];

	Flat(String Owner)
	{
		Scanner scan = new Scanner(System.in);
		int i, temp;

		System.out.println("Flat Information");
		System.out.println("---------------");

		int id = Global.random(0, 99999);
		id += 1000000;
		System.out.print("Name: ");
		name = scan.nextLine();

		System.out.println("");
		System.out.println("Flat Location");
		System.out.println("---------------");
		location.update();
		System.out.print("Floor (starts from 0): ");
		floor = scan.nextInt();

		updateLiftInfo();
		updateGeneratorInfo();
        
        // INSERT INTO Flat (FlatID, Name, X, Y, Floor, Owner, Lift, Generator, avgWaterHours, avgLSMinutes)
		// VALUES (id, name, location.x, location.y, floor, Owner, lift, generator, water, loadshedding);

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
	}

	void addRoom()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("1. Bedroom.");
		System.out.println("2. Dining Room.");
		System.out.println("3. Living Room.");
		System.out.println("4. Kitchen.");
		System.out.println("5. Bathroom.");
		System.out.println("6. Balcony.");
		System.out.println("7. Store Room.");
		System.out.println("8. Extra Room.");
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 9;
		
		if(choice == 1)
		{
			System.out.println("");
			System.out.println("Bedroom Information");
			System.out.println("---------------");
			rooms.add(new Bedroom(id));
		}
		
		else if(choice == 2)
		{
			System.out.println("");
			System.out.println("Dining Room Information");
			System.out.println("---------------");
			rooms.add(new DiningRoom(id));
		}
		
		else if(choice == 3)
		{
			System.out.println("");
			System.out.println("Living Room Information");
			System.out.println("---------------");
			rooms.add(new LivingRoom(id));
		}
		
		else if(choice == 4)
		{
			System.out.println("");
			System.out.println("Kitchen Information");
			System.out.println("---------------");
			rooms.add(new Kitchen(id));
		}
		
		else if(choice == 5)
		{
			System.out.println("");
			System.out.println("Bathroom Information");
			System.out.println("---------------");
			rooms.add(new Bathroom(id));
		}
		
		else if(choice == 6)
		{
			System.out.println("");
			System.out.println("Balcony Information");
			System.out.println("---------------");
			rooms.add(new Balcony(id));
		}
		
		else if(choice == 7)
		{
			System.out.println("");
			System.out.println("Store Room Information");
			System.out.println("---------------");
			rooms.add(new StoreRoom(id));
		}
		
		else if(choice == 8)
		{
			System.out.println("");
			System.out.println("Extra Room Information");
			System.out.println("---------------");
			rooms.add(new XtraRoom(id));
		}
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

	void setRent()
	{
		Scanner scan = new Scanner(System.in);
		int i;
		Boolean flag = false;
		double temp;

		for(i=0; i<Global.FACTORS; i++) value[i]=0;
		init(); refresh();

		System.out.println("");
		System.out.println("Rent Information");
		System.out.println("---------------");

		for(Room room: rooms)
		{
			int t = room.id/100000-1;
			value[t]+=room.value();
		}

		if(generator) value[8] = 1;
		else value[8] = 0;

		if(!lift && floor>3) value[9] = -1;
		else value[9] = 0;

		temp=0;
		for(i=0; i<Global.FACTORS; i++)
		{
			temp += value[i]*coff[i];
			if(coff[i] != 1) flag=true;
		}

		System.out.println("");
		if(flag) System.out.println("Standard Rent:\t\t" + temp);

		System.out.print("Rent:\t\t\t");
		rent = scan.nextInt();
		System.out.println("");

		// INSERT INTO Rent_Table (FlatID, Value, Rent)
		// VALUES (id, value, rent);
	}

	void save()
	{

	}

	void init()
	{
		if(initialized) return;
		initialized = true;

		for(int i=0; i<Global.FACTORS; i++) coff[i]=1;
	}

	void refresh()
	{
		
	}

	void findOutliers()
	{

	}

	void display()
	{
		System.out.println("Name: " + name);
		// Display Owner Info
		System.out.println("Rent: " + rent);

		// <a href="https://www.google.com/maps/search/?api=1&query="+location.x+"%2C"+location.y>Location</a><br>
		System.out.println("Floor: " + floor);
		
		System.out.print("Lift:"); if(lift) System.out.println('o'); else System.out.println('x');
		System.out.print("Generator:"); if(generator) System.out.println('o'); else System.out.println('x');
	}
}