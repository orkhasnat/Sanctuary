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

public class FlatFormController implements Initializable
{
    Flat flat;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML TextField namebox, areabox;
    @FXML ComboBox<String> floorbox = new ComboBox<>(), flatgenderbox = new ComboBox<>();
    @FXML CheckBox lightbox, almirahbox, sinkbox, cupboardbox, gasbox, ventilatorbox, showerbox, tseatbox, tpanbox, spraybox, geaserbox, bathtubbox;
    @FXML ComboBox<Integer> stovebox = new ComboBox<>();
    @FXML Hyperlink locationbutton = new Hyperlink();

    void init(Flat f) throws Exception
    {
        flat = f;

        /*nameLabel.setText(flat.getName());
        levelLabel.setText("Level " + flat.getLevel());
        roomcountLabel.setText("Room Count" + flat.rooms.size());
        liftbox.setSelected(flat.getLift());
        generatorbox.setSelected(flat.getGenerator());

        int rent = flat.getRent();
        if(rent < 0) rentviewbutton.setVisible(false);
        else rentviewbutton.setText("Rent: " + rent);*/
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/View.fxml"));
        root = loader.load();

        FlatController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void edit(ActionEvent event) throws Exception
    {

    }

    @FXML void setrent(ActionEvent event) throws Exception
    {

    }

    @FXML void delete(ActionEvent event) throws Exception
    {

    }

    @FXML void home(ActionEvent event) throws Exception
    {

    }

    @FXML void addbedroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Bed/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void adddiningroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Din/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addlivingroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Liv/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addkitchenpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Kit/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbathroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Bath/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbalconypage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Balk/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addstoreroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Strm/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addxtraroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Xtra/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbedroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        boolean lightsource = lightbox.isSelected();
        boolean almirah = almirahbox.isSelected();
        Room r;

        try
        {
            r = new Bedroom(flat.id, name, area, tiles, lightsource, almirah);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void adddiningroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        try
        {
            r = new DiningRoom(flat.id, name, area, tiles);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addlivingroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        boolean lightsource = lightbox.isSelected();
        Room r;

        try
        {
            r = new LivingRoom(flat.id, name, area, tiles, lightsource);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addkitchen(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        int stove = stovebox.getValue();
        boolean sink = sinkbox.isSelected();
        boolean cupboard = cupboardbox.isSelected();
        boolean gas = gasbox.isSelected();
        boolean ventilator = ventilatorbox.isSelected();
        Room r;

        try
        {
            r = new Kitchen(flat.id, name, area, tiles, stove, sink, cupboard, gas, ventilator);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbathroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        boolean shower = showerbox.isSelected();
        boolean sink = sinkbox.isSelected();
        boolean tseat = tseatbox.isSelected();
        boolean tpan = tpanbox.isSelected();
        boolean spray = spraybox.isSelected();
        boolean geaser = geaserbox.isSelected();
        boolean bathtub = bathtubbox.isSelected();
        Room r;

        try
        {
            r = new Bathroom(flat.id, name, area, tiles, shower, sink, tseat, tpan, spray, geaser, bathtub);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbalcony(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        try
        {
            r = new Balcony(flat.id, name, area, tiles);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addstoreroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        try
        {
            r = new StoreRoom(flat.id, name, area, tiles);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void addxtraroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        try
        {
            r = new XtraRoom(flat.id, name, area, tiles);
            flat.rooms.add(r);
            Global.AllRooms.put(r.id, r);
        }
        catch (Exception e)
        {
            r = null;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        root = loader.load();

        FlatFormController controller = loader.getController();
        controller.init(flat);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        flatgenderbox.getItems().addAll(Flat.genderlist);
        flatgenderbox.getSelectionModel().selectFirst();
        floorbox.getItems().addAll(Room.tileslist);
        stovebox.getItems().addAll(IntStream.of(IntStream.rangeClosed(0,5).toArray()).boxed().toArray(Integer[]::new));

        locationbutton.setOnAction(new EventHandler<ActionEvent>()
                                   {
                                       @Override public void handle(ActionEvent event)
                                       {
                                           try
                                           {
                                               Desktop.getDesktop().browse(new URI(flat.getLocation()));
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