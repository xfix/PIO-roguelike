package com.pio.roguelike.itemContainer;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;

public class itemContainer {
    ArrayList itemList;
    int quantity;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;
    BitmapFont font;
    
    itemContainer() {
        itemList = new ArrayList();
        quantity = 0;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        parameter = new FreeTypeFontParameter();
        font = generator.generateFont(parameter);
        generator.dispose();
    }
    
    void addItem(Item item){
        itemList.add(item);
        quantity++;
    }
    
    void removeItem(Item item){
        itemList.remove(item);
        quantity--;
    }
    
    void showItems(){
        for ( int i=0; i<itemList.size(); i++ ){
            /*
            System.out.println( itemList.get(i).name );
            System.out.println( itemList.get(i).price);
            System.out.println( itemList.get(i).kind );
            System.out.println( itemList.get(i).dmg_min );
            System.out.println( itemList.get(i).dmg_max);
            System.out.println( itemList.get(i).speed );
            */
        }
    }
    
    
}