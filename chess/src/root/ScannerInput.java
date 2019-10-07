package root;

import root.pieces.*;

import java.util.*;

import static root.Utils.columnPosition;

public class ScannerInput {

    public static List<Move> readMoves(Scanner scanner) {
        List<Move> moves = new ArrayList<>();

        while(scanner.hasNext()) {

            String line = scanner.nextLine();

            String delimiter;
            if(line.lastIndexOf('x') > 0){
                delimiter = "x";
            } else {
                delimiter = "-";
            }

            String[] positions = line.split(delimiter);

            char fromAlphaCharacter = positions[0].charAt(0);
            char toAlphaCharacter = positions[1].charAt(0);

            int fromColumn = columnPosition(fromAlphaCharacter);
            int toColumn = columnPosition(toAlphaCharacter);

            int fromRow = Utils.sanatiseMoveInput(Integer.parseInt(positions[0].substring(1)));
            int toRow = Utils.sanatiseMoveInput(Integer.parseInt(positions[1].substring(1)));

            Move m;
            boolean isKingSideCastling = line.substring(0, 2).equals("0-0");
            boolean isQueenSideCastling = line.substring(0, 3).equals("0-0-0");

            // IF Move is a Promotion Move
            if(line.lastIndexOf('=') > 0) {

                PieceType pt = null;
                switch (line.split("=")[1].toUpperCase()) {
                    case "A": pt = PieceType.AMAZON; break;
                    case "B": pt = PieceType.BISHOP; break;
                    case "F": pt = PieceType.DRAGON; break;
                    case "d": pt = PieceType.DRUNKED_PAWN; break;
                    case "E": pt = PieceType.ELEPHANT; break;
                    case "K": pt = PieceType.KING; break;
                    case "N": pt = PieceType.KNIGHT; break;
                    case "P": pt = PieceType.PAWN; break;
                    case "W": pt = PieceType.PRINCESS; break;
                    case "Q": pt = PieceType.QUEEN; break;
                    case "R": pt = PieceType.ROOK; break;
                    case ".": pt = PieceType.SPACE; break;
                }
                 m = new Move(new Position(fromColumn, fromRow), new Position(toColumn, toRow), pt, true);
            } else if(line.lastIndexOf('x') > 0){

                int checkIndex = line.lastIndexOf("+");
                boolean isCheck = false;
                boolean isCheckMate = false;

                if(checkIndex == 1){
                    isCheck = true;
                } else if(checkIndex == 2) {
                    isCheck = true;
                    isCheckMate = true;
                }

                m = new Move(new Position(fromColumn, fromRow), new Position(toColumn, toRow),true, isCheck, isCheckMate);
            } else if(isKingSideCastling || isQueenSideCastling) {
                if(isQueenSideCastling){ // IS Queen side
                    m = new Move(true, true, false);
                } else { // IS King side
                    m = new Move(true, false, true);
                }
            } else {
                int checkIndex = line.lastIndexOf("+");
                boolean isCheck = false;
                boolean isCheckMate = false;

                if(checkIndex == 1){
                    isCheck = true;
                } else if(checkIndex == 2) {
                    isCheck = true;
                    isCheckMate = true;
                }

                m = new Move(new Position(fromColumn, fromRow), new Position(toColumn, toRow), isCheck, isCheckMate);
            }

            moves.add(m);
        }

        return moves;
    }

    public static Board readBoard(Scanner scanner) {

        scanner.useDelimiter("-----");

        String pieceCount = scanner.next();
        String board = scanner.next();
        String status = scanner.next();

        Map<PieceType, Integer> pc = new HashMap<>();

        for(String row: pieceCount.trim().split("\n")){

            char p = row.split(":")[0].charAt(0);
            Integer n = Integer.parseInt(row.split(":")[1]);

            switch (p) {
                case 'a': pc.put(PieceType.AMAZON, n); break;
                case 'b': pc.put(PieceType.BISHOP, n); break;
                case 'k': pc.put(PieceType.KING, n); break;
                case 'q': pc.put(PieceType.QUEEN, n); break;
                case 'p': pc.put(PieceType.PAWN, n); break;
                case 'f': pc.put(PieceType.DRAGON, n); break;
                case 'r': pc.put(PieceType.ROOK, n); break;
                case 'n': pc.put(PieceType.KNIGHT, n); break;
                case 'e': pc.put(PieceType.ELEPHANT, n); break;
                case 'w': pc.put(PieceType.PRINCESS, n); break;
                case 'd': pc.put(PieceType.DRUNKED_PAWN, n); break;
            }
        }

        List<List<Piece>> boardAsPieces = new ArrayList<>();

        for(String piece : board.trim().split("\n")){

            List<Piece> row = new ArrayList<>();

            String[] chars = piece.split(" ");

            for(String c: chars) {

                switch(c.charAt(0)) {
                    case 'K' : row.add(new King(PlayerType.BLACK)); break;
                    case 'k' : row.add(new King(PlayerType.WHITE)); break;
                    case 'Q' : row.add(new Queen(PlayerType.BLACK)); break;
                    case 'q' : row.add(new Queen(PlayerType.WHITE)); break;
                    case 'A' : row.add(new Amazon(PlayerType.BLACK)); break;
                    case 'a' : row.add(new Amazon(PlayerType.WHITE)); break;
                    case 'F' : row.add(new Dragon(PlayerType.BLACK)); break;
                    case 'f' : row.add(new Dragon(PlayerType.WHITE)); break;
                    case 'D' : row.add(new DrunkenPawn(PlayerType.BLACK)); break;
                    case 'd' : row.add(new DrunkenPawn(PlayerType.WHITE)); break;
                    case 'B' : row.add(new Bishop(PlayerType.BLACK)); break;
                    case 'b' : row.add(new Bishop(PlayerType.WHITE)); break;
                    case 'E' : row.add(new Elephant(PlayerType.BLACK)); break;
                    case 'e' : row.add(new Elephant(PlayerType.WHITE)); break;
                    case 'N' : row.add(new Knight(PlayerType.BLACK)); break;
                    case 'n' : row.add(new Knight(PlayerType.WHITE)); break;
                    case 'P' : row.add(new Pawn(PlayerType.BLACK)); break;
                    case 'p' : row.add(new Pawn(PlayerType.WHITE)); break;
                    case 'W' : row.add(new Princess(PlayerType.BLACK)); break;
                    case 'w' : row.add(new Princess(PlayerType.WHITE)); break;
                    case 'R' : row.add(new Rook(PlayerType.BLACK)); break;
                    case 'r' : row.add(new Rook(PlayerType.WHITE)); break;
                    default : row.add(new Space()); break;
                }
            }

            boardAsPieces.add(row);
        }

        String[] statusItems = status.trim().split(":");

        PlayerType pt;
        if(statusItems[0].equals("w")) {
           pt = PlayerType.WHITE;
        } else {
            pt = PlayerType.BLACK;
        }

        String costling = statusItems[1];
        CostlingStatus blackStatus = new CostlingStatus();

        if(costling.charAt(0) == '+') {
            blackStatus.setHasQueenCostling(true);
        } else {
            blackStatus.setHasQueenCostling(false);
        }

        if(costling.charAt(1) == '+') {
            blackStatus.setHasKingCostling(true);
        } else {
            blackStatus.setHasKingCostling(false);
        }

        CostlingStatus whiteStatus = new CostlingStatus();

        if(costling.charAt(2) == '+') {
            whiteStatus.setHasQueenCostling(true);
        } else {
            whiteStatus.setHasQueenCostling(false);
        }

        if(costling.charAt(3) == '+') {
            whiteStatus.setHasKingCostling(true);
        } else {
            whiteStatus.setHasKingCostling(false);
        }

        return new Board(pt, boardAsPieces, pc, blackStatus, whiteStatus, Integer.parseInt(statusItems[2]), Integer.parseInt(statusItems[3]));

    }


}
