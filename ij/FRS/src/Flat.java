import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Flat
{
	public class Base
	{
		static String name, gender;
		static double x, y;
		static int level;
		static boolean lift, generator;
	}

	int id;
	private String name, gender;
	private int level;

	private Location location = new Location(23.94538493888004, 90.38274718424901);
	private boolean lift = false, generator = false;

	ArrayList<Room> rooms = new ArrayList<>();
	int rent;

	static boolean initialized = false;
	static double[] coff = new double[Global.FACTORS];
	static String[] genderlist={"Any", "Male", "Female"};

	double[] value = new double[Global.FACTORS];

	Flat(String owner, String _name, String _gender, double x, double y, int _level, boolean _lift, boolean _generator) throws Exception
	{
		id = getnewID();

		name = _name;
		updateGender(_gender);
		location = new Location(x, y);
		level = _level;

		lift = _lift;
		generator = _generator;

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"FlatID", "Name", "Gender", "X", "Y", "Level", "Owner", "Lift", "Generator"};
			Object[] params = {id, name, gender, location.x, location.y, level, owner, lift, generator};
			database.insert("flat", columns, params);
			System.out.println("REGISTRATION SUCCESSFUL!");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		// INSERT INTO Flat (FlatID, Name, Gender, X, Y, Level, Owner, Lift, Generator)
		// VALUES (id, name, gender, location.x, location.y, level, owner, lift, generator);
	}

	Flat(int _id, String _name, String _gender, double x, double y, int _level, boolean _lift, boolean _generator)
	{
		id = _id;
		name = _name;
		gender = _gender;
		location = new Location(x, y);
		level = _level;
		lift = _lift;
		generator = _generator;
	}

	private int getnewID()
	{
		int flatid = 1000000;

		try
		{
			while(true)
			{
				Flat.open(flatid);
				flatid++;
			}
		}
		catch (Exception e)
		{
			if(e.getMessage().equals("FLAT NOT FOUND IN THE DATABASE!")) return flatid;
		}

		return flatid;
	}

	/*private boolean updateName()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.print("Name: ");
		name = scan.nextLine();

		return true;
	}

	private boolean updateName(String temp)
	{
		name = temp;

		return true;
	}

	private boolean updateLocation()
	{
		System.out.println();
		System.out.println("Flat Location");
		System.out.println("---------------");
		location.update();

		return true;
	}

	private boolean updateLocation(double x, double y)
	{
		location = new Location(x, y);

		return true;
	}

	private boolean updateLevel()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.print("Level (starts from 1): ");
		level = scan.nextInt();

		return true;
	}

	private boolean updateLevel(int temp)
	{
		level = temp;

		return true;
	}*/

	private boolean updateGender(String temp)
	{
		switch(temp)
		{
			case "Male":
			case "Female":
			case "Any":
				gender = temp;
				return true;
		}

		return false;
	}

	/*private boolean updateLiftInfo()
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println();
		System.out.println("Lift:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		lift = (temp%2 != 0);

		return true;
	}

	private boolean updateGeneratorInfo()
	{
		Scanner scan = new Scanner(System.in);
		int temp;

		System.out.println();
		System.out.println("Generator:");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		temp = scan.nextInt();
		generator = (temp%2 != 0);

		return true;
	}

	private void addRoom()
	{
		Scanner scan = new Scanner(System.in);
		int choice;
		Room r;

		System.out.println();
		System.out.println("1. Bedroom.");
		System.out.println("2. Dining Room.");
		System.out.println("3. Living Room.");
		System.out.println("4. Kitchen.");
		System.out.println("5. Bathroom.");
		System.out.println("6. Balcony.");
		System.out.println("7. Store Room.");
		System.out.println("8. Extra Room.");
		System.out.println("9. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 9;

		if(choice == 1)
		{
			System.out.println();
			System.out.println("Bedroom Information");
			System.out.println("---------------");

			r = new Bedroom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 2)
		{
			System.out.println();
			System.out.println("Dining Room Information");
			System.out.println("---------------");

			r = new DiningRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 3)
		{
			System.out.println();
			System.out.println("Living Room Information");
			System.out.println("---------------");

			r = new LivingRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 4)
		{
			System.out.println();
			System.out.println("Kitchen Information");
			System.out.println("---------------");

			r = new Kitchen(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 5)
		{
			System.out.println();
			System.out.println("Bathroom Information");
			System.out.println("---------------");

			r = new Bathroom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 6)
		{
			System.out.println();
			System.out.println("Balcony Information");
			System.out.println("---------------");

			r = new Balcony(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 7)
		{
			System.out.println();
			System.out.println("Store Room Information");
			System.out.println("---------------");

			r = new StoreRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}

		else if(choice == 8)
		{
			System.out.println();
			System.out.println("Extra Room Information");
			System.out.println("---------------");

			r = new XtraRoom(id);
			Global.AllRooms.put(r.id, r);
			rooms.add(r);
		}
	}

	private void roomlist()
	{
		int i = 0;

		System.out.println();
		for(Room room: rooms)
		{
			System.out.println((i+1)+". "+room.name);
			i++;
		}
	}

	private void roomdisplay()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		roomlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= rooms.size()+1;

		if(choice != 0) rooms.get(choice-1).display();
	}

	private void roomview()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		roomlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= rooms.size()+1;

		if(choice != 0) rooms.get(choice-1).view();
	}*/

	private boolean setRent()
	{
		Scanner scan = new Scanner(System.in);
		int i;
		boolean flag = false;
		double temp;

		for(i = 0; i<Global.FACTORS; i++) value[i] = 0;
		init(); refresh();

		System.out.println();
		System.out.println("Rent Information");
		System.out.println("---------------");

		for(Room room: rooms)
		{
			int t = room.id/100000-1;
			value[t]+= room.value();
		}

		if(generator) value[8] = 1;
		else value[8] = 0;

		if(!lift && level>3) value[9] = -1;
		else value[9] = 0;

		temp = 0;
		for(i = 0; i<Global.FACTORS; i++)
		{
			temp += value[i]*coff[i];
			if(coff[i] != 1) flag = true;
		}

		System.out.println();
		if(flag) System.out.println("Standard Rent:\t\t" + temp);

		System.out.print("Rent:\t\t\t");
		rent = scan.nextInt();
		System.out.println();

		return true;

		// INSERT INTO Rent_Table (FlatID, Value, Rent)
		// VALUES (id, value, rent);
	}

	/*private void info()
	{
		System.out.println();
		System.out.println("Name: " + name);
		// Display Owner Info
		System.out.println("Rent: " + rent);

		// <a href = "https://www.google.com/maps/search/?api = 1&query = "+location.x+"%2C"+location.y>Location</a><br>
		System.out.println("Level: " + level);

		System.out.print("Lift:"); if(lift) System.out.println('o'); else System.out.println('x');
		System.out.print("Generator:"); if(generator) System.out.println('o'); else System.out.println('x');
	}

	void display()
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println();
			System.out.println("1. Display Rooms.");
			System.out.println("2. Back.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 2;

			if(choice == 1) roomdisplay();
		} while(choice != 0);
	}

	void display(long StudentID)
	{
		display();

		if(vacant)
		{
			// Will You Request Reservation
		}
	}

	void view()
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println();
			System.out.println("1. Edit Flat.");
			System.out.println("2. Delete Flat.");
			System.out.println("3. View Rooms.");
			System.out.println("4. Back.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 4;

			if(choice == 1) edit();

			else if(choice == 2)
			{
				delete();
				return;
			}

			else if(choice == 3) roomview();
		} while(choice != 0);
	}

	private void edit()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println();
		System.out.println("Edit Flat Information");
		System.out.println("---------------");
		System.out.println("1. Name.");
		System.out.println("2. Location");
		System.out.println("3. Level");
		System.out.println("4. Add Room.");
		System.out.println("5. Lift.");
		System.out.println("6. Generator.");
		System.out.println("7. Rent");
		System.out.println("8. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 8;

		if(choice == 1)
		{
			updateName();

			// UPDATE Flat
			// SET Name = name
			// WHERE FlatID = id;
		}

		else if(choice == 2)
		{
			updateLocation();

			// UPDATE Flat
			// SET X = location.x, Y = location.y
			// WHERE FlatID = id;
		}

		else if(choice == 3)
		{
			updateLevel();

			// UPDATE Flat
			// SET Level = level
			// WHERE FlatID = id;
		}

		else if(choice == 4) addRoom();

		else if(choice == 5)
		{
			updateLiftInfo();

			// UPDATE Flat
			// SET Lift = lift
			// WHERE FlatID = id;
		}

		else if(choice == 6)
		{
			updateGeneratorInfo();

			// UPDATE Flat
			// SET Generator = generator
			// WHERE FlatID = id;
		}

		else if(choice == 7)
		{
			if(vacant) setRent();

			// UPDATE Rent_Table
			// SET Rent = rent
			// WHERE FlatID = id;
		}
	}*/

	private void delete() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			Object[] params = {id};
			database.delete("flat", "FlatID = ?", params);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		// DELETE FROM Flat
		// WHERE FlatID = id;
	}

	static Flat open(int id) throws Exception
	{
		if(id == 0) return null;

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"FlatID", "Name", "Gender", "X", "Y", "Level", "Owner", "Lift", "Generator"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) throw new Exception("FLAT NOT FOUND IN THE DATABASE!");

			rs.next();
			int _id = rs.getInt("FlatID"), _level = rs.getInt("Level");
			String _name = rs.getString("Name"), _gender = rs.getString("Gender");
			double _x = rs.getDouble("X"), _y = rs.getDouble("Y");
			boolean _lift = rs.getBoolean("Lift"), _generator = rs.getBoolean("Generator");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("FLAT NOT FOUND IN THE DATABASE!");

			return new Flat(_id, _name, _gender, _x, _y, _level, _lift, _generator);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	String getName() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Name"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return null;

			rs.next();
			name = rs.getString("Name");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return name;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	String getGender() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Gender"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return null;

			rs.next();
			gender = rs.getString("Gender");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return gender;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	int getLevel() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Level"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return -1;

			rs.next();
			level = rs.getInt("Level");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return level;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	boolean getLift() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Lift"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return false;

			rs.next();
			lift = rs.getBoolean("Lift");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return lift;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	boolean getGenerator() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Generator"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return false;

			rs.next();
			generator = rs.getBoolean("Generator");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return generator;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	String getLocation() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"X", "Y"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return "https://www.google.com/maps";

			rs.next();
			double x = rs.getDouble("X");
			double y = rs.getDouble("Y");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return new Location(x, y).getLink();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	int getStudent() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"StudentID"};
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return -1;

			rs.next();
			int studentID = rs.getInt("StudentID");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return studentID;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	String getOwner() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Owner"};
			Object[] params = {id};
			ResultSet rs = database.select("flat", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return null;

			rs.next();
			String username = rs.getString("Owner");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return username;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	int getRent() throws Exception
	{
		if(1 == 1) return -1;

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Rent"};
			Object[] params = {id};
			ResultSet rs = database.select("rent_table", columns, "FlatID = ?", params);

			if(!rs.isBeforeFirst()) return -1;

			rs.next();
			rent = rs.getInt("Rent");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return rent;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	private void init()
	{
		if(initialized) return;
		initialized = true;

		for(int i = 0; i<Global.FACTORS; i++) coff[i] = 1;
	}

	private void refresh()
	{

	}

	void findOutliers()
	{

	}
}