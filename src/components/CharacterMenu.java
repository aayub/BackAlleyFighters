package components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class CharacterMenu {
	
	private Button go;
	private Button viewControls;

	// character selections
	private ImageView p1_select, p2_select;
	private ToggleButton p1charA, p1charB;
	private ToggleButton p2charA, p2charB;
	private Group groupP1, groupP2;
	
	private Scene charScene;
	private ImageView cMenu;
	// other scene
	private ViewControls vControls;
	private GamePlay gPlay;
	
	// characters
	private static String p01, p02;
	
	
	public CharacterMenu(Stage stage) throws Exception{
		// background set up
		Image imageBG = new Image(new FileInputStream("./src/resources/backgrounds/CS.png"));
		cMenu = new ImageView(imageBG);
		cMenu.setX(0);
		cMenu.setY(0);
		cMenu.setFitHeight(500);
		cMenu.setFitWidth(1200);
		
		// default character set
		p01 = "A";
		p02 = "B";
		
		setUpButtons(); // background and buttons
		setUpDefault(); // build menu, and set default character to player
		selectCharacter(); // sets character A & B buttons depending on user choice
		
		
		// actions 
		go.setOnAction(e-> {
			
				try {
					gPlay.createGame(stage, p01, p02);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			stage.setScene(this.gPlay.getScene());
			
		});
		viewControls.setOnAction(e -> stage.setScene(this.vControls.getScene()));
		
		// group elements in scene
		Group layout = new Group();
		layout.getChildren().addAll(cMenu, go, viewControls, groupP1, groupP2);
		
		charScene = new Scene(layout, 1200, 500);
		charScene.getStylesheets().add(getClass().getResource("applicationStyle.css").toString());

		charScene.setOnKeyPressed(e -> {
			// QUIT GAME:
			if (e.getCode() == KeyCode.ESCAPE)
				System.exit(0);

		});
		stage.setScene(charScene);
		stage.setResizable(false);
	}
	
	void setUpButtons() throws FileNotFoundException {

		// buttons set up
        // go
		go = new Button();
		go.setId("go");
		go.setLayoutX(525);
        go.setLayoutY(375);
        
		// viewControls
        viewControls = new Button();
		viewControls.setId("vC");
		viewControls.setLayoutX(600-100); 
        viewControls.setLayoutY(450);
       
	}
	
	void setUpDefault() throws FileNotFoundException {
		///* PLAYER 1 */
		// character 
		Image imgA = new Image(new FileInputStream("./src/menu/CharA.gif"));
		p1_select = new ImageView(imgA);
		// resize
		p1_select.setFitHeight(180);
		p1_select.setPreserveRatio(true);
		p1_select.setLayoutX(140);
        p1_select.setLayoutY(180);
		
		// character selection
        Image imgP1 = new Image(new FileInputStream("./src/selectPlayers/p1_blank.png")); // change to p1_blank
        ImageView p1 = new ImageView(imgP1);
        p1.setLayoutX(100); 
        p1.setLayoutY(125);
        
        // Character A Button
        p1charA = new ToggleButton();
        Image defaultP1 = new Image(new FileInputStream("./src/resources/buttons/A-select.png"));
        p1charA.setGraphic(new ImageView(defaultP1));
        p1charA.setId("charA");
        p1charA.setLayoutX(160);
        p1charA.setLayoutY(380);
        
        // Character B Button
        p1charB = new ToggleButton();
        Image P1_b = new Image(new FileInputStream("./src/resources/buttons/B.png"));
        p1charB.setGraphic(new ImageView(P1_b));
        p1charB.setId("charB");
        p1charB.setLayoutX(160);
        p1charB.setLayoutY(410);
        
        groupP1 = new Group();
		groupP1.getChildren().addAll(p1, p1charA, p1charB, p1_select); // add charA
        
		
		///* PLAYER 2 */
		// character
		Image imgB = new Image(new FileInputStream("./src/menu/CharB.gif"));
		p2_select = new ImageView(imgB);
		// resize
		p2_select.setFitHeight(160);
		p2_select.setPreserveRatio(true);
		p2_select.setLayoutX(790);
        p2_select.setLayoutY(180); 
		
        // character selection
        Image imgP2 = new Image(new FileInputStream("./src/selectPlayers/p2_blank.png"));
        ImageView p2 = new ImageView(imgP2);
        p2.setLayoutX(800);
        p2.setLayoutY(125);
        
        // Character A Button
        p2charA = new ToggleButton();
        Image P2_a = new Image(new FileInputStream("./src/resources/buttons/A.png"));
        p2charA.setGraphic(new ImageView(P2_a));
        p2charA.setId("charA");
        p2charA.setLayoutX(860);
        p2charA.setLayoutY(380);
        
        // Character B Button
        p2charB = new ToggleButton();
        Image defaultP2 = new Image(new FileInputStream("./src/resources/buttons/B-select.png"));
        p2charB.setGraphic(new ImageView(defaultP2));
        p2charB.setId("charB");
        p2charB.setLayoutX(860);
        p2charB.setLayoutY(410);
        
        groupP2 = new Group();
		groupP2.getChildren().addAll(p2, p2charA, p2charB, p2_select);
	}
	
	
	private void p1selectA() throws FileNotFoundException {
		// change button selected for p1 to Character A
        Image selectA = new Image(new FileInputStream("./src/resources/buttons/A-select.png"));
        p1charA.setGraphic(new ImageView(selectA));
        
        // deselect Character B
        Image deselectB = new Image(new FileInputStream("./src/resources/buttons/B.png"));
        p1charB.setGraphic(new ImageView(deselectB));
        
        p1_select.setImage(new Image(new FileInputStream("./src/menu/CharA.gif")));
        p1_select.setFitHeight(180);
		p1_select.setPreserveRatio(true);
		p1_select.setLayoutX(140);
        p1_select.setLayoutY(180);
        
              
	}
	
	private void p1selectB() throws FileNotFoundException {
		// change button selected for p1 to Character B
        Image selectA = new Image(new FileInputStream("./src/resources/buttons/A.png"));
        p1charA.setGraphic(new ImageView(selectA));
        
        // deselect Character A
        Image deselectB = new Image(new FileInputStream("./src/resources/buttons/B-select.png"));
        p1charB.setGraphic(new ImageView(deselectB));
        
        p1_select.setImage(new Image(new FileInputStream("./src/menu/CharB.gif")));
        p1_select.setFitHeight(160);
		p1_select.setPreserveRatio(true);
		p1_select.setLayoutX(115);
        p1_select.setLayoutY(180);
        
		
	}
	
	private void p2selectA() throws FileNotFoundException {
		// change button selected for p2 to Character A
        Image selectA = new Image(new FileInputStream("./src/resources/buttons/A-select.png"));
        p2charA.setGraphic(new ImageView(selectA));
        
        // deselect Character B
        Image deselectB = new Image(new FileInputStream("./src/resources/buttons/B.png"));
        p2charB.setGraphic(new ImageView(deselectB));
        
        p2_select.setImage(new Image(new FileInputStream("./src/menu/CharA.gif")));
        p2_select.setFitHeight(180);
		p2_select.setPreserveRatio(true);
		p2_select.setLayoutX(825);
        p2_select.setLayoutY(180); 
        
        
	}
	
	private void p2selectB() throws FileNotFoundException {
		// change button selected for p2 to Character B
        Image selectB = new Image(new FileInputStream("./src/resources/buttons/B-select.png"));
        p2charB.setGraphic(new ImageView(selectB));
        
        // deselect Character A
        Image deselectA = new Image(new FileInputStream("./src/resources/buttons/A.png"));
        p2charA.setGraphic(new ImageView(deselectA));
        
		
		p2_select.setImage(new Image(new FileInputStream("./src/menu/CharB.gif")));
		p2_select.setFitHeight(160);
		p2_select.setPreserveRatio(true);
		p2_select.setLayoutX(810);
        p2_select.setLayoutY(180); 
	}
	
	void selectCharacter() throws FileNotFoundException {
		
		// PLAYER 1 -> CHARACTER A selected
		p1charA.setOnAction(e -> { 
			try {
				p1selectA();
				p2selectB();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p01 = "A";
			p02 = "B";
		});
		
		// PLAYER 1 -> CHARACTER B selected
		p1charB.setOnAction(e -> {
			try {
				p1selectB();
				p2selectA();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p01 = "B";
			p02 = "A";
		
		});
		
		// PLAYER 2 -> CHARACTER A selected
		p2charA.setOnAction(e -> { 
			try {
				p2selectA();
				p1selectB();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			p02 = "A";
			p01 = "B";
		});
		
		// PLAYER 2 -> CHARACTER B selected
		p2charB.setOnAction(e -> {
			try {
				p2selectB();
				p1selectA();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p02 = "B";
			p01 = "A";
		});
	}
	
	public String getP1() {
		return p01;
	}
	
	public String getP2() {
		return p02;
	}
	
	
	public Scene getScene() {
        return this.charScene;
    }
	
	public void setVCScreen(ViewControls vc) {
        this.vControls = vc;
    }
	
	public void setPlayScreen(GamePlay gp) {
		this.gPlay = gp;
	}
	
}
