package dhu.android.games.snake;

import dhu.android.games.framework.Game;
import dhu.android.games.framework.Graphics;
import dhu.android.games.framework.Screen;
import dhu.android.games.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
        Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
        Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
        Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        Assets.egg1 = g.newPixmap("egg1.png", PixmapFormat.ARGB4444);
        Assets.egg2 = g.newPixmap("egg2.png", PixmapFormat.ARGB4444);
        Assets.egg3 = g.newPixmap("egg3.png", PixmapFormat.ARGB4444);
        Assets.arrowUP = g.newPixmap("arrow_up.png", PixmapFormat.ARGB4444);
        Assets.arrowDOWN = g.newPixmap("arrow_down.png", PixmapFormat.ARGB4444);

        Assets.click = game.getAudio().newSound("button_04.mp3");
        Assets.eat = game.getAudio().newSound("iky1a.mp3");
        Assets.bitten = game.getAudio().newSound("loop_82.wav");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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
