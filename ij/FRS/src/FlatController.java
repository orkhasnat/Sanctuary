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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.stream.IntStream;

public class FlatController implements Initializable
{
    static Flat flat;
    Flat.Base flatbase;

    private Stage stage;
    private Scene scene;
    private Parent root;

    static Stack<String> stack = new Stack<>();

    @FXML Label nameLabel, levelLabel, roomcountLabel;
    @FXML TextField namebox, areabox;
    @FXML ComboBox<String> floorbox = new ComboBox<>(), flatgenderbox = new ComboBox<>();
    @FXML CheckBox lightbox, almirahbox, sinkbox, cupboardbox, gasbox, ventilatorbox, showerbox, tseatbox, tpanbox, spraybox, geaserbox, bathtubbox;
    @FXML ComboBox<Integer> stovebox = new ComboBox<>();
    @FXML Hyperlink locationbutton = new Hyperlink();

    void init(Flat f)
    {
        flat = f;
        if(flat == null) System.exit(0);

        countrooms();

        stack.clear();
    }

    void countrooms()
    {
        roomcountLabel.setText("Room Count: "+flat.rooms.size());
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
            case "addroom":
                addroompage(event);
                break;
            /*case "set":
                settingspage(event);
                break;
            case "myflats":
                myflatpage(event);
                break;*/
        }
    }

    @FXML void view(ActionEvent event) throws Exception
    {
        stack.clear();
        stack.push("view");
        if(flat == null) flat = Flat.open(1000000);
        if(flat == null) System.out.println("NULL!");



        root = FXMLLoader.load(getClass().getResource("fxml/Flat/View.fxml"));

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addroompage(ActionEvent event) throws Exception
    {
        stack.push("addroom");
        if(flat == null) flat = Flat.open(1000000);
        if(flat == null) System.out.println("NULL!");

        root = FXMLLoader.load(getClass().getResource("fxml/Flat/AddRoom.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbedroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewBed.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void adddiningroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewDin.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addlivingroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewLiv.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addkitchenpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewKit.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbathroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewBath.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addbalconypage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewBalk.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addstoreroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewStor.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void addxtraroompage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Room/NewXtra.fxml"));
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
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
            countrooms();
        }
        catch (Exception e)
        {
            r = null;
        }

        addroompage(event);
    }

    @FXML void editpage(ActionEvent event) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("fxml/Flat/Edit.fxml"));
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

    @FXML void edit(ActionEvent event) throws Exception
    {

    }

    @FXML void settingspage(ActionEvent event) throws Exception
    {
        stack.push("set");
        root = FXMLLoader.load(getClass().getResource("fxml/Flat/Settings.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void setrentpage(ActionEvent event) throws Exception
    {
        stack.push("set");
        root = FXMLLoader.load(getClass().getResource("fxml/Flat/SetRent.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML void home(ActionEvent event) throws Exception
    {

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
                               @Override public void handle(ActionEvent e) {
                                   try {
                                       Desktop.getDesktop().browse(new URI(flat.getLocation()));
                                   } catch (IOException e1) {
                                       e1.printStackTrace();
                                   } catch (URISyntaxException e1) {
                                       e1.printStackTrace();
                                   }
                               }
                           }
        );
    }
}