import java.util.Scanner;

public class Kitchen extends Room
{
	int stove;
	Boolean sink, cupboard, ventilator, gas;

	Kitchen()
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.print("Number of stove: ");
		stove = scan.nextInt();

		System.out.println("Sink:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		sink = (temp%2 != 0);

		System.out.println("Cupboard:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		cupboard = (temp%2 != 0);

		System.out.println("Ventilator:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		ventilator = (temp%2 != 0);

		System.out.println("Gas Supply:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		gas = (temp%2 != 0);
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
}