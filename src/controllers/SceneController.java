package controllers;

import javafx.scene.Group;
import javafx.scene.Node;
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
	
	public void changeScenes(GameScene aSrcScene, GameScene aDstScene)
	{
		removeSrcSceneFromGameRoot(aSrcScene);
		addDstSceneToGameRoot(aDstScene);
	}
	
	private void removeSrcSceneFromGameRoot(GameScene aSrcScene)
	{
		fGameRoot.getChildren().remove(aSrcScene.getRoot());
	}
	
	private void addDstSceneToGameRoot(GameScene aDstScene)
	{
		fGameRoot.getChildren().add(0, aDstScene.getRoot());
		//fGameRoot.getChildren().add(aDstRoot);
	}
}