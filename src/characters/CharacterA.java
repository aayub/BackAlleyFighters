package characters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class CharacterA extends Player {
	
	public CharacterA() throws FileNotFoundException{
		
		// AS PLAYER 1
		Image charAIDLE_L = new Image(new FileInputStream("./src/selectPlayers/characterA/Player1/idle_A.gif"));
		Image charALEFT_L = new Image(new FileInputStream("./src/selectPlayers/characterA/Player1/left_A.gif"));
		Image charAPUNCH_L = new Image(new FileInputStream("./src/selectPlayers/characterA/Player1/punch_A.gif"));
		Image charARIGHT_L = new Image(new FileInputStream("./src/selectPlayers/characterA/Player1/right_A.gif"));
		Image charABULLET_L = new Image(new FileInputStream("./src/selectPlayers/bullet_L.gif"));
		Image charAKICK_L = new Image(new FileInputStream("./src/selectPlayers/characterA/Player1/kick_A.gif"));
		Image charAWIN_L = new Image(new FileInputStream("./src/selectPlayers/characterA/Player1/win_A.gif"));
		
		// AS PLAYER 2
		Image charAIDLE_R = new Image(new FileInputStream("./src/selectPlayers/characterA/Player2/idle_A.gif"));
		Image charALEFT_R = new Image(new FileInputStream("./src/selectPlayers/characterA/Player2/left_A.gif"));
		Image charAPUNCH_R = new Image(new FileInputStream("./src/selectPlayers/characterA/Player2/punch_A.gif"));
		Image charARIGHT_R = new Image(new FileInputStream("./src/selectPlayers/characterA/Player2/right_A.gif"));
		Image charABULLET_R = new Image(new FileInputStream("./src/selectPlayers/bullet_R.gif"));
		Image charAKICK_R = new Image(new FileInputStream("./src/selectPlayers/characterA/Player2/kick_A.gif"));
		Image charAWIN_R = new Image(new FileInputStream("./src/selectPlayers/characterA/Player2/win_A.gif"));
		
		super.setImagePlayerL(charAIDLE_L, charALEFT_L, charAPUNCH_L, charARIGHT_L, charAKICK_L, charABULLET_L, charAWIN_L);
		super.setImagePlayerR(charAIDLE_R, charALEFT_R, charAPUNCH_R, charARIGHT_R, charAKICK_R, charABULLET_R, charAWIN_R);
		
	}
	
	

}
