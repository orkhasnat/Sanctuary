import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField userbox;
    @FXML PasswordField passbox;

    @Override public void start(Stage primarystage) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Home-Student.fxml"));
        scene = new Scene(root/*, 1280, 720*/);
        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());

        primarystage.getIcons().add(new Image("img/icon.png"));
        primarystage.setTitle("FRS");

        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int choice;

        do
        {
            System.out.println("");
            System.out.println("1. Log In.");
            System.out.println("2. Register.");
            System.out.println("3. Launch.");
            System.out.println("4. Exit.");
            System.out.print("Enter Choice: ");
            choice = scan.nextInt();
            choice %= 4;

            if(choice == 1) login();
            else if(choice == 2) reg();
            else if(choice == 3) launch(args);
        } while(choice != 0);
    }

    @FXML void studentmode(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Home-Student.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownermode(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Home-Owner.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Login-Student.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Login-Owner.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        String user = userbox.getText();
        String pass = passbox.getText();

        System.out.println("Username: "+user);
        System.out.println("Password: "+Global.hash(pass+/*user+q.plc+*/"Home is Where the Start Is!"));

        Owner p = Owner.login(user, pass);
        if(p != null) { stage.close(); p.view(); }
        else Global.notify("LOG IN FAILED!");
    }

    @FXML void studentlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String user = userbox.getText();
        String pass = passbox.getText();

        System.out.println("Student ID: "+user);
        System.out.println("Password: "+Global.hash(pass+/*user+q.plc+*/"Home is Where the Start Is!"));

        Student p = Student.signin(Long.parseLong(user), pass);
        if(p != null) { stage.close(); p.view(); }
        else Global.notify("LOG IN FAILED!");
    }

    static void login()
    {
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("");
        System.out.println("1. As Student.");
        System.out.println("2. As Owner.");
        System.out.println("3. Back.");
        System.out.print("Enter Choice: ");
        choice = scan.nextInt();
        choice %= 3;

        if(choice == 1)
        {
            Student p = Student.signin();
            if(p != null) p.view();
            else Global.notify("LOG IN FAILED!");
        }

        else if(choice == 2)
        {
            Owner p = Owner.login();
            if(p != null) p.view();
            else Global.notify("LOG IN FAILED!");
        }
    }

    static void reg()
    {
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("");
        System.out.println("1. As Student.");
        System.out.println("2. As Owner.");
        System.out.println("3. Back.");
        System.out.print("Enter Choice: ");
        choice = scan.nextInt();
        choice %= 3;

        if(choice == 1)
        {
            System.out.println("");
            Student p = new Student();
            Global.AllStudents.put(p.id, p);
            p.view();
        }

        else if(choice == 2)
        {
            System.out.println("");
            Owner p = new Owner();
            Global.AllOwners.put(p.username, p);
            p.view();
        }
    }

    static void search()
    {
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("");
        System.out.println("1. Search Flat.");
        System.out.println("2. Search Owner.");
        System.out.println("3. Back.");
        System.out.print("Enter Choice: ");
        choice = scan.nextInt();
        choice %= 3;

        if(choice == 1)
        {
            Owner p;
            String user;

            System.out.println("");
            System.out.print("Username: ");
            user = scan.next();

            if(Global.AllOwners.containsKey(user))
            {
                p = Global.AllOwners.get(user);
                p.display();
            }

            else Global.notify("OWNER NOT FOUND!");
        }

        else if(choice == 2)
        {
            Flat f;

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
}
