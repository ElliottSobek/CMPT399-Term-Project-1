package gameboardvariants;

import logic.card.Card;
import logic.cell.CardCell;
import logic.card.CardType;
import logic.gameBoard.GameBoard;
import logic.cell.JailCell;
import logic.card.MoneyCard;
import logic.cell.PropertyCell;

public class GameBoardCCGainMoney extends GameBoard {

    public GameBoardCCGainMoney() {
        super();
        PropertyCell blue1 = new PropertyCell("Blue 1", 50, "blue", 10, 100);
        PropertyCell blue2 = new PropertyCell("Blue 2", 50, "blue", 10, 100);
        CardCell cc1 = new CardCell(CardType.COMMUNITY, "Community Chest 1");
        JailCell jail = new JailCell();
        CardCell chance1 = new CardCell(CardType.CHANCE, "Chance 1");

        Card ccCard1 = new MoneyCard("Win $50", 50, CardType.COMMUNITY);
        Card chanceCard1 = new MoneyCard("Win $30", 30, CardType.CHANCE);

        super.addCard(ccCard1);
        super.addCard(chanceCard1);
        super.addCell(cc1);
        super.addCell(blue1);
        super.addCell(jail);
        super.addCell(blue2);
        super.addCell(chance1);

    }
}
