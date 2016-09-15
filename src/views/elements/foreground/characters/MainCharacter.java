package views.elements.foreground.characters;

import utils.PictureNames;
import utils.Vector;

/**
 * Because this class is instantiated only once (only one main character),
 * I decided it would be easier to have the MainCharacter default to the 
 * PictureNames.MainCharacter photo. This way, one can simply go to the picture constants file
 * in utils to change the main character's picture
 * 
 * This code assumes that there is a MainCharacter field in the PictureNames class.  If this field
 * name is changed, then this class will produce an error.  This class also assumes that the MainCharacter
 * field stores a valid name of a picture
 * 
 * This code only depends on PictureNames and the Character superclass
 * 
 * A main character can be created simply by:
 * MainCharacter mainCharacter = new MainCharacter();
 * To add it's root to the scene, you may access it's root by
 * mainCharacter.getRoot();
 * since Character extends SceneElement
 * 
 * @author matthewfaw
 *
 */

public class MainCharacter extends Character {
	public static final Vector DEFAULT_POSITION = new Vector(200, 50);
	
	/**
	 * Creates a new main character
	 */
	public MainCharacter(String aPictureName)
	{
		super(aPictureName);
	}
}