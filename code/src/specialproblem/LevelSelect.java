package specialproblem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class LevelSelect extends State{
	
	private UIManager uiManager;
	private UIImageButton[] levelButtons;
	private UIImageButton mute, unmute;
	
	public LevelSelect(Handler handler) {
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
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		levelButtons = new UIImageButton[10];
		
		levelButtons[0] = new UIImageButton(106, 100 + 15, 0, 0, Assets.levels[0], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[0]);
					handler.getGame().teaching[0].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 0) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[0]);
						handler.getGame().teaching[0].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[0]);
		
		levelButtons[1] = new UIImageButton(106, 100 + 85 + 15 + 15, 0, 0, Assets.levels[1], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[1]);
					handler.getGame().teaching[1].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 1) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[1]);
						handler.getGame().teaching[1].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[1]);
		
		levelButtons[2] = new UIImageButton(106, 100 + 85 + 15 + 85 + 15 + 15, 0, 0, Assets.levels[2], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[2]);
					handler.getGame().teaching[2].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() -1 != 2) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[2]);
						handler.getGame().teaching[2].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[2]);
		
		levelButtons[3] = new UIImageButton(106, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 15, 0, 0, Assets.levels[3], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[3]);
					handler.getGame().teaching[3].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 3) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[3]);
						handler.getGame().teaching[3].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[3]);
		
		levelButtons[4] = new UIImageButton(106, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15 + 15, 0, 0, Assets.levels[4], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[4]);
					handler.getGame().teaching[4].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 4) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[4]);
						handler.getGame().teaching[4].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[4]);
		
		levelButtons[5] = new UIImageButton(504, 100 + 15, 0, 0, Assets.levels[5], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[5]);
					handler.getGame().teaching[5].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 5) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[5]);
						handler.getGame().teaching[5].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[5]);
		
		levelButtons[6] = new UIImageButton(504, 100 + 85 + 15 + 15, 0, 0, Assets.levels[6], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[6]);
					handler.getGame().teaching[6].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 6) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[6]);
						handler.getGame().teaching[6].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[6]);
		
		levelButtons[7] = new UIImageButton(504, 100 + 85 + 15 + 85 + 15 + 15, 0, 0, Assets.levels[7], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[7]);
					handler.getGame().teaching[7].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 7) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[7]);
						handler.getGame().teaching[7].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[7]);
		
		levelButtons[8] = new UIImageButton(504, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 15, 0, 0, Assets.levels[8], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[8]);
					handler.getGame().teaching[8].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 8) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[8]);
						handler.getGame().teaching[8].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[8]);
		
		levelButtons[9] = new UIImageButton(504, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15 + 15, 0, 0, Assets.levels[9], new ClickListener() {
			@Override
			public void onClick() {
				if(State.getPrevState() == null) {
					State.setState(handler.getGame().teaching[9]);
					handler.getGame().teaching[9].setUIManager();
				}
				else {
					if(State.getPrevState().getLevel() - 1 != 9) {
						// clear prev state here
						State.getPrevState().reset();
						handler.getGame().readHintWordsFormed();
						handler.getGame().setHints(0);
						//
						State.setState(handler.getGame().teaching[9]);
						handler.getGame().teaching[9].setUIManager();
					}
					else {
						State.setState(State.getPrevState());
						State.getState().setUIManager();
						if(State.getPrevState() instanceof Maze) {
							State.getPrevState().startTimer();
						}
					}
				}
				
				State.setPrevState(null);
			}
		});
		uiManager.addObject(levelButtons[9]);
		
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
		g.fillRoundRect(handler.getWidth() / 2 - 250, 15, 500, 70, 35, 25);
		
//		g.fillRoundRect(15, 100, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15 + 85 + 15 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(900 - 15 - 430, 100, 430, 85, 35, 25);
		
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
		g.setColor(Color.GRAY);
		g.drawRoundRect(106 - 5, 100 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(106 - 5, 100 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(106 - 5, 100 + 85 + 15 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(106 - 5, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(106 - 5, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(504 - 5, 100 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(504 - 5, 100 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(504 - 5, 100 + 85 + 15 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(504 - 5, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g.drawRoundRect(504 - 5, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15 + 15 - 5, 290 + 10, 55 + 10, 25, 25);
		g2.setStroke(oldStroke);
		
		g.drawImage(Assets.levelsLocked[0], 106, 100 + 15, null);
		g.drawImage(Assets.levelsLocked[1], 106, 100 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[2], 106, 100 + 85 + 15 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[3], 106, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[4], 106, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[5], 504, 100 + 15, null);
		g.drawImage(Assets.levelsLocked[6], 504, 100 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[7], 504, 100 + 85 + 15 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[8], 504, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 15, null);
		g.drawImage(Assets.levelsLocked[9], 504, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15 + 15, null);
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
		Text.drawString(g, "SELECT LEVEL", handler.getGame().getWidth() / 2, 57, true, Color.BLACK, Assets.courier);
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
	public void setUnlockedLevels() {
		for(int i = 0; i < 10; i++) {
			if(handler.getGame().getLevelsUnlocked(i) != -1) {
				levelButtons[i].setWidth(290);
				levelButtons[i].setHeight(55);
				levelButtons[i].updateBounds();
			}
		}
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
