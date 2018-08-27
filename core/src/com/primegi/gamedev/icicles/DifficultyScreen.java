package com.primegi.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.primegi.gamedev.icicles.Constants.Difficulty.DifficultyLevel;

public class DifficultyScreen  extends InputAdapter implements Screen {
    public static final String TAG = DifficultyScreen.class.getName();

    private IciclesGame game;

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private FitViewport viewport;

    private BitmapFont font;

    public DifficultyScreen(IciclesGame game) {
        this.game = game;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        final Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constants.DifficultyWorld.EASY_CENTER) < Constants.DifficultyWorld.DIFFICULTY_BUBBLE_RADIUS) {
            game.showIciclesScreen(DifficultyLevel.EASY);
        }

        if (worldTouch.dst(Constants.DifficultyWorld.MEDIUM_CENTER) < Constants.DifficultyWorld.DIFFICULTY_BUBBLE_RADIUS) {
            game.showIciclesScreen(DifficultyLevel.MEDIUM);
        }

        if (worldTouch.dst(Constants.DifficultyWorld.HARD_CENTER) < Constants.DifficultyWorld.DIFFICULTY_BUBBLE_RADIUS) {
            game.showIciclesScreen(DifficultyLevel.HARD);
        }

        return true;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.DifficultyWorld.DIFFICULTY_WORLD_SIZE,
                Constants.DifficultyWorld.DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.DifficultyWorld.DIFFICULTY_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constants.World.BACKGROUND_COLOR.r, Constants.World.BACKGROUND_COLOR.g,
                Constants.World.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Constants.DifficultyWorld.EASY_COLOR);
        renderer.circle(Constants.DifficultyWorld.EASY_CENTER.x, Constants.DifficultyWorld.EASY_CENTER.y,
                Constants.DifficultyWorld.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.DifficultyWorld.MEDIUM_COLOR);
        renderer.circle(Constants.DifficultyWorld.MEDIUM_CENTER.x, Constants.DifficultyWorld.MEDIUM_CENTER.y,
                Constants.DifficultyWorld.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.DifficultyWorld.HARD_COLOR);
        renderer.circle(Constants.DifficultyWorld.HARD_CENTER.x, Constants.DifficultyWorld.HARD_CENTER.y,
                Constants.DifficultyWorld.DIFFICULTY_BUBBLE_RADIUS);

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.Difficulty.EASY_LABEL);
        font.draw(batch, Constants.Difficulty.EASY_LABEL, Constants.DifficultyWorld.EASY_CENTER.x,
                Constants.DifficultyWorld.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.Difficulty.MEDIUM_LABEL);
        font.draw(batch, Constants.Difficulty.MEDIUM_LABEL, Constants.DifficultyWorld.MEDIUM_CENTER.x,
                Constants.DifficultyWorld.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.Difficulty.HARD_LABEL);
        font.draw(batch, Constants.Difficulty.HARD_LABEL, Constants.DifficultyWorld.HARD_CENTER.x,
                Constants.DifficultyWorld.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
