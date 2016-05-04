package com.pio.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pio.roguelike.map.ASCIIMap;
import com.pio.roguelike.playground.NotAnActor;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	ASCIIMap map;
    OrthographicCamera camera;
    long prev_time, lag;
    final long UPDATE_TIME_NS = 16666666;
    NotAnActor actor;
    SpriteBatch batch;

    // Możemy powiadomić rożne obiekty, ale tylko jeden w danym czasie
    InputObserver observer;

    @Override
    public void create() {
        map = new ASCIIMap("test");
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
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.K)) {
            observer.execute(InputAction.MOVE_UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.J)) {
            observer.execute(InputAction.MOVE_DOWN);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.L)) {
            observer.execute(InputAction.MOVE_RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.H)) {
            observer.execute(InputAction.MOVE_LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            observer.execute(InputAction.INTERACT);
        }
    }
}
