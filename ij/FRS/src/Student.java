import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class Student extends User
{
	long id;
	private String bloodgroup, gender;

	Student(long user, String _name, String pass, String _pass, String _gender, long _nid, long _phone, String _email, String _blg) throws Exception
	{
		id = user;
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
			String[] columns = {"Name", "StudentID", "Password", "PasswordLastChanged", "NID", "Phone", "Email", "BloodGroup"};
			Object[] params = {name, id, password, plc, nid, phone, email, bloodgroup};
			database.insert("student", columns, params);
			System.out.println("REGISTRATION SUCCESSFUL!");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		// INSERT INTO Student (Name, StudentID, Password, PasswordLastChanged, NID, Phone, Email, BloodGroup)
		// VALUES (name, id, password, plc, nid, phone, email, bloodgroup);
	}

	Student(int user, String _name, String pass, String _gender, long _nid, long _phone, String _email, String _blg, String _plc) throws Exception
	{
		id = user;
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
		password = Global.hash(pass+id+plc+"Home is Where the Start Is!");
	}

	protected boolean matchPassword(String pass)
	{
		try { pass = Global.hash(pass+id+plc+"Home is Where the Start Is!"); }
		catch(Exception e) { pass = ""; }

		return password.equals(pass);
	}

	void changePassword(String pass, String _pass) throws Exception
	{
		setPassword(pass, _pass);

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Password", "PasswordLastChanged"};
			Object[] params = {password, plc, id};
			database.update("student", columns, "StudentID = ?", params);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	void update(long phone, String email, String bloodgroup) throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Phone", "Email", "BloodGroup"};
			Object[] params = {phone, email, bloodgroup, id};
			database.update("student", columns, "StudentID = ?", params);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	private boolean updateBloodGroup(String temp)
	{
		if(temp==null) temp = bloodglist[0];
		bloodgroup = temp;

		return  true;
	}

	protected boolean setGender(String temp)
	{
		switch (temp)
		{
			case "Male":
			case "Female":
				gender = temp;
				return true;
		}

		return false;
	}

	/*void display() throws Exception
	{
		System.out.println("Name: " + name);
		System.out.println("Phone Number: " + phone);
		System.out.println("E-mail Address: " + email);
		System.out.println("Blood Group: " + bloodgroup);
	}

	void view() throws Exception
	{
		display();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println();
			System.out.println("1. Edit Profile.");
			System.out.println("2. Delete Profile.");
			System.out.println("3. My Flat/ Display a Flat.");
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
					pass = Global.hash(pass+id+plc+"Home is Where the Start Is!");
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

			else if(choice == 3)
			{
				Flat f;
				
				if(Global.AllFlats.containsKey(FlatID))
				{
					f = Global.AllFlats.get(FlatID);
					f.display();
				}

				else
				{
					int temp;

					System.out.println();
					System.out.print("Flat ID: ");
					temp = scan.nextInt();

					if(Global.AllFlats.containsKey(temp))
					{
						f = Global.AllFlats.get(temp);
						f.display();
					}
					else Global.notify("FLAT NOT FOUND!");
				}
			}
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
		System.out.println("5. Blood Group.");
		System.out.println("6. Change Password.");
		System.out.println("7. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 7;
		
		if(choice == 1)
		{
			updateName();

			// UPDATE Student
			// SET Name = name
			// WHERE StudentID = id;
		}
		
		else if(choice == 2)
		{
			updatePhone();

			// UPDATE Student
			// SET Phone = phone
			// WHERE StudentID = id;
		}
		
		else if(choice == 3)
		{
			updateEmail();

			// UPDATE Student
			// SET Email = email
			// WHERE StudentID = id;
		}

		else if(choice == 4)
		{
			updateNID();

			// UPDATE Student
			// SET NID = nid
			// WHERE StudentID = id;
		}

		else if(choice == 5)
		{
			updateBloodGroup();

			// UPDATE Student
			// SET BloodGroup = bloodgroup
			// WHERE StudentID = id;
		}

		else if(choice == 6)
		{
			String pass;
			System.out.println();
			System.out.print("Password: ");
			
			try
			{
				pass = scan.next();
				pass = Global.hash(pass+id+plc+"Home is Where the Start Is!");
			}
			catch(Exception e)
			{
				pass="";
			}

			if(password.equals(pass))
			{
//				setPassword();

				// UPDATE Student
				// SET Password = password, PasswordLastChanged = plc
				// WHERE StudentID = id;
			}
		}
	}*/

	protected void delete() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			Object[] params = {id};
			database.delete("student", "StudentID = ?", params);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		// DELETE FROM Student
		// WHERE StudentID = id;
	}

	static Student login(long user, String pass) throws Exception
	{
		Student p, q;

		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Name", "StudentID", "Password", "PasswordLastChanged", "Gender", "NID", "Phone", "Email", "BloodGroup"};
			Object[] params = {user};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

			if(!rs.isBeforeFirst()) throw new Exception("USER NOT FOUND IN THE DATABASE!");

			rs.next();
			int _user = rs.getInt("StudentID");
			String _name = rs.getString("Name"), _pass = rs.getString("Password"), _plc = rs.getString("PasswordLastChanged"), _gender = rs.getString("Gender"), _email = rs.getString("Email"), _blg = rs.getString("BloodGroup");
			long _nid = rs.getLong("NID"), _phone = rs.getLong("Phone");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("USER NOT FOUND IN THE DATABASE!");

			q = new Student(_user, _name, _pass, _gender, _nid, _phone, _email, _blg, _plc);
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

	int getBatch()
	{
		int temp = (int) id;
		while(temp>100) temp /= 10;
		return temp+2000;
	}

	String getDept()
	{
		int temp = (int) (id%100000);
		temp /= 1000;

		String dept = "";

		switch(temp)
		{
			case 41:
				dept = "Computer Science and Engineering";
				break;
			case 42:
				dept = "Software Engineering";
				break;
		}

		return dept;
	}

	int getSection()
	{
		int temp = (int) id;
		temp %= 1000;
		temp /= 100;
		return temp;
	}

	String getName() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"Name"};
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

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
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

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
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

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
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

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
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

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
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

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

	Flat getFlat() throws Exception
	{
		try
		{
			Database database = new Database("sanctuary", "root", "");
			String[] columns = {"FlatID"};
			Object[] params = {id};
			ResultSet rs = database.select("student", columns, "StudentID = ?", params);

			if(!rs.isBeforeFirst()) return null;

			rs.next();
			int flatID = rs.getInt("FlatID");

			rs.next();
			if(!rs.isAfterLast()) throw new Exception("DATABASE ERROR!");

			return Flat.open(flatID);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}