package specialproblem;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ThirdCoding extends State {
	private UIManager uiManager;
	private UIImageButton mute, unmute, proceed, restartLevel;
	private String result;
	
	public ThirdCoding(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		
		mute = new UIImageButton(794 - 58, 10, 0, 0, Assets.mute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().pauseMusic();
			}
		});
		
		uiManager.addObject(mute);
		
		unmute = new UIImageButton(794 - 58, 10, 0, 0, Assets.unmute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().playMusic();
			}
		});
		
		uiManager.addObject(unmute);
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setPrevState(State.getState());
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
				handler.getGame().getDisplay().setFrameInvisible(2);
			}
		}));
		
		uiManager.addObject(new UIImageButton(540 + 79, 470, 96, 64, Assets.submit_btn, new ClickListener() {
			@Override
			public void onClick() {
				createScriptFile();
				concatScriptFile();
				runScripts();
				checkOutput();
			}
		}));
		
		proceed = new UIImageButton(540 + 79 + 96 + 10, 470, 0, 0, Assets.proceed, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().writeHintsToFile();
				handler.getGame().setHints(0);
				handler.getGame().unlockLevel(6);
				handler.getGame().levelSelect.setUnlockedLevels();
				reset();
				handler.getGame().readHintWordsFormed();
				handler.getGame().setHints(0);
				State.setPrevState(null);
				State.setState(new LevelDone(handler, 0, 6, (float) 0.0));
				State.getState().setUIManager();
				handler.getGame().getDisplay().setFrameInvisible(2);
			}
		});
		
		uiManager.addObject(proceed);
		
		restartLevel = new UIImageButton(794 - 58 - 58, 10, 48, 32, Assets.restartLevel, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().createNewInstance(5);
				handler.getGame().readHintWordsFormed();
				handler.getGame().setHints(0);
				handler.getGame().getDisplay().setFrameInvisible(2);
				reset();
				State.setState(handler.getGame().teaching[5]);
				handler.getGame().teaching[5].setUIManager();
			}
		});
		
		uiManager.addObject(restartLevel);
		
		result = "";
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
//		State.getState().setUIManager();
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.updateRender();
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.GRAY);
		g.fillRect(40, 600 - 540 - 20, 500, 540);
		
		g.drawImage(Assets.blackBoard3, 560, 62, null);
		g.drawImage(Assets.codeHere, 545, 435, null);
		
		if(handler.getGame().getDisplay().getFrame().isActive() == false && handler.getGame().getDisplay().getChildFrame(2).isActive() == false) {
			handler.getGame().getDisplay().getChildFrame(2).setExtendedState(Frame.ICONIFIED);
		}
		else {
			handler.getGame().getDisplay().getChildFrame(2).setExtendedState(Frame.NORMAL);
		}
		
		if(result.equals("") == false) {
			if(result.equals("true")) {
				Text.drawString(g, "Code Works!", 540 + 180, 560, true, Color.BLACK, Assets.monospace);
			}
			else {
				Text.drawString(g, "Incorrect! Try Again...", 540 + 180, 560, true, Color.BLACK, Assets.monospace);
			}
		}
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
		handler.getGame().getDisplay().setFrameVisible(2);
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
	
	public void createScriptFile() {
		try {
//			Process pr = Runtime.getRuntime().exec("rm scripts/userscript.sh");
			BufferedWriter outFile = new BufferedWriter(new FileWriter("scripts/userscript.sh"));
			handler.getGame().getDisplay().getTextArea(2).write(outFile);
			outFile.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void concatScriptFile() {
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/concat3_func.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/concat3_func.sh"};
			Process pr2 = Runtime.getRuntime().exec(cmd);
			while(pr2.isAlive()) {
				
			}
		}
		catch(Exception e) {
			
		}
	}
	
	public void runScripts() {
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/userscript.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/userscript.sh"};
			Process pr2 = Runtime.getRuntime().exec(cmd);
			while(pr2.isAlive()) {
				
			}
			
			if(pr2.exitValue() != 0) {
				BufferedWriter writer = new BufferedWriter(new FileWriter("scripts/result.txt"));
				writer.write("false");
				writer.close();
			}
			
//			System.out.println("run scrpts");
//			checkOutput(pr2);
		}
		catch(Exception e) {
			
		}
	}
	
	public void checkOutput() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("scripts/result.txt"));
			
			result = reader.readLine();
			reader.close();
			
			if(result.equals("true")) {
				enableProceed();
			}
			else {
				disableProceed();
			}
			
			deleteScripts();
		}
		catch(Exception e) {
			
		}
	}
	
	public void deleteScripts() {
		try {
			File f1 = new File("scripts/result.txt");
			File f2 = new File("scripts/userscript.sh");
			
			f1.delete();
			f2.delete();
		}
		catch(Exception e) {
			
		}
	}
	
	public void reset() {
		result = "";
		handler.getGame().getDisplay().getTextArea(2).setText("#!/bin/bash");
		disableProceed();
	}
	
	public int getLevel() {
		return 6;
	}

	public void enableProceed() {
		proceed.setWidth(32 * 3);
		proceed.setHeight(32 * 2);
		proceed.updateBounds();
	}
	
	public void disableProceed() {
		proceed.setWidth(0);
		proceed.setHeight(0);
		proceed.updateBounds();
	}
}
