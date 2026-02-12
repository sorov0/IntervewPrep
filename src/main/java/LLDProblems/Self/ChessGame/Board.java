package LLDProblems.Self.ChessGame;

import java.util.ArrayList;
import java.util.List;

enum PieceType {
    KING,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN
}

enum PieceColor {
    WHITE,
    BLACK
}

enum MoveType {
    NORMAL,
    CAPTURE,
    CASTLING,
    EN_PASSANT,
    PROMOTION
}

enum GameStatus {
    IN_PROGRESS,
    CHECK,
    CHECKMATE,
    STALEMATE,
    DRAW,
    WHITE_WINS,
    BLACK_WINS
}

enum PlayerTurn {
    WHITE,
    BLACK
}


class Piece {

    private final int id;
    private final PieceType type;
    private final PieceColor color;
    private boolean isAlive;

    public Piece(int id, PieceType type, PieceColor color) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.isAlive = true;
    }

    public int getId() {
        return id;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}


class Cell {

    private final int row;
    private final int col;
    private Piece piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}



class Board {

    private final int SIZE = 8;
    private final Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return null;
        }
        return cells[row][col];
    }

    public int getSize() {
        return SIZE;
    }
}

class Player {

    private final String name;
    private final PieceColor color;

    public Player(String name, PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }
}

class Move {

    private final Piece piece;
    private final Cell from;
    private final Cell to;
    private final Piece killedPiece;
    private final MoveType moveType;

    public Move(Piece piece, Cell from, Cell to, Piece killedPiece, MoveType moveType) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.killedPiece = killedPiece;
        this.moveType = moveType;
    }

    public Piece getPiece() {
        return piece;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    public Piece getKilledPiece() {
        return killedPiece;
    }

    public MoveType getMoveType() {
        return moveType;
    }
}


class Game {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;

    private PlayerTurn currentTurn;
    private GameStatus status;

    private final List<Move> moveHistory;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentTurn = PlayerTurn.WHITE;
        this.status = GameStatus.IN_PROGRESS;
        this.moveHistory = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public PlayerTurn getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PlayerTurn currentTurn) {
        this.currentTurn = currentTurn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }
}

interface MoveStrategy {

    boolean isValidMove(Board board, Move move);

    List<Cell> getPossibleMoves(Board board, Piece piece);
}


class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move) {

        Piece pawn = move.getPiece();

        int fromRow = move.getFrom().getRow();
        int fromCol = move.getFrom().getCol();
        int toRow   = move.getTo().getRow();
        int toCol   = move.getTo().getCol();

        int direction = (pawn.getColor() == PieceColor.WHITE) ? -1 : 1;

        // ---------------------------
        // NORMAL FORWARD MOVE (1 step)
        // ---------------------------
        if (fromCol == toCol && move.getTo().getPiece() == null) {
            if (toRow == fromRow + direction) {
                return true;
            }
        }

        // ---------------------------
        // DOUBLE MOVE (only from starting row)
        // ---------------------------
        boolean isStartingRow =
                (pawn.getColor() == PieceColor.WHITE && fromRow == 6) ||
                        (pawn.getColor() == PieceColor.BLACK && fromRow == 1);

        if (isStartingRow && fromCol == toCol && move.getTo().getPiece() == null) {

            // Check if the square in between is empty
            int midRow = fromRow + direction;
            if (board.getCell(midRow, fromCol).getPiece() != null) {
                return false;
            }

            if (toRow == fromRow + 2 * direction) {
                return true;
            }
        }

        // ---------------------------
        // DIAGONAL CAPTURE
        // ---------------------------
        if (Math.abs(fromCol - toCol) == 1
                && move.getTo().getPiece() != null
                && toRow == fromRow + direction) {

            return true;
        }

        // No other pawn move allowed (LLD-level)
        return false;
    }

    @Override
    public List<Cell> getPossibleMoves(Board board, Piece pawn) {

        List<Cell> moves = new ArrayList<>();

        Cell current = findPiece(board, pawn);
        if (current == null) return moves;

        int row = current.getRow();
        int col = current.getCol();

        int direction = (pawn.getColor() == PieceColor.WHITE) ? -1 : 1;

        // Forward 1
        addIfEmpty(board, moves, row + direction, col);

        // Forward 2 (only starting row)
        boolean isStartingRow =
                (pawn.getColor() == PieceColor.WHITE && row == 6) ||
                        (pawn.getColor() == PieceColor.BLACK && row == 1);

        if (isStartingRow && board.getCell(row + direction, col).getPiece() == null) {
            addIfEmpty(board, moves, row + 2 * direction, col);
        }

        // Diagonal left capture
        addIfEnemy(board, moves, row + direction, col - 1, pawn.getColor());

        // Diagonal right capture
        addIfEnemy(board, moves, row + direction, col + 1, pawn.getColor());

        return moves;
    }

    // ----------- Helper Methods ------------

    private void addIfEmpty(Board board, List<Cell> moves, int r, int c) {
        Cell cell = board.getCell(r, c);
        if (cell != null && cell.getPiece() == null) {
            moves.add(cell);
        }
    }

    private void addIfEnemy(Board board, List<Cell> moves, int r, int c, PieceColor pawnColor) {
        Cell cell = board.getCell(r, c);

        if (cell != null && cell.getPiece() != null
                && cell.getPiece().getColor() != pawnColor) {
            moves.add(cell);
        }
    }

    private Cell findPiece(Board board, Piece piece) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).getPiece() == piece) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }
}


class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move) {
        int fromRow = move.getFrom().getRow();
        int fromCol = move.getFrom().getCol();
        int toRow = move.getTo().getRow();
        int toCol = move.getTo().getCol();

        // Rook moves straight (same row or same column)
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }

        // Check clear path
        return isPathClear(board, move.getFrom(), move.getTo());
    }

    @Override
    public List<Cell> getPossibleMoves(Board board, Piece piece) {
        List<Cell> moves = new ArrayList<>();
        Cell current = findPiece(board, piece);

        if (current == null) return moves;

        int r = current.getRow();
        int c = current.getCol();

        // horizontal + vertical moves
        sweep(board, r, c, 1, 0, moves);   // down
        sweep(board, r, c, -1, 0, moves);  // up
        sweep(board, r, c, 0, 1, moves);   // right
        sweep(board, r, c, 0, -1, moves);  // left

        return moves;
    }

    private boolean isPathClear(Board board, Cell from, Cell to) {
        int rowDir = Integer.compare(to.getRow(), from.getRow());
        int colDir = Integer.compare(to.getCol(), from.getCol());

        int r = from.getRow() + rowDir;
        int c = from.getCol() + colDir;

        while (r != to.getRow() || c != to.getCol()) {
            if (board.getCell(r, c).getPiece() != null) return false;
            r += rowDir;
            c += colDir;
        }

        return true;
    }

    private void sweep(Board board, int r, int c, int rd, int cd, List<Cell> moves) {
        int row = r + rd;
        int col = c + cd;

        while (board.getCell(row, col) != null) {
            moves.add(board.getCell(row, col));
            if (board.getCell(row, col).getPiece() != null) break;
            row += rd;
            col += cd;
        }
    }

    private Cell findPiece(Board board, Piece piece) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).getPiece() == piece) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }
}

class KnightMoveStrategy implements MoveStrategy {

    private final int[][] dir = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
    };

    @Override
    public boolean isValidMove(Board board, Move move) {
        int r = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
        int c = Math.abs(move.getFrom().getCol() - move.getTo().getCol());
        return (r == 2 && c == 1) || (r == 1 && c == 2);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board, Piece piece) {
        List<Cell> moves = new ArrayList<>();
        Cell current = findPiece(board, piece);
        if (current == null) return moves;

        int r = current.getRow();
        int c = current.getCol();

        for (int[] d : dir) {
            Cell next = board.getCell(r + d[0], c + d[1]);
            if (next != null) moves.add(next);
        }

        return moves;
    }

    private Cell findPiece(Board board, Piece piece) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).getPiece() == piece) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }
}

class BishopMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move) {
        int r = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
        int c = Math.abs(move.getFrom().getCol() - move.getTo().getCol());

        if (r != c) return false;

        return isPathClear(board, move.getFrom(), move.getTo());
    }

    @Override
    public List<Cell> getPossibleMoves(Board board, Piece piece) {
        List<Cell> moves = new ArrayList<>();
        Cell current = findPiece(board, piece);
        if (current == null) return moves;

        int r = current.getRow();
        int c = current.getCol();

        sweep(board, r, c, 1, 1, moves);
        sweep(board, r, c, 1, -1, moves);
        sweep(board, r, c, -1, 1, moves);
        sweep(board, r, c, -1, -1, moves);

        return moves;
    }

    private boolean isPathClear(Board board, Cell from, Cell to) {
        int rDir = Integer.compare(to.getRow(), from.getRow());
        int cDir = Integer.compare(to.getCol(), from.getCol());

        int r = from.getRow() + rDir;
        int c = from.getCol() + cDir;

        while (r != to.getRow() || c != to.getCol()) {
            if (board.getCell(r, c).getPiece() != null) return false;
            r += rDir;
            c += cDir;
        }

        return true;
    }

    private void sweep(Board board, int r, int c, int rd, int cd, List<Cell> moves) {
        int row = r + rd, col = c + cd;
        while (board.getCell(row, col) != null) {
            moves.add(board.getCell(row, col));
            if (board.getCell(row, col).getPiece() != null) break;
            row += rd;
            col += cd;
        }
    }

    private Cell findPiece(Board board, Piece piece) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).getPiece() == piece) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }
}

class QueenMoveStrategy implements MoveStrategy {

    private final RookMoveStrategy rook = new RookMoveStrategy();
    private final BishopMoveStrategy bishop = new BishopMoveStrategy();

    @Override
    public boolean isValidMove(Board board, Move move) {
        return rook.isValidMove(board, move) ||
                bishop.isValidMove(board, move);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board, Piece piece) {
        List<Cell> moves = new ArrayList<>();
        moves.addAll(rook.getPossibleMoves(board, piece));
        moves.addAll(bishop.getPossibleMoves(board, piece));
        return moves;
    }
}

class KingMoveStrategy implements MoveStrategy {

    @Override
    public boolean isValidMove(Board board, Move move) {
        int r = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
        int c = Math.abs(move.getFrom().getCol() - move.getTo().getCol());
        return r <= 1 && c <= 1; // simple king logic
    }

    @Override
    public List<Cell> getPossibleMoves(Board board, Piece piece) {
        List<Cell> moves = new ArrayList<>();
        Cell current = findPiece(board, piece);
        if (current == null) return moves;

        int r = current.getRow();
        int c = current.getCol();

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                Cell next = board.getCell(r + dr, c + dc);
                if (next != null) moves.add(next);
            }
        }

        return moves;
    }

    private Cell findPiece(Board board, Piece piece) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).getPiece() == piece) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }
}


class MoveStrategyFactory {

    public static MoveStrategy getStrategy(PieceType type) {
        switch (type) {
            case PAWN:
                return new PawnMoveStrategy();
            case ROOK:
                return new RookMoveStrategy();
            case KNIGHT:
                return new KnightMoveStrategy();
            case BISHOP:
                return new BishopMoveStrategy();
            case QUEEN:
                return new QueenMoveStrategy();
            case KING:
                return new KingMoveStrategy();
            default:
                throw new IllegalArgumentException("Unknown piece type: " + type);
        }
    }
}

class BoardInitializer {

    private static int idCounter = 1;

    public static void initialize(Board board) {
        setupPieces(board, PieceColor.WHITE);
        setupPieces(board, PieceColor.BLACK);
    }

    private static void setupPieces(Board board, PieceColor color) {

        int pawnRow = (color == PieceColor.WHITE) ? 6 : 1;
        int majorRow = (color == PieceColor.WHITE) ? 7 : 0;

        // Pawns
        for (int col = 0; col < 8; col++) {
            board.getCell(pawnRow, col).setPiece(
                    new Piece(idCounter++, PieceType.PAWN, color)
            );
        }

        // Rooks
        board.getCell(majorRow, 0).setPiece(new Piece(idCounter++, PieceType.ROOK, color));
        board.getCell(majorRow, 7).setPiece(new Piece(idCounter++, PieceType.ROOK, color));

        // Knights
        board.getCell(majorRow, 1).setPiece(new Piece(idCounter++, PieceType.KNIGHT, color));
        board.getCell(majorRow, 6).setPiece(new Piece(idCounter++, PieceType.KNIGHT, color));

        // Bishops
        board.getCell(majorRow, 2).setPiece(new Piece(idCounter++, PieceType.BISHOP, color));
        board.getCell(majorRow, 5).setPiece(new Piece(idCounter++, PieceType.BISHOP, color));

        // Queen
        board.getCell(majorRow, 3).setPiece(new Piece(idCounter++, PieceType.QUEEN, color));

        // King
        board.getCell(majorRow, 4).setPiece(new Piece(idCounter++, PieceType.KING, color));
    }
}


class MoveValidatorService {

    private final CheckService checkService;

    public MoveValidatorService(CheckService checkService) {
        this.checkService = checkService;
    }

    public boolean isValidMove(Game game, Move move) {

        Piece piece = move.getPiece();

        // 1. You cannot move opponent's piece
        if (piece.getColor() != getColorFromTurn(game.getCurrentTurn())) {
            System.out.println("Invalid: Not your piece.");
            return false;
        }

        // 2. Piece specific movement (Strategy)
        MoveStrategy strategy = MoveStrategyFactory.getStrategy(piece.getType());
        if (!strategy.isValidMove(game.getBoard(), move)) {
            System.out.println("Invalid: Move pattern not allowed.");
            return false;
        }

        // 3. Cannot capture your own piece
        if (move.getTo().getPiece() != null &&
                move.getTo().getPiece().getColor() == piece.getColor()) {
            System.out.println("Invalid: Cannot capture own piece.");
            return false;
        }

        // 4. Simplified: do not allow move putting your own king in check
        if (checkService.doesMoveExposeKing(game, move)) {
            System.out.println("Invalid: Move exposes your king.");
            return false;
        }

        return true;
    }

    private PieceColor getColorFromTurn(PlayerTurn turn) {
        return (turn == PlayerTurn.WHITE) ? PieceColor.WHITE : PieceColor.BLACK;
    }
}


class MoveExecutionService {

    public void executeMove(Game game, Move move) {

        Cell from = move.getFrom();
        Cell to = move.getTo();

        Piece movingPiece = from.getPiece();
        Piece killed = to.getPiece();

        // If a piece was killed, mark it dead
        if (killed != null) {
            killed.setAlive(false);
        }

        // Move the piece
        to.setPiece(movingPiece);
        from.setPiece(null);

        // Add move to history
        game.getMoveHistory().add(move);
    }
}


class CheckService {

    public boolean isKingInCheck(Game game, PieceColor kingColor) {
        Board board = game.getBoard();

        Cell kingCell = findKingCell(board, kingColor);
        if (kingCell == null) return false;

        // Check all opponent pieces
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                Piece piece = board.getCell(i, j).getPiece();
                if (piece == null) continue;
                if (piece.getColor() == kingColor) continue;

                MoveStrategy strategy = MoveStrategyFactory.getStrategy(piece.getType());
                List<Cell> moves = strategy.getPossibleMoves(board, piece);

                if (moves.contains(kingCell)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean doesMoveExposeKing(Game game, Move move) {
        // Temporarily execute move
        Cell from = move.getFrom();
        Cell to = move.getTo();
        Piece movedPiece = move.getPiece();
        Piece originalToPiece = to.getPiece();

        // simulate
        from.setPiece(null);
        to.setPiece(movedPiece);

        boolean check = isKingInCheck(game, movedPiece.getColor());

        // rollback
        from.setPiece(movedPiece);
        to.setPiece(originalToPiece);

        return check;
    }

    private Cell findKingCell(Board board, PieceColor color) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Piece p = board.getCell(i, j).getPiece();
                if (p != null && p.getType() == PieceType.KING && p.getColor() == color) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }
}


class CheckmateService {

    private final MoveValidatorService validator;
    private final MoveExecutionService executor;

    public CheckmateService(
            MoveValidatorService validator,
            MoveExecutionService executor) {
        this.validator = validator;
        this.executor = executor;
    }

    public boolean isCheckmate(Game game, PieceColor playerColor) {

        CheckService checkService = new CheckService();

        if (!checkService.isKingInCheck(game, playerColor)) {
            return false;
        }

        // Try all moves of all pieces
        Board board = game.getBoard();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                Piece piece = board.getCell(i, j).getPiece();
                if (piece == null || piece.getColor() != playerColor) continue;

                MoveStrategy strategy = MoveStrategyFactory.getStrategy(piece.getType());
                List<Cell> possibleMoves = strategy.getPossibleMoves(board, piece);

                for (Cell to : possibleMoves) {

                    Move testMove = new Move(piece,
                            board.getCell(i, j),
                            to,
                            to.getPiece(),
                            MoveType.NORMAL);

                    if (validator.isValidMove(game, testMove)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}


class PlayerTurnService {

    public void switchTurn(Game game) {
        game.setCurrentTurn(
                game.getCurrentTurn() == PlayerTurn.WHITE ?
                        PlayerTurn.BLACK :
                        PlayerTurn.WHITE
        );
    }
}

class UndoService {

    public void undoLastMove(Game game) {

        if (game.getMoveHistory().isEmpty()) {
            System.out.println("No moves to undo.");
            return;
        }

        Move last = game.getMoveHistory().remove(
                game.getMoveHistory().size() - 1);

        Cell from = last.getFrom();
        Cell to = last.getTo();

        // Move piece back
        from.setPiece(last.getPiece());
        to.setPiece(last.getKilledPiece());  // if null, it's fine

        // If killed piece existed, mark alive again
        if (last.getKilledPiece() != null) {
            last.getKilledPiece().setAlive(true);
        }
    }
}
