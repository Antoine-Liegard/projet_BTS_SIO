/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.ihm;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import projetAntoine.entites.CSessionFormation;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
    public class CComboBoxRenderer extends JLabel implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
            JLabel l = new JLabel(((CSessionFormation) e).toString());
            return l;
        }

    }
