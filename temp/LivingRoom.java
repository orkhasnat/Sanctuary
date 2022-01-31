import java.util.Scanner;

public class LivingRoom extends Room
{
	Boolean lightsource;

	LivingRoom()
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println("Open Light Source:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lightsource = (temp%2 != 0);
	}

	double value()
	{
		double temp = super.value();
		if(lightsource) temp *= 1.1;

		return temp;
	}
}