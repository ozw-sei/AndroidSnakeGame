package dhu.android.games.snake;

import dhu.android.games.framework.Screen;
import dhu.android.games.framework.impl.AndroidGame;

public class SnakeGame extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }
}