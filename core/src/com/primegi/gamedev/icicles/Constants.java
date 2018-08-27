package com.primegi.gamedev.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static class World {
        public static final float WORLD_SIZE = 10.0f;
        public static final Color BACKGROUND_COLOR = Color.BLUE;
    }

    public static class HUD {
        public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
        public static final float HUD_MARGIN = 20.0f;
    }

    public static class Sensors {
        public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    }

    public static class Physics {
        public static final float GRAVITATIONAL_ACCELERATION = 9.8f;
    }

    public static class Player {
        public static final int PLAYER_HEAD_SEGMENTS = 20;
        public static final float PLAYER_HEAD_RADIUS = 0.5f;
        public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
        public static final float PLAYER_LIMB_WIDTH = 0.1f;

        public static final Color PLAYER_COLOR = Color.BLACK;
        public static final float PLAYER_MOVEMENT_SPEED = 10.0f;
    }

    public static class Icicle {
        public static final float ICICLE_HEIGHT = 1.0f;
        public static final float ICICLE_WIDTH = 0.5f;
        public static final Color ICICLE_COLOR = Color.WHITE;
        public static final Vector2 ICICLE_ACCELERATION = new Vector2(0, -5.0f);
    }

    public static class DifficultyWorld {
        public static final float DIFFICULTY_WORLD_SIZE = 480.0f;
        public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;
        public static final float DIFFICULTY_LABEL_SCALE = 1.5f;

        public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
        public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
        public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);

        public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
        public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
        public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);
    }

    public static class Difficulty {
        public static final String EASY_LABEL = "Cold";
        public static final String MEDIUM_LABEL = "Colder";
        public static final String HARD_LABEL = "Coldest";

        private static final float EASY_SPAWNS_PER_SECOND = 5;
        private static final float MEDIUM_SPAWNS_PER_SECOND = 15;
        private static final float HARD_SPAWNS_PER_SECOND = 25;

        public enum DifficultyLevel {
            EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
            MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
            HARD(HARD_SPAWNS_PER_SECOND, HARD_LABEL);

            public float spawnRate;
            public String label;

            DifficultyLevel(float spawnRate, String label) {
                this.spawnRate = spawnRate;
                this.label = label;
            }
        }
    }
}