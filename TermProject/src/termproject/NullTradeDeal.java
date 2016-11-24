package termproject;

public class NullTradeDeal extends TradeDeal {

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public String getPropertyName() {
        return "";
    }

    @Override
    public int getPlayerIndex() {
        return 0;
    }

    @Override
    public String makeMessage() {
        return "";
    }

}