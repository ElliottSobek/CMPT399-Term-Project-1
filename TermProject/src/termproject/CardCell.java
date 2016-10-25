package termproject;

public class CardCell extends Cell {

    private final int type;

    public CardCell(int type, String name) {
        super.setName(name);
        this.type = type;
    }

    @Override
    public void playAction() {
    }

    public int getType() {
        return type;
    }
}
