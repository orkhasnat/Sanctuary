import java.lang.Math;
import java.util.HashMap;
import java.util.HashSet;

public class Global
{
	static final int FACTORS = 10;
	static final char[] SYMBOLS = ("_.#").toCharArray();

	static HashMap<String, Owner> AllOwners = new HashMap<>();
	static HashMap<Long, Student> AllStudents = new HashMap<>();
	static HashMap<Integer, Flat> AllFlats = new HashMap<>();
	static HashMap<Integer, Room> AllRooms = new HashMap<>();

	static int random(int min, int max)
	{
		max++;
		return (int)Math.floor(Math.random()*(max-min)+min);
	}

	static String encodePassword(String pass)
	{
		return pass;
	}

	static boolean checkIdentifier(String str, String xtra_sym)
	{
		if(str.length() == 0) return false;

		HashSet<Character> symbols = new HashSet<>();
		for(char c: SYMBOLS) symbols.add(c);

		char[] ch = xtra_sym.toCharArray();
		for(char c: ch) symbols.add(c);

		ch = str.toCharArray();
		for(char c: ch)
		{
			if(c >= 'a' && c <= 'z');
			else if(c >= 'A' && c <= 'Z');
			else if(c >= '0' && c <= '9');
			else if(symbols.contains(c));
			else return false;
		}

		return true;
	}

	static boolean checkIdentifier(String str)
	{
		return checkIdentifier(str, "");
	}

	static void notify(String msg)
	{
		System.out.println(msg+'\n');
	}
}