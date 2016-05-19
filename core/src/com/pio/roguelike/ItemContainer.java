package com.pio.roguelike;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pio.roguelike.Item;

public class ItemContainer {
    ArrayList<Item> itemList;
    BitmapFont font;
    
    public ItemContainer() {
        itemList = new ArrayList();
    }
    
    public void itemContainer(BitmapFont font) {
        itemList = new ArrayList();
        this.font = font;
    }
    
    void addItem(Item item){
        itemList.add(item);
    }
    
    void removeItem(Item item){
        itemList.remove(item);
    }
    
    void showItems(){
        float width= 80f;
        float height = 80f;
        
        float start_w = 20f;
        float start_h = 20f;
        Color text_normal = new Color(0xebcb8bff);
        Color green = new Color(0xa3be8cff);

        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(green);
        shape.rect(start_w, start_h, width, height);
        shape.end();
        
        for ( int i=0; i<itemList.size(); i++ ){
            Batch batch = new SpriteBatch();
            batch.begin();
            font.setColor(text_normal);
            font.draw(batch, "Name: " + itemList.get(i).name, start_w + 1f, start_h + 1f);
            font.draw(batch, "Price: " + itemList.get(i).price, start_w + 1f, start_h + 11f);
            font.draw(batch, "ID: " + itemList.get(i).id, start_w + 1f, start_h + 21f);
            batch.end();
        }
    }
}
