import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable
{
    User user;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String name, plc, email, bloodgroup;
    private long nid, phone;

    StudentController(User p)
    {
        user = p;
        name = p.getName();
        plc = p.getPLC();
        email = p.getMail();
        bloodgroup = p.getBloodGroup();
        nid = p.getNID();
        phone = p.getPhone();
    }

    @FXML void display(Stage _stage) throws Exception
    {
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phone);
        System.out.println("E-mail Address: " + email);
        System.out.println("Blood Group: " + bloodgroup);

        root = FXMLLoader.load(getClass().getResource("fxml/View-Student.fxml"));
        scene = new Scene(root);
        stage = _stage;

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}