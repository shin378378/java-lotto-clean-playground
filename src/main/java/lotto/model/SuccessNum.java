package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessNum {
    private List<Integer> successList = new ArrayList<>();
    private Map<Integer, Integer> statistics = new HashMap<>();
    private List<String> resultStatistics = new ArrayList<>();
    private static final int BONUSBALL_KEY=7;
    private int[] MATCH_PRICE= new int[8];
    private int bonusBall;
    private double profitRate;

    public Map<Integer, Integer> getStatistics() {
        return statistics;
    }

    public void decisionBonusBall(Integer bonusBall){
        this.bonusBall=bonusBall;
    }

    public List<String> getResultStatistics() {
        return resultStatistics;
    }

    public void DecisionPrice(){
        MATCH_PRICE[3]=5000;
        MATCH_PRICE[4]=50000;
        MATCH_PRICE[5]=1500000;
        MATCH_PRICE[BONUSBALL_KEY]=30000000;
        MATCH_PRICE[6]=2000000000;
    }

    //통계 초기화
    void statisticsInit(){
        statistics.put(3,0);
        statistics.put(4,0);
        statistics.put(5,0);
        statistics.put(6,0);
        statistics.put(BONUSBALL_KEY,0);
    }

    //당첨 번호 배열로 변환
    public void changeSuccessNumStrtoArr(String successNumStr){
        String[] successNumArr = successNumStr.split(", ");
        for(String num : successNumArr){
            successList.add(Integer.parseInt(num));
        }
    }

    //티켓 일치율 계산
    public Integer ticketMatchRate(List<Integer> lottoTicket){
        int matchCount=0;
        for(Integer num : lottoTicket){
            if(successList.contains(num)){
                matchCount++;
            }
            if (successList.contains(bonusBall)) {
                ticketMatchBonusBall();
            }
        }
        return matchCount;
    }

    //보너스볼 일치시 보너스볼 value값 높여주기
    public void ticketMatchBonusBall(){
        statistics.put(BONUSBALL_KEY, statistics.get(BONUSBALL_KEY)+1);
    }

    //당첨 통계
    public void settingStatistics(List<Ticket> lottoTickets){
        for (Ticket ticket : lottoTickets) {
            List<Integer> lottoTicket=ticket.getLottoTicket();
            int matchCount= ticketMatchRate(lottoTicket);
            statistics.put(matchCount , statistics.getOrDefault(matchCount, 0) + 1);
        }
    }

    //당첨 통계 결과 스트링으로 만들기
    public void settingResultStatistics() {
        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            Integer matchCount = entry.getKey();
            Integer price = MATCH_PRICE[matchCount];
            Integer count = entry.getValue();
            String result;
            switch(matchCount){
                case 3,4,5,6:
                    result = matchCount + "개 일치 (" + price + "원) - " + count + "개";
                    resultStatistics.add(result);
                    break;
                case BONUSBALL_KEY:
                    result = "5개 일치, 보너스 볼 일치(" + price + "원) - " + count + "개";
                    resultStatistics.add(result);
                    break;
            }
        }
    }

    //결과 설정
    public void resultStatisticsController(){
        DecisionPrice();
        statisticsInit();
        settingResultStatistics();
    }

    //수익률 계산
    public void calculateProfit(int purchasePrice){
        int profit=0;
        for(Map.Entry<Integer,Integer> entry:statistics.entrySet()){
            Integer matchCount = entry.getKey();
            Integer price = MATCH_PRICE[matchCount];
            Integer count = entry.getValue();
            profit += price*count;
        }
        profitRate=(double)profit/purchasePrice;
    }

    //수익률 결과 스트링으로 만들기
    public String settingResultProfit(){
        String resultProfit= "총 수익률은 "+profitRate+"입니다.";
        return resultProfit;
    }
}
