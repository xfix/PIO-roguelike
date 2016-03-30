package com.pio.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pio.roguelike.map.ASCIIMap;

public class Game extends ApplicationAdapter {
	ASCIIMap map;
    OrthographicCamera camera;

    @Override
    public void create() {
        map = new ASCIIMap("test");
        camera = new OrthographicCamera(800, 600);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render(camera);
    }
}
