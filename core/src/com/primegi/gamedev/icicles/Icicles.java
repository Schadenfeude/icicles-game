package com.primegi.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Icicles {
    public static final String TAG = Icicles.class.getSimpleName();

    private DelayedRemovalArray<Icicle> icicleList;
    private Viewport viewport;

    public Icicles(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        icicleList = new DelayedRemovalArray<Icicle>(false, 100);
    }

    public void update(float delta) {
        if (MathUtils.random() < delta * Constants.Icicle.ICICLE_SPAWNS_PER_SECOND) {
            final Vector2 newIciclePosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight());
            final Icicle newIcicle = new Icicle(newIciclePosition);
            icicleList.add(newIcicle);
        }

        for (final Icicle icicle : icicleList) {
            icicle.update(delta);
        }

        icicleList.begin();
        for (int i = 0; i < icicleList.size; i++) {
            if (outOfScreen(icicleList.get(i))) {
                icicleList.removeIndex(i);
            }
        }
        icicleList.end();
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.Icicle.ICICLE_COLOR);

        for (Icicle icicle : icicleList) {
            icicle.render(renderer);
        }
    }

    private boolean outOfScreen(Icicle icicle) {
        return icicle.position.y < -Constants.Icicle.ICICLE_HEIGHT;
    }
}
