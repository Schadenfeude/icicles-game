package com.primegi.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.primegi.gamedev.icicles.model.Icicles;
import com.primegi.gamedev.icicles.model.Player;

public class IciclesScreen implements Screen {
    public static final String TAG = IciclesScreen.class.getSimpleName();

    private ExtendViewport iciclesViewport;
    private ScreenViewport hudViewport;
    private ShapeRenderer renderer;

    private SpriteBatch batch;
    private BitmapFont font;

    private Player player;
    private Icicles icicles;

    private int topScore;

    @Override
    public void show() {
        iciclesViewport = new ExtendViewport(Constants.World.WORLD_SIZE, Constants.World.WORLD_SIZE);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        hudViewport = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        player = new Player(iciclesViewport);
        icicles = new Icicles(iciclesViewport);

        topScore = 0;
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        icicles.update(delta);

        if (player.hitByIcicle(icicles)) {
            icicles.init();
        }

        iciclesViewport.apply(true);
        Gdx.gl.glClearColor(Constants.World.BACKGROUND_COLOR.r,
                Constants.World.BACKGROUND_COLOR.g,
                Constants.World.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(iciclesViewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.Icicle.ICICLE_COLOR);
        icicles.render(renderer);
        player.render(renderer);
        renderer.end();

        topScore = Math.max(topScore, icicles.getIciclesDodged());
        hudViewport.apply();

        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        font.draw(batch, "Deaths: " + player.getDeaths(),
                Constants.HUD.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD.HUD_MARGIN);

        font.draw(batch, "Score: " + icicles.getIciclesDodged() + "\nTop Score: " + topScore,
                hudViewport.getWorldWidth() - Constants.HUD.HUD_MARGIN,
                hudViewport.getWorldHeight() - Constants.HUD.HUD_MARGIN,
                0, Align.right, false);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        iciclesViewport.update(width, height, true);

        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD.HUD_FONT_REFERENCE_SCREEN_SIZE);

        player.init();
        icicles.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
