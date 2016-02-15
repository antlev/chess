/**
 * Created by antoine on 2/15/16.
 */

import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;


public class Window {

    private Chessboard chessboard;
    private JLabel[][] tab;

    private JPanel navPanel = new JPanel(); // panel du haut
    private JPanel gridPanel = new JPanel(); // panel du bas ( grille )
    GridLayout gridLayout1 = new GridLayout();

    private JButton start = new JButton();
    private JButton reset = new JButton();

    private JTextField text = new JTextField();
    private JPanel whitePanel = new JPanel();
    private JPanel blackPanel = new JPanel();



    public Window(){


    }

    public void jbInit() throws Exception {


    }

    private class GestionnaireEvenement extends MouseAdapter {

        public void mouseClicked(MouseEvent eve) {

        }
        public void reset(){

        }
    }




    }
