import java.util.Scanner;

public class Location
{
	double x, y;

	Location(double _x, double _y)
	{
		x = _x;
		y = _y;
	}

	void update()
	{
		Scanner scan = new Scanner(System.in);

		System.out.print("Latitude:\t");
		x = scan.nextDouble();
		System.out.print("Longitude:\t");
		y = scan.nextDouble();
	}

	String getLink()
	{
		return "https://www.google.com/maps/search/?api=1&query="+x+"%2C"+y;
	}
}