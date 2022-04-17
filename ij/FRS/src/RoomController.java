import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.stream.IntStream;

public class RoomController implements Initializable
{
    static Room room;

    private Stage stage;
    private Scene scene;
    private Parent root;

    static Stack<String> stack = new Stack<>();

    @FXML TextField namebox, areabox;
    @FXML CheckBox lightbox, almirahbox, sinkbox, cupboardbox, gasbox, ventilatorbox, showerbox, tseatbox, tpanbox, spraybox, geaserbox, bathtubbox;
    @FXML ComboBox<String> floorbox = new ComboBox<>();
    @FXML ComboBox<Integer> stovebox = new ComboBox<>();

    void init(Room r)
    {
        room = r;
        if(room == null) System.exit(0);

        stack.clear();
    }

    @FXML void back(ActionEvent event) throws Exception
    {
        /*if(stack.isEmpty())
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
        }*/
    }

    @FXML void viewbedroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Bed/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewdiningroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Din/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewlivingroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Liv/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewkitchenpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Kit/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewbathroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Bath/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewbalconypage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Balk/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewstoreroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Strm/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void viewxtraroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Xtra/View.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editbedroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Bed/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editdiningroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Din/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editlivingroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Liv/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editkitchenpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Kit/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editbathroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Bath/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editbalconypage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Balk/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editstoreroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Strm/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editxtraroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Xtra/Edit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void editbedroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        boolean lightsource = lightbox.isSelected();
        boolean almirah = almirahbox.isSelected();
        Room r;

        /*try
        {
            r = new Bedroom(room.id, name, area, tiles, lightsource, almirah);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editdiningroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        /*try
        {
            r = new DiningRoom(room.id, name, area, tiles);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editlivingroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        boolean lightsource = lightbox.isSelected();
        Room r;

        /*try
        {
            r = new LivingRoom(room.id, name, area, tiles, lightsource);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editkitchen(ActionEvent event) throws Exception
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

        /*try
        {
            r = new Kitchen(room.id, name, area, tiles, stove, sink, cupboard, gas, ventilator);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editbathroom(ActionEvent event) throws Exception
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

        /*try
        {
            r = new Bathroom(room.id, name, area, tiles, shower, sink, tseat, tpan, spray, geaser, bathtub);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editbalcony(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        /*try
        {
            r = new Balcony(room.id, name, area, tiles);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editstoreroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        /*try
        {
            r = new StoreRoom(room.id, name, area, tiles);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void editxtraroom(ActionEvent event) throws Exception
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String name = namebox.getText();
        double area = Double.parseDouble(areabox.getText());
        String tiles = floorbox.getValue();
        Room r;

        /*try
        {
            r = new XtraRoom(room.id, name, area, tiles);
            room.rooms.add(r);
            Global.AllRooms.put(r.id, r);
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);*/
    }

    @FXML void deletepage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/Delete.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void delete(ActionEvent event) throws Exception
    {

    }

    @FXML void home(ActionEvent event) throws Exception
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        stovebox.getItems().addAll(IntStream.of(IntStream.rangeClosed(0,5).toArray()).boxed().toArray(Integer[]::new));
    }
}