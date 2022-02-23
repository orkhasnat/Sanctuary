import java.util.Scanner;
import java.util.ArrayList;

public class Owner
{
	String name, username, password, phone, email;
	long nid;

	ArrayList<Flat> flats = new ArrayList<>();

	Owner()
	{
		Scanner scan = new Scanner(System.in);
		int i, temp;

		System.out.println("Owner Information");
		System.out.println("---------------");

		updateName();
		while(!setUsername());
		while(!setPassword());
		
		updateNID();
		updatePhone();
		updateEmail();

		// INSERT INTO Owner (Name, Username, Password, NID, Phone, Email)
		// VALUES (name, username, password, nid, phone, email);
	}

	void updateName()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("");
		System.out.print("Name: ");
		name = scan.nextLine();
	}

	boolean setUsername()
	{
		Scanner scan = new Scanner(System.in);
		String user;

		System.out.println("");
		System.out.print("Username: ");
		user = scan.next();
		
		if(!Global.checkIdentifier(user))
		{
			Global.notify("INVALID USERNAME!");
			return false;
		}
		
		if(Global.AllOwners.containsKey(user)) // Check from DATABASE
		{
			Global.notify("USERNAME NOT AVAILABLE! PLEASE TRY SOMETHING DIFFERENT.");
			return false;
		}

		username = user;
		return true;
	}

	boolean setPassword()
	{
		Scanner scan = new Scanner(System.in);
		String pass, temp;

		System.out.println("");
		System.out.print("Enter Password: ");
		pass = scan.next();
		
		if(!Global.checkIdentifier(pass))
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

		password = Global.encodePassword(pass);
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

	void addFlat()
	{
		System.out.println("");
		Flat f = new Flat(username);
		flats.add(f);
		Global.AllFlats.put(f.id, f);
	}

	void flatlist()
	{
		int i = 0;

		System.out.println("");
		for(Flat flat: flats)
		{
			System.out.println((i+1)+". "+flat.name);
			i++;
		}
	}

	void flatdisplay()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		flatlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= flats.size()+1;

		if(choice != 0) flats.get(choice-1).display();
	}

	void flatview()
	{
		Scanner scan = new Scanner(System.in);
		int choice;

		flatlist();
		System.out.println("0. Back.");
		System.out.print("Enter Choice: ");
		choice = scan.nextInt();
		choice %= flats.size()+1;

		if(choice != 0) flats.get(choice-1).view();
	}

	void info()
	{
		System.out.println("Name: " + name);
		System.out.println("Phone Number: " + phone);
		System.out.println("E-mail Address: " + email);
	}

	void display()
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println("");
			System.out.println("1. Display Flats.");
			System.out.println("2. Back.");
			System.out.print("Enter Choice: ");
			choice = scan.nextInt();
			choice %= 2;

			if(choice == 1) flatdisplay();
		} while(choice != 0);
	}

	void view()
	{
		info();
		Scanner scan = new Scanner(System.in);

		int choice;
		do
		{
			System.out.println("");
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
				System.out.println("");
				System.out.print("Password: ");
				pass = scan.next();

				if(password.equals(Global.encodePassword(pass)))
				{
					delete();
					return;
				}
			}

			else if(choice == 3) flatview();
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

		else if(choice == 5) addFlat();

		else if(choice == 6)
		{
			String pass;
			System.out.println("");
			System.out.print("Password: ");
			pass = scan.next();

			if(password.equals(Global.encodePassword(pass)))
			{
				setPassword();

				// UPDATE Owner
				// SET Password = password
				// WHERE Username = username;
			}
		}
	}

	void delete()
	{
		Global.AllOwners.remove(username);

		// DELETE FROM Owner
		// WHERE Username = username;
	}

	static Owner signin()
	{
		Scanner scan = new Scanner(System.in);
		Owner p = null, q;
		String user, pass;

		System.out.println("");
		System.out.print("Username: ");
		user = scan.next();
		System.out.print("Password: ");
		pass = scan.next();

		if(Global.AllOwners.containsKey(user)) q = Global.AllOwners.get(user);
		else return null;

		if(q.password.equals(Global.encodePassword(pass))) p = q;
		else return null;

		return p;
	}
}