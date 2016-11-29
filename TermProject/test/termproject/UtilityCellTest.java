package termproject;

import Mocks.MockGUI;
import gameboardvariants.GameBoardUtility;
import logic.GameMaster;
import logic.cell.UtilityCell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class UtilityCellTest {

    private GameMaster gameMaster;

    @Before
    public void setUp() {
        gameMaster = GameMaster.INSTANCE;
        gameMaster.setGameBoard(new GameBoardUtility());
        gameMaster.setNumberOfPlayers(2);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
    }

    @Test
    public void testMonopoly() {
        int u1CellIndex = gameMaster.getGameBoard().queryCellIndex("Utility 1");
        gameMaster.movePlayer(0, u1CellIndex);
        gameMaster.getPlayer(0).getActions().purchase();
        int u2CellIndex = gameMaster.getGameBoard().queryCellIndex("Utility 2");
        gameMaster.movePlayer(0, u2CellIndex - u1CellIndex);
        gameMaster.getPlayer(0).getActions().purchase();
        assertFalse(gameMaster.getPlayer(0).canBuyHouse());
    }

    @Test
    public void testPlayerAction() {
        UtilityCell cell
                = (UtilityCell) gameMaster.getGameBoard().queryCell("Utility 1");
        int cellIndex = gameMaster.getGameBoard().queryCellIndex("Utility 1");
        gameMaster.movePlayer(0, cellIndex);
        gameMaster.getPlayer(0).getActions().purchase();
        gameMaster.switchTurn();
        gameMaster.movePlayer(1, cellIndex);
        cell.playAction();
        int diceRoll = gameMaster.getUtilDiceRoll();
        assertEquals(1500 - cell.getRent(diceRoll), gameMaster.getPlayer(1).getMoney());
        assertEquals(1350 + cell.getRent(diceRoll), gameMaster.getPlayer(0).getMoney());
    }

    @Test
    public void testPurchaseUtility() {
        assertEquals(0, gameMaster.getPlayer(0).getProperty().getNumberOfUtil());
        int cellIndex = gameMaster.getGameBoard().queryCellIndex("Utility 1");
        gameMaster.movePlayer(0, cellIndex);
        gameMaster.getPlayer(0).getActions().purchase();
        assertEquals(1350, gameMaster.getPlayer(0).getMoney());
        assertEquals(1, gameMaster.getPlayer(0).getProperty().getNumberOfUtil());
    }

    @Test
    public void testRent() {
        UtilityCell u1
                = (UtilityCell) gameMaster.getGameBoard().queryCell("Utility 1");
        int cellIndex1 = gameMaster.getGameBoard().queryCellIndex("Utility 1");
        gameMaster.movePlayer(0, cellIndex1);
        gameMaster.getPlayer(0).getActions().purchase();
        assertEquals(40, u1.getRent(10));

        UtilityCell u2
                = (UtilityCell) gameMaster.getGameBoard().queryCell("Utility 2");
        int cellIndex2 = gameMaster.getGameBoard().queryCellIndex("Utility 2");
        gameMaster.movePlayer(0, cellIndex2 - cellIndex1);
        gameMaster.getPlayer(0).getActions().purchase();
        assertEquals(100, u1.getRent(10));
        assertEquals(100, u2.getRent(10));
    }
}
