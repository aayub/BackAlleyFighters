package characters;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
	
	
	
	// Player health and +/- debuffs
	private int health = 100;

	// SUCESSFUL KICKING WILL GIVE U HEALTH
	private static int punchDamage = -20;
	private static int kickDamage = -15;
	private static int punchRecovery = +0;
	private static int kickRecovery = +5;
	//private static int bulletDamage = -10;
	
	
	// Player Animation for each positions
	private Image playerIDLE_L;
	private Image playerIDLE_R;
	
	private Image playerLEFT_L;
	private Image playerLEFT_R;
	
	private Image playerPUNCH_L;
	private Image playerPUNCH_R;
	
	private Image playerRIGHT_L;
	private Image playerRIGHT_R;
	
	private Image playerKICK_L;
	private Image playerKICK_R;
	
	private Image playerBULLET_L;
	private Image playerBULLET_R;
	
	private Image playerWIN_L;
	private Image playerWIN_R;
	

	/*
	 * HEALTH & +/- DEBUFFS
	 */
	public int getHealth(){
		return this.health;
	}
	
	public void gotPunched(){
		int limit = this.health + punchDamage;
		if (limit >= 0)
			this.health += punchDamage;
		else
			this.health = 0;
		
	}
	
	public void gotKicked(){
		int limit = this.health + kickDamage;
		if (limit >= 0)
			this.health += kickDamage;
		else
			this.health = 0;
	}
	
	
	public void dealPunch(){
		int limit = this.health + punchRecovery;
		if (limit <= 100)
			this.health += punchRecovery;
	}
	
	public void dealKick() {
		int limit = this.health + kickRecovery;
		if (limit <= 100)
			this.health += kickRecovery;
	}
	
	/*
	 * PLAYER TITLES
	 */
	
	
	
	/*
	 * SET PLAYER IMAGES (L & R)
	 */
	public void setImagePlayerL(Image idle_L, Image left_L, Image punch_L, Image right_L, Image kick_L, Image bullet_L, 
			Image win_L){
		this.playerIDLE_L = idle_L;
		this.playerLEFT_L = left_L;
		this.playerPUNCH_L = punch_L;
		this.playerRIGHT_L = right_L;
		this.playerKICK_L = kick_L;
		this.playerBULLET_L = bullet_L;
		this.playerWIN_L = win_L;
		
	}
	
	public void setImagePlayerR(Image idle_R, Image left_R, Image punch_R, Image right_R, Image kick_R, Image bullet_R,
			Image win_R){
		this.playerIDLE_R = idle_R;
		this.playerLEFT_R = left_R;
		this.playerPUNCH_R = punch_R;
		this.playerRIGHT_R = right_R;
		this.playerKICK_R = kick_R;
		this.playerBULLET_R = bullet_R;
		this.playerWIN_R = win_R;
	}
	
	/*
	 * GET PLAYER IMAGES (L & R)
	 */
	
	// idle
	public Image getImageIDLE_L(){
		return playerIDLE_L;
	}
	public Image getImageIDLE_R(){
		return playerIDLE_R;
	}
	// moving left
	public Image getImageLEFT_L(){
		return playerLEFT_L;
	}
	public Image getImageLEFT_R(){
		return playerLEFT_R;
	}
	// punch
	public Image getImagePUNCH_L(){
		return playerPUNCH_L;
	}
	public Image getImagePUNCH_R(){
		return playerPUNCH_R;
	}
	// moving right
	public Image getImageRIGHT_L(){
		return playerRIGHT_L;
	}
	public Image getImageRIGHT_R(){
		return playerRIGHT_R;
	}
	// bullet
	public Image getImageBULLET_L(){
		return playerBULLET_L;
	}
	public Image getImageBULLET_R(){
		return playerBULLET_R;
	}
	// kick
	public Image getImageKICK_L(){
		return playerKICK_L;
	}
	public Image getImageKICK_R(){
		return playerKICK_R;
	}
	
	// win
	public Image getImageWIN_L(){
		return playerWIN_L;
	}
	
	public Image getImageWIN_R(){
		return playerWIN_R;
	}
	
	public Rectangle getHealthBar() {
		// width, height, fill
        return new Rectangle(this.health*4, 15, Color.RED);
    }
	
}
