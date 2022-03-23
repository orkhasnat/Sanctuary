import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TextField userbox, namebox, nidbox, phonebox, emailbox;
    @FXML private PasswordField passbox;
    @FXML private ChoiceBox<String> blgbox;

    @FXML void studenthome(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Home-Student.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerhome(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Home-Owner.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Login-Student.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Login-Owner.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentregpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Register-Student.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerregpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("Register-Owner.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        long user = Long.parseLong(userbox.getText());
        String pass = passbox.getText();

        Student p = Student.login(user, pass);
        if(p != null) { stage.close(); p.view(); }
        else Global.notify("LOG IN FAILED!");
    }

    @FXML void ownerlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String user = userbox.getText();
        String pass = passbox.getText();

        Owner p = Owner.login(user, pass);
        if(p != null) { stage.close(); p.view(); }
        else Global.notify("LOG IN FAILED!");
    }

    @FXML void studentreg(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        long user = Long.parseLong(userbox.getText());
        String pass = passbox.getText();
        long nid = Long.parseLong(nidbox.getText());
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String bloodgroup = blgbox.getValue();
        Student p;

        try
        {
            p = new Student(user, name, pass, nid, phone, email, bloodgroup);
            Global.AllStudents.put(p.id, p);
        }
        catch (Exception e)
        {
            p = null;
        }
        if(p != null) { stage.close(); p.view(); }
    }

    @FXML void ownerreg(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        String user = userbox.getText();
        String pass = passbox.getText();
        long nid = Long.parseLong(nidbox.getText());
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String bloodgroup = blgbox.getValue();
        Owner p;

        try
        {
            p = new Owner(user, name, pass, nid, phone, email, bloodgroup);
            Global.AllOwners.put(p.username, p);
        }
        catch (Exception e)
        {
            p = null;
        }
        if(p != null) { stage.close(); p.view(); }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
//        blgbox.getItems().addAll(User.bloodglist);
//        blgbox.setOnAction(this::getFood);
    }
}