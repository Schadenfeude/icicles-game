package com.primegi.gamedev.icicles.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.primegi.gamedev.icicles.Constants;

public class Icicles {
    public static final String TAG = Icicles.class.getSimpleName();

    DelayedRemovalArray<Icicle> icicleList;

    private Viewport viewport;
    private int iciclesDodged;

    public Icicles(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    //region Public API
    public void init() {
        icicleList = new DelayedRemovalArray<Icicle>(false, 100);
        iciclesDodged = 0;
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
                iciclesDodged += 1;
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

    public int getIciclesDodged() {
        return iciclesDodged;
    }

    //region Public API

    private boolean outOfScreen(Icicle icicle) {
        return icicle.position.y < -Constants.Icicle.ICICLE_HEIGHT;
    }
}
