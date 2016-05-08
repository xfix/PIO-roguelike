package com.pio.roguelike.playground;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pio.roguelike.InputAction;
import com.pio.roguelike.InputListener;
import com.pio.roguelike.map.ASCIIMap;

public class NotAnActor extends Sprite implements InputListener {
    int pos_w, pos_h;
    float vx, vy;
    int frames_to_complete;
    ASCIIMap map;
    public NotAnActor(ASCIIMap map) {
        Texture texture = new Texture(Gdx.files.internal("ascii/fira_mono_medium_24.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Sprite sprite = new Sprite(texture, 131, 156, 21, 25);
        set(sprite);
        this.pos_w = 1;
        this.pos_h = 0;
        this.vx = 0f;
        this.vy = 0f;
        this.map = map;
        translate(400f + (float)map.tile_width(), 300f + (float)map.tile_height());
    }

    @Override
    public void execute(InputAction action) {
        int w = pos_w, h = pos_h;
        if (frames_to_complete == 0) {
            if (action == InputAction.MOVE_UP) {
                h += 1;
            }
            else if (action == InputAction.MOVE_DOWN) {
                h -= 1;
            }
            else if (action == InputAction.MOVE_RIGHT) {
                w += 1;
            }
            else if (action == InputAction.MOVE_LEFT) {
                w -= 1;
            }
            else {
                return;
            }
            if (map.is_valid(w, h)) {
                frames_to_complete = 15;
                vx = ((float) map.tile_width() / (float) frames_to_complete) * (float) (w - pos_w);
                vy = ((float) map.tile_height() / (float) frames_to_complete) * (float) (h - pos_h);
                pos_w = w;
                pos_h = h;
            }
        }
    }

    public void update() {
        if (frames_to_complete > 0) {
            translate(vx, vy);
            frames_to_complete--;
        }
        if (frames_to_complete == 0) {
            vx = 0f;
            vy = 0f;
        }
    }

    public float velocity_x() { return vx; }
    public float velocity_y() { return vy; }
}
