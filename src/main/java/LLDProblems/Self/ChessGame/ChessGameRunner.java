package LLDProblems.Self.ChessGame;

class ChessGameRunner {

    private final Game game;

    private final MoveValidatorService validatorService;
    private final MoveExecutionService executionService;
    private final CheckService checkService;
    private final CheckmateService checkmateService;
    private final PlayerTurnService turnService;
    private final UndoService undoService;

    public ChessGameRunner(String whiteName, String blackName) {

        Player white = new Player(whiteName, PieceColor.WHITE);
        Player black = new Player(blackName, PieceColor.BLACK);

        this.game = new Game(white, black);

        // Initialize board with pieces
        BoardInitializer.initialize(game.getBoard());

        // Services wiring
        this.checkService = new CheckService();
        this.validatorService = new MoveValidatorService(checkService);
        this.executionService = new MoveExecutionService();
        this.turnService = new PlayerTurnService();
        this.undoService = new UndoService();
        this.checkmateService = new CheckmateService(validatorService, executionService);
    }

    public Game getGame() {
        return game;
    }

    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {

        Cell from = game.getBoard().getCell(fromRow, fromCol);
        Cell to = game.getBoard().getCell(toRow, toCol);

        if (from == null || to == null) {
            System.out.println("Invalid cell coordinates.");
            return false;
        }

        Piece moving = from.getPiece();

        if (moving == null) {
            System.out.println("No piece at source.");
            return false;
        }

        Move move = new Move(
                moving,
                from,
                to,
                to.getPiece(),
                (to.getPiece() == null) ? MoveType.NORMAL : MoveType.CAPTURE
        );

        // Validate
        if (!validatorService.isValidMove(game, move)) {
            System.out.println("Move invalid.");
            return false;
        }

        // Execute
        executionService.executeMove(game, move);

        // Check game status
        PieceColor opponentColor = (moving.getColor() == PieceColor.WHITE)
                ? PieceColor.BLACK : PieceColor.WHITE;

        if (checkService.isKingInCheck(game, opponentColor)) {
            game.setStatus(GameStatus.CHECK);
            System.out.println("CHECK!");
        } else {
            game.setStatus(GameStatus.IN_PROGRESS);
        }

        // Checkmate?
        if (checkmateService.isCheckmate(game, opponentColor)) {
            System.out.println("CHECKMATE!");
            game.setStatus(
                    opponentColor == PieceColor.WHITE
                            ? GameStatus.BLACK_WINS
                            : GameStatus.WHITE_WINS
            );
            return true;
        }

        // Switch turn
        turnService.switchTurn(game);
        System.out.println("Turn switched to " + game.getCurrentTurn());

        return true;
    }

    public void undoLastMove() {
        undoService.undoLastMove(game);
        turnService.switchTurn(game);
        System.out.println("Undo completed. Turn switched.");
    }

    public void printBoard() {
        Board board = game.getBoard();
        System.out.println("---------- BOARD ----------");

        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                Piece p = board.getCell(r, c).getPiece();

                if (p == null) {
                    System.out.print(". ");
                } else {
                    char color = (p.getColor() == PieceColor.WHITE) ? 'W' : 'B';
                    char type = p.getType().toString().charAt(0);
                    System.out.print("" + color + type + " ");
                }
            }
            System.out.println();
        }

        System.out.println("----------------------------");
    }

    public static void main(String[] args) {

        ChessGameRunner manager = new ChessGameRunner("Alice", "Bob");

        System.out.println("=== GAME STARTED ===");
        manager.printBoard();

        // Example moves

        // White Pawn moves (6,0) → (4,0)
        manager.makeMove(6, 0, 4, 0);
        manager.printBoard();

        // Black Pawn moves (1,0) → (3,0)
        manager.makeMove(1, 0, 3, 0);
        manager.printBoard();

        // White Knight moves (7,1) → (5,2)
        manager.makeMove(7, 1, 5, 2);
        manager.printBoard();

        // Undo last move
        manager.undoLastMove();
        manager.printBoard();
    }
}

