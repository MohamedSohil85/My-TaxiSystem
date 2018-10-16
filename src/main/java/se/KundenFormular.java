/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog;
//import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
//import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * 
 */
public class KundenFormular implements Serializable{

    static private Kunde kunde;
    static private Kundenliste kundenliste;
    static private int row;

    // Anzeige eines Dialogs zur Eingabe der Fahrerdaten
    public static Kunde showInputDialog(Window owner, String message, String title, Kundenliste kListe) {

        kundenliste = kListe;

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        int kListeSize = kListe.getKundeliste().size();

        JDialog jD_KundeDialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable jT_Kunde = new javax.swing.JTable();
        JButton jB_edit = new javax.swing.JButton();
        JButton jB_delete = new javax.swing.JButton();
        JButton jB_cancel = new javax.swing.JButton();
        JTextField jTextField1 = new javax.swing.JTextField(5);

        Object[][] cellData = new Object[kListeSize][6];
        if (kListeSize != 0) {
            for (int i = 0; i < kListeSize; i++) {
                cellData[i][0] = kListe.getKundeliste().get(i).getKundenNr();
                cellData[i][1] = kListe.getKundeliste().get(i).getKundenName();
                cellData[i][2] = kListe.getKundeliste().get(i).getKundenVorname();
                cellData[i][3] = kListe.getKundeliste().get(i).getKundenStrasse();
                cellData[i][4] = kListe.getKundeliste().get(i).getKundenOrt();
                cellData[i][5] = kListe.getKundeliste().get(i).getKundenHausnummer();
            }
        } else {
            jB_edit.setEnabled(false);
            jB_delete.setEnabled(false);
        }
        String[] columnNames = {"Kundennummer", "Kundenname", "Kundenvorname", "Strasse", "Ort", "Hausnummer"};

        jD_KundeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jT_Kunde.setModel(new javax.swing.table.DefaultTableModel(cellData, columnNames) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });

        jScrollPane1.setViewportView(jT_Kunde);
        if (jT_Kunde.getColumnModel().getColumnCount() > 0) {
            jT_Kunde.getColumnModel().getColumn(0).setPreferredWidth(6);
            jT_Kunde.getColumnModel().getColumn(5).setPreferredWidth(7);
        }

        jB_edit.setText("Bearbeiten");
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton1ActionPerformed(evt);
            }
        });

        jB_delete.setText("Löschen");

        jB_cancel.setText("Abbrechen");

        jT_Kunde.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = jT_Kunde.getSelectedRow();
                jTextField1.setText(String.valueOf(jT_Kunde.getValueAt(row, 0)));
            }
        });

        jB_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jT_Kunde.getSelectedRow();
                if (index != -1) {
                    jD_KundeDialog.dispose();

                    Kunde kunde = KundeBearbeiten.showInputDialog(null, "Kunden bearbeiten", "Kunden bearbeiten", kundenliste, index);

                    if (kunde != null) {
                        System.out.println(kunde.getKundenNr());
                        System.out.println(kunde.getKundenName());
                        System.out.println(kunde.getKundenVorname());
                        System.out.println(kunde.getKundenStrasse());
                        System.out.println(kunde.getKundenOrt());
                        System.out.println(kunde.getKundenHausnummer());
                    }
                }
            }
        }
        );

        // Delete- Button
        jB_delete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kundenliste.getKundeliste().size() != 0) {
                    int[] indexes = jT_Kunde.getSelectedRows();
                    Object[] options = {"Ja", "Nein"};
                    if (indexes.length != 0) {
                        int antwort = JOptionPane.showOptionDialog(jD_KundeDialog, "Wollen Sie diesen Kunden loeschen? ", "Warnung",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[1]);
                        if (antwort == JOptionPane.YES_OPTION) {

                            int k = indexes.length - 1; // 
                            for (int i = k; i >= 0; i--) {
                                kundenliste.getKundeliste().remove(indexes[i]);
                            }
                            if (k < 0) {
                                JOptionPane.showMessageDialog(null, "Kunde geloescht");
                            }
                        }

                        jD_KundeDialog.dispose();
                        KundenFormular.showInputDialog(null, message, title, kListe);
                    }
                }
            }
        }
        ); // kundenliste.getKundeliste()

        // Cancel-Button
        jB_cancel.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                jD_KundeDialog.dispose();
            }
        }
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jTextField1
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jD_KundeDialog.getContentPane());
        jD_KundeDialog.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jB_edit)
                        .addGap(18, 18, 18)
                        .addComponent(jB_delete)
                        .addGap(18, 18, 18)
                        .addComponent(jB_cancel)
                        .addContainerGap(181, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jB_edit)
                                .addComponent(jB_delete)
                                .addComponent(jB_cancel))
                        .addContainerGap())
        );

        jD_KundeDialog.setLocation(300, 200);
        jD_KundeDialog.pack();
        jD_KundeDialog.setVisible(true);

        return null;
    }
}
