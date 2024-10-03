package lotto.view;

import java.util.List;

public class OutputView {
    //티켓 개수 출력
    public void printTickets(String lottoTicketsStr,int NumOfPassivityTickets, int NumOfRandomTickets){
        System.out.println("수동으로 "+NumOfPassivityTickets+"장, 자동으로 "+NumOfRandomTickets+"개를 구매했습니다.");
        System.out.println(lottoTicketsStr);
    }

    //당첨 통계 출력
    public void printStatistics(){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    //일치개수를 알려주는 문장 출력
    public void printMatchCount(List<String> resultStatistics){
        for (String resultStatistic : resultStatistics) {
            System.out.println(resultStatistic);
        }
    }

    //수익을 출력
    public void printProfit(String resultProfit){
        System.out.println(resultProfit);
    }
}
