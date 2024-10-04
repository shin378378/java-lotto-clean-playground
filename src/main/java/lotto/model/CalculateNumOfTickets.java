package lotto.model;

public class CalculateNumOfTickets {
    private int numOfTickets;
    private int purchasePrice;
    private final int TICKET_PRICE = 1000;

    public CalculateNumOfTickets() {
    }

    public CalculateNumOfTickets(int numOfTickets, int purchasePrice) {
        this.numOfTickets = numOfTickets;
        this.purchasePrice = purchasePrice;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    //예외 - 구매금액이 양수가 아닌 경우
    public void purchasePriceException(int purchasePrice){
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
        }
    }

    //티켓 개수 정하기
    public void settingNumOfTickets(int purchasePrice) {
        purchasePriceException(purchasePrice);
        this.purchasePrice=purchasePrice;
        this.numOfTickets = purchasePrice/TICKET_PRICE;
    }
}
