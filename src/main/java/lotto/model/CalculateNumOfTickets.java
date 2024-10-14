package lotto.model;

public class CalculateNumOfTickets {
    private static final int TICKET_PRICE = 1000;
    private static final int MINIMUM_PURCHASE_PRICE=0;

    private int numOfTickets;
    private int purchasePrice;

    public CalculateNumOfTickets(int numOfTickets, int purchasePrice) {
        this.numOfTickets = numOfTickets;
        this.purchasePrice = purchasePrice;
    }

    public static CalculateNumOfTickets createCalculateNumOfTickets() {
        return new CalculateNumOfTickets(0,0);
    }

    //티켓 개수 정하기
    public void settingNumOfTickets(int purchasePrice) {
        validatePriceGreaterThanMinimumPurchasePrice(purchasePrice);
        this.purchasePrice=purchasePrice;
        this.numOfTickets = purchasePrice/TICKET_PRICE;
    }

    //예외 - 구매금액이 양수가 아닌 경우
    private void validatePriceGreaterThanMinimumPurchasePrice(int purchasePrice){
        if (purchasePrice <= MINIMUM_PURCHASE_PRICE) {
            throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
        }
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }
}
