package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultStatistics {
    private static int BONUSBALL_KEY = 7;

    private Map<Integer, Integer> statistics = new HashMap<>();
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

    //통계 초기화
    public void statisticsInit() {
        statistics.put(3, 0);
        statistics.put(4, 0);
        statistics.put(5, 0);
        statistics.put(6, 0);
        statistics.put(BONUSBALL_KEY, 0);
    }

    //당첨티켓과 티켓 일치율 계산
    public void ticketMatchRate(List<LottoTicket> tickets, List<Integer> successList, int bonusBall) {
        for (LottoTicket ticket : tickets) {
            List<Integer> ticketNumbers = ticket.getLottoTicket();
            ticketMatchCount(ticketNumbers,successList,bonusBall);
        }
    }

    public void ticketMatchCount(List<Integer> ticket, List<Integer> successList, int bonusBall){
        int matchCount = 0;

        for (Integer num : ticket) {
            if (successList.contains(num)) {
                matchCount++;
            }
        }

        if (3<=matchCount && matchCount<=6) {
            if(!ticket.contains(bonusBall)){
                matchCountAndBonusBall.put(matchCount, false);
                statistics.put(matchCount, statistics.get(matchCount)+ 1);
            }
            else if(ticket.contains(bonusBall)){
                matchCountAndBonusBall.put(matchCount, true);
                int currentMatchCount= statistics.get(matchCount);
                statistics.put(matchCount, statistics.get(matchCount)+ 1);
            }
        }
    }

    //수익률 계산
    public void calculateProfit(int purchasePrice) {
        int profit = 0;
        for (Integer matchCount : matchCountAndBonusBall.keySet()) {
            Integer price = TicketPrize.decisionPrice(matchCountAndBonusBall);
            Integer count = statistics.get(matchCount);
            profit += price * count;
        }
        profitRate = (double) profit / purchasePrice;
    }

    //당첨 통계 결과 스트링으로 만들기
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
            int matchNum = entry.getKey();
            int price = TicketPrize.decisionPrice(matchNum);
            int count = entry.getValue();
            settingResultStr(matchNum, price, count);
        }
        String tmp = resultStatisticsList.get(3);
        resultStatisticsList.remove(3);
        resultStatisticsList.add(tmp);
    }
}


