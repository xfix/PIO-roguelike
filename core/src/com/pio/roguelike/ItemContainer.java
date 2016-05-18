package com.pio.roguelike.ItemContainer;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;

public class ItemContainer {
    ArrayList itemList;
    int quantity;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;
    BitmapFont font;
    
    itemContainer() {
        itemList = new ArrayList();
    }
    
    itemContainer(BitMapFont font) {
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
