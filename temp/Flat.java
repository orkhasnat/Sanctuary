import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Flat
{
	int id;
	String name;
	int floor;

	Location location = new Location();
	Boolean generator = false, lift = false;
	double water = 24, loadshedding = 10;

	ArrayList<Room> rooms = new ArrayList<>();
	int rent;

	static Boolean initialized = false;
	static double[] coff = new double[Global.FACTORS];

	void init()
	{
		if(initialized) return;
		initialized = true;

		for(int i=0; i<Global.FACTORS; i++) coff[i]=1;
	}

	double[] value = new double[Global.FACTORS];

	Flat(String Owner)
	{
		Scanner scan = new Scanner(System.in);
		int i, temp;
		for(i=0; i<Global.FACTORS; i++) value[i]=0;
		init(); refresh();

		System.out.println("Flat Information");
		System.out.println("---------------");

		int id = Global.random(0, 99999);
		id += 1000000;

		System.out.print("Name: ");
		name = scan.nextLine();
		System.out.print("Floor (starts from 0): ");
		floor = scan.nextInt();
		getLocation();

		updateLiftInfo();
		updateGeneratorInfo();
		updateWaterInfo();
		updateLSInfo();
        
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

	void getLocation()
	{
		Scanner scan = new Scanner(System.in);

        System.out.println("");
        System.out.println("Flat Location");
        System.out.println("---------------");
        System.out.print("Latitude:\t");
        location.x = scan.nextDouble();
        System.out.print("Longitude:\t");
        location.y = scan.nextDouble();
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

	void updateWaterInfo()
	{
		Scanner scan = new Scanner(System.in);
		
        do
        {
            System.out.println("");
            System.out.print("Average hours of water supply in one day: ");
            water = scan.nextDouble();
            if(water>24) System.out.println("Invalid!");
        } while(water>24);
	}
	
	void updateLSInfo()
	{
		Scanner scan = new Scanner(System.in);
		
        do
        {
            System.out.println("");
            System.out.print("Average minutes of load shedding in one day: ");
            loadshedding = scan.nextInt();
            if(loadshedding>1440) System.out.println("Invalid!");
        } while(loadshedding>1440);
	}

	void setRent()
	{
		Scanner scan = new Scanner(System.in);
		int i;
		Boolean flag = false;
		double temp;

		System.out.println("");
		System.out.println("Rent Information");
		System.out.println("---------------");

		for(Room room: rooms)
		{
			int t = room.id/100000-1;
			value[t]+=room.value();
			System.out.println(room.name+"\t"+t);
		}

		if(generator) value[8] = 1;
		else value[8] = 0;

		if(!lift && floor>3) value[9] = -1;
		else value[9] = 0;

		value[10] = water-24;
		value[11] = -loadshedding;

		temp=0;
		for(i=0; i<Global.FACTORS; i++)
		{
			System.out.println(value[i]);
			temp += value[i]*coff[i];
			if(coff[i] != 1) flag=true;
		}

		System.out.println("");
		if(flag) System.out.println("Standard Rent:\t\t" + temp);

		System.out.print("Rent:\t\t\t");
		rent = scan.nextInt();
		if(rent>0) save();
		System.out.println("");

		// INSERT INTO Rent_Table (FlatID, Value, Rent)
		// VALUES (id, value, rent);
	}

	void save()
	{

	}

	void refresh()
	{
		
	}

	void findOutliers()
	{

	}
}