package views.elements.foreground.characters;

public class Enemy extends Character {
	private boolean fIsActive;
	private int fId;
	
	public Enemy(String aEnemyFileName, int aId)
	{
		super(aEnemyFileName);
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