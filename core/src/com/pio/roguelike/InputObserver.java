package com.pio.roguelike;

// Chyba lepiej będzie wybrać inną nazwę, aby nie mylić z tym „właściwym” obserwatorem
public interface InputObserver {
    public void execute(InputAction action);
}
