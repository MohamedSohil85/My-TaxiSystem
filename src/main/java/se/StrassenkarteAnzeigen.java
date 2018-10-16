/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 *
 */
public class StrassenkarteAnzeigen extends JPanel{
    
    private Strassenkarte sKa;
    ArrayList<Integer> xPositions = new ArrayList<>();
    ArrayList<Integer> yPositions = new ArrayList<>();
    
    @Override
    public void paintComponent(Graphics g) {
         
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i<=sKa.getStrassenliste().size()-1;i++){
                  //Draw one point per street and add the name to it
                  g2d.setColor(Color.BLACK);
                  g2d.drawString(sKa.getStrassenliste().get(i).getStrassenName(), xPositions.get(i), yPositions.get(i));                  
        }
        //Draw connections
        for(int i = 0; i<=sKa.getStrassenliste().size()-1;i++){
            for(int j = 0; j<=sKa.getStrassenliste().get(i).getAnknuepfendeStrassen().size()-1;j++){
                String currentConnectingStreet = sKa.getStrassenliste().get(i).getAnknuepfendeStrassen().get(j).target.getStrassenName();
                for(int k = 0; k<=sKa.getStrassenliste().size()-1;k++){
                    if(currentConnectingStreet.compareTo(sKa.getStrassenliste().get(k).getStrassenName()) == 0){
                        g2d.setColor(Color.BLUE);
                        g2d.drawLine(xPositions.get(i), yPositions.get(i), xPositions.get(k), yPositions.get(k));
                    }
                }
            }   
        }
    }
    
    private void SetPositions(){
        /*alternative  
          int w = 1000 - distance - 100;
          int h = 800 - distance - 25;
        
        for (int i = 0; i < sKa.getStrassenliste().size(); i++) {
            if(i==0){
                xPositions.add(distance);
                yPositions.add(distance);
            }else{

                Random r = new Random();
                int x;
                int y;

                do{
                  x = Math.abs(r.nextInt()) % w + 10;
                }while(!checkEnoughDistance(x,xPositions, 25));
                do{
                  y = Math.abs(r.nextInt()) % h + 10;
                }while(!checkEnoughDistance(y,yPositions, 50));
                            xPositions.add(x);
            yPositions.add(y);
              }

        }*/
        //temporäres festes einfügen von 12 Positionen
        //Pos 1
        xPositions.add(350);
        yPositions.add(50);
        //Pos 2
        xPositions.add(550);
        yPositions.add(50);
        //Pos 3
        xPositions.add(750);
        yPositions.add(125);
        //Pos 4
        xPositions.add(850);
        yPositions.add(275);
        //Pos 5
        xPositions.add(850);
        yPositions.add(425);
        //Pos 6
        xPositions.add(750);
        yPositions.add(575);
        //Pos 7
        xPositions.add(550);
        yPositions.add(650);
        //Pos 8
        xPositions.add(350);
        yPositions.add(650);
        //Pos 9
        xPositions.add(150);
        yPositions.add(575);
        //Pos 10
        xPositions.add(50);
        yPositions.add(425);
        //Pos 11
        xPositions.add(50);
        yPositions.add(275);
        //Pos 12
        xPositions.add(150);
        yPositions.add(125);
    }
    public void SetupStrassenkarte(Strassenkarte sKa){
        JFrame frame = new JFrame("Strassenkarte");

            this.sKa = sKa;
            SetPositions();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
//        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        frame.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                // Sicherheitsabfrage bevor Anwendung beendet wird.
//                int antwort = JOptionPane.showConfirmDialog(frame, "Anwendung beenden!", "Sicherheitsfrage",
//                        JOptionPane.YES_NO_OPTION);
//                // Falls Benutzer Anwendung schließen möchte ...
//                if (antwort == JOptionPane.YES_OPTION) {
//                    // .. dann beenden
//                    //backUpAuftragsListeOld();
//
//                    System.exit(0);
//                }
//            }
//        });
        
        
        
    }
    boolean checkEnoughDistance(int origin, ArrayList<Integer> compareArrayList, int minDistance){
        for(int i = 0; i<compareArrayList.size();i++){
            if(origin+minDistance<compareArrayList.get(i) && origin-minDistance>compareArrayList.get(i)){
                return false;
            }
        }
        return true;
    }
}
