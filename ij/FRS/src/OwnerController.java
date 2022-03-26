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

public class OwnerController implements Initializable
{
    Owner owner;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Stack<String> stack = new Stack<>();

    @FXML private Label nameLabel, blgLabel;
    @FXML private TextField namebox, nidbox, phonebox, emailbox;
    @FXML private PasswordField passbox, _passbox, oldpassbox;
    @FXML private Button flatbutton = new Button();
    @FXML private ComboBox<String> blgbox = new ComboBox<>();
    @FXML private ImageView blgicon;

    void init(Owner p)
    {
        owner = p;
        if(owner == null) System.exit(0);

        nameLabel.setText(nameLabel.getText()+p.getName());

        setBloodGroup();
        stack.push("view");
    }

    private void setBloodGroup()
    {
        for(int i=1; i<User.bloodglist.length; i++)
        {
            if (owner.getBloodGroup().equals(User.bloodglist[i]))
            {
                blgLabel.setText(User.bloodglist[i]);
                return;
            }
        }

        blgicon.setVisible(false);
        blgLabel.setVisible(false);
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        if(stack.isEmpty())
        {
            view(event);
            return;
        }

        String page = stack.pop();
        switch(page)
        {
            case "view":
                view(event);
                break;
        }
    }

    @FXML void view(ActionEvent event) throws Exception
    {
        stack.push("view");
        if(owner == null) System.out.println("NULL!");

        root = FXMLLoader.load(getClass().getResource("fxml/Owner/View.fxml"));

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        blgbox.getItems().addAll(User.bloodglist);
        flatbutton.setVisible(false);
    }
}