package dhu.android.games.snake;

import android.graphics.Color;
import dhu.android.games.framework.Game;
import dhu.android.games.framework.Graphics;
import dhu.android.games.framework.Pixmap;
import dhu.android.games.framework.Screen;
import dhu.android.games.framework.Input.TouchEvent;

import java.util.List;

//メインゲーム画面
public class GameScreen extends Screen {
    //
    enum GameState {
        Ready,      //準備
        Running,    //実行中
        Paused,     //一時停止中
        GameOver    //ゲームオーバー
    }

    //準備
    GameState state = GameState.Ready;

    //Contraller
    World world;

    //スコア
    int oldScore = 0;
    //
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
    		
        //入力検出と更新処理
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        switch (state) {
            case Ready:
                updateReady(touchEvents);
                break;
            case Running:
                updateRunning(touchEvents, deltaTime);
                break;
            case Paused:
                updatePaused(touchEvents);
                break;
            case GameOver:
                updateGameOver(touchEvents);
                break;
        }
    }

    //Ready!?　の画面
    private void updateReady(List<TouchEvent> touchEvents) {
        //タッチされたら、実行に遷移
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    //メインゲーム
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        final int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            final TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    state = GameState.Paused;
                    return;
                }
            }
            //buttonの入力検出
            if (event.type == TouchEvent.TOUCH_DOWN) {
                //左方向転換
                if (event.x < 64 && event.y > 416) {
                    world.snake.moveLeft();
                }
                //右方向転換
                if (event.x > 256 && event.y > 416) {
                    world.snake.moveRight();
                }
                //上方向転換
                if (event.x < 128 && event.y > 416 &&
                        event.x > 64) {
                    world.snake.moveUP();
                }
                //下方向転換
                if (event.x > 192 && event.y > 416 &&
                        event.x < 256) {
                    world.snake.moveDown();
                }
            }
        }

        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.bitten.play(1);
            }
            state = GameState.GameOver;
        }
        if (oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if (Settings.soundEnabled) {
                Assets.eat.play(1);
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    }
                    if (event.y > 148 && event.y < 196) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }
        drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2, g.getHeight() - 42);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();
        Snake snake = world.snake;
        SnakePart head = snake.parts.get(0);
        Egg stain = world.stain;

        Pixmap stainPixmap = null;
        if (stain.type == Egg.TYPE_1) {
            stainPixmap = Assets.egg1;
        }
        if (stain.type == Egg.TYPE_2) {
            stainPixmap = Assets.egg2;
        }
        if (stain.type == Egg.TYPE_3) {
            stainPixmap = Assets.egg3;
        }
        int x = stain.x * 32;
        int y = stain.y * 32;
        g.drawPixmap(stainPixmap, x, y);

        int len = snake.parts.size();
        for (int i = 1; i < len; i++) {
            SnakePart part = snake.parts.get(i);
            x = part.x * 32;
            y = part.y * 32;
            g.drawPixmap(Assets.tail, x, y);
        }

        Pixmap headPixmap = null;
        if (snake.direction == Snake.UP) {
            headPixmap = Assets.headUp;
        }
        if (snake.direction == Snake.LEFT) {
            headPixmap = Assets.headLeft;
        }
        if (snake.direction == Snake.DOWN) {
            headPixmap = Assets.headDown;
        }
        if (snake.direction == Snake.RIGHT) {
            headPixmap = Assets.headRight;
        }
        x = head.x * 32 + 16;
        y = head.y * 32 + 16;
        g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
        g.drawPixmap(Assets.arrowUP, 64, 416);
        g.drawPixmap(Assets.arrowDOWN, 192, 416);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            }
            else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;

        }

        if (world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
