package lotto.view;

import java.util.List;

public class OutputView {
    public void printTickets(String lottoTicketsStr,int NumOfPassivityTickets, int NumOfRandomTickets){
        System.out.println("수동으로 "+NumOfPassivityTickets+"장, 자동으로 "+NumOfRandomTickets+"개를 구매했습니다.");
        System.out.println(lottoTicketsStr);
    }

    public void printStatistics(){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printMatchCount(List<String> resultStatistics){
        for (String resultStatistic : resultStatistics) {
            System.out.println(resultStatistic);
        }
    }


    public void printProfit(String resultProfit){
        System.out.println(resultProfit);
    }
}
