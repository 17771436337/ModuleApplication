package cai.project.game.game_teris.Listenner;


import cai.project.game.game_teris.base.Ground;
import cai.project.game.game_teris.base.Shape;

public interface ComeBackAdapter {

	public void refreshDisplay(Shape shape);
	public void refreshDisplay(Shape shape, Ground ground);
	public boolean isEnd(Shape eshape);
	public void gameOver();
}
