package com.pio.roguelike.map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.Collection;
import java.util.Collections;

public class ASCIIMap {
    Mesh mesh;
    Texture texture;
    ShaderProgram shader;

    public ASCIIMap(String name, ASCIITextureInfo tex_info) {
        shader = new ShaderProgram(Gdx.files.internal("shaders/map.v.glsl"), Gdx.files.internal("shaders/map.f.glsl"));
        texture = new Texture(Gdx.files.internal("ascii/fira_mono_medium_24.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        int img_w = texture.getWidth();
        int img_h = texture.getHeight();
        int width = tex_info.char_width();
        int height = tex_info.char_height();

        String map = Gdx.files.internal("maps/" + name).readString();
        String s = "";
        int map_i, map_w = 0, map_h = 0;
        for (map_i = 0; map_i < map.length(); ++map_i) {
            if (map.charAt(map_i) == ' ') {
                map_w = Integer.parseInt(s);
            } else if (map.charAt(map_i) == '\n') {
                map_h = Integer.parseInt(s);
                break;
            } else {
                s += map.charAt(map_i);
            }
        }

        // Odwracamy mapę aby ją oprawnie narysować
        String lines[] = map.split("\n");
        map = "";
        for (int i = lines.length - 1; i > 0; i--) {
            map += lines[i] + "\n";
        }

        // [map_h * map_w * 5 * 6] - wymiary mapy * wielkość wierzchołak * ilość wierzchołków
        float[] vert = new  float[map_h * map_w * 5 * 6];
        int i = 0, w_pos = 0, h_pos = 0;

        for (map_i = 0; map_i < map.length(); ++map_i) {
            char c = map.charAt(map_i);
            // Jak ktoś zamieni Unixowe zakończenie plików na Windowsowe…
            if (c == '\n') {
                w_pos = 0;
                h_pos++;
                continue;
            }
            CharMetric met = tex_info.get(c);
            float x = w_pos * width + met.offset_x;
            float y = h_pos * height + met.height + met.offset_y;

            // Pierwszy trójkąt (wierchołki w polejności - pozycja(x, y, z), tekstura(u, v))
            vert[i++] = x;
            vert[i++] = y;
            vert[i++] = 0;
            vert[i++] = met.pos_x / img_w;
            vert[i++] = (met.pos_y + met.height) / img_h;

            vert[i++] = x;
            vert[i++] = y + met.height;
            vert[i++] = 0;
            vert[i++] = met.pos_x / img_w;
            vert[i++] = met.pos_y / img_h;

            vert[i++] = x + met.width;
            vert[i++] = y;
            vert[i++] = 0;
            vert[i++] = (met.pos_x + met.width) / img_w;
            vert[i++] = (met.pos_y + met.height) / img_h;

            // Drugi trójkąt
            vert[i++] = x;
            vert[i++] = y + met.height;
            vert[i++] = 0;
            vert[i++] = met.pos_x / img_w;
            vert[i++] = met.pos_y / img_h;

            vert[i++] = x + met.width;
            vert[i++] = y;
            vert[i++] = 0;
            vert[i++] = (met.pos_x + met.width) / img_w;
            vert[i++] = (met.pos_y + met.height) / img_h;

            vert[i++] = x + met.width;
            vert[i++] = y + met.height;
            vert[i++] = 0;
            vert[i++] = (met.pos_x + met.width) / img_w;
            vert[i++] = met.pos_y / img_h;

            w_pos++;
        }

        mesh = new Mesh(true, vert.length, 0,
                        new VertexAttribute(VertexAttributes.Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE),
                        new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + "0"));
        mesh.setVertices(vert);
    }

    public void render(Camera camera) {
        texture.bind();
        shader.begin();
        shader.setUniformi("u_texture", 0);
        shader.setUniformMatrix("u_projectionViewMatrix", camera.combined);
        mesh.render(shader, GL20.GL_TRIANGLES);
        shader.end();
    }
}
