/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pio.roguelike.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pio.roguelike.InputAction;
import com.pio.roguelike.map.ASCIIMap;
import java.util.Observable;

/**
 *
 * @author xfix
 */
public class ActorSprite extends Observable {

    int xPosition = 1;
    int yPosition;
    float horizontalVelocity;
    float verticalVelocity;
    int framesToComplete;
    ASCIIMap map;
    Sprite sprite;

    public ActorSprite(ASCIIMap map) {
        Texture texture = new Texture(Gdx.files.internal("ascii/fira_mono_medium_24.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture, 131, 156, 21, 25);
        sprite.set(sprite);
        this.map = map;
        sprite.translate(map.tile_width() + 400, map.tile_height() + 300);
    }

    public void execute(InputAction action) {
        int width = xPosition;
        int height = yPosition;
        if (framesToComplete != 0) {
            return;
        }
        switch (action) {
            case MOVE_UP:
                height += 1;
                break;
            case MOVE_DOWN:
                height -= 1;
                break;
            case MOVE_RIGHT:
                width += 1;
                break;
            case MOVE_LEFT:
                width -= 1;
                break;
            default:
                return;
        }
        if (map.is_valid(width, height)) {
            framesToComplete = 15;
            horizontalVelocity = ((float) map.tile_width() / (float) framesToComplete) * (float) (width - xPosition);
            verticalVelocity = ((float) map.tile_height() / (float) framesToComplete) * (float) (height - yPosition);
            xPosition = width;
            yPosition = height;
        }
    }

    public void update() {
        if (framesToComplete > 0) {
            sprite.translate(horizontalVelocity, verticalVelocity);
            framesToComplete--;
        }
        if (framesToComplete == 0) {
            horizontalVelocity = 0;
            verticalVelocity = 0;
            setChanged();
            notifyObservers(new Move(xPosition, yPosition));
        }
    }
    
    public void draw(SpriteBatch batch, float progress) {
        sprite.translate(horizontalVelocity * progress, verticalVelocity * progress);
        sprite.draw(batch);
        sprite.translate(-horizontalVelocity * progress, -verticalVelocity * progress);
    }
}
