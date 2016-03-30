package com.pio.roguelike.map;

import java.util.HashMap;
import java.util.Map;

public class ASCIITextureInfo {
    public ASCIITextureInfo(String name) {
        // TODO: Trzeba napisać parser plików, który zrobi to co tutaj jest na razie pokazane. Najłatwiej będzie chyba
        // zrobić do dla plików .sfl (zobacz w katalogu assets/ascii. Format pliku jest nastepujacy:
        // Nazwa czcionki (zignorować)
        // Wilekość czcionki (zignorować), Wysokość czcionki (char_height)
        // Tekstura (zignorowac)
        // Liczba znaków
        // Tutaj jest lista znaków, aż do lini w której jest tylko 0. Format jest nastepujący:
        // Kod znaku - zamienić na odpowiedni znak, pos_x, pos_y, width, height, offset_x, offset_y, char_width
        // Przy czym szerokość znaku powinna być taka sama dla wszystkich, więc wystarczy tylko raz wczytać, resztę
        // zignorować.
        char_width = 19;
        char_height = 38;

        char_metrics = new HashMap<Character, CharMetric>();

        CharMetric ch_at = new CharMetric();
        ch_at.pos_x = 131;
        ch_at.pos_y = 156;
        ch_at.width = 21;
        ch_at.height = 25;
        ch_at.offset_x = -1;
        ch_at.offset_y = 16;
        CharMetric ch_X = new CharMetric();
        ch_X.pos_x = 1;
        ch_X.pos_y = 156;
        ch_X.width = 21;
        ch_X.height = 22;
        ch_X.offset_x = -1;
        ch_X.offset_y = 16;
        CharMetric ch_dot = new CharMetric();
        ch_dot.pos_x = 201;
        ch_dot.pos_y = 21;
        ch_dot.width = 9;
        ch_dot.height = 7;
        ch_dot.offset_x = 5;
        ch_dot.offset_y = 31;

        char_metrics.put('@', ch_at);
        char_metrics.put('X', ch_X);
        char_metrics.put('.', ch_dot);
    }

    public CharMetric get(char c) {
        return (CharMetric)char_metrics.get(c);
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

    Map char_metrics;
}
