package views.elements.foreground.characters;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;
import utils.PictureNames;

public class Enemy extends Character {
	private boolean fIsActive;
	private int fId;
	
	public Enemy(int aWidth, int aHeight, String aEnemyFileName, int aId)
	{
		super(aWidth, aHeight, aEnemyFileName);
		fIsActive = true;
		fId = aId;
	}
	
	public boolean isActive()
	{
		return fIsActive;
	}
	
	public void setActivity(boolean aIsActive)
	{
		fIsActive = aIsActive;
	}
	
	public int getId()
	{
		return fId;
	}
}