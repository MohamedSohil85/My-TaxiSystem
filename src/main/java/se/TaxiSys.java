/*
 * Projekt: TaxiSys
 * Datei  : TaxiSys.java (Hauptprogramm)
 * Gruppe : <hier Namen der Mitglieder eintragen>
 * Letzte Änderung: 26.08.2016
 *
 * Änderungen
 * 23.08.16 : Initialisierung der Komponenten
 * 26.08.16 : Einbindung des Kunden-Dialogs
 *
 */
package se;

import java.awt.Container;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TaxiSys extends javax.swing.JFrame {

    JFrame frame; // Aktuelles Fenster
    Container cp; // Container fuer Fenster, enthaelt Bereiche inkl. GUI-Elemente

    Kundenliste kListe;    // Kundenliste verwalten
    Auftragsliste aListe;
    Taxifahrerliste tfListe;
    Taxiliste tListe;
    Strassenkarte sKarte;
    StrassenkarteAnzeigen sKa;

    // Initialisierung des Java-Swing-Anwendung
    public TaxiSys() {
        initComponents();       // GUI aufbauen
        initTaxiSys();          // Anwendung initialisieren
    }

    // Initialisierung Anwendung
    private void initTaxiSys() {
        frame = this;  // Pointer auf aktuelles Frameobjekt 
        cp = getContentPane();  // Container-Referenz speichern

        jT_AktuellsteAufträge.setFont(new Font("Serif", Font.ITALIC, 12));
        jTF_Status.setText("Status: initTaxiSys");
        
        sKa = new StrassenkarteAnzeigen();
        
        //Straßenkarte
        sKarte = new Strassenkarte();
        try {
            sKarte.Laden("");
        } catch (IOException ex) {
            Logger.getLogger(TaxiSys.class.getName()).log(Level.SEVERE, null, ex);
        }

        // (Leere) Kundenliste erstellen
        if (restoreKundenliste() != null) {
            kListe = restoreKundenliste();
            System.out.println("restoreKundenliste()");
        } else {
            kListe = new Kundenliste();
        }

        if (restoreFahrerliste() != null) {
            tfListe = restoreFahrerliste();
            System.out.println("restoreFahrerliste()");
            
        } else {
            tfListe = new Taxifahrerliste();
        }

        if (restoreTaxiliste() != null) {
            tListe = restoreTaxiliste();
            System.out.println("restoreTaxiliste()");
        } else {
            tListe = new Taxiliste();
        }

   
        aListe = new Auftragsliste(sKarte, kListe, tListe, tfListe);
        //System.out.println("restoreAuftragsliste()");


        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Sicherheitsabfrage bevor Anwendung beendet wird.
                int antwort = JOptionPane.showConfirmDialog(frame, "Anwendung beenden!", "Sicherheitsfrage",
                        JOptionPane.YES_NO_OPTION);
                // Falls Benutzer Anwendung schließen möchte ...
                if (antwort == JOptionPane.YES_OPTION) {
                    // .. dann beenden
                    //backUpAuftragsListeOld();

                    backupKundenliste();
                    backupFahrerliste();
                    backupTaxiliste();
                    //backupAuftragsliste();
                    System.exit(0);
                }
            }
        });

    }

    // Anwendung beenden
    private void exitTaxiSys() {
        // Sicherheitsabfrage bevor Anwendung beendet wird.
        int antwort = JOptionPane.showConfirmDialog(frame, "Anwendung beenden!", "Sicherheitsfrage",
                JOptionPane.YES_NO_OPTION);
        // Falls Benutzer Anwendung schließen möchte ...
        if (antwort == JOptionPane.YES_OPTION) {
            // .. dann beenden
            //backUpAuftragsListeOld();

            backupKundenliste();
            backupFahrerliste();
            backupTaxiliste();
            backupAuftragsliste();
            System.exit(0);
        }
    }

    private void backupKundenliste() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("backupKundenliste.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(kListe);
            out.close();
            fileOutput.close();

        } catch (IOException e) {
        }
    }

    private void backupTaxiliste() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("backupTaxiliste.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(tListe);
            out.close();
            fileOutput.close();

        } catch (IOException e) {
        }
    }

    private void backupFahrerliste() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("backupFahrerliste.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(tfListe);
            out.close();
            fileOutput.close();

        } catch (IOException e) {
        }
    }

    private void backupAuftragsliste() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("backupAuftragsliste.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(aListe);
            out.close();
            fileOutput.close();

        } catch (IOException e) {
        }
    }

    private Kundenliste restoreKundenliste() {
        Kundenliste tmpKundenliste = null;
        try {
            FileInputStream fileIn = new FileInputStream("backupKundenliste.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            tmpKundenliste = (Kundenliste) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }



        return tmpKundenliste;

    }

    private Taxiliste restoreTaxiliste() {
        Taxiliste tmpTaxiliste = null;
        try {
            FileInputStream fileIn = new FileInputStream("backupTaxiliste.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            tmpTaxiliste = (Taxiliste) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }


        return tmpTaxiliste;

    }

    private Taxifahrerliste restoreFahrerliste() {
        Taxifahrerliste tmpTaxifahrerliste = null;
        try {
            FileInputStream fileIn = new FileInputStream("backupFahrerliste.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            tmpTaxifahrerliste = (Taxifahrerliste) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }



        return tmpTaxifahrerliste;

    }

    private Auftragsliste restoreAuftragsliste() {
        Auftragsliste tmpAuftragsliste = null;
        try {
            FileInputStream fileIn = new FileInputStream("backupAuftragsliste.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            tmpAuftragsliste = (Auftragsliste) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }



        return tmpAuftragsliste;

    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jP_TaxiSys = new javax.swing.JPanel();
        jTF_Status = new javax.swing.JTextField();
        jL_Gruppe = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jT_AktuellsteAufträge = new javax.swing.JTable();
        jMBar_Taxi = new javax.swing.JMenuBar();
        jMenu_Datei = new javax.swing.JMenu();
        jMenuItemBeenden = new javax.swing.JMenuItem();
        jMenu_Auftrag = new javax.swing.JMenu();
        jMenuItem_NeuerAuftrag = new javax.swing.JMenuItem();
        jMenuItem_Auftragsliste = new javax.swing.JMenuItem();
        jMenu_Kunde = new javax.swing.JMenu();
        jMenuItem_NeuerKunde = new javax.swing.JMenuItem();
        jMenuItem_Kundenliste = new javax.swing.JMenuItem();
        jMenu_Taxiverwaltung = new javax.swing.JMenu();
        jMenuItem_NeuesTaxi = new javax.swing.JMenuItem();
        jMenuItem_Taxiliste = new javax.swing.JMenuItem();
        jMenuItem_NeuerTaxifahrer = new javax.swing.JMenuItem();
        jMenuItem_Taxifahrerliste = new javax.swing.JMenuItem();
        jMenu_Strassenkarte = new javax.swing.JMenu();
        jMenuItem_Strassenkarte_anzeigen = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi System");

        jP_TaxiSys.setBorder(javax.swing.BorderFactory.createTitledBorder("Aktuelle Auftragsliste"));

        jTF_Status.setText("..");

        jL_Gruppe.setText("3BDi5_4");

        jButton1.setText("Aktualisieren");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aktualisierenActionPerformed(evt);
            }
        });

        jT_AktuellsteAufträge.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nr", "Start Ort", "Ziel Ort", "Zeit", "Taxi", "Taxifahrer", "Preis", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jT_AktuellsteAufträge);

        javax.swing.GroupLayout jP_TaxiSysLayout = new javax.swing.GroupLayout(jP_TaxiSys);
        jP_TaxiSys.setLayout(jP_TaxiSysLayout);
        jP_TaxiSysLayout.setHorizontalGroup(
            jP_TaxiSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_TaxiSysLayout.createSequentialGroup()
                .addGroup(jP_TaxiSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTF_Status)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_TaxiSysLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jP_TaxiSysLayout.createSequentialGroup()
                        .addComponent(jL_Gruppe)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap())
        );
        jP_TaxiSysLayout.setVerticalGroup(
            jP_TaxiSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_TaxiSysLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jTF_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL_Gruppe))
        );

        jMenu_Datei.setText("Datei");

        jMenuItemBeenden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBeenden.setText("Beenden");
        jMenuItemBeenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBeendenActionPerformed(evt);
            }
        });
        jMenu_Datei.add(jMenuItemBeenden);

        jMBar_Taxi.add(jMenu_Datei);

        jMenu_Auftrag.setText("Auftrag");

        jMenuItem_NeuerAuftrag.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_NeuerAuftrag.setText("Neuer Auftrag");
        jMenuItem_NeuerAuftrag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NeuerAuftragActionPerformed(evt);
            }
        });
        jMenu_Auftrag.add(jMenuItem_NeuerAuftrag);

        jMenuItem_Auftragsliste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Auftragsliste.setText("Auftragsliste bearbeiten");
        jMenuItem_Auftragsliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_AuftragslisteActionPerformed(evt);
            }
        });
        jMenu_Auftrag.add(jMenuItem_Auftragsliste);

        jMBar_Taxi.add(jMenu_Auftrag);

        jMenu_Kunde.setText("Kunde");

        jMenuItem_NeuerKunde.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_NeuerKunde.setText("Neuer Kunde");
        jMenuItem_NeuerKunde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NeuerKundeActionPerformed(evt);
            }
        });
        jMenu_Kunde.add(jMenuItem_NeuerKunde);

        jMenuItem_Kundenliste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Kundenliste.setText("Kundenliste bearbeiten");
        jMenuItem_Kundenliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_KundenlisteActionPerformed(evt);
            }
        });
        jMenu_Kunde.add(jMenuItem_Kundenliste);

        jMBar_Taxi.add(jMenu_Kunde);

        jMenu_Taxiverwaltung.setText("Verwaltung");

        jMenuItem_NeuesTaxi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_NeuesTaxi.setText("Neues Taxi");
        jMenuItem_NeuesTaxi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NeuesTaxiActionPerformed(evt);
            }
        });
        jMenu_Taxiverwaltung.add(jMenuItem_NeuesTaxi);

        jMenuItem_Taxiliste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Taxiliste.setText("Taxiliste bearbeiten");
        jMenuItem_Taxiliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_TaxilisteActionPerformed(evt);
            }
        });
        jMenu_Taxiverwaltung.add(jMenuItem_Taxiliste);

        jMenuItem_NeuerTaxifahrer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_NeuerTaxifahrer.setText("Neuer Taxifahrer");
        jMenuItem_NeuerTaxifahrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NeuerTaxifahrerActionPerformed(evt);
            }
        });
        jMenu_Taxiverwaltung.add(jMenuItem_NeuerTaxifahrer);

        jMenuItem_Taxifahrerliste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Taxifahrerliste.setText("Taxifahrerliste bearbeiten");
        jMenuItem_Taxifahrerliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_TaxifahrerlisteActionPerformed(evt);
            }
        });
        jMenu_Taxiverwaltung.add(jMenuItem_Taxifahrerliste);

        jMBar_Taxi.add(jMenu_Taxiverwaltung);

        jMenu_Strassenkarte.setText("Strassenkarte");

        jMenuItem_Strassenkarte_anzeigen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Strassenkarte_anzeigen.setText("Karte anzeigen");
        jMenuItem_Strassenkarte_anzeigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_Strassenkarte_anzeigenActionPerformed(evt);
            }
        });
        jMenu_Strassenkarte.add(jMenuItem_Strassenkarte_anzeigen);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Karte laden");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu_Strassenkarte.add(jMenuItem1);

        jMBar_Taxi.add(jMenu_Strassenkarte);

        setJMenuBar(jMBar_Taxi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP_TaxiSys, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jP_TaxiSys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        setSize(new java.awt.Dimension(790, 304));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemBeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBeendenActionPerformed
        exitTaxiSys();
    }//GEN-LAST:event_jMenuItemBeendenActionPerformed

    private void jMenuItem_KundenlisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_KundenlisteActionPerformed

        Kunde kunde = KundenFormular.showInputDialog(null, "Kundenliste", "Kunde Bearbeiten", kListe);
    }//GEN-LAST:event_jMenuItem_KundenlisteActionPerformed

    private void jMenuItem_NeuerAuftragActionPerformed(java.awt.event.ActionEvent evt) {
        // Dialogoptionen
        //Object[] options = {"Neuen Auftrag anlegen",
        //  "Dialog beenden"};

        // Auswahldialog anzeigen
        //int antwort = JOptionPane.showOptionDialog(frame, "Neuer Auftrag", "Auswahldialog",
        //      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
        //    null, options, options[1]);
        // Soll neuer Kundenauftrag angelegt werden?
        //Taxifahrerliste fListe;
        //Taxiliste tListe;
        //if (antwort == JOptionPane.YES_OPTION) {
        Auftrag a = NeuerAuftragDialog.showInputDialog(null, "Neuen Auftrag anlegen", "Auftragsverwaltung",
                aListe, kListe, tListe, tfListe, sKarte);

        if (a != null) {
            System.out.println(a.getStartOrt());
            System.out.println(a.getZielOrt());
            System.out.println(a.getDatum());
            System.out.println(a.getTaxi());
            System.out.println(a.getTaxifahrer());
        }

        //}
    }

    private void jMenuItem_Strassenkarte_anzeigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_Strassenkarte_anzeigenActionPerformed

        sKa.SetupStrassenkarte(sKarte);
        //JOptionPane.showMessageDialog(frame, "Strassenkarte : noch nicht implementiert!");
    }//GEN-LAST:event_jMenuItem_Strassenkarte_anzeigenActionPerformed

    private void jMenuItem_NeuerKundeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NeuerKundeActionPerformed
        // Dialogoptionen
        Kunde k = NeuerKundeDialog.showInputDialog(null, "Neuen Kunden anlegen", "Kundenverwaltung",
                kListe);


    }//GEN-LAST:event_jMenuItem_NeuerKundeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
int antwort = JOptionPane.showConfirmDialog(frame, "Das Laden einer neuen Karte wird die Ortsdaten bestehender Aufträge invalidieren.\n Fortfahren?", "Hinweis!",
                JOptionPane.YES_NO_OPTION);
        // Falls Benutzer Anwendung schließen möchte ...
        if (antwort == JOptionPane.YES_OPTION) {

       
        
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Kartendateien (*.xml, *.csv)",
                "xml", "csv");
        fc.setFileFilter(filter);

        switch (fc.showOpenDialog(null)) {
            case JFileChooser.APPROVE_OPTION:
                File file = fc.getSelectedFile();
                System.out.println("Datei " + file.getName() + " ausgewählt.");
        {
            try {
                sKarte.Laden(file.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(TaxiSys.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            default:
                System.out.println("Auswahl abgebrochen");
        }
 }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem_AuftragslisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_AuftragslisteActionPerformed
        // JOptionPane.showMessageDialog(frame, "Auftragsliste: noch nicht implementiert!");

        Auftrag auftrag = AuftragsFormular.showInputDialog(null, "Auftragsliste", "Auftrag Bearbeiten",
                aListe, kListe, tListe, tfListe, sKarte);
//    
        if (auftrag != null) {
            System.out.println(auftrag.getStartOrt());
            System.out.println(auftrag.getZielOrt());
            System.out.println(auftrag.getDatum());
            System.out.println(auftrag.getTaxi());
            System.out.println(auftrag.getTaxifahrer());
        }


    }//GEN-LAST:event_jMenuItem_AuftragslisteActionPerformed

    private void jMenuItem_NeuerTaxifahrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NeuerTaxifahrerActionPerformed

        Taxifahrer taxifahrer = NeuerTaxifahrerDialog.showInputDialog(null, "Neuen Taxifahrer anlegen", "Fahrerverwaltung", tfListe);

        if (taxifahrer != null) {
            System.out.println(taxifahrer.getFahrerName());
        }
    }//GEN-LAST:event_jMenuItem_NeuerTaxifahrerActionPerformed

    private void jMenuItem_TaxifahrerlisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_TaxifahrerlisteActionPerformed
        // Taxifahrerliste anzeigen
        Taxifahrer taxifahrer = TaxifahrerFormular.showInputDialog(null, "Taxifahrerliste", "Taxifahrerliste", tfListe);
        if (taxifahrer != null) {
            System.out.println(taxifahrer.getFahrerName());
        }
    }//GEN-LAST:event_jMenuItem_TaxifahrerlisteActionPerformed

    private void jMenuItem_TaxilisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_TaxilisteActionPerformed

        Taxi taxi = TaxiFormular.showInputDialog(null, "Taxiliste", "Taxiliste", tListe);
        if (taxi != null) {
            System.out.println(taxi.getTaxiNr());
        }
    }//GEN-LAST:event_jMenuItem_TaxilisteActionPerformed

    private void jMenuItem_NeuesTaxiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NeuesTaxiActionPerformed
        // TODO add your handling code here:

        // Konstruktor von Taxi (String tNr, String knz, String fTyp)
        Taxi taxi = NeuesTaxiDialog.showInputDialog(null, " Taxiliste", "Taxiliste", tListe);

        if (taxi != null) {
            System.out.println(taxi.getTaxiNr());
        }

    }//GEN-LAST:event_jMenuItem_NeuesTaxiActionPerformed

    private void aktualisierenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aktualisierenActionPerformed
        //Stati aktualisieren
        aListe.statusaktualisieren();
        
        
        /*for (int i = 0; i < aktuellsteAufträge.size(); i++) {
{
    if(i == 3){
                    if(aktuellsteAufträge.get(i).getDatum().before(aktuellsteAufträge.get(2).getDatum()))
                    {
                        aktuellsteAufträge.set(2,aktuellsteAufträge.get(i));
                        AuftragsarraySortieren(aktuellsteAufträge);
                    }
                }
            }*/
// TODO add your handling code here:
ArrayList<Auftrag> aktuellsteAufträge = new ArrayList();
ArrayList<Auftrag> tmp_aktuelleAufträge = aListe.getAuftragsliste();

//Befüllen der ArrayList aktuellsteAufträge mit Aufträgen die nicht abgeschlossen sind
for(int j = 0; j < tmp_aktuelleAufträge.size(); j++)
{
    if(tmp_aktuelleAufträge.get(j).getStatus() == "Abgeschlossen")
    {
        //j = j +1;//Auftrag wird nicht kopiert""
    } else{
        aktuellsteAufträge.add(tmp_aktuelleAufträge.get(j));
    }
}

if(aktuellsteAufträge.size() == 0){
     JOptionPane.showMessageDialog(null, "Es müssen noch mindestens drei Aufträge erstellt werden, die nicht abgeschlossen sind");
}
else if(aktuellsteAufträge.size() == 1){
     JOptionPane.showMessageDialog(null, "Es müssen noch mindestens zwei Aufträge erstellt werden, die nicht abgeschlossen sind");
}
else if(aktuellsteAufträge.size() == 2){
    JOptionPane.showMessageDialog(null, "Es muss noch mindestens ein Auftrag erstellt werden, der nicht abgeschlossen ist");
}
if(aktuellsteAufträge.size() == 3 || aktuellsteAufträge.size() > 3)
{
            AuftragsarraySortieren(aktuellsteAufträge);
            //Aufträge anzeigen
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

            String[] columnNames = {"Nr", "Start Ort", "Ziel Ort", "Zeit", "Taxi", "Taxifahrer", "Preis", "Status"};
            Object[][] cellData = new Object[aktuellsteAufträge.size()][8];
            if (!aktuellsteAufträge.isEmpty()) {
                for (int k = 0; k < aktuellsteAufträge.size(); k++) {
                    cellData[k][0] = aktuellsteAufträge.get(k).getAuftragsNr();
                    cellData[k][1] = aktuellsteAufträge.get(k).getStartOrt();
                    cellData[k][2] = aktuellsteAufträge.get(k).getZielOrt();
                    Date tmpDate = aktuellsteAufträge.get(k).getDatum();
                    cellData[k][3] = formatter.format(tmpDate);
                    cellData[k][4] = aktuellsteAufträge.get(k).getTaxi().getKennzeichen();
                    cellData[k][5] = aktuellsteAufträge.get(k).getTaxifahrer().getFahrerName();
                    cellData[k][6] = aktuellsteAufträge.get(k).getPreis();
                    cellData[k][7] = aktuellsteAufträge.get(k).getStatus();
                }

                jT_AktuellsteAufträge.setModel(new javax.swing.table.DefaultTableModel(cellData, columnNames) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, false, false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });
            }
}
    }//GEN-LAST:event_aktualisierenActionPerformed
    public ArrayList<Auftrag> AuftragsarraySortieren(ArrayList<Auftrag> aa) {
        Auftrag tempAuftrag;
        for (int i = 1; i < aa.size(); i++) {
            for (int j = 0; j < aa.size() - i; j++) {
                if (aa.get(j) != null && aa.get(j + 1) != null) {
                    if (aa.get(j).getDatum().after(aa.get(j + 1).getDatum())) {
                        tempAuftrag = aa.get(j);
                        aa.set(j, aa.get(j + 1));
                        aa.set(j + 1, tempAuftrag);
                    }
                }
            }
        }

        return aa;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaxiSys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaxiSys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaxiSys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaxiSys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaxiSys().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jL_Gruppe;
    private javax.swing.JMenuBar jMBar_Taxi;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemBeenden;
    private javax.swing.JMenuItem jMenuItem_Auftragsliste;
    private javax.swing.JMenuItem jMenuItem_Kundenliste;
    private javax.swing.JMenuItem jMenuItem_NeuerAuftrag;
    private javax.swing.JMenuItem jMenuItem_NeuerKunde;
    private javax.swing.JMenuItem jMenuItem_NeuerTaxifahrer;
    private javax.swing.JMenuItem jMenuItem_NeuesTaxi;
    private javax.swing.JMenuItem jMenuItem_Strassenkarte_anzeigen;
    private javax.swing.JMenuItem jMenuItem_Taxifahrerliste;
    private javax.swing.JMenuItem jMenuItem_Taxiliste;
    private javax.swing.JMenu jMenu_Auftrag;
    private javax.swing.JMenu jMenu_Datei;
    private javax.swing.JMenu jMenu_Kunde;
    private javax.swing.JMenu jMenu_Strassenkarte;
    private javax.swing.JMenu jMenu_Taxiverwaltung;
    private javax.swing.JPanel jP_TaxiSys;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTF_Status;
    private javax.swing.JTable jT_AktuellsteAufträge;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
