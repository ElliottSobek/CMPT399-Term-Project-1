package gameboardvariants;

import logic.card.Card;
import logic.cell.CardCell;
import logic.card.CardType;
import logic.gameBoard.GameBoard;
import logic.cell.JailCell;
import logic.card.MovePlayerCard;
import logic.cell.PropertyCell;

public class GameBoardCCMovePlayer extends GameBoard {

    public GameBoardCCMovePlayer() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 50, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 50, "blue", 10, 100);
        CardCell cc1 = new CardCell(CardType.COMMUNITY, "Community Chest 1");
        JailCell jail = new JailCell();
        CardCell chance1 = new CardCell(CardType.CHANCE, "Chance 1");

        Card ccCard1 = new MovePlayerCard("Blue 1", CardType.COMMUNITY);
        Card ccCard2 = new MovePlayerCard("Blue 2", CardType.COMMUNITY);

        super.addCard(ccCard1);
        super.addCard(ccCard2);
        super.addCell(blue1);
        super.addCell(cc1);
        super.addCell(jail);
        super.addCell(blue2);
        super.addCell(chance1);

    }
}
