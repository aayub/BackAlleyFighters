package components;

import java.io.FileInputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ViewControls {
	
	private ImageView vControls;
	private Button back;
	
	private Scene vcScene;
	private CharacterMenu charMenu;
	
	public ViewControls(Stage stage) throws Exception{
		// background set up
		Image imageBG = new Image(new FileInputStream("./src/resources/backgrounds/VC.png"));
		vControls = new ImageView(imageBG);
		vControls.setX(0);
		vControls.setY(0);
		vControls.setFitWidth(1200);
		vControls.setFitHeight(500);

		// back button
		back = new Button();
		back.setId("back");
		back.setLayoutX(550);
        back.setLayoutY(400);
        back.setOnAction(event -> stage.setScene(this.charMenu.getScene()));
        
        // group elements in scene
     	Group layout = new Group();
     	layout.getChildren().addAll(vControls, back);
     		
     	vcScene = new Scene(layout, 1200, 500);
     	vcScene.getStylesheets().add(getClass().getResource("applicationStyle.css").toString());
		vcScene.setOnKeyPressed(e -> {
			// QUIT GAME:
			if (e.getCode() == KeyCode.ESCAPE)
				System.exit(0);

		});
     	stage.setScene(vcScene);
     	stage.setResizable(false);
	}
	
	public Scene getScene() {
        return this.vcScene;
    }
	
	public void setCharacterScreen(CharacterMenu cs) {
        this.charMenu = cs;
    }

}
