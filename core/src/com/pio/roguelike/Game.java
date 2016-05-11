package com.pio.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pio.roguelike.map.ASCIIMap;
import com.pio.roguelike.map.ASCIITextureInfo;
import com.pio.roguelike.actor.Actor;
import com.pio.roguelike.actor.ActorSprite;
import com.pio.roguelike.actor.Death;
import java.util.Observable;
import javax.swing.JOptionPane;

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
    ASCIITextureInfo texture_info;
    OrthographicCamera camera;
    long prev_time, lag;
    final long UPDATE_TIME_NS = 16666666;
    ActorSprite sprite;
    SpriteBatch batch;

    // Możemy powiadomić rożne obiekty, ale tylko jeden w danym czasie
    InputListener listener;

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
        texture_info = new ASCIITextureInfo("ascii/fira_mono_medium_24.sfl");
        map = new ASCIIMap("test", texture_info);
        camera = new OrthographicCamera(800, 600);

        batch = new SpriteBatch();
        Actor actor = new Actor(map, "Player");
        actor.addObserver((object, event) -> {
            if (event instanceof Death) {
                final String deathMessage = "Do you want your possessions identified?";
                final String deathTitle = "You die!";
                JOptionPane.showMessageDialog(null, deathMessage, deathTitle, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        sprite = actor.getSprite();
        listener = actor;

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

        float progress = (float)lag / (float)UPDATE_TIME_NS;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render(camera);
        batch.begin();

        sprite.update();
        sprite.draw(batch, progress);

        batch.end();
    }

    void update() {
        for (KeyActionMapping mapping : keyActions) {
            if (Gdx.input.isKeyPressed(mapping.key)) {
                listener.execute(mapping.action);
            }
        }

        sprite.update();
    }
}
