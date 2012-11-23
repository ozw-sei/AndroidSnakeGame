package dhu.android.games.snake;

import dhu.android.games.framework.Game;
import dhu.android.games.framework.Graphics;
import dhu.android.games.framework.Music;
import dhu.android.games.framework.Pixmap;
import dhu.android.games.framework.Sound;
import dhu.android.games.framework.Graphics.PixmapFormat;

public class Assets {
    //背景
    public static Pixmap background;
    //ロゴ
    public static Pixmap logo;
    //メニューUI
    public static Pixmap mainMenu;
    //音量などのボタン
    public static Pixmap buttons;
    //矢印↑
    public static Pixmap arrowUP;
    //矢印↓
    public static Pixmap arrowDOWN;
    //マニュアル
    public static Pixmap help1;
    public static Pixmap help2;
    public static Pixmap help3;
    //数字
    public static Pixmap numbers;
    //Ready文字
    public static Pixmap ready;
    //Pause文字
    public static Pixmap pause;
    //ゲームオーバ
    public static Pixmap gameOver;
    //蛇の頭↑
    public static Pixmap headUp;
    //蛇の頭←
    public static Pixmap headLeft;
    //蛇の頭↓
    public static Pixmap headDown;
    //蛇の頭→
    public static Pixmap headRight;
    //蛇の尻尾（体）
    public static Pixmap tail;
    //food1
    public static Pixmap egg1;
    //food2
    public static Pixmap egg2;
    //food3
    public static Pixmap egg3;

    public static Sound click;
    public static Sound eat;
    public static Sound bitten;

    public static void load(Game game) {
        Graphics g = game.getGraphics();
        background = g.newPixmap("background.png", PixmapFormat.RGB565);
        logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
        headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
        headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
        headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
        tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        egg1 = g.newPixmap("egg1.png", PixmapFormat.ARGB4444);
        egg2 = g.newPixmap("egg2.png", PixmapFormat.ARGB4444);
        egg3 = g.newPixmap("egg3.png", PixmapFormat.ARGB4444);
        arrowUP = g.newPixmap("arrow_up.png", PixmapFormat.ARGB4444);
        arrowDOWN = g.newPixmap("arrow_down.png", PixmapFormat.ARGB4444);

        click = game.getAudio().newSound("click.mp3");
        eat = game.getAudio().newSound("eat.wav");
        
        bitten = game.getAudio().newSound("hit.mp3");
        Settings.load(game.getFileIO());
    }
}
