/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.util.ArrayList;

/**
 *
 * @
 */
public class Strasse implements Comparable<Strasse>
{
    private String strassenName;
    private ArrayList<Strassenverbindung> anknuepfendeStrassen;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Strasse previous;
    
    
    public Strasse(String strassenName) { 
        this.strassenName = strassenName; 
        this.anknuepfendeStrassen = new ArrayList<>();
    }
    
    @Override
    public String toString() { return getStrassenName(); }
    
    @Override
    public int compareTo(Strasse other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

    /**
     * @return the strassenName
     */
    public String getStrassenName() {
        return strassenName;
    }

    /**
     * @param strassenName the strassenName to set
     */
    public void setStrassenName(String strassenName) {
        this.strassenName = strassenName;
    }

    /**
     * @return the anknuepfendeStrassen
     */
    public ArrayList<Strassenverbindung> getAnknuepfendeStrassen() {
        return anknuepfendeStrassen;
    }

    /**
     * @param anknuepfendeStrassen the anknuepfendeStrassen to set
     */
    public void setAnknuepfendeStrassen(ArrayList<Strassenverbindung> anknuepfendeStrassen) {
        this.anknuepfendeStrassen = anknuepfendeStrassen;
    }

}
