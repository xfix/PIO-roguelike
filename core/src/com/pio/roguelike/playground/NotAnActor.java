package com.pio.roguelike.playground;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pio.roguelike.InputAction;
import com.pio.roguelike.InputObserver;

public class NotAnActor extends Sprite implements InputObserver {

    public NotAnActor() {
        Texture texture = new Texture(Gdx.files.internal("ascii/fira_mono_medium_24.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Sprite sprite = new Sprite(texture, 131, 156, 21, 25);
        set(sprite);
    }

    @Override
    public void execute(InputAction action) {
        if (action == InputAction.MOVE_UP) {
            translate(0.0f, 10.0f);
        }
        if (action == InputAction.MOVE_DOWN) {
            translate(0.0f, -10.0f);
        }
        if (action == InputAction.MOVE_RIGHT) {
            translate(10.0f, 0.0f);
        }
        if (action == InputAction.MOVE_LEFT) {
            translate(-10.0f, 0.0f);
        }
    }
}
