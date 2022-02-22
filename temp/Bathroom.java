import java.util.Scanner;

public class Bathroom extends Room
{
	Boolean shower, sink, tseat, tpan, spraywasher, geaser, bathtub;

	Bathroom(int FlatID)
	{
		super();
		id += 500000;
		name = "Bathroom";

		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println("Shower:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		shower = (temp%2 != 0);

		System.out.println("Sink:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		sink = (temp%2 != 0);

		System.out.println("Toilet Seat:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		tseat = (temp%2 != 0);

		System.out.println("Toilet Pan:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		tpan = (temp%2 != 0);

		System.out.println("Spray Washer:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		spraywasher = (temp%2 != 0);

		System.out.println("Geaser:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		geaser = (temp%2 != 0);

		System.out.println("Bathtub:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		bathtub = (temp%2 != 0);

		// INSERT INTO Bathroom (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 5, area, tiles, FlatID);
	}

	double value()
	{
		double temp = super.value();
		if(shower) temp *= 1.02;
		if(sink) temp *= 1.02;
		if(tseat) temp *= 1.05;
		if(tpan) temp *= 1.01;
		if(spraywasher) temp *= 1.01;
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
		System.out.print("Spraywasher:"); if(spraywasher) System.out.println('o'); else System.out.println('x');
		System.out.print("Geaser: "); if(geaser) System.out.println('o'); else System.out.println('x');
		System.out.print("Bathtub: "); if(bathtub) System.out.println('o'); else System.out.println('x');
	}
}