package specialproblem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class MenuState extends State{
	
	private UIManager uiManager;
	private UIImageButton mute, unmute;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		mute = new UIImageButton(794 + 48, 10, 0, 0, Assets.mute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().pauseMusic();
			}
		});
		
		uiManager.addObject(mute);
		
		unmute = new UIImageButton(794 + 48, 10, 0, 0, Assets.unmute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().playMusic();
			}
		});
		
		uiManager.addObject(unmute);
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 200, 128, 64, Assets.play_btn, new ClickListener() {
			@Override
			public void onClick() {
//				handler.getMouseManager().setUIManager(null);
				
//				State.setState(handler.getGame().crosswords[0]);
//				handler.getGame().crosswords[0].setUIManager();
				
				handler.getGame().levelSelect.setUnlockedLevels();
				State.setState(handler.getGame().levelSelect);
				handler.getGame().levelSelect.setUIManager();
//				
//				State.setState(handler.getGame().coding);
//				handler.getGame().coding.setUIManager();
			}
		}));
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 300, 128, 64, Assets.tutorial_btn, new ClickListener() {
			@Override
			public void onClick() {
//				System.out.println("tutorial!");
				
				State.setState(handler.getGame().tutorial);
				handler.getGame().tutorial.setUIManager();
				
//				State.setState(handler.getGame().secondCoding);
//				handler.getGame().secondCoding.setUIManager();
			}
		}));
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 400, 128, 64, Assets.credits_btn, new ClickListener() {
			@Override
			public void onClick() {
//				System.out.println("credits!");
				State.setState(handler.getGame().credits);
				handler.getGame().credits.setUIManager();
				
//				State.setState(handler.getGame().thirdCoding);
//				handler.getGame().thirdCoding.setUIManager();
			}
		}));
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 500, 128, 64, Assets.quit_btn, new ClickListener() {
			@Override
			public void onClick() {
				System.exit(0);
				
//				State.setState(handler.getGame().fourthCoding);
//				handler.getGame().fourthCoding.setUIManager();
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		if(handler.getGame().getBgMusicPlayer().status.equals("play")) {
			onMuteIcon();
		}
		else {
			onUnmuteIcon();
		}
		State.getState().setUIManager();
		uiManager.updateRender();
		
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
		
		Text.drawString(g, "Less Hell with Shell", handler.getGame().getWidth() / 2 + 2,
				100 + 2, true, Color.ORANGE, Assets.font50);
		
		Text.drawString(g, "Less Hell with Shell", handler.getGame().getWidth() / 2,
				100, true, Color.YELLOW, Assets.font50);
		
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
		g.setColor(Color.GRAY);
		g.drawRoundRect((handler.getGame().getWidth() / 2) - 64 - 5, 200 - 5, 128 + 10, 64 + 10, 25, 25);
		g.drawRoundRect((handler.getGame().getWidth() / 2) - 64 - 5, 300 - 5, 128 + 10, 64 + 10, 25, 25);
		g.drawRoundRect((handler.getGame().getWidth() / 2) - 64 - 5, 400 - 5, 128 + 10, 64 + 10, 25, 25);
		g.drawRoundRect((handler.getGame().getWidth() / 2) - 64 - 5, 500 - 5, 128 + 10, 64 + 10, 25, 25);
		g2.setStroke(oldStroke);
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

	public void onMuteIcon() {
		mute.setWidth(48);
		mute.setHeight(32);
		mute.updateBounds();
		
		unmute.setWidth(0);
		unmute.setHeight(0);
		unmute.updateBounds();
	}
	
	public void onUnmuteIcon() {
		mute.setWidth(0);
		mute.setHeight(0);
		mute.updateBounds();
		
		unmute.setWidth(48);
		unmute.setHeight(32);
		unmute.updateBounds();
	}
}
