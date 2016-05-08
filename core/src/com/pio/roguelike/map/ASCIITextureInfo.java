package com.pio.roguelike.map;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ASCIITextureInfo {
    public ASCIITextureInfo(String name) throws FileNotFoundException {
        char_width = 19;
        char_height = 38;
        char_metrics = new HashMap<Character, CharMetric>();
        char znak;
        FileReader file = new FileReader("ascii/fira_mono_medium_24.sfl");
        //CharMetric temp = new CharMetric();
        Scanner input = new Scanner(file);
        try {
            input.nextLine();
            input.nextLine();
            input.nextLine();
            int n = input.nextInt();
            input.nextLine();
            System.out.println(n);
            while (true)
            {
                znak = (char) input.nextInt();
                if (znak == 0) {
                    break;
                }
                CharMetric temp = new CharMetric();
                temp.pos_x = input.nextInt();
                temp.pos_y = input.nextInt();
                temp.width = input.nextInt();
                temp.height = input.nextInt();
                temp.offset_x = input.nextInt();
                temp.offset_y = input.nextInt();
                char_metrics.put((char)znak, temp);
                // System.out.println("znak= "+ (char)znak+" x= " + temp.pos_x + " y= " + temp.pos_y + " szer = " + temp.width + " heg= " + temp.height + " offsetx= " + temp.offset_x + " offsety= " + temp.offset_y + " char_wid= " + input.nextInt() )
                input.nextLine();
            }
        }
        finally {
            input.close();
        }
    }

    public CharMetric get(char c) {
        return char_metrics.get(c);
    }

    public int char_width() {
        return char_width;
    }

    public int char_height() {
        return char_height;
    }

    /// Max character width
    int char_width;
    /// Max character height
    int char_height;

    Map<Character, CharMetric> char_metrics;
}
