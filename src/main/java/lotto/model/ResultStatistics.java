package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultStatistics {
    private static int BONUSBALL_KEY = 7;

    private Map<Integer, Integer> statistics = new HashMap<>();
    private List<String> resultStatisticsList = new ArrayList<>();
    private int[] MATCH_PRICE = new int[8];
    private double profitRate;

    public ResultStatistics() {
    }

    public ResultStatistics(int BONUSBALL_KEY) {
        this.BONUSBALL_KEY=BONUSBALL_KEY;
    }

    public List<String> getResultStatisticsList() {
        return resultStatisticsList;
    }

    //등수별 상금 정하기
    public void decisionPrice() {
        MATCH_PRICE[3] = 5000;
        MATCH_PRICE[4] = 50000;
        MATCH_PRICE[5] = 1500000;
        MATCH_PRICE[BONUSBALL_KEY] = 30000000;
        MATCH_PRICE[6] = 2000000000;
    }

    //통계 초기화
    public void statisticsInit() {
        statistics.put(3, 0);
        statistics.put(4, 0);
        statistics.put(5, 0);
        statistics.put(6, 0);
        statistics.put(BONUSBALL_KEY, 0);
    }

    //당첨티켓과 티켓 일치율 계산
    public Integer ticketMatchRate(List<Integer> ticket, List<Integer> successList, int bonusBall) {
        int matchCount = 0;
        for (Integer num : ticket) {
            if (successList.contains(num)) {
                matchCount++;
            }
        }
        if (ticket.contains(bonusBall) && matchCount == 5) {
            return BONUSBALL_KEY;
        }
        return matchCount;
    }

    //당첨 수 통계
    public void settingStatistics(List<LottoTicket> tickets, List<Integer> successList, int bonusBall) {
        for (LottoTicket ticket : tickets) {
            List<Integer> lottoTicket = ticket.getLottoTicket();
            int matchCount = ticketMatchRate(lottoTicket, successList, bonusBall);
            statistics.put(matchCount, statistics.getOrDefault(matchCount, 0) + 1);
        }
    }

    //수익률 계산
    public void calculateProfit(int purchasePrice) {
        int profit = 0;
        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            Integer matchCount = entry.getKey();
            Integer price = MATCH_PRICE[matchCount];
            Integer count = entry.getValue();
            profit += price * count;
        }
        profitRate = (double) profit / purchasePrice;
    }

    //당첨 통계 결과 스트링으로 만들기
    public void settingResultStr(int matchCount, int price, int count) {
        String result=null;
        if (matchCount == 3 || matchCount == 4 || matchCount == 5) {
            result = matchCount + "개 일치 (" + price + "원) - " + count + "개";
            resultStatisticsList.add(result);
        } else if (matchCount == BONUSBALL_KEY) {
            result = "5개 일치, 보너스 볼 일치(" + price + "원) - " + count + "개";
            resultStatisticsList.add(result);
        } else if (matchCount == 6) {
            result = matchCount + "개 일치 (" + price + "원) - " + count + "개";
            resultStatisticsList.add(result);
        }
    }

    //수익률 결과 스트링으로 만들기
    public String settingResultProfit() {
        String resultProfit = "총 수익률은 " + profitRate + "입니다.";
        if (profitRate > 1) resultProfit = resultProfit.concat("(기준이 1이기 때문에 결과적으로 이익이라는 의미임)");
        else if (profitRate < 1) resultProfit = resultProfit.concat("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        return resultProfit;
    }

    //당첨 통계 결과 리스트만들기
    public void settingResultStatistics() {
        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            int matchCount = entry.getKey();
            int price = MATCH_PRICE[matchCount];
            int count = entry.getValue();
            settingResultStr(matchCount, price, count);
        }
        String tmp = resultStatisticsList.get(3);
        resultStatisticsList.remove(3);
        resultStatisticsList.add(tmp);
    }
}
