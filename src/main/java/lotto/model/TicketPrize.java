package lotto.model;

import java.util.Map;

public enum TicketPrize {
    THREE_MATCH(3, 5000),
    FOUR_MATCH(4, 50000),
    FIVE_MATCH(5, 1500000),
    FIVE_MATCH_WITH_BONUS(7, 30000000),
    SIX_MATCH(6, 2000000000);

    private final int matchNum;
    private final int prize;

    TicketPrize(int matchNum, int prize) {
        this.matchNum = matchNum;
        this.prize= prize;
    }

    public int getPrize(int matchNum) {
        return prize;
    }

    public static int decisionPrice(int matchNum) {
        for (TicketPrize ticketPrize : TicketPrize.values()) {
            if (matchNum == ticketPrize.matchNum) {
                return ticketPrize.getPrize(matchNum);
            }
        }
        return 0;
    }

    public static int decisionPrice(Map<Integer, Boolean> matchCountAndBonusBall) {
        for (Integer matchCount : matchCountAndBonusBall.keySet()) {
            if (matchCount == 5 && matchCountAndBonusBall.get(matchCount)) {
                return FIVE_MATCH_WITH_BONUS.getPrize(matchCount);
            }
            for (TicketPrize ticketPrize : TicketPrize.values()) {
                if (matchCount== ticketPrize.matchNum) {
                    return ticketPrize.getPrize(matchCount);
                }
            }
        }
        return 0;
    }
}