import java.util.Scanner;

abstract class User
{
    protected String name, password, plc, email, bloodgroup;
    protected long nid, phone;

    protected boolean updateName()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.print("Name: ");
        name = scan.nextLine();

        return true;
    }

    abstract protected boolean setPassword();

    protected boolean updatePhone()
    {
        Scanner scan = new Scanner(System.in);
        long temp;

        System.out.println();
        System.out.print("Phone Number: ");
        temp = scan.nextLong();

        // Check if phone number is valid and if it already exists in database
        phone = temp;

        return true;
    }

    protected boolean updateEmail()
    {
        Scanner scan = new Scanner(System.in);
        String temp;

        System.out.println();
        System.out.print("E-mail Address: ");
        temp = scan.next();

        // Check if email is valid and if it already exists in database
        email = temp;

        return true;
    }

    protected boolean updateNID()
    {
        Scanner scan = new Scanner(System.in);
        long temp;

        System.out.println();
        System.out.print("NID Number: ");
        temp = scan.nextLong();

        // Check if nid number is valid and if it already exists in database
        nid = temp;

        return true;
    }

    protected boolean updateBloodGroup()
    {
        Scanner scan = new Scanner(System.in);
        int i, choice;

        String[] bg={"I don't know", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

        System.out.println();
        System.out.println("Blood Group");
        System.out.println("---------------");
        for(i=1; i<=9; i++) System.out.println(i + ". " + bg[i%9]);
        System.out.print("Enter Choice: ");
        choice = scan.nextInt();
        choice %= 9;

        bloodgroup = bg[choice];

        return  true;
    }

    abstract void display();
    abstract void view();
    abstract protected void edit();
    abstract protected void delete();
}