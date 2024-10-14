package lotto.model;

import java.util.Map;

public enum TicketPrize {
    THREE_MATCH(3, 5000),         // 3개 맞춤
    FOUR_MATCH(4, 50000),         // 4개 맞춤
    FIVE_MATCH(5, 1500000),       // 5개 맞춤
    FIVE_MATCH_WITH_BONUS(7, 30000000),  // 5개 + 보너스볼 맞춤
    SIX_MATCH(6, 2000000000);     // 6개 맞춤

    private final int matchCount;
    private final int prize;

    TicketPrize(int matchCount, int prizeMoney) {
        this.matchCount = matchCount;
        this.prize= prizeMoney;
    }

    public int getPrize(int matchCount) {
        return prize;
    }

    // 주어진 matchCount에 해당하는 Prize를 반환하는 메서드
    public static int decisionPrice(Map<Integer, Boolean> matchCountAndBonusBall) {
        for (Integer matchCount : matchCountAndBonusBall.keySet()) {
            if (matchCount == 5 && matchCountAndBonusBall.get(matchCount)) {
                return FIVE_MATCH_WITH_BONUS.getPrize(matchCount);
            }
            for (TicketPrize ticketPrize : TicketPrize.values()) {
                if (ticketPrize.matchCount == matchCount) {
                    return ticketPrize.getPrize(matchCount);
                }
            }
        }
        return 0;
    }
}