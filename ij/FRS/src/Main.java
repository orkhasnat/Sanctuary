import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application
{
    @Override public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Student/Home.fxml"));
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("img/icon.png"));
        stage.setTitle("FRS");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    /*static void search()
    {
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println();
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

            System.out.println();
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
    }*/
}
