package lotto.model;

import java.util.*;

public class ResultStatistics {
    private static int BONUSBALL_KEY = 7;

    private Map<Integer, Integer> statistics = new LinkedHashMap<>();
    private Map<Integer, Boolean> matchCountAndBonusBall = new HashMap<>();
    private List<String> resultStatisticsList = new ArrayList<>();
    private double profitRate;

    public ResultStatistics() {
    }

    public ResultStatistics(int BONUSBALL_KEY) {
        this.BONUSBALL_KEY=BONUSBALL_KEY;
    }

    public List<String> getResultStatisticsList() {
        return resultStatisticsList;
    }

    public void statisticsInit() {
        statistics.put(3, 0);
        statistics.put(4, 0);
        statistics.put(5, 0);
        statistics.put(BONUSBALL_KEY, 0);
        statistics.put(6, 0);
    }

    public void ticketMatchCount(List<Integer> ticket, List<Integer> successList, int bonusBall){
        int matchCount = 0;

        for (Integer num : ticket) {
            if (successList.contains(num)) {
                matchCount++;
            }
        }

        if(matchCount==5&&ticket.contains(bonusBall)){
            matchCountAndBonusBall.put(matchCount, true);
            statistics.put(BONUSBALL_KEY, statistics.get(BONUSBALL_KEY)+ 1);
        }
        else if(3<=matchCount && matchCount<=6){
            matchCountAndBonusBall.put(matchCount, false);
            statistics.put(matchCount, statistics.get(matchCount)+ 1);
        }
    }

    public void ticketMatchRate(List<LottoTicket> tickets, List<Integer> successList, int bonusBall) {
        for (LottoTicket ticket : tickets) {
            List<Integer> ticketNumbers = ticket.getLottoTicket();
            ticketMatchCount(ticketNumbers,successList,bonusBall);
        }
    }

    public void calculateProfit(int purchasePrice) {
        int profit = 0;
        for (Integer matchNum : statistics.keySet()) {
            Integer price = TicketPrize.decisionPrice(matchNum);
            Integer count = statistics.get(matchNum);
            profit += price * count;
        }
        profitRate = (double) profit / purchasePrice;
    }

    public String settingResultProfit() {
        String resultProfit = "총 수익률은 " + profitRate + "입니다.";
        if (profitRate > 1) resultProfit = resultProfit.concat("(기준이 1이기 때문에 결과적으로 이익이라는 의미임)");
        else if (profitRate < 1) resultProfit = resultProfit.concat("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        return resultProfit;
    }

    public void settingResultStatistics() {
        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            int matchNum = entry.getKey();
            int price = TicketPrize.decisionPrice(matchNum);
            int count = entry.getValue();
            settingResultStr(matchNum, price, count);
        }
    }

    public void settingResultStr(int matchNum, int price, int count) {
        String result=null;
        if (matchNum == 3 || matchNum == 4 || matchNum == 5) {
            result = matchNum + "개 일치 (" + price + "원) - " + count + "개";
            resultStatisticsList.add(result);
        } else if (matchNum == BONUSBALL_KEY) {
            result = "5개 일치, 보너스 볼 일치(" + price + "원) - " + count + "개";
            resultStatisticsList.add(result);
        } else if (matchNum == 6) {
            result = matchNum + "개 일치 (" + price + "원) - " + count + "개";
            resultStatisticsList.add(result);
        }
    }
}


