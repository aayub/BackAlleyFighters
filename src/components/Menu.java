package components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import components.CharacterMenu;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Menu {
	
	private Button start, exit;
	private ImageView logo, bg, charA, charB;
	
	// scenes
	private Scene menuScene;
	private CharacterMenu charMenu;
	
	
	public Menu(Stage stage) throws Exception {
		// initialize everything
		setUp();
		// add actions to buttons
		start.setOnAction(e -> stage.setScene(this.charMenu.getScene()));
		exit.setOnAction(e -> System.exit(0));
		
		// group elements in scene
		Group layout = new Group();
        layout.getChildren().addAll(bg,logo,start,exit, charA, charB);
        
		menuScene = new Scene(layout, 1200, 500);
		menuScene.getStylesheets().add(getClass().getResource("applicationStyle.css").toString());
		stage.setScene(menuScene);
		stage.setResizable(false);
	}
	
	void setUp() throws FileNotFoundException {
		
		// background set up
		Image background = new Image(new FileInputStream("./src/resources/backgrounds/Main.png"));
        bg = new ImageView(background);
        bg.setFitHeight(500);
        bg.setPreserveRatio(true);
        bg.setX(0);
        bg.setY(0);


        // logo
        Image imgLogo = new Image(new FileInputStream("./src/resources/logo/BAFighters.png"));
        logo = new ImageView(imgLogo);
        logo.setFitHeight(60);
        logo.setPreserveRatio(true);
        logo.setX(logo.getImage().getHeight());
        logo.setY(50);
		

        // start button
		start = new Button();
		start.setId("start");
		start.setLayoutX(550);
        start.setLayoutY(150);
        
		// exit button
		exit = new Button();
		exit.setId("exit");
		exit.setLayoutX(550);
        exit.setLayoutY(250);

        // character A & B
        Image a_Img = new Image(new FileInputStream("./src/menu/charA.png"));
        charA = new ImageView(a_Img);
        charA.setFitHeight(120);
        charA.setPreserveRatio(true);
        charA.setX(530);
        charA.setY(350);

        Image b_Img = new Image(new FileInputStream("./src/menu/charB.png"));
        charB = new ImageView(b_Img);
        charB.setFitHeight(120);
        charB.setPreserveRatio(true);
        charB.setX(615);
        charB.setY(350);
		
	}
	
	public Scene getScene() {
        return this.menuScene;
    }
	
	public void setCharacterScreen(CharacterMenu cs) {
        this.charMenu = cs;
    }


}
