package gui;

import controller.GameController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import logic.card.Card;
import logic.cell.Cell;
import logic.player.Player;

public class PlayerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int TEXT_AREA_ROWS = 30;
    private final int TEXT_AREA_COLS = 70;
    private final int PNL_ACTION_ROWS = 3;
    private final int PNL_ACTION_COLS = 3;
    private final JButton btnBuyHouse;
    private final JButton btnDrawCard;
    private final JButton btnEndTurn;
    private final JButton btnGetOutOfJail;
    private final JButton btnPurchaseProperty;
    private final JButton btnRollDice;
    private final JButton btnTrade;
    private final JLabel moneyLabel;
    private final JLabel nameLabel;
    private final Player player;
    private final JTextPane propertyText;
    private final GameController gameController;

    public PlayerPanel(Player player) {
        this.player = player;
        gameController = GameController.INSTANCE;

        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        nameLabel = new JLabel();
        moneyLabel = new JLabel();
        propertyText = new JTextPane();
        propertyText.setSize(TEXT_AREA_ROWS, TEXT_AREA_COLS);
        Font font = new Font("Serif", Font.ITALIC, 20);
        setJTextPaneFont(propertyText, font, Color.RED);
        propertyText.setEnabled(false);
        setUpPanels();
        initPlayerPanelButtons();
        addActionListeners();
    }
    
    private void setJTextPaneFont(JTextPane jtp, Font font, Color color) {
        MutableAttributeSet attrs = jtp.getInputAttributes();
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
        StyleConstants.setForeground(attrs, color);
        StyledDocument doc = jtp.getStyledDocument();
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }
    
    public void displayInfo() {
        setPlayerLabels();
        StringBuilder buf = new StringBuilder();
        Cell[] cells = player.getProperty().getAllProperties();
        for (Cell cell : cells) {
            buf.append(cell)
                    .append("\n");
        }
        propertyText.setText(buf.toString());
    }

    public void setBuyHouseEnabled(boolean enabled) {
        btnBuyHouse.setEnabled(enabled);
    }

    public void setDrawCardEnabled(boolean enabled) {
        btnDrawCard.setEnabled(enabled);
    }

    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurn.setEnabled(enabled);
    }

    public void setGetOutOfJailEnabled(boolean enabled) {
        btnGetOutOfJail.setEnabled(enabled);
    }

    public void setPurchasePropertyEnabled(boolean enabled) {
        btnPurchaseProperty.setEnabled(enabled);
    }

    public void setRollDiceEnabled(boolean enabled) {
        btnRollDice.setEnabled(enabled);
    }

    public void setTradeEnabled(boolean enabled) {
        btnTrade.setEnabled(enabled);
    }
    
    public boolean isBuyHouseButtonEnabled() {
        return btnBuyHouse.isEnabled();
    }

    public boolean isDrawCardButtonEnabled() {
        return btnDrawCard.isEnabled();
    }

    public boolean isEndTurnButtonEnabled() {
        return btnEndTurn.isEnabled();
    }

    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJail.isEnabled();
    }

    public boolean isPurchasePropertyButtonEnabled() {
        return btnPurchaseProperty.isEnabled();
    }

    public boolean isRollDiceButtonEnabled() {
        return btnRollDice.isEnabled();
    }

    public boolean isTradeButtonEnabled() {
        return btnTrade.isEnabled();
    }
    
    private void setPlayerLabels() {
        Color color = new Color(player.getPlayerColor().getRed(), player.getPlayerColor().getGreen(),
                player.getPlayerColor().getBlue(), 200);

        nameLabel.setText(player.getName());
        moneyLabel.setText("$ " + player.getMoney());
        nameLabel.setForeground(color);
        moneyLabel.setForeground(color);
    }

    private void setUpPanels() {
        JPanel actionPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel panelProperties = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(namePanel, BorderLayout.NORTH);
        infoPanel.add(panelProperties, BorderLayout.CENTER);

        panelProperties.setLayout(new OverlayLayout(panelProperties));

        namePanel.add(nameLabel);
        namePanel.add(moneyLabel);
        panelProperties.add(propertyText);

        initPanelAction(actionPanel);
        infoPanel.validate();
        namePanel.validate();
        panelProperties.validate();
        addElementsToJPanel(infoPanel, actionPanel);
    }

    private void addElementsToJPanel(JPanel infoPanel, JPanel actionPanel) {
        super.validate();
        super.setLayout(new BorderLayout());
        super.add(infoPanel, BorderLayout.CENTER);
        super.add(actionPanel, BorderLayout.SOUTH);
        super.setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    private void initPanelAction(JPanel actionPanel) {
        actionPanel.setLayout(new GridLayout(PNL_ACTION_ROWS, PNL_ACTION_COLS));
        actionPanel.add(btnBuyHouse);
        actionPanel.add(btnRollDice);
        actionPanel.add(btnPurchaseProperty);
        actionPanel.add(btnGetOutOfJail);
        actionPanel.add(btnEndTurn);
        actionPanel.add(btnDrawCard);
        actionPanel.add(btnTrade);
        actionPanel.validate();
    }

    private void initPlayerPanelButtons() {
        btnRollDice.setEnabled(false);
        btnPurchaseProperty.setEnabled(false);
        btnEndTurn.setEnabled(false);
        btnBuyHouse.setEnabled(false);
        btnGetOutOfJail.setEnabled(false);
        btnDrawCard.setEnabled(false);
        btnTrade.setEnabled(false);
    }

    private void addActionListeners() {
        btnRollDice.addActionListener((ActionEvent e) -> {
            gameController.getGUIController().btnRollDiceClicked();
        });

        btnEndTurn.addActionListener((ActionEvent e) -> {
            gameController.getGUIController().btnEndTurnClicked();
        });

        btnPurchaseProperty.addActionListener((ActionEvent e) -> {
            gameController.getGUIController().btnPurchasePropertyClicked();
        });

        btnBuyHouse.addActionListener((ActionEvent e) -> {
            gameController.getGUIController().btnBuyHouseClicked();
        });

        btnGetOutOfJail.addActionListener((ActionEvent e) -> {
            gameController.getGUIController().btnGetOutOfJailClicked();
        });

        btnDrawCard.addActionListener((ActionEvent e) -> {
            Card card = gameController.getGUIController().btnDrawCardClicked();
            JOptionPane
                    .showMessageDialog(PlayerPanel.this, card.getLabel());
            displayInfo();
        });

        btnTrade.addActionListener((ActionEvent e) -> {
            gameController.getGUIController().btnTradeClicked();
        });
    }
}
