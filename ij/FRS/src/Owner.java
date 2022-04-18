import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Owner extends User
{
	String username;

	Owner(String user, String _name, String pass, String _pass, String _gender, long _nid, long _phone, String _email, String _blg) throws Exception
	{
		Global.checkIdentifier(user, "Username");
		setPassword(pass, _pass);
		name = _name;
		nid = _nid;
		phone = _phone;
		email = _email;

		if(!setGender(_gender) || !updateBloodGroup(_blg))
			throw new Exception("Error!");

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Name", "Username", "Password", "PasswordLastChanged", "Gender", "NID", "Phone", "Email", "BloodGroup"};
			Object[] params = {name, username, password, plc, gender, nid, phone, email, bloodgroup};
			database.insert("owner", columns, params);
			System.out.println("REGISTRATION SUCCESSFUL!");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		// INSERT INTO Owner (Name, Username, Password, PasswordLastChanged, Gender, NID, Phone, Email)
		// VALUES (name, username, password, plc, nid, gender, phone, email);
	}

	Owner(String user, String _name, String pass, String _gender, long _nid, long _phone, String _email, String _blg, String _plc) throws Exception
	{
		username = user;
		password = pass;
		plc = _plc;
		name = _name;
		gender = _gender;
		nid = _nid;
		phone = _phone;
		email = _email;
		bloodgroup = _blg;
	}

	protected void setPassword(String pass, String _pass) throws Exception
	{
		Global.checkIdentifier(pass, "Password");

		if(pass.length() < 6) throw new Exception("PASSWORD TOO SHORT!");
		else if(pass.length() > 2000) throw new Exception("PASSWORD TOO LONG!");

		if(!pass.equals(_pass)) throw new Exception("PASSWORDS DO NOT MATCH!");

		plc = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"));
		password = Global.hash(pass+username+plc+"Home is Where the Start Is!");
	}

	protected boolean matchPassword(String pass)
	{
		try { pass = Global.hash(pass+username+plc+"Home is Where the Start Is!"); }
		catch(Exception e) { pass = ""; }

		return password.equals(pass);
	}

	/*private void flatlist() throws Exception
	{
		int i = 0;

		System.out.println();
		for(Flat flat: flats())
		{
			System.out.println((i+1)+". "+flat.name);
			i++;
		}
	}

	private void flatdisplay() throws Exception
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		flatlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= flats().size()+1;

		if(choice != 0) flats().get(choice-1).display();
	}

	private void flatview() throws Exception
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		flatlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= flats().size()+1;

		if(choice != 0) flats().get(choice-1).view();
	}

	private void info()
	{
		System.out.println("Name: " + name);
		System.out.println("Phone Number: " + phone);
		System.out.println("E-mail Address: " + email);
		System.out.println("Blood Group: " + bloodgroup);
	}

	void display() throws Exception
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println();
			System.out.println("1. Display Flats.");
			System.out.println("2. Back.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 2;

			if(choice == 1) flatdisplay();
		} while(choice != 0);
	}

	void view() throws Exception
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println();
			System.out.println("1. Edit Profile.");
			System.out.println("2. Delete Profile.");
			System.out.println("3. My Flats.");
			System.out.println("4. Sign Out.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 4;

			if(choice == 1) edit();

			else if(choice == 2)
			{
				String pass;
				System.out.println();
				System.out.print("Password: ");
				
				try
				{
					pass = scan.next();
					pass = Global.hash(pass+username+plc+"Home is Where the Start Is!");
				}
				catch(Exception e)
				{
					pass="";
				}

				if(password.equals(pass))
				{
					delete();
					return;
				}
			}

			else if(choice == 3) flatview();
		} while(choice != 0);
	}

	protected void edit()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println();
		System.out.println("Edit Profile");
		System.out.println("---------------");
		System.out.println("1. Name.");
		System.out.println("2. Phone Number.");
		System.out.println("3. E-mail Address.");
		System.out.println("4. NID.");
		System.out.println("5. Add Flat.");
		System.out.println("6. Change Password.");
		System.out.println("7. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 7;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Owner
			// SET Name = name
			// WHERE Username = username;
		}
		
		else if(choice == 2)
		{
			updatePhone();

			// UPDATE Owner
			// SET Phone = phone
			// WHERE Username = username;
		}
		
		else if(choice == 3)
		{
			updateEmail();

			// UPDATE Owner
			// SET Email = email
			// WHERE Username = username;
		}

		else if(choice == 4)
		{
			updateNID();

			// UPDATE Owner
			// SET NID = nid
			// WHERE Username = username;
		}

		else if(choice == 5) // addFlat();

		else if(choice == 6)
		{
			String pass;
			System.out.println();
			System.out.print("Password: ");
			
			try
			{
				pass = scan.next();
				pass = Global.hash(pass+username+plc+"Home is Where the Start Is!");
			}
			catch(Exception e)
			{
				pass="";
			}

			if(password.equals(pass))
			{
//				setPassword();

				// UPDATE Owner
				// SET Password = password, PasswordLastChanged = plc
				// WHERE Username = username;
			}
		}
	}*/

	protected void delete() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			Object[] params = {username};
			database.delete("owner", "Username = ?", params);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		// DELETE FROM Owner
		// WHERE Username = username;
	}

	static Owner login(String user, String pass) throws Exception
	{
		Owner p, q;

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Name", "Username", "Password", "PasswordLastChanged", "Gender", "NID", "Phone", "Email", "BloodGroup"};
			Object[] params = {user};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

			if(!rs.isBeforeFirst()) throw new Exception("USER NOT FOUND IN THE DATABASE!");

			rs.next();
			String _name = rs.getString("Name"), _user = rs.getString("Username"), _pass = rs.getString("Password"), _plc = rs.getString("PasswordLastChanged"), _gender = rs.getString("Gender"), _email = rs.getString("Email"), _blg = rs.getString("BloodGroup");
			long _nid = rs.getLong("NID"), _phone = rs.getLong("Phone");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("USER NOT FOUND IN THE DATABASE!");

			q = new Owner(_user, _name, _pass, _gender, _nid, _phone, _email, _blg, _plc);
			if(q.matchPassword(pass)) p = q;
			else throw new Exception("WRONG PASSWORD!");

			return p;
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
			Object[] params = {username};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

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
			Object[] params = {username};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

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

	String getMail() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Email"};
			Object[] params = {username};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

			if(!rs.isBeforeFirst()) return null;

			rs.next();
			email = rs.getString("Email");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return email;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	String getBloodGroup() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"BloodGroup"};
			Object[] params = {username};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

			if(!rs.isBeforeFirst()) return null;

			rs.next();
			bloodgroup = rs.getString("BloodGroup");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return bloodgroup;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	long getNID() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"NID"};
			Object[] params = {username};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

			if(!rs.isBeforeFirst()) return -1;

			rs.next();
			nid = rs.getLong("NID");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return nid;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	long getPhone() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Phone"};
			Object[] params = {username};
			ResultSet rs = database.select("owner", columns, "Username = ?", params);

			if(!rs.isBeforeFirst()) return -1;

			rs.next();
			phone = rs.getLong("Phone");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return phone;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	ArrayList<Flat> flats() throws Exception
	{
		ArrayList<Flat> _flats = new ArrayList<>();

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"FlatID", "Name", "Gender", "X", "Y", "Level", "Owner", "Lift", "Generator"};
			Object[] params = {username};
			ResultSet rs = database.select("flat", columns, "Owner = ?", params);

			if(!rs.isBeforeFirst()) throw new Exception("FLAT NOT FOUND IN THE DATABASE!");

			while(rs.next())
			{
				int _id = rs.getInt("FlatID"), _level = rs.getInt("Level");
				String _name = rs.getString("Name"), _gender = rs.getString("Gender");
				double _x = rs.getDouble("X"), _y = rs.getDouble("Y");
				boolean _lift = rs.getBoolean("Lift"), _generator = rs.getBoolean("Generator");

				_flats.add(new Flat(_id, _name, _gender, _x, _y, _level, _lift, _generator));
			}

			return _flats;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}