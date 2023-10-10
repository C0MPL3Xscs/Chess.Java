/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Square.Bishop;
import Square.King;
import Square.Knight;
import Square.Pawn;
import Square.Queen;
import Square.Square;
import Square.Tower;
import java.util.ArrayList;

/**
 *
 * @author Samuel Sampaio
 */
public class Board {

    private final Square[][] board = new Square[8][8];

    public Board() {
        initialBoard();
    }

    //Returns the number of pieces
    public int numberPieces() {
        int num = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j].containsPiece()) {
                    num++;
                }
            }
        }
        return num;
    }

    //Returns the board
    public Square[][] getBoard() {
        return this.board;
    }

    // Initializes the board with the pieces in their initial positions
    private void initialBoard() {
        String[] BpawnPositions = {"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7"};
        String[] WpawnPositions = {"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2"};
        String[] BtowerPositions = {"A8", "H8"};
        String[] WtowerPositions = {"A1", "H1"};
        String[] BknightPositions = {"B8", "G8"};
        String[] WknightPositions = {"B1", "G1"};
        String[] BbishopPositions = {"C8", "F8"};
        String[] WbishopPositions = {"C1", "F1"};
        String BqueenPosition = "D8";
        String WqueenPosition = "D1";
        String BkingPosition = "E8";
        String WkingPosition = "E1";

        // Fill the board with empty squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String position = numberToString(i) + String.valueOf(j + 1);
                Square square = new Square(position, null);
                board[i][j] = square;
            }
        }

        // Place Black pawns
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(true, BpawnPositions[i]);
            Square square = new Square(BpawnPositions[i], pawn);
            board[i][6] = square;
        }

        // Place White pawns
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(false, WpawnPositions[i]);
            Square square = new Square(WpawnPositions[i], pawn);
            board[i][1] = square;
        }

        // Place Black towers
        for (String position : BtowerPositions) {
            Tower tower = new Tower(true, position);
            Square square = new Square(position, tower);
            board[stringToNumber(position.charAt(0))][7] = square;
        }

        // Place White towers
        for (String position : WtowerPositions) {
            Tower tower = new Tower(false, position);
            Square square = new Square(position, tower);
            board[stringToNumber(position.charAt(0))][0] = square;
        }

        // Place Black knights
        for (String position : BknightPositions) {
            Knight knight = new Knight(true, position);
            Square square = new Square(position, knight);
            board[stringToNumber(position.charAt(0))][7] = square;
        }

        // Place White knights
        for (String position : WknightPositions) {
            Knight knight = new Knight(false, position);
            Square square = new Square(position, knight);
            board[stringToNumber(position.charAt(0))][0] = square;
        }

        // Place Black bishops
        for (String position : BbishopPositions) {
            Bishop bishop = new Bishop(true, position);
            Square square = new Square(position, bishop);
            board[stringToNumber(position.charAt(0))][7] = square;
        }

        // Place White bishops
        for (String position : WbishopPositions) {
            Bishop bishop = new Bishop(false, position);
            Square square = new Square(position, bishop);
            board[stringToNumber(position.charAt(0))][0] = square;
        }

        // Place Black queen
        Queen bQueen = new Queen(true, BqueenPosition);
        Square bQueenSquare = new Square(BqueenPosition, bQueen);
        board[stringToNumber(BqueenPosition.charAt(0))][7] = bQueenSquare;

        // Place White queen
        Queen wQueen = new Queen(false, WqueenPosition);
        Square wQueenSquare = new Square(WqueenPosition, wQueen);
        board[stringToNumber(WqueenPosition.charAt(0))][0] = wQueenSquare;

        // Place Black king
        King bKing = new King(true, BkingPosition);
        Square bKingSquare = new Square(BkingPosition, bKing);
        board[stringToNumber(BkingPosition.charAt(0))][7] = bKingSquare;

        // Place White king
        King wKing = new King(false, WkingPosition);
        Square wKingSquare = new Square(WkingPosition, wKing);
        board[stringToNumber(WkingPosition.charAt(0))][0] = wKingSquare;
    }
    
    //returns an array with the possible moves for each piece
    public ArrayList<String> possibleMoves(String Position) {
        ArrayList<String> moves = new ArrayList<String>();
        Square square = getSquare(Position);
        if (square.getPiece() instanceof Pawn) {
            moves = PawnMoves(moves, square, Position);
        } else if (square.getPiece() instanceof Tower) {
            moves = TowerMoves(moves, square, Position);
        } else if (square.getPiece() instanceof Knight) {
            moves = KnightMoves(moves, square, Position);
        } else if (square.getPiece() instanceof Bishop) {
            moves = BishopMoves(moves, square, Position);
        } else if (square.getPiece() instanceof Queen) {
            moves = QueenMoves(moves, square, Position);
        } else if (square.getPiece() instanceof King) {
            moves = KingMoves(moves, square, Position);
        }
        return moves;
    }
    
    //returns an array with the possible moves for the tower on "Position"
    private ArrayList<String> TowerMoves(ArrayList<String> moves, Square square, String Position) {
        // Define the possible directions a tower can move (up, down, left, right)
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Iterate through each direction
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            int x = stringToNumber(Position.charAt(0));
            int y = Character.getNumericValue(Position.charAt(1));

            // Continue moving in the current direction until we reach the board's edge or a piece
            while (true) {
                x += dx;
                y += dy;

                // Check if the new position is within the board bounds
                if (x < 0 || x >= 8 || y < 1 || y > 8) {
                    break; // Out of bounds, stop moving in this direction
                }

                String newPosition = numberToString(x) + y;

                Square targetSquare = getSquare(newPosition);

                // Check if the target square is empty (no piece)
                if (!targetSquare.containsPiece()) {
                    moves.add(newPosition);
                } else {
                    // There's a piece in the way, if it's an opponent's piece, add it as a legal move
                    if (targetSquare.getPiece().Black != square.getPiece().Black) {
                        moves.add(newPosition);
                    }
                    break; // Stop moving in this direction
                }
            }
        }

        return moves;
    }

    //returns an array with the possible moves for the pawn on "Position"
    private ArrayList<String> PawnMoves(ArrayList<String> moves, Square square, String Position) {
        Pawn pawn = (Pawn) square.getPiece();
        if (pawn.isFirstMove()) {
            int i = 1;
            while (i <= 2) {
                String newPosition;
                if (square.getPiece().Black) {
                    newPosition = String.valueOf(Position.charAt(0)) + String.valueOf(Character.getNumericValue(Position.charAt(1)) - i);
                } else {
                    newPosition = String.valueOf(Position.charAt(0)) + String.valueOf(Character.getNumericValue(Position.charAt(1)) + i);
                }

                i++;
                moves.add(newPosition);
            }
        } else {
            String newPosition;
            if (square.getPiece().Black) {
                newPosition = String.valueOf(Position.charAt(0)) + String.valueOf(Character.getNumericValue(Position.charAt(1)) - 1);
            } else {
                newPosition = String.valueOf(Position.charAt(0)) + String.valueOf(Character.getNumericValue(Position.charAt(1)) + 1);
            }
            if (!getSquare(newPosition).containsPiece()) {
                moves.add(newPosition);
            }
        }
        //Checks for diagonal positions

        String LDiagonal;
        String RDiagonal;

        //checks if the piece is black or white
        if (!square.getPiece().Black) {
            LDiagonal = getWLDiagonalPosition(Position);
            RDiagonal = getWRDiagonalPosition(Position);
        } else {
            LDiagonal = getBLDiagonalPosition(Position);
            RDiagonal = getBRDiagonalPosition(Position);
        }

        //Checks for left diagonal
        //Checks if the diagonal is outside the board
        if (LDiagonal != null) {
            Square leftD = getSquare(LDiagonal);
            //Checks if there is a piece in the diagonal
            if (leftD.getPiece() != null) {
                //Checks if that piece is from the oposite team
                if (leftD.getPiece().Black != square.getPiece().Black) {
                    moves.add(leftD.getPosition());
                }
            }
        }

        //Checks for right diagonal
        //Checks if the diagonal is outside the board
        if (RDiagonal != null) {
            Square rightD = getSquare(RDiagonal);
            //Checks if there is a piece in the diagonal
            if (rightD.getPiece() != null) {
                //Checks if that piece is from the oposite team
                if (rightD.getPiece().Black != square.getPiece().Black) {
                    moves.add(rightD.getPosition());
                }
            }
        }
        return moves;
    }

    //returns an array with the possible moves for the knight on "Position"
    private ArrayList<String> KnightMoves(ArrayList<String> moves, Square square, String Position) {
        int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        int x = stringToNumber(Position.charAt(0));
        int y = Character.getNumericValue(Position.charAt(1));

        for (int[] move : knightMoves) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (newX >= 0 && newX < 8 && newY >= 1 && newY <= 8) {
                String newPosition = numberToString(newX) + newY;
                Square targetSquare = getSquare(newPosition);

                if (!targetSquare.containsPiece() || (targetSquare.containsPiece() && targetSquare.getPiece().Black != square.getPiece().Black)) {
                    moves.add(newPosition);
                }
            }
        }

        return moves;
    }

    //returns an array with the possible moves for the bishop on "Position"
    private ArrayList<String> BishopMoves(ArrayList<String> moves, Square square, String Position) {
    int x = stringToNumber(Position.charAt(0));
    int y = Character.getNumericValue(Position.charAt(1));

    int[][] diagonalMoves = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    for (int[] move : diagonalMoves) {
        int newX = x;
        int newY = y;

        while (true) {
            newX += move[0];
            newY += move[1];

            if (newX >= 0 && newX < 8 && newY >= 1 && newY <= 8) {
                String newPosition = numberToString(newX) + newY;
                Square targetSquare = getSquare(newPosition);

                if (!targetSquare.containsPiece()) {
                    moves.add(newPosition);
                } else if (targetSquare.getPiece().Black != square.getPiece().Black) {
                    // Capture opponent's piece and stop checking this diagonal
                    moves.add(newPosition);
                    break;
                } else {
                    // Blocked by own piece, stop checking this diagonal
                    break;
                }
            } else {
                // Out of bounds, stop checking this diagonal
                break;
            }
        }
    }

    return moves;
}

    //returns an array with the possible moves for the queen on "Position"
    private ArrayList<String> QueenMoves(ArrayList<String> moves, Square square, String Position) {
        // Get moves from TowerMoves
        moves = TowerMoves(new ArrayList<>(moves), square, Position);

        // Get moves from BishopMoves and add them to the existing moves
        moves.addAll(BishopMoves(new ArrayList<>(), square, Position));

        return moves;
    }

    //returns an array with the possible moves for the king on "Position"
    private ArrayList<String> KingMoves(ArrayList<String> moves, Square square, String Position) {
        int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        int x = stringToNumber(Position.charAt(0));
        int y = Character.getNumericValue(Position.charAt(1));

        for (int[] move : kingMoves) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (newX >= 0 && newX < 8 && newY >= 1 && newY <= 8) {
                String newPosition = numberToString(newX) + newY;
                Square targetSquare = getSquare(newPosition);

                if (!targetSquare.containsPiece() || (targetSquare.containsPiece() && targetSquare.getPiece().Black != square.getPiece().Black)) {
                    moves.add(newPosition);
                }
            }
        }

        return moves;
    }

    //returns the position of the square on the left-up position relatibe to "Position"
    private String getWLDiagonalPosition(String Position) {
        String Diagonal;
        String D1 = numberToString(stringToNumber(Position.charAt(0)) - 1);
        String D2 = String.valueOf(Character.getNumericValue(Position.charAt(1)) + 1);
        if (D1.equals("0") || D2.equals("0")) {
            return null;
        }
        Diagonal = D1 + D2;

        return Diagonal;
    }

    //returns the position of the square on the right-up position relatibe to "Position"
    private String getWRDiagonalPosition(String Position) {
        String Diagonal;
        String D1 = numberToString(stringToNumber(Position.charAt(0)) + 1);
        String D2 = String.valueOf(Character.getNumericValue(Position.charAt(1)) + 1);
        if (D1.equals("0") || D2.equals("0")) {
            return null;
        }
        Diagonal = D1 + D2;

        return Diagonal;
    }

    //returns the position of the square on the left-down position relatibe to "Position"
    private String getBLDiagonalPosition(String Position) {
        String Diagonal;
        String D1 = numberToString(stringToNumber(Position.charAt(0)) - 1);
        String D2 = String.valueOf(Character.getNumericValue(Position.charAt(1)) - 1);
        if (D1.equals("0") || D2.equals("0")) {
            return null;
        }
        Diagonal = D1 + D2;

        return Diagonal;
    }

    //returns the position of the square on the right-down position relatibe to "Position"
    private String getBRDiagonalPosition(String Position) {
        String Diagonal;
        String D1 = numberToString(stringToNumber(Position.charAt(0)) + 1);
        String D2 = String.valueOf(Character.getNumericValue(Position.charAt(1)) - 1);
        if (D1.equals("0") || D2.equals("0")) {
            return null;
        }
        Diagonal = D1 + D2;

        return Diagonal;
    }

    //Registers a move on the board
    public void movePiece(String previous, String next) {

        Square square = new Square(previous, null);

        int i = stringToNumber(previous.charAt(0));
        int j = Character.getNumericValue(previous.charAt(1)) - 1;

        int i1 = stringToNumber(next.charAt(0));
        int j1 = Character.getNumericValue(next.charAt(1)) - 1;

        board[i1][j1] = getSquare(previous);
        getSquare(previous).setPosition(next);

        board[i][j] = square;
    }

    //Returns the square in the position "Position"
    public Square getSquare(String position) {
        int i = stringToNumber(position.charAt(0));
        int j = Character.getNumericValue(position.charAt(1)) - 1;

        return board[i][j];
    }
    
    //Returns the number corresponding to the char
    private int stringToNumber(char char1) {
        int i = 0;
        switch (char1) {
            case 'A':
                i = 0;
                break;
            case 'B':
                i = 1;
                break;
            case 'C':
                i = 2;
                break;
            case 'D':
                i = 3;
                break;
            case 'E':
                i = 4;
                break;
            case 'F':
                i = 5;
                break;
            case 'G':
                i = 6;
                break;
            case 'H':
                i = 7;
                break;
            default:
                i = 0;
        }

        return i;
    }

    //Returns the String corresponding to the number
    private String numberToString(int i) {
        String char1 = "";
        switch (i) {
            case 0:
                char1 = "A";
                break;
            case 1:
                char1 = "B";
                break;
            case 2:
                char1 = "C";
                break;
            case 3:
                char1 = "D";
                break;
            case 4:
                char1 = "E";
                break;
            case 5:
                char1 = "F";
                break;
            case 6:
                char1 = "G";
                break;
            case 7:
                char1 = "H";
                break;
            default:
                char1 = "0";
        }
        return char1;
    }
}
