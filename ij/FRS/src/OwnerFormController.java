import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class OwnerFormController implements Initializable
{
    Owner owner;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField namebox, phonebox = new TextField(), emailbox = new TextField(), passbox, _passbox, oldpassbox, xbox, ybox;
    @FXML Hyperlink locationbutton = new Hyperlink();
    @FXML ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>(), flatgenderbox = new ComboBox<>();
    @FXML ComboBox<Integer> levelbox = new ComboBox<>();
    @FXML CheckBox liftbox, generatorbox;

    void init(Owner p) throws Exception
    {
        owner = p;
        if(owner == null) System.exit(0);

        phonebox.setText(String.valueOf(owner.getPhone()));
        emailbox.setText(owner.getMail());
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
        root = loader.load();

        OwnerController controller = loader.getController();
        controller.init(owner);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void edit(ActionEvent event) throws Exception
    {
        long phone = Long.parseLong(phonebox.getText());
        String email = emailbox.getText();

        String pass = passbox.getText();
        if(!owner.matchPassword(pass)) throw new Exception("WRONG PASSWORD!");
        owner.update(phone, email);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
        root = loader.load();

        OwnerController controller = loader.getController();
        controller.init(owner);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void resetpass(ActionEvent event) throws Exception
    {
        String pass = oldpassbox.getText(), _pass;
        if(!owner.matchPassword(pass)) throw new Exception("WRONG PASSWORD!");

        pass = passbox.getText();
        _pass = _passbox.getText();

        owner.changePassword(pass, _pass);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/View.fxml"));
        root = loader.load();

        OwnerController controller = loader.getController();
        controller.init(owner);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event) throws Exception
    {
        String pass = passbox.getText();
        if(!owner.matchPassword(pass)) throw new Exception("WRONG PASSWORD!");
        owner.delete();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Login.fxml"));
        root = loader.load();

        OwnerController controller = loader.getController();
        controller.init(owner);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addflat(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText(), gender = genderbox.getValue();
        double x = Double.parseDouble(xbox.getText()), y = Double.parseDouble(ybox.getText());
        int level = levelbox.getValue();
        boolean lift = liftbox.isSelected(), generator = generatorbox.isSelected();
        Flat f;

        try
        {
            owner = Owner.login("ork", "000000");
            f = new Flat(owner.username, name, gender, x, y, level, lift, generator);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
            root = loader.load();
            FlatController controller = loader.getController();
            controller.init(f);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        flatgenderbox.getItems().addAll(Flat.genderlist);
        flatgenderbox.getSelectionModel().selectFirst();
        blgbox.getItems().addAll(User.bloodglist);
        levelbox.getItems().addAll(IntStream.of(IntStream.rangeClosed(1,25).toArray()).boxed().toArray(Integer[]::new));

        locationbutton.setOnAction(new EventHandler<ActionEvent>()
                                   {
                                       @Override public void handle(ActionEvent event)
                                       {
                                           try
                                           {
                                               Desktop.getDesktop().browse(new URI("https://www.google.com/maps"));
                                           }
                                           catch (Exception e)
                                           {
                                               e.printStackTrace();
                                               Alert alert = new Alert(Alert.AlertType.ERROR);
                                               alert.setContentText(e.getMessage());
                                               alert.show();
                                           }
                                       }
                                   }
        );
    }
}