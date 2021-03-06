package specialproblem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CrosswordTile extends UIObject{

	private BufferedImage[] images;
	private ClickListener clicker;
	private boolean selected;
	private Crossword crossword;
	private float origX, origY;
	private int alphabetIndex;

	public CrosswordTile(float x, float y, int width, int height, BufferedImage[] images, int alphabetIndex, Crossword crossword) {
		super(x, y, width, height);
		this.origX = x;
		this.origY = y;
		this.images = images;
		selected = false;
		this.alphabetIndex = alphabetIndex;
		this.clicker = new ClickListener() {
			@Override
			public void onClick() {
				changeCoord();
			}
		};
		this.crossword = crossword;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
	
	public void changeCoord() {
		if(selected == false) {
			this.x = crossword.getNumLettersSelected() * 50 + 300;
			this.y = 70;
			crossword.incrementNumLettersSelected();
			selected = !selected;
			updateBounds();
			this.crossword.buildWord(this.alphabetIndex);
		}
//		else {
//			this.x = origX;
//			this.y = origY;
//			crossword.decrementNumLettersSelected();
//		}
		
	}
	
	public void resetCoord() {
		this.x = origX;
		this.y = origY;
		crossword.decrementNumLettersSelected();
		selected = !selected;
		updateBounds();
	}
	
	

	public boolean isSelected() {
		return selected;
	}
	
	public int getOrigX() {
		return (int)origX;
	}
	
	public int getOrigY() {
		return (int)origY;
	}
	
	public void setOrigX(int x) {
		origX = (float)x;
	}
	
	public void setOrigY(int y) {
		origY = (float)y;
	}

}
