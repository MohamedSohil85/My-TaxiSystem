/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * 
 * 
 */
public class AuftragsFormular implements Serializable{

    static private Auftrag auftrag;
    static private Auftragsliste auftragsliste;
    //static private Kunde kunde;
    static private Kundenliste kundenliste;
   // static private Taxi taxi;
    static private Taxiliste taxiliste;
   // static private Taxifahrer taxifahrer;
    static private Taxifahrerliste taxifahrerliste;
    static private int row;
    static private Strassenkarte skarte;
   // static private int col;

    // Anzeige eines Dialogs zur Eingabe der Daten
    public static Auftrag showInputDialog(Window owner, String message, String title,
            Auftragsliste aListe, Kundenliste kListe, Taxiliste tListe, Taxifahrerliste fListe, Strassenkarte sKarte) {

        //Stati aktualisieren
        aListe.statusaktualisieren();
        
        kundenliste = kListe;
        auftragsliste = aListe;
        taxiliste = tListe;
        taxifahrerliste = fListe;
        skarte = sKarte;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        int aListeSize = aListe.getAuftragsliste().size();

        Object[][] cellData = new Object[aListeSize][9];

        if (aListeSize != 0) {
 
            for (int i = 0; i < aListeSize; i++) {
                cellData[i][0] = aListe.getAuftragsliste().get(i).getAuftragsNr();
                Kunde kunde = aListe.getAuftragsliste().get(i).getKunde();
                cellData[i][1] = kunde.getKundenVorname() + " " + kunde.getKundenName();
                cellData[i][2] = aListe.getAuftragsliste().get(i).getStartOrt();
                cellData[i][3] = aListe.getAuftragsliste().get(i).getZielOrt();
                // Datum und Zeit
                Date tmpDate = aListe.getAuftragsliste().get(i).getDatum();
                cellData[i][4] = formatter.format(tmpDate);
                //Taxi
                Taxi taxi = aListe.getAuftragsliste().get(i).getTaxi();
                cellData[i][5] = taxi.getTaxiNr() + ": " + taxi.getKennzeichen();
                //Taxifahrer
                Taxifahrer tFahrer = aListe.getAuftragsliste().get(i).getTaxifahrer();
                cellData[i][6] = tFahrer.getFahrerNr() + ": " + tFahrer.getFahrerVorname() + " " + tFahrer.getFahrerName();

                cellData[i][7] = aListe.getAuftragsliste().get(i).getPreis();
                cellData[i][8] = aListe.getAuftragsliste().get(i).getStatus();
            }
        }
 
        String[] columnNames = {"Nr", "Kunde", "Start Ort", "Ziel Ort", "Zeit", "Taxi", "Taxifahrer", "Preis", "Status"};

        JDialog jDialog1 = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable jT_auftraege = new javax.swing.JTable();
        jT_auftraege.setRowSelectionAllowed(true);
        jT_auftraege.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JButton jB_edit = new javax.swing.JButton();
        JButton jB_delete = new javax.swing.JButton();
        JButton jB_cancel = new javax.swing.JButton();
        JTextField jTF_nummer = new javax.swing.JTextField(5);

        jDialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jT_auftraege.setModel(new javax.swing.table.DefaultTableModel(cellData, columnNames) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPane1.setViewportView(jT_auftraege);
        if (jT_auftraege.getColumnModel().getColumnCount() > 0) {
            jT_auftraege.getColumnModel().getColumn(0).setPreferredWidth(6);
            jT_auftraege.getColumnModel().getColumn(7).setPreferredWidth(7);
        }

        jB_edit.setText("Bearbeiten");
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton1ActionPerformed(evt);
            }
        });

        jB_delete.setText("Loeschen");

        jB_cancel.setText("Abbrechen");

        jT_auftraege.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = jT_auftraege.getSelectedRow();
                //col = jTable1.getSelectedColumn();
                Object tmpNr = jT_auftraege.getValueAt(row, 0);

                jTF_nummer.setText(String.valueOf(tmpNr));
            }
        });
        if (auftragsliste.getAuftragsliste().size() == 0) {
            jB_edit.setEnabled(false);
            jB_delete.setEnabled(false);

        }
        // Edit-Button
        jB_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auftragsliste.getAuftragsliste().size() != 0) {
                    int index = jT_auftraege.getSelectedRow();
                    if (index != -1) {
                        jDialog1.dispose();
                        //if (indx < 0 || indx >= auftragsliste.getAuftragsliste().size()) 
                        AuftragBearbeiten.showInputDialog(owner, message, title, auftragsliste, kundenliste, taxiliste, taxifahrerliste, skarte, index);
                        if (auftrag
                                != null) {
                            System.out.println(auftrag.getStartOrt());
                            System.out.println(auftrag.getZielOrt());
                            System.out.println(auftrag.getDatum());
                            System.out.println(auftrag.getTaxi());
                            System.out.println(auftrag.getTaxifahrer());
                            System.out.println(auftrag.getPreis());
                        }
                    }
                }
            }
        }
        );

        // Delete- Button
        jB_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auftragsliste.getAuftragsliste().size() != 0) {
                    Object[] options = {"Ja", "Nein"};
                    //int index = jT_auftraege.getSelectedRow();
                    int[] indexes = jT_auftraege.getSelectedRows();
                    if (indexes.length != 0) {
                        int antwort = JOptionPane.showOptionDialog(jDialog1, "Wollen Sie Auftrag loeschen? ", "Warnung",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[1]);
                        if (antwort == JOptionPane.YES_OPTION) {

                            int k = indexes.length - 1; // 
                            for (int i = k; i >= 0; i--) {
                                auftragsliste.getAuftragsliste().remove(indexes[i]);
                            }
                            if (k < 0) {
                                JOptionPane.showMessageDialog(null, "Auftrag geloescht");
                            }
                        }

                        jDialog1.dispose();
                        AuftragsFormular.showInputDialog(owner, message, title, auftragsliste, kundenliste, taxiliste, taxifahrerliste, skarte);
                    }
                }
            }
        }
        );

        // Cancel-Button
        jB_cancel.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                jDialog1.dispose();
            }
        }
        );

        jTF_nummer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        
                        //.addComponent(jTF_nummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        //.addGap(18, 18, 18)
                        .addComponent(jB_edit)
                        .addGap(18, 18, 18)
                        .addComponent(jB_delete)
                        .addGap(18, 18, 18)
                        .addComponent(jB_cancel)
                        .addContainerGap(300, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jB_edit)
                                .addComponent(jB_delete)
                                .addComponent(jB_cancel)
                        //.addComponent(jTF_nummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        )
                        .addContainerGap())
        );
        jDialog1.setLocation(280, 200);
        jDialog1.pack();
        jDialog1.setVisible(true);

        return null;
    }

}
