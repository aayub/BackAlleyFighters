package components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import characters.CharacterA;
import characters.CharacterB;
import characters.Player;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GamePlay {
	
	private GameOver gmOver;
	
	// game screen
	private Scene gameScene;
	private ImageView arena;
	
	// players
	private static ImageView imgPlayer1, imgPlayer2;
	private static String p01, p02;
	private static Player player1, player2;
	private static Player winnerPlayer;
	private ImageView p1Title, p2Title;
	private Rectangle p1HealthBar, p2HealthBar; // health bar
	
	// keyboard inputs
	// player 1
	private SimpleBooleanProperty pressed_A = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_S = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_D = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_Q = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_W = new SimpleBooleanProperty();
	// player 2
	private SimpleBooleanProperty pressed_LEFT = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_DOWN = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_RIGHT = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_SLASH = new SimpleBooleanProperty();
	private SimpleBooleanProperty pressed_UP = new SimpleBooleanProperty();
	
	Group layout;
	
	private Stage thisStage;
	
	public void createGame(Stage stage, String p1, String p2) throws Exception{
		thisStage = stage;
		
		
		// background set up
		Image imageBG = new Image(new FileInputStream("./src/resources/backgrounds/Arena.png"));
		arena = new ImageView(imageBG);
		arena.setX(0);
		arena.setY(0);
		arena.setFitHeight(500);
		arena.setFitWidth(1200);
		
		p01 = p1;
		p02 = p2;
		
		if (p01.equals("A") || p02.equals("B")) {
			player1 = new CharacterA();
			player2 = new CharacterB();
		} else {
			player2 = new CharacterA();
			player1 = new CharacterB();
		}
		
		// PLAYER 1 IMAGE
		imgPlayer1 = new ImageView(player1.getImageIDLE_L());
		imgPlayer1.setX(300);
		imgPlayer1.setY(300);
		imgPlayer1.setFitHeight(150);
		imgPlayer1.setPreserveRatio(true);
		
		// PLAYER 2 IMAGE
		imgPlayer2 = new ImageView(player2.getImageIDLE_R());
		imgPlayer2.setX(800);
		imgPlayer2.setY(300);
		imgPlayer2.setFitHeight(150);
		imgPlayer2.setPreserveRatio(true);
		
		// TITLE: PLAYER 1
		Image gP1 = new Image(new FileInputStream("./src/resources/buttons/Player1.png"));
		p1Title = new ImageView(gP1);
		p1Title.setFitHeight(50);
		p1Title.setPreserveRatio(true);
		p1Title.setX(50);
		p1Title.setY(50);	
		
		// TITLE: PLAYER 2
		Image gP2 = new Image(new FileInputStream("./src/resources/buttons/Player2.png"));
		p2Title = new ImageView(gP2);
		p2Title.setFitHeight(50);
		p2Title.setPreserveRatio(true);
		p2Title.setX(875);
		p2Title.setY(50);	
		
		// PLAYER 1: HEALTH BAR
		p1HealthBar = player1.getHealthBar();
        p1HealthBar.setX(0);
        p1HealthBar.setY(0);
        // label for health bar
        String p1getHealth = String.valueOf(player1.getHealth());
        Text p1Health = new Text(p1getHealth);
        p1Health.setX(350);
        p1Health.setY(50);
        p1Health.setStyle("-fx-fill: red; -fx-font-size: 30px;");
        

        // PLAYER 2: HEALTH BAR
        p2HealthBar = player2.getHealthBar();
        p2HealthBar.setX(800); // 1200 size of window, 400 for health bar: 1200-400 = 8000
        p2HealthBar.setY(0);
        String p2getHealth = String.valueOf(player2.getHealth());
        Text p2Health = new Text(p2getHealth);
        p2Health.setX(800);
        p2Health.setY(50);
        p2Health.setStyle("-fx-fill: red; -fx-font-size: 30px;");
        
		// group elements in scene
		layout = new Group();
		layout.getChildren().addAll(arena, imgPlayer1, imgPlayer2, p1HealthBar, p2HealthBar, p1Title, p2Title, p1Health, p2Health);
		
		gameScene = new Scene(layout, 1200, 500);
		gameScene.getStylesheets().add(getClass().getResource("applicationStyle.css").toString());
		setUpKeyboard();
		
		thisStage.setScene(gameScene);
		thisStage.setResizable(false);
		
		
		// LISTENERS:
		// punch: attack and gain health
		// PLAYER 1 PUNCHES PLAYER 2
		pressed_S.addListener((obs, wP, nP) -> {
			if(pressed_S.get()) {
				if (imgPlayer1.getBoundsInParent().intersects(imgPlayer2.getBoundsInParent())) {
					
					// gain health perks
					player1.dealPunch();
					p1HealthBar.setWidth(player1.getHealth()*4);
					p1Health.setText(String.valueOf(player1.getHealth()));
					
					// opponent take damage
					player2.gotPunched();
					p2HealthBar.setWidth(player2.getHealth()*4); // change health bar
					p2Health.setText(String.valueOf(player2.getHealth()));
					
				}
				
			}
		});
		// PLAYER 2 PUNCHES PLAYER 1
		pressed_DOWN.addListener((obs, wP, nP) -> {
			if(pressed_DOWN.get()) {
				if (imgPlayer2.getBoundsInParent().intersects(imgPlayer1.getBoundsInParent())) {
					
					// gain health perks
					player2.dealPunch();
					p2HealthBar.setWidth(player2.getHealth()*4);
					p2Health.setText(String.valueOf(player2.getHealth()));
					
					// opponent take damage
					player1.gotPunched();
					p1HealthBar.setWidth(player1.getHealth()*4); // change health bar
					p1Health.setText(String.valueOf(player1.getHealth()));
					
				}
				
			}
		});
		
		// kick: attack and gain health
		// PLAYER 1 KICKS PLAYER 2
		pressed_Q.addListener((obs, wP, nP) -> {
			if(pressed_Q.get()) {
				if (imgPlayer1.getBoundsInParent().intersects(imgPlayer2.getBoundsInParent())) {
					
					// gain health perks
					player1.dealKick();
					p1HealthBar.setWidth(player1.getHealth()*4);
					p1Health.setText(String.valueOf(player1.getHealth()));
					
					// opponent take damage
					player2.gotKicked();
					p2HealthBar.setWidth(player2.getHealth()*4); // change health bar
					p2Health.setText(String.valueOf(player2.getHealth()));
					
				}
				
			}
		});
		
		
		// PLAYER 2 KICKS PLAYER 1
		pressed_SLASH.addListener((obs, wP, nP) -> {
			if(pressed_SLASH.get()) {
				if (imgPlayer2.getBoundsInParent().intersects(imgPlayer1.getBoundsInParent())) {
					
					// gain health perks
					player2.dealKick();
					p2HealthBar.setWidth(player2.getHealth()*4);
					p2Health.setText(String.valueOf(player2.getHealth()));
					
					// opponent take damage
					player1.gotKicked();
					p1HealthBar.setWidth(player1.getHealth()*4); // change health bar
					p1Health.setText(String.valueOf(player1.getHealth()));
					
				}
				
			}
		});
		
		
	}
	
	public  boolean isDefault() {
		// is it default??
		if (p01.equals("A") || p02.equals("B"))
			return true;
		else
			return false;
		
	}
	
		
	// Player getters
	public Player getPlayer1() {
		return this.player1;
	}
	
	public Player getPlayer2() {
		return this.player2;
	}
	
	public String getP1String() {
		return p01;
	}
	
	
	void setUpKeyboard() {
		Image idle = player1.getImageIDLE_L();
		Image left = player1.getImageLEFT_L();
		Image right = player1.getImageRIGHT_L();
		Image punch = player1.getImagePUNCH_L();
		Image kick = player1.getImageKICK_L();
		Image bullet = player1.getImageBULLET_L();
		
		Image idle2 = player2.getImageIDLE_R();
		Image left2 = player2.getImageLEFT_R();
		Image right2 = player2.getImageRIGHT_R();
		Image punch2 = player2.getImagePUNCH_R();
		Image kick2 = player2.getImageKICK_R();
		Image bullet2 = player2.getImageBULLET_R();
		
		
		// -- AFTER RELEASE, STOP ACTION
		gameScene.setOnKeyReleased(e -> {
			// Player 1
			if (e.getCode() == KeyCode.A) {
				imgPlayer1.setImage(idle);
				pressed_A.set(false);
			}
			if (e.getCode() == KeyCode.S) {
				// adjustments for Character B
				if(player1 instanceof CharacterB) {
					imgPlayer1.setTranslateX(50);
				}
				imgPlayer1.setImage(idle);
				pressed_S.set(false);
			}
			if (e.getCode() == KeyCode.D) {
				imgPlayer1.setImage(idle);
				pressed_D.set(false);
			}
			if (e.getCode() == KeyCode.Q) {
				if(player1 instanceof CharacterB) {
					imgPlayer1.setTranslateY(0);
					imgPlayer1.setFitHeight(150);
					imgPlayer1.setPreserveRatio(true);
				}
				imgPlayer1.setImage(idle);
				pressed_Q.set(false);
			}
			if (e.getCode() == KeyCode.W) {
				imgPlayer1.setImage(idle);
				pressed_W.set(false);
			}
			
			// Player 2
			if (e.getCode() == KeyCode.LEFT) {
				imgPlayer2.setImage(idle2);
				pressed_LEFT.set(false);
			}
			if (e.getCode() == KeyCode.DOWN) {
				// adjustments for Character B
				if(player2 instanceof CharacterB) {
					imgPlayer2.setTranslateX(50);
				}
				imgPlayer2.setImage(idle2);
				pressed_DOWN.set(false);
			}
			if (e.getCode() == KeyCode.RIGHT) {
				imgPlayer2.setImage(idle2);
				pressed_RIGHT.set(false);
			}
			if (e.getCode() == KeyCode.SLASH) {
				
				if(player2 instanceof CharacterB) {
					imgPlayer2.setTranslateY(0);
					imgPlayer2.setTranslateX(-50);
					imgPlayer2.setFitHeight(150);
					imgPlayer2.setPreserveRatio(true);
				}
				imgPlayer2.setImage(idle2);
				pressed_SLASH.set(false);
			}
			if (e.getCode() == KeyCode.UP) {
				imgPlayer2.setImage(idle2);
				pressed_UP.set(false);
			}
			
			// WINNER:
			// IF PLAYER 1 WINS:
			if (player1.getHealth() > 0 && player2.getHealth() <= 0) {
				try {
					gmOver.createEndScene(thisStage, player1, 1);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				thisStage.setScene(this.gmOver.getScene());
			}
			// IF PLAYER 2 WINS:
			else if (player1.getHealth() <= 0 && player2.getHealth() > 0) {
				try {
					gmOver.createEndScene(thisStage, player2, 2);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				thisStage.setScene(this.gmOver.getScene());
			}
			
			
		});
		
		// -- PRESS, PERFORM ACTION
		gameScene.setOnKeyPressed(e -> {
			
			// QUIT GAME:
			if(e.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			}

			if (player1.getHealth() > 0 && player2.getHealth() > 0) {
				
				// Player 1
				// A: MOVE LEFT
				if (e.getCode() == KeyCode.A) {
					// CANNOT PUNCH OR KICK OR JUMP AT THIS TIME:
					if (!pressed_S.get() && !pressed_Q.get() && !pressed_W.get()) {
						imgPlayer1.setTranslateX(-10);
						imgPlayer1.setX(imgPlayer1.xProperty().get() - 15);
						imgPlayer1.setImage(left);
						pressed_A.set(true);
					}
					
					if(player1 instanceof CharacterA) {
						imgPlayer1.setTranslateX(10);
					}
					
				}
				
				// Player 1
				// D: MOVE RIGHT
				if (e.getCode() == KeyCode.D) {
					// CANNOT PUNCH OR KICK OR JUMP AT THIS TIME:
					if (!pressed_S.get() && !pressed_Q.get() && !pressed_W.get()) {
						imgPlayer1.setTranslateX(10);
						imgPlayer1.setX(imgPlayer1.xProperty().get() + 15);
						imgPlayer1.setImage(right);
						pressed_D.set(true);
					}
					if(player1 instanceof CharacterA) {
						imgPlayer1.setTranslateX(10);
					}
				}
				
				// Player 1
				// W: JUMP
				if (e.getCode() == KeyCode.W) {
						pressed_W.set(true);
				}
				
				/* USING ANIMATIONS: */
				// Player 1
				// S: PUNCH
				if (e.getCode() == KeyCode.S) {
					
					// NO KICK OR JUMP
					if (!pressed_Q.get() && !pressed_W.get() && !pressed_S.get()) {
						
						// STOP ANY MOVEMENT
						pressed_A.set(false); 
						pressed_D.set(false);
						
						imgPlayer1.setImage(punch);
						pressed_S.set(true);
						
					}
				}
				
				
				// Player 1
				// Q: KICK
				if (e.getCode() == KeyCode.Q) {
					
					// NO PUNCH OR JUMP
					if (!pressed_S.get() && !pressed_W.get()) {
						
						// STOP ANY MOVEMENT
						pressed_A.set(false); 
						pressed_D.set(false);
						
						// adjustments for Character B
						if(player1 instanceof CharacterB) {
							imgPlayer1.setFitHeight(200);
							imgPlayer1.setPreserveRatio(true);
							imgPlayer1.setTranslateX(0);
							imgPlayer1.setTranslateY(-50);
						}
						imgPlayer1.setImage(kick);
						
						pressed_Q.set(true);
					}		
					
					
				}
				
				
				
				// Player 2
				// <-: MOVE LEFT
				if (e.getCode() == KeyCode.LEFT) {
					// CANNOT PUNCH OR KICK OR JUMP AT THIS TIME:
					if (!pressed_DOWN.get() && !pressed_UP.get() && !pressed_SLASH.get()) {
						imgPlayer1.setTranslateX(-10);
						imgPlayer2.setImage(left2);
						imgPlayer2.setX(imgPlayer2.xProperty().get() - 15);
						pressed_LEFT.set(true);
					}
				}
				// Player 2
				// ->: MOVE RIGHT
				if (e.getCode() == KeyCode.RIGHT) {
					// CANNOT PUNCH OR KICK OR JUMP AT THIS TIME:
					if (!pressed_DOWN.get() && !pressed_UP.get() && !pressed_SLASH.get()) {
						imgPlayer1.setTranslateX(10);
						imgPlayer2.setImage(right2);
						imgPlayer2.setX(imgPlayer2.xProperty().get() + 15);
						pressed_RIGHT.set(true);
					}
				}
				// Player 2
				// DOWN: PUNCH
				if (e.getCode() == KeyCode.DOWN) {
					
					// NO KICK OR JUMP
					if (!pressed_SLASH.get() && !pressed_UP.get()) {
						
						pressed_DOWN.set(true);
						
						// STOP ANY MOVEMENT
						pressed_LEFT.set(false); 
						pressed_RIGHT.set(false);
						
						
						// adjustments for Character B
						if(player2 instanceof CharacterB) {
							imgPlayer2.setTranslateX(-50);
						}
						imgPlayer2.setImage(punch2);
					}
				}
				
				// PLAYER 2 KICK
				if (e.getCode() == KeyCode.SLASH) {
					// NO PUNCH OR JUMP
					if (!pressed_DOWN.get() && !pressed_UP.get()) {
					
						// STOP ANY MOVEMENT
						pressed_LEFT.set(false); 
						pressed_RIGHT.set(false);
					
						// adjustments for Character B
						if(player2 instanceof CharacterB) {
							imgPlayer2.setFitHeight(200);
							imgPlayer2.setPreserveRatio(true);
							imgPlayer2.setTranslateY(-50);
							imgPlayer2.setTranslateX(-50);
						}
						imgPlayer2.setImage(kick2);
						
						pressed_SLASH.set(true);	
					}
				}
				
				
				if (e.getCode() == KeyCode.UP) {
					pressed_UP.set(true);
				}
			}
				
		});
		
		AnimationTimer timer = new AnimationTimer() {
			
			public void handle(long now) {
				
				// PLAYER 1 PRESSED JUMP:
				if(!pressed_W.get()) {
					while (imgPlayer1.yProperty().get() != 300.0)
						imgPlayer1.setY(imgPlayer1.yProperty().get() + 50);	
					}
					
				if(pressed_W.get()) {
					while (imgPlayer1.yProperty().get() != 50.0)
						imgPlayer1.setY(imgPlayer1.yProperty().get() - 50);
					}	
				
				
				// PLAYER 2 PRESSED JUMP:
				if(!pressed_UP.get()) {
					while (imgPlayer2.yProperty().get() != 300.0)
						imgPlayer2.setY(imgPlayer2.yProperty().get() + 50);	
					}
					
				if(pressed_UP.get()) {
					while (imgPlayer2.yProperty().get() != 50.0)
						imgPlayer2.setY(imgPlayer2.yProperty().get() - 50);
					}	
				
				// TO AVOID PLAYERS MOVING OFF SCREEN:
				// PLAYER 1: LEFT
				if(imgPlayer1.getX() <= 0) {
						imgPlayer1.setX(10);
				}
				// PLAYER 2: RIGHT
				if(imgPlayer2.getX() >= 1050) {
					imgPlayer2.setX(1050);
				}
				
				// IF PLAYERS RUN INTO ONE ANOTHER, DO NOT ALLOW TO GO PAST ANOTHER
				if (imgPlayer1.getBoundsInParent().intersects(imgPlayer2.getBoundsInParent())){
					//System.out.println("NO CONTACT WITH 2");
					if(imgPlayer1.getX() < imgPlayer2.getX() && pressed_D.get()) {
						imgPlayer1.setX(imgPlayer1.getX()-2);
						
						}
					}
					
				if (imgPlayer2.getBoundsInParent().intersects(imgPlayer1.getBoundsInParent())){
					//System.out.println("NO CONTACT WITH 1");
					if(imgPlayer2.getX() > imgPlayer1.getX() && pressed_LEFT.get()) {
						imgPlayer2.setX(imgPlayer2.getX()+2);
							
						}
					
					}
			}
		};
		timer.start();	
		
		
		}
	
	public static Player getWinner() {
		return winnerPlayer;
	}
	
	public Scene getScene() {
        return this.gameScene;
    }
	
	
	public void setGameOverScreen(GameOver gO) {
		this.gmOver = gO;
	}

}
