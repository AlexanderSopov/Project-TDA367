package edu.chl.Game.model.gameobject.entity.entityTools;
import edu.chl.Game.view.graphics.*;

public class LoadingSprites {

	private Sprite[] spriteArray;
	private int numberOfSprites;
	private SpriteSheet spriteSheet;
	private int width, height;
	
	public LoadingSprites(SpriteSheet spriteSheet, Sprite[] spriteArray, int numberOfSprites, int width, int height){
		this.width = width;
		this.height = height;
		this.spriteSheet = spriteSheet;
		this.numberOfSprites = numberOfSprites;
		this.spriteArray = spriteArray;
	}
	
	public void loadSprites(){

		for (int i = 0; i < (numberOfSprites/2); i++) {	
			spriteArray[i] = new Sprite(spriteSheet, i, 0, width, height);
		}

		for (int i = 0; i < (numberOfSprites/2); i++) { 
			spriteArray[i + (numberOfSprites/2)] = new Sprite(spriteSheet, i, 1, width, height);
		}

	}

}