package main.java;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public enum Keys {
    LEFT(KeyEvent.VK_A),
    RIGHT(KeyEvent.VK_D),
    UP(KeyEvent.VK_W),
    DOWN(KeyEvent.VK_S),
    BOMB(KeyEvent.VK_SPACE),
    left(KeyEvent.VK_LEFT),
    right(KeyEvent.VK_RIGHT),
    up(KeyEvent.VK_UP),
    down(KeyEvent.VK_DOWN),
    bomb(KeyEvent.VK_NUMPAD0);
    private final static Set<Integer> KeySet = new HashSet<>();
    private int KeyValue;
    Keys(int KeyValue) {
        this.KeyValue = KeyValue;
    }
    public boolean use(){
        return KeySet.contains(KeyValue);
    }
    public static void add(int KeyCode){
        KeySet.add(KeyCode);
    }
    public static void remove(int KeyCode){
        KeySet.remove(KeyCode);
    }
}