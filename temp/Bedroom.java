import java.util.Scanner;

public class Bedroom extends Room
{
	Boolean lightsource, almirah;
	Balcony balcony = null;
	Bathroom bathroom = null;

	Bedroom()
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println("Open Light Source:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lightsource = (temp%2 != 0);

		System.out.println("Attached Balcony:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		if(temp%2 == 1)
		{
			System.out.println("");
			System.out.println("Balcony Information");
			System.out.println("---------------");
			balcony = new Balcony();

			System.out.println("");
			System.out.println("Bedroom Information");
			System.out.println("---------------");
		}

		System.out.println("Attached Almirah:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		almirah = (temp%2 != 0);

		System.out.println("Attached Bathroom:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		if(temp%2 == 1)
		{
			System.out.println("");
			System.out.println("Bathroom Information");
			System.out.println("---------------");
			bathroom = new Bathroom();
		}
	}

	double value()
	{
		double temp = super.value();
		if(lightsource) temp *= 1.1;
		if(almirah) temp *= 1.02;
        if(balcony != null) temp += balcony.value()*1.01;
        if(bathroom != null) temp += bathroom.value()*1.01;

        return temp;
	}
}