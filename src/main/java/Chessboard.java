/**
 * Created by antoine on 2/15/16.
 */
public class Chessboard implements ChessboardMethods{

    private Box[][] chessboard;

    public Chessboard() {
        chessboard = new Box[8][8];
        for (int  i= 0; i <= 7; ++i)
            for (int j = 0; j <= 7; ++j)
                chessboard[i][j] = new Box();
    }

    public void start() {
        int line = 7;
		/*Je fais les instructions deux fois, au premier passage, ligne est égal ˆ 7 et la couleur ˆ noir,
		 * et au deuxime passage, la couleur est blanche et la ligne est égal ˆ 0.
		 */
        for (String color = "black" ; !color.equals("white"); color = "white", line = 0){
            //J'initialise tout mes pices de la premires rangŽe (tour, cavalier etc...)
            chessboard[0][line].setPiece(new Rook(color));
            chessboard[1][line].setPiece(new Knight(color));
            chessboard[2][line].setPiece(new Bishop(color));
            chessboard[3][line].setPiece(new Queen(color));
            chessboard[4][line].setPiece(new King(color));
            chessboard[5][line].setPiece(new Bishop(color));
            chessboard[6][line].setPiece(new Knight(color));
            chessboard[7][line].setPiece(new Rook(color));
            //Je change de ligne, dépendament de la couleur que je suis en train de traiter.
            line += color.equals("noir") ? -1 : 1;
            //J'initialise tout mes pions.
            for (int i = 0; i <= 7; ++i)
                chessboard[i][line].setPiece(new Pawn(color));
        }
    }

    public Box getBox(int line, int column) {
        return chessboard[column][line];
    }

    public boolean possiblePath(Move departure) {
        Piece departure = chessboard[(int)departure.getDeparture().getColumn()][(int)departure.getDeparture().getLine()].getPiece();

		/*deux premire condition fondamentale, que la case d'arrivŽ sois libre ou qu'elle possde une pice de couleur
		contraire ˆ celle de la pice de dŽpart*/
        if (!chessboard[(int)departure.getArrival().getColumn()][(int)departure.getArrival().getLine()].isOccupied(departure.getColor().equals("white") ? "white" : "black")
                | departure.isNul()){
            if (!(departure instanceof Knight)){

                if(!(departure instanceof Pawn)){
                    //Je vŽrifie que le dŽplacement est supŽrieur ˆ un.
                    if(!(Math.abs(departure.getMoveX()) - Math.abs(departure.getMoveY()) <= 1
                            && Math.abs(departure.getMoveX()) + Math.abs(departure.getMoveY()) <= 1)){

						/*JumpX et jumpY seront sois 0,  1 ou -1, ils indiquent l'incrŽmentation que je devrai utiliser pour les valeurs X et Y pour
						  vŽrifier toute les cases entre le dŽpart et l'arrivŽ*/
                        int jumpX = departure.getMoveX() == 0 ? 0 : (int)(departure.getArrival().getColumn() - departure.getDeparture().getColonne())
                                /Math.abs((int)(departure.getArrival().getColumn() - departure.getDeparture*().getColonne()));

                        int jumpY = departure.getMoveY() == 0 ? 0 : (int)(departure.getArrival().getLine() - departure.getDeparture().getLine())
                                /Math.abs((int)(departure.getArrival().getLine() - departure.getDeparture().getLine()));

                        //Je vŽrifie succcessivement toutes les cases entre l'arrivŽe et le dŽpart
                        for (int ctrX = (int)departure.getDeparture().getColonne() + jumpX, ctrY = (int)departure.getDeparture().getLine() + jumpY;
                             ctrX != (int)departure.getArrival().getColumn() | ctrY != (int)departure.getArrival().getLine();
                             ctrX += jumpX, ctrY += jumpY){
                            if (chessboard[ctrX][ctrY].estOccupe()){
                                return false;
                            }
                        }
                        return true;
                    }
                    else
						/*Si le dŽplacement est Žgal il est automatiquement valide car il a passŽ les prŽcedents test. Puisque
						le dŽplacement est de 1, je n'ai pas besoin de vŽrifier les cases entre le dŽpart et l'arrivŽ*/
                        return true;
                }
                else
                    //Si c'est un pion, je vŽrifie si la case est libre de toute pice.
                    return !chessboard[(int)departure.getArrival().getColumn()][(int)departure.getArrival().getLine()].estOccupe();

            }
            else
                //je renvoie true car un cavalier peut sauter par dessus les autres pices.
                return true;
        }
        else
            //Le dŽplacement est automatiquement invalide si la case d'arrivŽ contient une pice de mme couleur que la pice de dŽpart.
            return false;


    }


    public boolean canBeCaptured(Move move) {
        //Je vŽrifie si la pice est un pion
        if(location[deplacement.getDepart().getColonne()][deplacement.getDepart().getLigne()].getPiece() instanceof Pion)
        {
        //initialisation des variables dont j'aurai besoin dans mes conditions, ˆ savoir la couleur de la pice de dŽpart et la case d'arrivŽ.
        Case Arrive = location[(int)deplacement.getArrivee().getColonne()][(int)deplacement.getArrivee().getLigne()];
        String couleurDepart = location[(int)deplacement.getDepart().getColonne()][(int)deplacement.getDepart().getLigne()].getPiece().getCouleur();

        //je vŽrifie d'abord si la pice d'arrivŽ existe et si elle est de la couleur contraire de celle de dŽpart.
        if(Arrive.estOccupe(couleurDepart.equals("blanc") ? "noir" : "blanc"))
				/*Je vŽrifie si le dŽplacement est valide,
				 *Le dŽplacement est valide si le produits du dŽplacement x et y donne 1 si la couleur de dŽpart est noir
				 *ou -1 si la pice de dŽpart est blanche.
				 */
        return (deplacement.getDeplacementY() * Math.abs(deplacement.getDeplacementX()) == (couleurDepart.equals("noir") ? 1 : -1));
        }
        return false;

        }
}
