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

		// INSERT INTO Bathroom (RoomID, Name, Area, Tiles, Type, FlatID)
		// VALUES (id, name, area, tiles, 5, FlatID);
	}

	double value()
	{
		double temp = area*7;
		if(shower) temp += 200;
		if(sink) temp += 200;
		if(tseat) temp += 500;
		if(tpan) temp += 100;
		if(spraywasher) temp += 100;
		if(geaser) temp += 400;
		if(bathtub) temp += 400;
        temp *= tiles;

        return temp/7;
	}
}