import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Group root = new Group();
		Scene scene = new Scene(root, 1280, 720);

		stage.getIcons().add(new Image("img/logo.png"));
		stage.setTitle("HOM");

		ImageView bg = new ImageView(new Image("img/home.jpg"));
		bg.setPreserveRatio(true);
		bg.setFitWidth(scene.getWidth());
		bg.setFitHeight(scene.getHeight());
		root.getChildren().add(bg);

		Text text = new Text(50, 50, "The quick brown fox jumps over the lazy dog");
		text.setFont(Font.font("Arial", 12));
		text.setFill(Color.WHITE);
		root.getChildren().add(text);

		Rectangle rect = new Rectangle(50, 80, 50, 50);
		rect.setFill(Color.GREEN);
		rect.setStroke(Color.WHITE);
		rect.setStrokeWidth(5);
		root.getChildren().add(rect);

		Circle circle = new Circle(350, 100, 50, Color.PURPLE);
		root.getChildren().add(circle);

		Polygon triangle = new Polygon(50, 400, 50, 500, 150, 500);
		triangle.setFill(Color.BLUE);
		root.getChildren().add(triangle);

		ImageView img = new ImageView(new Image("https://smaller-pictures.appspot.com/images/dreamstime_xxl_65780868_small.jpg"));
		img.setPreserveRatio(true);
		img.setX(150);
		img.setY(100);
		img.setFitWidth(100);
		root.getChildren().add(img);

		Button[] btn = new Button[2];
		btn[0] = new Button("Log In");
		btn[1] = new Button("Register");
		for(int i=0; i<2; i++)
		{
			btn[i].setLayoutX(250);
			btn[i].setLayoutY(550+50*i);

			root.getChildren().add(btn[i]);
		}

		stage.setScene(scene);
		stage.show();
	}
}