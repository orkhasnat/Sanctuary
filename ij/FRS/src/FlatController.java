import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class FlatController implements Initializable
{
    Flat flat;
    Flat.Base flatbase;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Stack<String> stack = new Stack<>();

    @FXML private Label nameLabel, blgLabel, roomcountlabel;
    @FXML private TextField namebox, nidbox, phonebox, emailbox, xbox, ybox;
    @FXML private PasswordField passbox, _passbox, oldpassbox;
    @FXML private Button flatbutton = new Button();
    @FXML private ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();
    @FXML private ComboBox<Integer> levelbox = new ComboBox<>();
    @FXML private CheckBox liftbox, generatorbox;
    @FXML private ImageView blgicon;

    void init(Flat f)
    {
        flat = f;
        if(flat == null) System.exit(0);

        /*nameLabel.setText(nameLabel.getText()+p.getName());

        setBloodGroup();
        setFlat();
        stack.push("view");*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        genderbox.getItems().addAll(User.genderlist);
        blgbox.getItems().addAll(User.bloodglist);
        flatbutton.setVisible(false);
    }
}