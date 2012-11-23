package dhu.android.games.snake;

import dhu.android.games.framework.Game;
import dhu.android.games.framework.Graphics;
import dhu.android.games.framework.Screen;
import dhu.android.games.framework.Input.TouchEvent;

import java.util.List;

public class HelpScreen2 extends Screen {
    public HelpScreen2(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        Graphics g = game.getGraphics();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.x > g.getWidth() - 64 && event.y > g.getHeight() - 64) {
                game.setScreen(new HelpScreen3(game));
                if (Settings.soundEnabled) {
                    Assets.click.play(1);
                }
                return;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.help2, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
