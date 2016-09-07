package controllers;

import javafx.scene.Group;
import javafx.scene.Node;

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
	
	public void changeScenes(Group aSrcRoot, Group aDstRoot)
	{
		removeSrcSceneFromGameRoot(aSrcRoot);
		addDstSceneToGameRoot(aDstRoot);
	}
	
	private void removeSrcSceneFromGameRoot(Group aSrcRoot)
	{
		fGameRoot.getChildren().remove(aSrcRoot);
	}
	
	private void addDstSceneToGameRoot(Group aDstRoot)
	{
		//fGameRoot.getChildren().add(aDstRoot);
	}
}