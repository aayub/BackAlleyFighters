package components;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application
{
	public static void main(String args[])
    {
        Application.launch(args);
    }
 
    @Override
    public void start(Stage stage) throws Exception
    {
    	stage.setTitle("BackAlley Fighters");
        stage.setResizable(false);

        // create
        Menu mM = new Menu(stage);
        CharacterMenu cM = new CharacterMenu(stage);
        ViewControls vC = new ViewControls(stage);
        GamePlay gP = new GamePlay();
        GameOver gO = new GameOver();
        
        // Menu -> CharacterMenu
        mM.setCharacterScreen(cM);
        // CharacterMenu -> ViewControls
        cM.setVCScreen(vC);
        // View Controls -> CharacterMenu
        vC.setCharacterScreen(cM);
        // CharacterMenu -> GamePlay
        cM.setPlayScreen(gP);
        // GamePlay -> GameOver
        gP.setGameOverScreen(gO);
        
        stage.setScene(mM.getScene());
        stage.show();
    }
}