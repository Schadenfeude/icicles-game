package com.primegi.gamedev.icicles;

import com.badlogic.gdx.Game;
import com.primegi.gamedev.icicles.Constants.Difficulty.DifficultyLevel;

public class IciclesGame extends Game {

    @Override
    public void create() {
        showDifficultyScreen();
    }

    public void showDifficultyScreen() {
        setScreen(new DifficultyScreen(this));
    }

    public void showIciclesScreen(DifficultyLevel difficulty) {
        setScreen(new IciclesScreen(this, difficulty));
    }
}
