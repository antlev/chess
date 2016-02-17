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
import javax.swing.text.Position;
import java.awt.Color;

/**
 * classe Fenetre jeu servant � representer la GUI du jeu d'echec, contient la planche de jeu, les boutons debuter et reset,
 * et le champ texte informant le joueur sur le tour. Contient �galement deux jPanel contenant les pieces mang�es.
 *
 * @author Francois Allard
 */
public class Window extends JFrame {
    /**
     * Echiquier du jeu, contient le tableau de case.
     */
    private Chessboard chessboard; // echiquier
    private JLabel[][] jLabel; // tableau de JLabels

    private JPanel controlPanel = new JPanel(); // panel du haut
    private JPanel gridPanel = new JPanel(); // panel du bas ( grille )
    GridLayout gridLayout1 = new GridLayout();

    private JButton startButton = new JButton();
    private JTextField textField = new JTextField();
    private JButton resetButton = new JButton();
    private JPanel whitePanel = new JPanel();
    private JPanel blackPanel = new JPanel();

    /**
     * Constructeur, appelle m�thode JBInit
     */
    public Window() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * initialise la surface de jeu. Cr�� tout les �lements et initialise leur position leur couleur.. etc
     */
    private void jbInit() throws Exception {

        jLabel = new JLabel[8][8]; // cr�ation du tableau de JLabel
        chessboard = new Chessboard(); // cr�ation de l'�chiquier

        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(784, 585));
        this.setTitle("Jeu d'Echecs");
        controlPanel.setBounds(new Rectangle(5, 10, 550, 45));
        controlPanel.setBorder(BorderFactory
                .createEtchedBorder(EtchedBorder.RAISED));
        controlPanel.setLayout(null);
        gridPanel.setBounds(new Rectangle(5, 65, 550, 465));
        gridPanel.setBorder(BorderFactory
                .createEtchedBorder(EtchedBorder.RAISED));
        gridPanel.setLayout(gridLayout1);
        gridLayout1.setColumns(8);
        gridLayout1.setRows(8);
        this.getContentPane().add(blackPanel, null);
        this.getContentPane().add(whitePanel, null);
        this.getContentPane().add(gridPanel, null);
        controlPanel.add(resetButton, null);
        controlPanel.add(textField, null);
        controlPanel.add(startButton, null);
        this.getContentPane().add(controlPanel, null);
        startButton.setBounds(new Rectangle(15, 10, 130, 25));
        startButton.setText("DEBUTER");
        textField.setBounds(new Rectangle(160, 10, 215, 25));

        // les �couteurs
        resetButton.setText("RESET");
        resetButton.setBounds(new Rectangle(390, 10, 130, 25));
        GestionnaireEvenement gest = new GestionnaireEvenement();
        startButton.addMouseListener(gest);
        resetButton.addMouseListener(gest);

        //cr�ation des labels
        whitePanel.setBounds(new Rectangle(570, 65, 75, 480));
        whitePanel.setBackground(new Color(255, 255, 255));
        whitePanel.setLayout(new FlowLayout());
        blackPanel.setBounds(new Rectangle(655, 65, 75, 475));
        blackPanel.setBackground(new Color(100, 100, 100));
        blackPanel.setLayout(new FlowLayout());

        //J'attribue la couleur aux JLabels
        int a = 1;
        for (int ligne = 0; ligne < 8; ligne++) {
            a = a == 1 ? 0 : 1;
            for (int colonne = 0; colonne < 8; colonne++) {
                jLabel[colonne][ligne] = new JLabel(); // cr��ation du JLabel
                jLabel[colonne][ligne].setOpaque(true);
                gridPanel.add(jLabel[colonne][ligne]); // ajouter au Panel
                jLabel[colonne][ligne].setOpaque(true);
                jLabel[colonne][ligne].setHorizontalAlignment(SwingConstants.CENTER); // pour
                jLabel[colonne][ligne].addMouseListener(gest); // ajouter l'��couteur aux
                if ((colonne + 1) % 2 == a)
                    jLabel[colonne][ligne].setBackground(new Color(255, 255, 255));
                else
                    jLabel[colonne][ligne].setBackground(new Color(100, 100, 100));

            }
        }

    }

    /**
     * classe privee pour la gestion des evenement de la souris.
     *
     * @author Francois
     */
    private class GestionnaireEvenement extends MouseAdapter {

        Piece piece = null;
        ImageIcon image;
        int lineClic;
        int columnClic;
        Position departure, arrival;
        String color = "white";
        Position tempPos = null;


        /**
         * methode s'excutant si l'on clique sur la surface de jeu. La methode determine ensuite ou est-ce que l'on cliquer
         * et fait les action en consequence
         */
        public void mouseClicked(MouseEvent eve) {
            // si on clique sur le bouton d�buter
            if (eve.getSource() == startButton) {
                //initialise le champ texte, apelle la m�thode d�buter, et initialise toute les variables
                textField.setText("Player white :");
                startButton.setEnabled(false);
                chessboard.start(); // code
                String folderIcon = "Icone/";
                char[] pieceOrdre = {'R', 'C', 'B', 'Q', 'K', 'B', 'C', 'R'};
                int increment = 1;
                int line = 0;
                char color = 'N';
                Piece tempo = null;
                chessboard.start(); // code

                // Je place les ic�nes des pi�ces sur leur cases respectives
                while (increment >= -1) {
                    for (int i = 0; i <= 7; i++) {
                        jLabel[i][line].setIcon(new ImageIcon(folderIcon + pieceOrdre[i] + color + ".gif"));
                        switch (pieceOrdre[i]) {
                            case 'R':
                                tempo = new Rook(line < 5 ? "black" : "white");
                                break;

                            case 'C':
                                tempo = new Knight(line < 5 ? "black" : "white");
                                break;

                            case 'B':
                                tempo = new Bishop(line < 5 ? "black" : "white");
                                break;

                            case 'Q':
                                tempo = new Queen(line < 5 ? "black" : "white");
                                break;

                            case 'K':
                                tempo = new King(line < 5 ? "black" : "white");
                                break;
                        }
                        chessboard.getBox(i, line).setPiece(tempo);
                        jLabel[i][line + increment].setIcon(new ImageIcon(folderIcon + 'P' + color + ".gif"));
                        chessboard.getBox(i, line + increment).setPiece(new Pawn(line < 5 ? "black" : "white"));

                    }
                    color = 'B';
                    increment -= 2;
                    line = 7;
                }

            } else if (eve.getSource() == resetButton) {
                RAZ();


            } else if (eve.getSource() instanceof JLabel) // donc on a cliqu� sur un Label
            {
                for (int i = 0; i < 8; i++)
                    //je d�termine sur quelle Jlabel on a cliqu�
                    for (int j = 0; j < 8; j++)
                        if (eve.getSource() == jLabel[j][i]) {
                            lineClic = i;
                            columnClic = j;
                        }
                //si on a cliqu� sur une case non vide et que le tampon n'est pas null
                if ((chessboard.getBox(columnClic, lineClic).getPiece() != null | piece != null)) {
                    //si le tampon est null
                    if (piece == null) {
                        //si c'est au tour de la couleur de controle � jouer
                        if (chessboard.getBox(columnClic, lineClic).getPiece().getColor().equals(color)) {
                            //J'initialise la piece tampon a la piece sur laquelle on a cliqu�
                            piece = chessboard.getBox(columnClic, lineClic).getPiece();
                            image = (ImageIcon) jLabel[columnClic][lineClic].getIcon();
                            tempPos = new Position(columnClic, lineClic);
                            jLabel[columnClic][lineClic].setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 5));
                        }

                    } else {
                        //je cr�� un d�placement
                        Move move = new Move(tempPos, new Position(columnClic, lineClic));
                        //je v�rifie si le d�placement est valide, si le chemin est possible et si il est possible, pour un pion de manger la piece
                        if ((piece.isValid(move) && chessboard.possiblePath(move)) | chessboard.canBeCaptured(move)) {
                            //je cr�� un jLabel avec l'ic�ne de la pi�ce manger
                            JLabel eat = new JLabel(jLabel[columnClic][lineClic].getIcon());
                            eat.setHorizontalAlignment(SwingConstants.CENTER);

                            //je l'ajoute au bon jPanel
                            if (color.equals("white"))
                                whitePanel.add(eat);
                            else
                                blackPanel.add(eat);

                        /* je v�rifie si la pi�ce manger est un roi, si oui le jeu est termin� et L'utilisateurs
                        peut choisir si il veut continuer a jouer ou non*/
                            if (chessboard.getBox(columnClic, lineClic).getPiece() instanceof King) {
                                if (JOptionPane.showConfirmDialog(null, "You win !!! Do you want to play again ?\n", JOptionPane.YES_NO_OPTION) == 0) {
                                    RAZ();
                                    jLabel[tempPos.getColumn()][tempPos.getLine()].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
                                } else
                                    System.exit(0);

                            } else//si on d�pose la piece sur une case vide
                            {
                                //on met le tampon sur la case vide et on vide le tampon apr�s
                                chessboard.getBox(tempPos.getColumn(), tempPos.getLine()).setPiece(null);
                                jLabel[tempPos.getColumn()][tempPos.getLine()].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));

                                jLabel[columnClic][lineClic].setIcon(image);
                                chessboard.getBox(columnClic, lineClic).setPiece(piece);
                                jLabel[tempPos.getColumn()][tempPos.getLine()].setIcon(null);


                                piece = null;
                                image = null;
                                tempPos = null;

                                color = color.equals("blanc") ? "noir" : "blanc";
                                textField.setText("C'est le tour aux " + color);
                            }
                        } else {
                            jLabel[tempPos.getColumn()][tempPos.getLine()].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
                            piece = null;
                            image = null;
                            tempPos = null;

                        }

                    }

                }

            }
        }
    }

    //Je remet tout les attributs de la classe a 0
    public void RAZ() {
        for (int line = 0; line < 8; ++line)
            for (int column = 0; column < 8; column++) {
                jLabel[column][line].setIcon(null);
                chessboard.getBox(column, line).setPiece(null);
            }
        textField.setText("");
        startButton.setEnabled(true);
        chessboard.start();
        String colorControl = "black";

        whitePanel.removeAll();
        whitePanel.repaint();
        blackPanel.removeAll();
        blackPanel.repaint();

    }
}
