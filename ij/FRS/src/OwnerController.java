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

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.stream.IntStream;

public class OwnerController implements Initializable
{
    Owner owner;
    Flat.Base flatbase;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Stack<String> stack = new Stack<>();

    @FXML private Label nameLabel, blgLabel, roomcountlabel;
    @FXML private TextField namebox, nidbox, phonebox, emailbox, xbox, ybox;
    @FXML private PasswordField passbox, _passbox, oldpassbox;
    @FXML private ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();
    @FXML private ComboBox<Integer> levelbox = new ComboBox<>();
    @FXML private MenuButton menubutton;
    @FXML private CheckBox liftbox, generatorbox;
    @FXML private ImageView blgicon;

    void init(Owner p)
    {
        owner = p;
        if(owner == null) System.exit(0);

        nameLabel.setText(nameLabel.getText()+p.getName());

        for(Flat flat: owner.flats) menubutton.getItems().add(new MenuItem(flat.name));
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
            case "set":
                settingspage(event);
                break;
            case "myflats":
                myflatpage(event);
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

    @FXML void editpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        /*namebox.setText(owner.getName());
        nidbox.setText(String.valueOf(owner.getNID()));
        phonebox.setText(String.valueOf(owner.getPhone()));
        emailbox.setText(owner.getMail());

        System.out.println(nameLabel.getText());
        System.out.println("Name: " + namebox.getText());
        System.out.println("NID Number: " + owner.getNID());
        System.out.println("Phone Number: " + "+880" + owner.getPhone());
        System.out.println("E-mail Address: " + owner.getMail());*/

        stage.setScene(scene);
        stage.show();
    }

    @FXML void settingspage(ActionEvent event) throws Exception
    {
        stack.push("set");
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Settings.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void myflatpage(ActionEvent event) throws Exception
    {
        stack.push("myflats");
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void newflatpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Flat/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML public void addflat(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        flatbase.name = namebox.getText();
        flatbase.gender = genderbox.getValue();
        flatbase.x = Double.parseDouble(xbox.getText());
        flatbase.y = Double.parseDouble(ybox.getText());
        flatbase.level = levelbox.getValue();
        flatbase.lift = liftbox.isSelected();
        flatbase.generator = generatorbox.isSelected();
        Flat f;

        try
        {
            owner = Owner.login("ork", "000000");
            f = new Flat(owner.username, flatbase);
            owner.flats.add(f);
            Global.AllFlats.put(f.id, f);
        }
        catch (Exception e)
        {
            f = null;
        }

        if(f != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
            root = loader.load();
            FlatController controller = loader.getController();
            controller.init(f);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else Global.notify("TASK FAILED!");
    }

    public void passpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Password.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void deletepage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Owner/Delete.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void edit(ActionEvent event) throws Exception
    {
        System.out.println(namebox.getText());
        System.out.println(nidbox.getText());
        System.out.println(phonebox.getText());
        System.out.println(emailbox.getText());
        System.out.println(blgbox.getValue());

        if(owner == null) System.out.println("NULL!");
        else
        {
            String pass = passbox.getText();
            if(!owner.matchPassword(pass)) return;

            String _name = owner.getName(), _email = owner.getMail(), _bloodgroup = owner.getBloodGroup();
            long _nid = owner.getNID(), _phone = owner.getPhone();

            if(!owner.updateName(namebox.getText()) || !owner.updateNID(Long.parseLong(nidbox.getText())) || !owner.updateEmail(emailbox.getText()) || !owner.updatePhone(Long.parseLong(phonebox.getText())) || !owner.updateBloodGroup(blgbox.getValue()))
            {
                owner.updateName(_name);
                owner.updateNID(_nid);
                owner.updateEmail(_email);
                owner.updatePhone(_phone);
                owner.updateBloodGroup(_bloodgroup);
            }
        }

        back(event);
    }

    public void resetpass(ActionEvent event) throws Exception
    {
        if(owner == null) System.out.println("NULL!");
        else
        {
            String pass = oldpassbox.getText(), _pass;
            if(!owner.matchPassword(pass)) return;

            pass = passbox.getText();
            _pass = _passbox.getText();

            owner.setPassword(pass, _pass);
        }

        back(event);
    }

    public void delete(ActionEvent event) throws Exception
    {
        if(owner == null) System.out.println("NULL!");
        else
        {
            String pass = passbox.getText();
            if(!owner.matchPassword(pass)) return;

            owner.delete();
        }

        logout(event);
    }

    @FXML void logout(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Home.fxml"));
        root = loader.load();

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        genderbox.getItems().addAll(User.genderlist);
        blgbox.getItems().addAll(User.bloodglist);
        levelbox.getItems().addAll(IntStream.of(IntStream.rangeClosed(1,25).toArray()).boxed().toArray(Integer[]::new));
    }
}