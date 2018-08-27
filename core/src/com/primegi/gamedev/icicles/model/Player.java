package com.primegi.gamedev.icicles.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.primegi.gamedev.icicles.Constants;

public class Player {
    public static final String TAG = Player.class.getSimpleName();

    private Vector2 position;
    private Viewport viewport;

    public Player(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    //region Public API
    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.Player.PLAYER_HEAD_HEIGHT);
    }

    public void update(float delta) {
        switch (Gdx.app.getType()) {
            case Android:
                accelerometerControls(delta);
                break;
            case Desktop:
                keyControls(delta);
                break;
            default:
        }

        ensureInBounds();
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.Player.PLAYER_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y, Constants.Player.PLAYER_HEAD_RADIUS, Constants.Player.PLAYER_HEAD_SEGMENTS);

        final Vector2 torsoTop = new Vector2(position.x, position.y - Constants.Player.PLAYER_HEAD_RADIUS);
        final Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 2 * Constants.Player.PLAYER_HEAD_RADIUS);

        renderer.rectLine(torsoTop, torsoBottom, Constants.Player.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x + Constants.Player.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.Player.PLAYER_HEAD_RADIUS, Constants.Player.PLAYER_LIMB_WIDTH);
        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x - Constants.Player.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.Player.PLAYER_HEAD_RADIUS, Constants.Player.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x + Constants.Player.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.Player.PLAYER_HEAD_RADIUS, Constants.Player.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x - Constants.Player.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.Player.PLAYER_HEAD_RADIUS, Constants.Player.PLAYER_LIMB_WIDTH);
    }

    public boolean hitByIcicle(Icicles icicles) {
        boolean isHit = false;

        for (final Icicle icicle : icicles.icicleList) {
            if (icicle.position.dst(position) < Constants.Player.PLAYER_HEAD_RADIUS) {
                isHit = true;
            }
        }

        return isHit;
    }
    //endregion Public API

    //region Private Methods
    private void accelerometerControls(float delta) {
        float accelerometerInput = -Gdx.input.getAccelerometerY() /
                (Constants.Physics.GRAVITATIONAL_ACCELERATION * Constants.Sensors.ACCELEROMETER_SENSITIVITY);

        position.x += -delta * accelerometerInput * Constants.Player.PLAYER_MOVEMENT_SPEED;
    }

    private void keyControls(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * Constants.Player.PLAYER_MOVEMENT_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * Constants.Player.PLAYER_MOVEMENT_SPEED;
        }
    }

    private void ensureInBounds() {
        if (position.x - Constants.Player.PLAYER_HEAD_RADIUS < 0) {
            position.x = Constants.Player.PLAYER_HEAD_RADIUS;
        }
        if (position.x + Constants.Player.PLAYER_HEAD_RADIUS > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - Constants.Player.PLAYER_HEAD_RADIUS;
        }
    }
    //endregion Private Methods
}
