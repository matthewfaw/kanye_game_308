package controllers;

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
	
	public SceneController(Group aGameRoot)
	{
		fGameRoot = aGameRoot;
	}
	
	public void transportToNewScene(Tunnel aTunnel)
	{
		removeSrcSceneFromGameRoot(aTunnel.getSrc());
		addDstSceneToGameRoot(aTunnel.getDst());
	}
	
	private void removeSrcSceneFromGameRoot(GameScene aSrcScene)
	{
		fGameRoot.getChildren().remove(aSrcScene.getRoot());
	}
	
	private void addDstSceneToGameRoot(GameScene aDstScene)
	{
		fGameRoot.getChildren().add(0, aDstScene.getRoot());
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
}