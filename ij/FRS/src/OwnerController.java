import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerController implements Initializable
{
    Owner owner;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML Label nameLabel;
    @FXML PasswordField passbox, _passbox, oldpassbox;
    @FXML MenuButton flatlist;
    @FXML MenuItem editbutton = new MenuItem(), passbutton = new MenuItem(), deletebutton = new MenuItem(), addflatbutton = new MenuItem();

    void init(Owner p) throws Exception
    {
        owner = p;

        nameLabel.setText(nameLabel.getText()+p.getName());

        for(Flat flat: owner.flats())
        {
            MenuItem item = new MenuItem(flat.getName());
            item.setOnAction(new EventHandler<ActionEvent>()
                             {
                                 @Override public void handle(ActionEvent event)
                                 {
                                     try
                                     {
                                         stage = (Stage) item.getParentPopup().getOwnerWindow();
                                         Flat f = Flat.open(flat.id);

                                         FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/View.fxml"));
                                         root = loader.load();
                                         FlatController controller = loader.getController();
                                         controller.init(f);

                                         scene = new Scene(root);
                                         stage.setScene(scene);
                                         stage.show();
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
            flatlist.getItems().add(item);
        }
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
        editbutton.setOnAction(new EventHandler<ActionEvent>()
                                   {
                                       @Override public void handle(ActionEvent event)
                                       {
                                           try
                                           {
                                               stage = (Stage) editbutton.getParentPopup().getOwnerWindow();
                                               FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Edit.fxml"));
                                               root = loader.load();

                                               OwnerFormController controller = loader.getController();
                                               controller.init(owner);

                                               scene = new Scene(root);
                                               stage.setScene(scene);
                                               stage.show();
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

        passbutton.setOnAction(new EventHandler<ActionEvent>()
                               {
                                   @Override public void handle(ActionEvent event)
                                   {
                                       try
                                       {
                                           stage = (Stage) passbutton.getParentPopup().getOwnerWindow();
                                           FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Password.fxml"));
                                           root = loader.load();

                                           OwnerFormController controller = loader.getController();
                                           controller.init(owner);

                                           scene = new Scene(root);
                                           stage.setScene(scene);
                                           stage.show();
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

        deletebutton.setOnAction(new EventHandler<ActionEvent>()
                                 {
                                     @Override public void handle(ActionEvent event)
                                     {
                                         try
                                         {
                                             stage = (Stage) deletebutton.getParentPopup().getOwnerWindow();
                                             FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Owner/Delete.fxml"));
                                             root = loader.load();

                                             OwnerFormController controller = loader.getController();
                                             controller.init(owner);

                                             scene = new Scene(root);
                                             stage.setScene(scene);
                                             stage.show();
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

        addflatbutton.setOnAction(new EventHandler<ActionEvent>()
                                 {
                                     @Override public void handle(ActionEvent event)
                                     {
                                         try
                                         {
                                             stage = (Stage) addflatbutton.getParentPopup().getOwnerWindow();
                                             FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/Register.fxml"));
                                             root = loader.load();

                                             OwnerFormController controller = loader.getController();
                                             controller.init(owner);

                                             scene = new Scene(root);
                                             stage.setScene(scene);
                                             stage.show();
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