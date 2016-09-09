package controllers;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.obstacles.Tunnel;
import views.scenes.CollegeScene;
import views.scenes.DoorExplorationScene;
import views.scenes.ForestScene;
import views.scenes.GameScene;

/*
 * The purpose of the scene controller is to:
 * 1. Handle setting up a given scene
 * 2. Handle scene transitions
 */

public class SceneController {
	private Group fGameRoot;
	private int fSceneWidth;
	private int fSceneHeight;
	
	public SceneController()
	{
		//fGameRoot = aGameRoot;
//		fSceneWidth = (int) aGameRoot.getLayoutBounds().getWidth();
//		fSceneHeight = (int) aGameRoot.getLayoutBounds().getHeight();
	}
	
	public void createGameRoot(int aWidth, int aHeight)
	{
		fGameRoot = new Group();
		fSceneWidth = aWidth;
		fSceneHeight = aHeight;
	}
	
	public Group getGameRoot()
	{
		return fGameRoot;
	}
	
	public void transportToNewScene(Tunnel aTunnel)
	{
		removeSceneFromGameRoot(aTunnel.getSrc());
		addToGameRoot(aTunnel.getDst());
	}
	
	private void removeSceneFromGameRoot(GameScene aSrcScene)
	{
		fGameRoot.getChildren().remove(aSrcScene.getRoot());
	}
	
	public void addToGameRoot(GameScene aDstScene)
	{
		fGameRoot.getChildren().add(0, aDstScene.getRoot());
	}
	
	public void addToGameRoot(Group aDstRoot)
	{
		fGameRoot.getChildren().add(aDstRoot);
	}
	
	//XXX: perhaps make this more customizable?
	// Create all scenes of the game, and return the first one
	public GameScene createScenes(int aWidth, int aHeight)
	{
		CollegeScene collegeScene = new CollegeScene(aWidth, aHeight);
		ForestScene forestScene = new ForestScene(aWidth, aHeight);
		DoorExplorationScene doorExplorationScene = new DoorExplorationScene(aWidth, aHeight);

		collegeScene.getDstTunnel().setDst(forestScene);
		forestScene.getSrcTunnel().setDst(collegeScene);
		forestScene.getDstTunnel().setDst(doorExplorationScene);
		doorExplorationScene.getSrcTunnel().setDst(forestScene);

		GameScene initialScene = collegeScene;
		return initialScene;
	}
	
//	public void addEnemiesToScene()
//	{
//		CharacterController enemyController = new CharacterController(fGameRoot);
//		Group enemyRoot = enemyController.createEnemy(50,50);
//		fGameRoot.getChildren().add(enemyRoot);
//		
//	}
}