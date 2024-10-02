package lotto.model;

public class CalculateNumOfTickets {
    private int numOfTickets;
    private int purchasePrice;
    private final int TICKET_PRICE = 1000;

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    //티켓 개수 정하기
    public void settingNumOfTickets(int purchasePrice) {
        this.purchasePrice=purchasePrice;
        this.numOfTickets = purchasePrice/TICKET_PRICE;
    }
}
