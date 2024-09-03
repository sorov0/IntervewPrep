
# Objects:Think and define in the sequence given

1. Piece (x , O) - It should be extensible (X , O , # , ^)
2. Board (n * m)
3. Players ()

# Sequence to follow while creating objects/class:

1 PieceTypeEnum (X , O)
2 PlayingPieceClass (PieceTypeEnum , PlayingPieceClass Constructor)
3 PieceXClass Extends PlayingPieceClass
4 PieceOClass Extends PlatingPieceClass
5 BoardClass (int size ,PlayingPiece[][] 2-D Array)
6 PlayerClass (name , PlayingPiece)

# Main Class to implement all the business logic (Start of the application)
7 GameClass (List<PlayerClass> , BoardClass)