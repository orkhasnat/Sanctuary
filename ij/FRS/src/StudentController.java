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

public class StudentController implements Initializable
{
    Student student;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Stack<String> stack = new Stack<>();

    @FXML private Label nameLabel, batchLabel, deptLabel, sectionLabel, blgLabel;
    @FXML private TextField namebox, nidbox, phonebox, emailbox;
    @FXML private PasswordField passbox, _passbox, oldpassbox;
    @FXML private Button flatbutton = new Button();
    @FXML private ComboBox<String> blgbox = new ComboBox<>(), genderbox = new ComboBox<>();
    @FXML private ImageView blgicon;

    void init(Student p)
    {
        student = p;
        if(student == null) System.exit(0);

        nameLabel.setText(nameLabel.getText()+p.getName());
        batchLabel.setText(batchLabel.getText()+p.getBatch());
        deptLabel.setText(deptLabel.getText()+p.getDept());
        sectionLabel.setText(sectionLabel.getText()+p.getSection());

        setBloodGroup();
        setFlat();
        stack.push("view");
    }

    private void setBloodGroup()
    {
        for(int i=1; i<User.bloodglist.length; i++)
        {
            if (student.getBloodGroup().equals(User.bloodglist[i]))
            {
                blgLabel.setText(User.bloodglist[i]);
                return;
            }
        }

        blgicon.setVisible(false);
        blgLabel.setVisible(false);
    }

    private void setFlat()
    {
        if(student.checkFlat()) flatbutton.setVisible(false);
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
        }
    }

    @FXML void view(ActionEvent event) throws Exception
    {
        stack.push("view");
        if(student == null) System.out.println("NULL!");

        root = FXMLLoader.load(getClass().getResource("fxml/Student/View.fxml"));

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        /*namebox.setText(student.getName());
        nidbox.setText(String.valueOf(student.getNID()));
        phonebox.setText(String.valueOf(student.getPhone()));
        emailbox.setText(student.getMail());

        System.out.println(nameLabel.getText());
        System.out.println("Name: " + namebox.getText());
        System.out.println("NID Number: " + student.getNID());
        System.out.println("Phone Number: " + "+880" + student.getPhone());
        System.out.println("E-mail Address: " + student.getMail());*/

        stage.setScene(scene);
        stage.show();
    }

    @FXML void settingspage(ActionEvent event) throws Exception
    {
        stack.push("set");
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Settings.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void discoverpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Discover.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void myflatpage(ActionEvent event) throws Exception
    {
        
    }

    public void passpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Password.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void deletepage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Student/Delete.fxml"));
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

        if(student == null) System.out.println("NULL!");
        else
        {
            String pass = passbox.getText();
            if(!student.matchPassword(pass)) return;

            String _name = student.getName(), _email = student.getMail(), _bloodgroup = student.getBloodGroup();
            long _nid = student.getNID(), _phone = student.getPhone();

            if(!student.updateName(namebox.getText()) || !student.updateNID(Long.parseLong(nidbox.getText())) || !student.updateEmail(emailbox.getText()) || !student.updatePhone(Long.parseLong(phonebox.getText())) || !student.updateBloodGroup(blgbox.getValue()))
            {
                student.updateName(_name);
                student.updateNID(_nid);
                student.updateEmail(_email);
                student.updatePhone(_phone);
                student.updateBloodGroup(_bloodgroup);
            }
        }

        back(event);
    }

    public void resetpass(ActionEvent event) throws Exception
    {
        if(student == null) System.out.println("NULL!");
        else
        {
            String pass = oldpassbox.getText(), _pass;
            if(!student.matchPassword(pass)) return;

            pass = passbox.getText();
            _pass = _passbox.getText();

            student.setPassword(pass, _pass);
        }

        back(event);
    }

    public void delete(ActionEvent event) throws Exception
    {
        if(student == null) System.out.println("NULL!");
        else
        {
            String pass = passbox.getText();
            if(!student.matchPassword(pass)) return;

            student.delete();
        }

        logout(event);
    }

    @FXML void logout(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Student/Home.fxml"));
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
        flatbutton.setVisible(false);
    }
}