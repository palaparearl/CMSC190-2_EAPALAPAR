package specialproblem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Credits extends State {
	private UIManager uiManager;
	private UIImageButton mute, unmute;
	
	public Credits(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
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
		
		uiManager.addObject(new UIImageButton(450 - 96 / 2, 500, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(handler.getGame().getBgMusicPlayer().status.equals("play")) {
			onMuteIcon();
		}
		else {
			onUnmuteIcon();
		}
		uiManager.updateRender();
		
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(handler.getWidth() / 2 - 150, 15, 300, 70, 35, 25);
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
		Text.drawString(g, "CREDITS", handler.getGame().getWidth() / 2, 57, true, Color.BLACK, Assets.courier);
		
		g.setColor(Color.GRAY);
		g.fillRoundRect(50, 130, handler.getWidth() - 100, 350, 35, 35);
		
//		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawRoundRect(50 + 5, 130 + 5, handler.getWidth() - 100 - 10, 350 - 10, 35, 35);
		g2.setStroke(oldStroke);
		
		Text.drawString(g, "Developer:", 100, 200, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, "Adviser:", 100, 260, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, "Sprites:", 100, 320, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, "Background Music:", 100, 380, false, Color.BLACK, Assets.monospace);
		
		Text.drawString(g, "Earl Joseph Palapar", 450, 200, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, "Zenith Arnejo", 450, 260, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, "Earl Joseph Palapar", 450, 320, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, "\"Night in Venice\"", 450, 380, false, Color.BLACK, Assets.monospace);
		Text.drawString(g, " by Kevin MacLeod", 450, 410, false, Color.BLACK, Assets.monospace);
		
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
