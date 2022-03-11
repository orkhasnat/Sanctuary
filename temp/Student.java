import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student
{
	String name, password, plc, phone, email;
	long id, nid;
	int FlatID = 0;

	Student()
	{
		System.out.println("Student Information");
		System.out.println("---------------");

		updateName();
		while(!setIUT_ID());
		while(!setPassword());
		
		updateNID();
		updatePhone();
		updateEmail();

        // INSERT INTO Student (Name, StudentID, Password, PasswordLastChanged, NID, Phone, Email)
		// VALUES (name, id, password, plc, nid, phone, email);
	}

	void updateName()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("");
		System.out.print("Name: ");
		name = scan.nextLine();
	}

	boolean setIUT_ID()
	{
		Scanner scan = new Scanner(System.in);
		long temp;

		System.out.println("");
		System.out.print("IUT ID: ");
		temp = scan.nextLong();
		
		if(Global.AllStudents.containsKey(temp)) // Check from DATABASE
		{
			Global.notify("ID ALREADY EXISTS! PLEASE TRY SIGNING IN.");
			return false;
		}

		id = temp;
		return true;
	}

	boolean setPassword()
	{
		Scanner scan = new Scanner(System.in);
		String pass, temp;

		System.out.println("");
		System.out.print("Enter Password: ");
		pass = scan.next();
		
		if(!Global.checkIdentifier(pass) || pass.length() < 6 || pass.length() > 2000)
		{
			Global.notify("INVALID PASSWORD!");
			return false;
		}

		System.out.println("");
		System.out.print("Re-enter Password: ");
		temp = scan.next();
		
		if(!temp.equals(pass))
		{
			Global.notify("PASSWORDS DO NOT MATCH!");
			return false;
		}

		try
		{
			plc = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"));
			password = Global.hash(pass+id+plc+"Home is Where the Start Is!");
		}
		catch(Exception e)
		{
			return false;
		}

		return true;
	}

	void updatePhone()
	{
		Scanner scan = new Scanner(System.in);
		String temp;

		System.out.println("");
		System.out.print("Phone Number: ");
		temp = scan.next();

		// Check if phone number is valid and if it already exists in database
		phone = temp;
	}

	void updateEmail()
	{
		Scanner scan = new Scanner(System.in);
		String temp;

		System.out.println("");
		System.out.print("E-mail Address: ");
		temp = scan.next();

		// Check if email is valid and if it already exists in database
		email = temp;
	}

	void updateNID()
	{
		Scanner scan = new Scanner(System.in);
		long temp;

		System.out.println("");
		System.out.print("NID Number: ");
		temp = scan.nextLong();

		// Check if nid number is valid and if it already exists in database
		nid = temp;
	}

	void display()
	{
		System.out.println("Name: " + name);
		System.out.println("Phone Number: " + phone);
		System.out.println("E-mail Address: " + email);
	}

	void view()
	{
		display();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println("");
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
				System.out.println("");
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

					System.out.println("");
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

	void edit()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("");
		System.out.println("Edit Profile");
		System.out.println("---------------");
		System.out.println("1. Name.");
		System.out.println("2. Phone Number.");
		System.out.println("3. E-mail Address.");
		System.out.println("4. NID.");
		System.out.println("5. Change Password.");
		System.out.println("6. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= 6;
		
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
			String pass;
			System.out.println("");
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
				setPassword();

				// UPDATE Student
				// SET Password = password, PasswordLastChanged = plc
				// WHERE StudentID = id;
			}
		}
	}

	void delete()
	{
		Global.AllStudents.remove(id);

		// DELETE FROM Student
		// WHERE StudentID = id;
	}

	static Student signin()
	{
		Scanner scan = new Scanner(System.in);
		Student p = null, q;
		long user;
		String pass;

		System.out.println("");
		System.out.print("ID: ");
		user = scan.nextLong();
		System.out.print("Password: ");
		pass = scan.next();

		if(Global.AllStudents.containsKey(user)) q = Global.AllStudents.get(user);
		else return null;

		try
		{
			pass = Global.hash(pass+Long.toString(user)+q.plc+"Home is Where the Start Is!");
		}
		catch(Exception e)
		{
			return null;
		}

		if(q.password.equals(pass)) p = q;
		else return null;

		return p;
	}
}