package lotto.model;

public class CalculateNumOfTicket {
    private int numOfTicket;
    private int ticketPrice=1000;
    private int purchasePrice;

    public int getNumOfTickets() {
        return numOfTicket;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    //티켓 개수 저장하기
    public void settingNumOfTickets(int purchasePrice) {
        this.purchasePrice=purchasePrice;
        this.numOfTicket = purchasePrice/ticketPrice;
    }
}
