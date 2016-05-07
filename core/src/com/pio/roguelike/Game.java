package com.pio.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pio.roguelike.map.ASCIIMap;
import com.pio.roguelike.playground.NotAnActor;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {
    private class KeyActionMapping {
        int key;
        InputAction action;

        KeyActionMapping(int key, InputAction action) {
            this.key = key;
            this.action = action;
        }
    };

    ASCIIMap map;
    OrthographicCamera camera;
    long prev_time, lag;
    final long UPDATE_TIME_NS = 16666666;
    NotAnActor actor;
    SpriteBatch batch;

    // Możemy powiadomić rożne obiekty, ale tylko jeden w danym czasie
    InputObserver observer;

    KeyActionMapping[] keyActions = {
        new KeyActionMapping(Input.Keys.W, InputAction.MOVE_UP),
        new KeyActionMapping(Input.Keys.S, InputAction.MOVE_DOWN),
        new KeyActionMapping(Input.Keys.A, InputAction.MOVE_LEFT),
        new KeyActionMapping(Input.Keys.D, InputAction.MOVE_RIGHT),

        new KeyActionMapping(Input.Keys.K, InputAction.MOVE_UP),
        new KeyActionMapping(Input.Keys.J, InputAction.MOVE_DOWN),
        new KeyActionMapping(Input.Keys.L, InputAction.MOVE_LEFT),
        new KeyActionMapping(Input.Keys.H, InputAction.MOVE_RIGHT),

        new KeyActionMapping(Input.Keys.DPAD_UP, InputAction.MOVE_UP),
        new KeyActionMapping(Input.Keys.DPAD_DOWN, InputAction.MOVE_DOWN),
        new KeyActionMapping(Input.Keys.DPAD_LEFT, InputAction.MOVE_LEFT),
        new KeyActionMapping(Input.Keys.DPAD_RIGHT, InputAction.MOVE_RIGHT),

        new KeyActionMapping(Input.Keys.F, InputAction.INTERACT),
    };

    @Override
    public void create() {
        try {
            map = new ASCIIMap("test");
        }
        catch (FileNotFoundException e) {
            System.err.println("Cannot load 'test' map");
            System.exit(1);
        }
        camera = new OrthographicCamera(800, 600);

        batch = new SpriteBatch();
        actor = new NotAnActor();
        observer = actor;

        prev_time = System.nanoTime();
        lag = 0;
    }

    @Override
    public void render() {
        long current_time = System.nanoTime();
        long elapsed = current_time - prev_time;
        prev_time = current_time;
        lag += elapsed;

        while (lag >= UPDATE_TIME_NS) {
            update();
            lag -= UPDATE_TIME_NS;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render(camera);
        batch.begin();
        actor.draw(batch);
        batch.end();
    }

    void update() {
        for (KeyActionMapping mapping : keyActions) {
            if (Gdx.input.isKeyPressed(mapping.key)) {
                observer.execute(mapping.action);
            }
        }
    }
}
