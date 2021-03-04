package characters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class CharacterB extends Player {
	
	public CharacterB() throws FileNotFoundException{
		
		// AS PLAYER 1
		Image charBIDLE_L = new Image(new FileInputStream("./src/selectPlayers/characterB/Player1/idle_B.gif"));
		Image charBLEFT_L = new Image(new FileInputStream("./src/selectPlayers/characterB/Player1/left_B.gif"));
		Image charBPUNCH_L = new Image(new FileInputStream("./src/selectPlayers/characterB/Player1/punch_B.gif"));
		Image charBRIGHT_L = new Image(new FileInputStream("./src/selectPlayers/characterB/Player1/right_B.gif"));
		Image charBKICK_L = new Image(new FileInputStream("./src/selectPlayers/characterB/Player1/kick_B.gif"));
		Image charBBULLET_L = new Image(new FileInputStream("./src/selectPlayers/bullet_L.gif"));
		Image charBWIN_L = new Image(new FileInputStream("./src/selectPlayers/characterB/Player1/win_B.gif"));
				
		// AS PLAYER 2
		Image charBIDLE_R = new Image(new FileInputStream("./src/selectPlayers/characterB/Player2/idle_B.gif"));
		Image charBLEFT_R = new Image(new FileInputStream("./src/selectPlayers/characterB/Player2/left_B.gif"));
		Image charBPUNCH_R = new Image(new FileInputStream("./src/selectPlayers/characterB/Player2/punch_B.gif"));
		Image charBRIGHT_R = new Image(new FileInputStream("./src/selectPlayers/characterB/Player2/right_B.gif"));
		Image charBKICK_R = new Image(new FileInputStream("./src/selectPlayers/characterB/Player2/kick_B.gif"));
		Image charBBULLET_R = new Image(new FileInputStream("./src/selectPlayers/bullet_R.gif"));
		Image charBWIN_R = new Image(new FileInputStream("./src/selectPlayers/characterB/Player2/win_B.gif"));
		
		
		super.setImagePlayerL(charBIDLE_L, charBLEFT_L, charBPUNCH_L, charBRIGHT_L, charBKICK_L, charBBULLET_L, charBWIN_L);
		super.setImagePlayerR(charBIDLE_R, charBLEFT_R, charBPUNCH_R, charBRIGHT_R, charBKICK_R, charBBULLET_R, charBWIN_R);
		
	}
	
	

}
