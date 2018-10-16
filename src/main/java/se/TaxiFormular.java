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
public class TaxiFormular implements Serializable{

    static private Taxi taxi;
    static private Taxiliste taxiliste;
    static private int row;

    // Anzeige eines Dialogs zur Eingabe der Taxidaten
    public static Taxi showInputDialog(Window owner, String message, String title, Taxiliste tListe) {

        taxiliste = tListe;     // Referenz speichern

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JDialog jD_TaxiDialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable jT_Taxi = new javax.swing.JTable();
        JButton jB_edit = new javax.swing.JButton();
        JButton jB_delete = new javax.swing.JButton();
        JButton jB_cancel = new javax.swing.JButton();
        JTextField jTextField1 = new javax.swing.JTextField(2);

        int tListeSize = tListe.getTaxiliste().size();
        Object[][] cellData = new Object[tListeSize][3];
        //Setup Tabledata
        if (tListeSize != 0) {
            for (int i = 0; i < tListeSize; i++) {
                cellData[i][0] = tListe.getTaxiliste().get(i).getTaxiNr();
                cellData[i][1] = tListe.getTaxiliste().get(i).getKennzeichen();
                cellData[i][2] = tListe.getTaxiliste().get(i).getFahrzeugtyp();
            }
        } else {
            jB_edit.setEnabled(false);
            jB_delete.setEnabled(false);
        }
        String[] columnNames = {"ID", "Kennzeichen", "Fahrzeugtyp"};

        jD_TaxiDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jT_Taxi.setModel(new javax.swing.table.DefaultTableModel(cellData, columnNames) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });

        jScrollPane1.setViewportView(jT_Taxi);
        if (jT_Taxi.getColumnModel().getColumnCount() > 0) {
            jT_Taxi.getColumnModel().getColumn(0).setPreferredWidth(6);
            jT_Taxi.getColumnModel().getColumn(2).setPreferredWidth(7);
        }

        jB_edit.setText("Bearbeiten");
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton1ActionPerformed(evt);
            }
        });

        jB_delete.setText("Löschen");

        jB_cancel.setText("Abbrechen");

        jT_Taxi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = jT_Taxi.getSelectedRow();
                jTextField1.setText(String.valueOf(jT_Taxi.getValueAt(row, 0)));
            }
        });

        jB_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jT_Taxi.getSelectedRow();
                if (index != -1) {
                    jD_TaxiDialog.dispose();

                    Taxi taxi = TaxiBearbeiten.showInputDialog(null, "Taxi bearbeiten", "Taxi bearbeiten", taxiliste, index);

                    if (taxi != null) {
                        System.out.println(taxi.getTaxiNr());
                        System.out.println(taxi.getKennzeichen());
                        System.out.println(taxi.getFahrzeugtyp());

                    }
                }
            }
        }
        );

        // Delete- Button
        jB_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (taxiliste.getTaxiliste().size() != 0) {
                    Object[] options = {"Ja", "Nein"};
                    int[] indexes = jT_Taxi.getSelectedRows();
                    if (indexes.length != 0) {
                        int antwort = JOptionPane.showOptionDialog(jD_TaxiDialog, "Wollen Sie dieses Taxi loeschen? ", "Warnung",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[1]);
                        if (antwort == JOptionPane.YES_OPTION) {
                            int k = indexes.length - 1; // 
                            for (int i = k; i >= 0; i--) {
                                taxiliste.getTaxiliste().remove(indexes[i]);
                            }
                            if (k < 0) {
                                JOptionPane.showMessageDialog(null, "Taxi geloescht");
                            }

                            jD_TaxiDialog.dispose();
                            TaxiFormular.showInputDialog(null, message, title, tListe);
                        }
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
                jD_TaxiDialog.dispose();
            }
        }
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jTextField1
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jD_TaxiDialog.getContentPane());
        jD_TaxiDialog.getContentPane().setLayout(layout);
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

        jD_TaxiDialog.setLocation(300, 200);
        jD_TaxiDialog.pack();
        jD_TaxiDialog.setVisible(true);

        return null;
    }
}
