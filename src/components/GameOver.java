package components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import characters.Player;
import characters.CharacterA;
import characters.CharacterB;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameOver {
	
	private Scene gOScene;
	
	public void createEndScene(Stage stage, Player winChar, Integer position) throws FileNotFoundException {
		ImageView winPlayer;
		
		// background set up
		Image imageBG = new Image(new FileInputStream("./src/resources/backgrounds/Arena.png"));
		ImageView arena = new ImageView(imageBG);
		arena.setX(0);
		arena.setY(0);
		arena.setFitHeight(500);
	    arena.setFitWidth(1200);		
		
		// WIN BANNER
        ImageView player1Wins = new ImageView(new Image(new FileInputStream("./src/resources/logo/Player1Wins.png")));
        player1Wins.setX(400);
        player1Wins.setY(150);
        

        ImageView player2Wins = new ImageView(new Image(new FileInputStream("./src/resources/logo/Player2Wins.png")));
        player2Wins.setX(400);
        player2Wins.setY(150);
        
        
        // group elements in scene
     	Group layout = new Group();
     	
     	// Determine if winner is Player 1 or Player 2
        if(position == 1) {
        	// PLAYER 1
        	winPlayer = new ImageView(winChar.getImageWIN_L());
        	winPlayer.setX(400);
        	winPlayer.setY(200);
        	layout.getChildren().addAll(arena, player1Wins, winPlayer);
        } else {
        	// PLAYER 2
        	winPlayer = new ImageView(winChar.getImageWIN_R());
        	winPlayer.setX(400);
        	winPlayer.setY(200);
        	layout.getChildren().addAll(arena, player2Wins, winPlayer);
        }
        
        
     	gOScene = new Scene(layout, 1200, 500);
     	stage.setScene(gOScene);
		stage.setResizable(false);

		gOScene.setOnKeyPressed(e -> {
			// QUIT GAME:
			if (e.getCode() == KeyCode.ESCAPE)
				System.exit(0);

		});
        
	}
	
	public Scene getScene() {
		return this.gOScene;
	}

}
