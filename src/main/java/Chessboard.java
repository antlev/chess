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

    public boolean possiblePath(Move move) {
        Piece departure = chessboard[(int)move.getDeparture().getColumn()][(int)move.getDeparture().getLine()].getPiece();

		/*deux premire condition fondamentale, que la case d'arrivŽ sois libre ou qu'elle possde une pice de couleur
		contraire ˆ celle de la pice de dŽpart*/
        if (!chessboard[(int)move.getArrival().getColumn()][(int)move.getArrival().getLine()].pieceIsPresent(departure.getColor().equals("white") ? "white" : "black")
                | move.isNull()){
            if (!(departure instanceof Knight)){

                if(!(departure instanceof Pawn)){
                    //Je vŽrifie que le dŽplacement est supŽrieur ˆ un.
                    if(!(Math.abs(move.getMoveOnX()) - Math.abs(move.getMoveOnY()) <= 1
                            && Math.abs(move.getMoveOnX()) + Math.abs(move.getMoveOnY()) <= 1)){

						/*JumpX et jumpY seront sois 0,  1 ou -1, ils indiquent l'incrŽmentation que je devrai utiliser pour les valeurs X et Y pour
						  vŽrifier toute les cases entre le dŽpart et l'arrivŽ*/
                        int jumpX = move.getMoveOnX() == 0 ? 0 : (int)(move.getArrival().getColumn() - move.getDeparture().getColumn())
                                /Math.abs((int)(move.getArrival().getColumn() - move.getDeparture().getColumn()));

                        int jumpY = move.getMoveOnY() == 0 ? 0 : (int)(move.getArrival().getLine() - move.getDeparture().getLine())
                                /Math.abs((int)(move.getArrival().getLine() - move.getDeparture().getLine()));

                        //Je vŽrifie succcessivement toutes les cases entre l'arrivŽe et le dŽpart
                        for (int ctrX = (int)move.getDeparture().getColumn() + jumpX, ctrY = (int)move.getDeparture().getLine() + jumpY;
                             ctrX != (int)move.getArrival().getColumn() | ctrY != (int)move.getArrival().getLine();
                             ctrX += jumpX, ctrY += jumpY){
                            if (chessboard[ctrX][ctrY].pieceIsPresent()){
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
                    return !chessboard[(int)move.getArrival().getColumn()][(int)move.getArrival().getLine()].pieceIsPresent();

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
        if(chessboard[move.getDeparture().getColumn()][move.getDeparture().getLine()].getPiece() instanceof Pawn)
        {
        //initialisation des variables dont j'aurai besoin dans mes conditions, ˆ savoir la couleur de la pice de dŽpart et la case d'arrivŽ.
        Box Arrival = chessboard[(int)move.getArrival().getColumn()][(int)move.getArrival().getLine()];
        String couleurDepart = chessboard[(int)move.getDeparture().getColumn()][(int)move.getDeparture().getLine()].getPiece().getColor();

        //je vŽrifie d'abord si la pice d'arrivŽ existe et si elle est de la couleur contraire de celle de dŽpart.
        if(Arrival.pieceIsPresent(couleurDepart.equals("blanc") ? "noir" : "blanc"))
				/*Je vŽrifie si le dŽplacement est valide,
				 *Le dŽplacement est valide si le produits du dŽplacement x et y donne 1 si la couleur de dŽpart est noir
				 *ou -1 si la pice de dŽpart est blanche.
				 */
        return (move.getMoveOnY() * Math.abs(move.getMoveOnX()) == (couleurDepart.equals("noir") ? 1 : -1));
        }
        return false;

        }
}
