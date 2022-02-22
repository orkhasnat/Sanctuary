import java.lang.Math;

public class Global
{
	static final int FACTORS = 10;
	static final String DATABASE = "Flats.csv";

	static int random(int min, int max)
	{
		max++;
		return (int)Math.floor(Math.random()*(max-min)+min);
	}
}