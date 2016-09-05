package views.scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class GameScene {
	void constructSceneFromFile(String aFileName)
	{
		File file = new File(aFileName);
		try {
			Scanner fileScanner = new Scanner(file);
			
			// Parse the file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!!");
			e.printStackTrace();
		} 
	}
}