/*
 * i certify that this is my original work
 * Byron De Paz PID 3999061
 */
package Nim;

public interface Player {
    
    int move(int pile);
    
    String playerName();
    
    String getRace();
    
    int objectsTaken();
    
    void addToObjects(int x);
}
