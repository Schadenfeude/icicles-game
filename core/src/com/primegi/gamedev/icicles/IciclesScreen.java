package com.primegi.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class IciclesScreen implements Screen {
    public static final String TAG = IciclesScreen.class.getSimpleName();

    private ExtendViewport iciclesViewport;
    private ShapeRenderer renderer;

    private Player player;
    private Icicles icicles;

    @Override
    public void show() {
        iciclesViewport = new ExtendViewport(Constants.World.WORLD_SIZE, Constants.World.WORLD_SIZE);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        player = new Player(iciclesViewport);
        icicles = new Icicles(iciclesViewport);
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        icicles.update(delta);

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
    }

    @Override
    public void resize(int width, int height) {
        iciclesViewport.update(width, height, true);
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
    }
}
