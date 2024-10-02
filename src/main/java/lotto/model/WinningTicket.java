package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningTicket {
    private List<Integer> successList = new ArrayList<>();
    private Map<Integer, Integer> statistics = new HashMap<>();
    private List<String> resultStatistics = new ArrayList<>();
    private static final int BONUSBALL_KEY=7;
    private int[] MATCH_PRICE= new int[8];
    private int bonusBall;
    private double profitRate;

    //당첨 번호 배열로 변환
    public void changeNumStrToArr(String successNumStr){
        String[] successNumArr = successNumStr.split(", ");
        for(String num : successNumArr){
            successList.add(Integer.parseInt(num));
        }
    }

    //보너스볼 숫자 정하기
    public void decisionBonusBall(Integer bonusBall){
        this.bonusBall=bonusBall;
    }

    public Map<Integer, Integer> getStatistics() {
        return statistics;
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
    public void statisticsInit(){
        statistics.put(3,0);
        statistics.put(4,0);
        statistics.put(5,0);
        statistics.put(6,0);
        statistics.put(BONUSBALL_KEY,0);
    }



    //티켓 일치율 계산
    public Integer ticketMatchRate(List<Integer> lottoTicket){
        int matchCount=0;
        for(Integer num : lottoTicket){
            if(successList.contains(num)){
                matchCount++;
            }
        }
        if (lottoTicket.contains(bonusBall) && matchCount==5) {
            return BONUSBALL_KEY;
        }
        return matchCount;
    }

    //당첨 통계
    public void settingStatistics(List<LottoTicket> lottoTickets){
        for (LottoTicket ticket : lottoTickets) {
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
            String result=null;
            if(matchCount==3 || matchCount==4 || matchCount==5){
                result = matchCount + "개 일치 (" + price + "원) - " + count + "개";
                resultStatistics.add(result);
            }
            else if(matchCount==BONUSBALL_KEY){
                result = "5개 일치, 보너스 볼 일치(" + price + "원) - " + count + "개";
                resultStatistics.add(result);
            }
            else if(matchCount==6){
                result = matchCount + "개 일치 (" + price + "원) - " + count + "개";
                resultStatistics.add(result);
            }
        }
        String tmp= resultStatistics.get(3);
        resultStatistics.remove(3);
        resultStatistics.add(tmp);
    }

    //결과 설정
    public void resultStatisticsController(){
        DecisionPrice();
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
        if(profitRate>1)resultProfit=resultProfit.concat("(기준이 1이기 때문에 결과적으로 이익이라는 의미임)");
        else if(profitRate<1)resultProfit=resultProfit.concat("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        return resultProfit;
    }
}
