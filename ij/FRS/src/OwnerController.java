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

public class OwnerController implements Initializable
{
    Owner owner;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String name, plc, email, bloodgroup;
    private long nid, phone;

    OwnerController(Owner p)
    {
        owner = p;
        name = p.getName();
        plc = p.getPLC();
        email = p.getMail();
        bloodgroup = p.getBloodGroup();
        nid = p.getNID();
        phone = p.getPhone();
    }

    @FXML void display(ActionEvent event) throws Exception
    {
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phone);
        System.out.println("E-mail Address: " + email);
        System.out.println("Blood Group: " + bloodgroup);

        root = FXMLLoader.load(getClass().getResource("fxml/View-Owner.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}