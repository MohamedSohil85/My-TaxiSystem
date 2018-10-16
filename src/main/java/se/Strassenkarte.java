/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.*;
import org.w3c.dom.NodeList;

/**
 *
 * 
 */
public class Strassenkarte {
    
    private final ArrayList<Strasse> strassenliste;
    
    public Strassenkarte(){
        strassenliste = new ArrayList<>();
    }
    
    private final String basisDateiPfad = "Strassenkarte.xml";

    
    void Laden(String dateiPfad) throws FileNotFoundException, IOException{
        strassenliste.clear();
         try {	
             File inputFile = null;
             if(dateiPfad.compareTo("")==0){
                inputFile = new File(basisDateiPfad);
             }else{
                inputFile = new File(dateiPfad);
             }
            DocumentBuilderFactory dbFactory 
               = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
         
         
         System.out.println("Root element :" 
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("Strasse");
         System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               //Erst alle Straßen eintragen
               System.out.println("Strassenname: " 
                  + eElement.getTextContent());
               strassenliste.add(new Strasse(eElement.getTextContent()));
            }
         }
         nList = doc.getElementsByTagName("Strassenverbindung");
         System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               String Ausgang = eElement.getElementsByTagName("Ausgang").item(0).getTextContent();
               System.out.println("Start: " 
                  + eElement.getElementsByTagName("Ausgang").item(0).getTextContent());
               String Ziel = eElement.getElementsByTagName("Ziel").item(0).getTextContent();
               System.out.println("Ziel: " 
                  + eElement.getElementsByTagName("Ziel").item(0).getTextContent());
               System.out.println("Gewicht: " 
                  + eElement.getElementsByTagName("Gewicht").item(0).getTextContent());
               //Strassenverbindungen erstellen
               Strassenverbindung sVerbindungZiel = null;
               Strassenverbindung sVerbindungStart = null;
               for (int i = 0; i < strassenliste.size(); i++) {
                   if(strassenliste.get(i).getStrassenName().compareTo(Ausgang)==0){
                          sVerbindungZiel = new Strassenverbindung(strassenliste.get(i), Integer.parseInt(eElement.getElementsByTagName("Gewicht").item(0).getTextContent())); 
                   }
                   if(strassenliste.get(i).getStrassenName().compareTo(Ziel)==0){
                          sVerbindungStart = new Strassenverbindung(strassenliste.get(i), Integer.parseInt(eElement.getElementsByTagName("Gewicht").item(0).getTextContent()));          
                   }
               }
               //Find Ausgangsstrasse und Zielstrasse in strassenliste
                for (int i = 0; i < strassenliste.size(); i++) {
                    if(strassenliste.get(i).getStrassenName().compareTo(Ausgang)==0){
                        strassenliste.get(i).getAnknuepfendeStrassen().add(sVerbindungStart);
                    }
                    if(strassenliste.get(i).getStrassenName().compareTo(Ziel)==0){
                        strassenliste.get(i).getAnknuepfendeStrassen().add(sVerbindungZiel);
                    }
                }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
    }
     
     public double DistanzBerechnen(Strasse anfang, Strasse ziel){
        computePaths(anfang);
        List<Strasse> distanzListe = getShortestPathTo(ziel);
        return distanzListe.get(distanzListe.size()-1).minDistance;
    }
    private static void computePaths(Strasse source)
    {
        source.minDistance = 0.;
        PriorityQueue<Strasse> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
           Strasse u = vertexQueue.poll();
               // Visit each edge exiting u
               for (int i = 0; i < u.getAnknuepfendeStrassen().size(); i++) {
                   Strassenverbindung e = u.getAnknuepfendeStrassen().get(i);
                   Strasse v = e.target;
                   double weight = e.weight;
                   double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
               }
           }
    }

    private static List<Strasse> getShortestPathTo(Strasse target)
    {
        List<Strasse> path = new ArrayList<>();
        for (Strasse vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    /**
     * @return the strassenliste
     */
    public ArrayList<Strasse> getStrassenliste() {
        return strassenliste;
    }

}
