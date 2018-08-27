package com.primegi.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Icicle {
    public static final String TAG = Icicle.class.getSimpleName();

    Vector2 position;
    private Vector2 velocity;

    public Icicle(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2();
    }

    public void update(float delta) {
        velocity.mulAdd(Constants.Icicle.ICICLE_ACCELERATION, delta);
        position.mulAdd(velocity, delta);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.Icicle.ICICLE_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.triangle(
                position.x, position.y,
                position.x - Constants.Icicle.ICICLE_WIDTH / 2, position.y + Constants.Icicle.ICICLE_HEIGHT,
                position.x + Constants.Icicle.ICICLE_WIDTH / 2, position.y + Constants.Icicle.ICICLE_HEIGHT
        );
    }
}
