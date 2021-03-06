package logic.card;

import controller.GameController;
import logic.cell.Cell;
import logic.player.Player;

public class MovePlayerCard extends Card {

    private final String destination;
    private final CardType cardType;
    private final GameController gameController;

    public MovePlayerCard(String destination, CardType cardType) {
        this.destination = destination;
        this.cardType = cardType;
        gameController = GameController.INSTANCE;
    }

    private static boolean iscurrentCellGreaterThanNewCell(int currentCell, int newCell) {
        return currentCell > newCell;
    }

    private int calculateDiceValue(Cell currentPosition) {
        int newCell = gameController.getGameBoardController().getGameBoard().queryCellIndex(destination);
        int currentCell = gameController.getGameBoardController().getGameBoard().queryCellIndex(currentPosition.getName());
        if (iscurrentCellGreaterThanNewCell(currentCell, newCell)) {
            int boardCell = gameController.getGameBoardController().getGameBoard().getCellNumber();
            return (boardCell + (newCell - currentCell));
        }
        return newCell - currentCell;
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String getLabel() {
        return "Go to " + destination;
    }

    @Override
    public void applyAction() {
        Player currentPlayer = gameController.getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int diceValue = calculateDiceValue(currentPosition);
        gameController.movePlayer(currentPlayer, diceValue);
    }

}
