package lotto.view;

import java.util.List;

public class OutputView {

    //티켓 개수 출력
    public void printNumOfTickets(int numOfTickets){
        System.out.println();
        System.out.println(numOfTickets+"개를 구매했습니다.");
    }

    //티켓 출력
    public void printTickets(String lottoTicketsStr){
        System.out.println(lottoTicketsStr);
    }

    //당첨 통계 출력
    public void printStatistics(){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    //일치개수 출력
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
