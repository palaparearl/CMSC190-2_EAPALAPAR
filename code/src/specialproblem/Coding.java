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

public class Coding extends State {
	private UIManager uiManager;
	private UIImageButton mute, unmute, proceed, restartLevel;
	private String result;
//	private JFrame frame;
//	private JTextArea textArea;

	public Coding(Handler handler) {
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
//				try {
//					String[] cmd = new String[] {"/bin/sh", "script.sh"};
//					Process pr = Runtime.getRuntime().exec(cmd);
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//				}
				
				State.setPrevState(State.getState());
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
				handler.getGame().getDisplay().setFrameInvisible(0);
			}
		}));
		
		uiManager.addObject(new UIImageButton(540 + 79, 470, 96, 64, Assets.submit_btn, new ClickListener() {
			@Override
			public void onClick() {
				createScriptFile();
				if(checkKeyword() == true) {
					concatScriptFile();
					runScripts();
				}
				checkOutput();
			}
		}));
		
		proceed = new UIImageButton(540 + 79 + 96 + 10, 470, 0, 0, Assets.proceed, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().writeHintsToFile();
				handler.getGame().setHints(0);
				handler.getGame().unlockLevel(3);
				handler.getGame().levelSelect.setUnlockedLevels();
				reset();
				handler.getGame().readHintWordsFormed();
				handler.getGame().setHints(0);
				State.setPrevState(null);
				State.setState(new LevelDone(handler, 0, 3, (float) 0.0));
				State.getState().setUIManager();
				handler.getGame().getDisplay().setFrameInvisible(0);
			}
		});
		
		uiManager.addObject(proceed);
		
		restartLevel = new UIImageButton(794 - 58 - 58, 10, 48, 32, Assets.restartLevel, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().createNewInstance(2);
				handler.getGame().readHintWordsFormed();
				handler.getGame().setHints(0);
				handler.getGame().getDisplay().setFrameInvisible(0);
				reset();
				State.setState(handler.getGame().teaching[2]);
				handler.getGame().teaching[2].setUIManager();
			}
		});
		
		uiManager.addObject(restartLevel);
		
		result = "";
		
//		frame = new JFrame();
//		textArea = new JTextArea();
//		textArea.setBounds(0, 0, 300, 300);
//		textArea.setBackground(Color.DARK_GRAY);
//		textArea.setForeground(Color.WHITE);
//		textArea.setFont(Assets.monospace);
//		textArea.setTabSize(3);
//		textArea.setCaretColor(Color.WHITE);
//		
//		frame.add(textArea);
//		frame.setUndecorated(true);
//		frame.setAlwaysOnTop(true);
//		frame.setSize(300, 300);
//		frame.setLayout(null);
		
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
		
		g.drawImage(Assets.blackBoard1, 560, 62, null);
		g.drawImage(Assets.codeHere, 545, 435, null);
//		Text.drawStringMultiLine(g, "-create a function named \"funcname\" that accepts three arguments and prints these arguments, but only the variable for the first argument must be used for printing", 274, 580, 97, Color.WHITE, Assets.filmCryptic);
		
//		frame.setLocation(handler.getGame().getDisplay().getFrame().getX() + 20, handler.getGame().getDisplay().getFrame().getY() + 60);
		if(handler.getGame().getDisplay().getFrame().isActive() == false && handler.getGame().getDisplay().getChildFrame(0).isActive() == false) {
			handler.getGame().getDisplay().getChildFrame(0).setExtendedState(Frame.ICONIFIED);
		}
		else {
			handler.getGame().getDisplay().getChildFrame(0).setExtendedState(Frame.NORMAL);
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
		handler.getGame().getDisplay().setFrameVisible(0);
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
			handler.getGame().getDisplay().getTextArea(0).write(outFile);
			outFile.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void concatScriptFile() {
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/concat1_func.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/concat1_func.sh"};
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
	
	public boolean checkKeyword() {
		String returnVal = "";
		
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/keyword1.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/keyword1.sh"};
			Process pr2 = Runtime.getRuntime().exec(cmd);
			while(pr2.isAlive()) {
				
			}
			
			BufferedReader reader = new BufferedReader(new FileReader("scripts/result.txt"));
			
			returnVal = reader.readLine();
			reader.close();
		}
		catch(Exception e) {
			
		}
		
		if(returnVal.equals("true")) {
			return true;
		}
		
		return false;
	}
	
	public void reset() {
		result = "";
		handler.getGame().getDisplay().getTextArea(0).setText("#!/bin/bash");
		disableProceed();
	}
	
	public int getLevel() {
		return 3;
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
