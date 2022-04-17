import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField userbox, namebox, nidbox, phonebox, emailbox;
    @FXML PasswordField passbox, _passbox;
    @FXML ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();


    @FXML void studenthome(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Home.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerhome(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Home.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Login.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerloginpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Login.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void studentregpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void ownerregpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Register.fxml"));
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

        if(p != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
            root = loader.load();
            StudentController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else Global.notify("LOG IN FAILED!");
    }

    @FXML void ownerlogin(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String user = userbox.getText();
        String pass = passbox.getText();

        Owner p = Owner.login(user, pass);

        if(p != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
            root = loader.load();
            OwnerController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else Global.notify("LOG IN FAILED!");
    }

    @FXML void studentreg(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        String gender = genderbox.getValue();
        if(gender != "Male" && gender != "Female") gender = "Male";
        long user = Long.parseLong(userbox.getText());
        String pass = passbox.getText();
        String _pass = _passbox.getText();
        long nid = Long.parseLong(nidbox.getText());
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String bloodgroup = blgbox.getValue();
        Student p;

        try
        {
            p = new Student(user, name, pass, _pass, gender, nid, phone, email, bloodgroup);
            Global.AllStudents.put(p.id, p);
        }
        catch (Exception e)
        {
            p = null;
        }

        if(p != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/View.fxml"));
            root = loader.load();

            StudentController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML void ownerreg(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        String gender = genderbox.getValue();
        if(gender != "Male" && gender != "Female") gender = "Male";
        String user = userbox.getText();
        String pass = passbox.getText();
        String _pass = _passbox.getText();
        long nid = Long.parseLong(nidbox.getText());
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();
        String bloodgroup = blgbox.getValue();
        Owner p;

        try
        {
            p = new Owner(user, name, pass, _pass, gender, nid, phone, email, bloodgroup);
            Global.AllOwners.put(p.username, p);
        }
        catch (Exception e)
        {
            p = null;
        }

        if(p != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
            root = loader.load();

            OwnerController controller = loader.getController();
            controller.init(p);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML void exit(ActionEvent event)
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        genderbox.getItems().addAll(User.genderlist);
        genderbox.getSelectionModel().selectFirst();
        blgbox.getItems().addAll(User.bloodglist);
    }
}