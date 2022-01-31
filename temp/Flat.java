import java.util.Scanner;

public class Flat
{
	Location location = new Location();
	int bed, bath, balk, xtra;
	Boolean generator, lift;
	int floor;
	int waterproblem, electricityproblem;

	Bedroom[] bedroom = null;
	DiningRoom dining = null;
	LivingRoom living = null;
	Balcony[] balcony = null;
	Kitchen kitchen = null;
	Bathroom[] bathroom = null;
	StoreRoom storeroom = null;
	Room[] extraroom = null;

	static Boolean initialized = false;
	static double[] coff = new double[Global.FACTORS];

	void init()
	{
		if(initialized) return;
		initialized = true;

		for(int i=0; i<Global.FACTORS; i++) coff[i]=1;
	}

	double[] value = new double[Global.FACTORS];
	int rent;

	Flat()
	{
		Scanner scan = new Scanner(System.in);
		int i, temp;
		for(i=0; i<Global.FACTORS; i++) value[i]=0;
		init(); refresh();

		System.out.println("Flat Location");
		System.out.println("---------------");
		System.out.print("Latitude:\t");
		location.x = scan.nextDouble();
		System.out.print("Longitude:\t");
		location.y = scan.nextDouble();

		System.out.println("");
		System.out.println("Flat Information");
		System.out.println("---------------");
		System.out.print("Floor (starts from 0): ");
		floor = scan.nextInt();

		System.out.print("Number of bedrooms: ");
		bed = scan.nextInt();
		bedroom = new Bedroom[bed];

		for(i=0; i<bed; i++)
		{
			System.out.println("");
			System.out.println("Bedroom " + (i+1) + " Information");
			System.out.println("---------------");
			bedroom[i] = new Bedroom();
		}

		System.out.println("");
		System.out.println("Living Room:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		temp %= 2;
		if(temp != 0)
		{
			System.out.println("");
			System.out.println("Living Room Information");
			System.out.println("---------------");
			living = new LivingRoom();
		}

		System.out.println("");
		System.out.println("Dining Room:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		temp %= 2;
		if(temp != 0)
		{
			System.out.println("");
			System.out.println("Dining Room Information");
			System.out.println("---------------");
			dining = new DiningRoom();
		}

		System.out.println("");
		System.out.println("Kitchen:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		temp %= 2;
		if(temp != 0)
		{
			System.out.println("");
			System.out.println("Kitchen Information");
			System.out.println("---------------");
			kitchen = new Kitchen();
		}

		System.out.println("");
		System.out.print("Number of general bathrooms: ");
		bath = scan.nextInt();
		bathroom = new Bathroom[bath];

		for(i=0; i<bath; i++)
		{
			System.out.println("");
			System.out.println("Bathroom " + (i+1) + " Information");
			System.out.println("---------------");
			bathroom[i] = new Bathroom();
		}

		System.out.println("");
		System.out.print("Number of general balcony: ");
		balk = scan.nextInt();
		balcony = new Balcony[balk];

		for(i=0; i<balk; i++)
		{
			System.out.println("");
			System.out.println("Balcony " + (i+1) + " Information");
			System.out.println("---------------");
			balcony[i] = new Balcony();
		}

		System.out.println("");
		System.out.println("Store Room:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		temp %= 2;
		if(temp != 0)
		{
			System.out.println("");
			System.out.println("Store Room Information");
			System.out.println("---------------");
			storeroom = new StoreRoom();
		}

		System.out.println("");
		System.out.print("Number of extra rooms: ");
		xtra = scan.nextInt();
		extraroom = new Room[xtra];

		for(i=0; i<xtra; i++)
		{
			System.out.println("");
			System.out.println("Extra Room " + (i+1) + " Information");
			System.out.println("---------------");
			extraroom[i] = new Room();
		}

		System.out.println("");
		System.out.println("Lift:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lift = (temp%2 != 0);

		System.out.println("");
		System.out.println("Generator:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		generator = (temp%2 != 0);

        do
        {
            System.out.println("");
            System.out.print("Average hours of water supply in one day: ");
            temp = scan.nextInt();
            if(temp>24) System.out.println("Invalid!");
        } while(temp>24);
        waterproblem = temp-24;

        do
        {
            System.out.println("");
            System.out.print("Average minutes of load shedding in one day: ");
            temp = scan.nextInt();
            if(temp>1440) System.out.println("Invalid!");
        } while(temp>1440);
        electricityproblem = -temp;

        setRent();
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

		for(i=0; i<bed; i++)
		{
			temp=bedroom[i].value();
			value[0] += temp;
		}

		if(dining != null) value[1] = dining.value();
		if(living != null) value[2] = living.value();
		if(kitchen != null) value[3] = kitchen.value();

		for(i=0; i<bath; i++)
		{
			temp=bathroom[i].value();
			value[4] += temp;
		}

		for(i=0; i<balk; i++)
		{
			temp=balcony[i].value();
			value[5] += temp;
		}

		if(storeroom != null) value[6]=storeroom.value();

		for(i=0; i<xtra; i++)
		{
			temp=extraroom[i].value();
			value[7] += temp;
		}

		if(generator) value[8] = 1;
		else value[8] = 0;

		if(!lift && floor>3) value[9] = -1;
		value[9] = 0;

		value[10] = waterproblem;
		value[11] = electricityproblem;

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
		if(rent>0) save();
		System.out.println("");
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