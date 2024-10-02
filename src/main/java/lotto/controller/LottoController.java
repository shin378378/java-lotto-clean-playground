package lotto.controller;

import lotto.model.CalculateNumOfTicket;
import lotto.model.SuccessNum;
import lotto.model.Ticket;
import lotto.model.Tickets;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    int numOfTickets;
    static InputView inputView = new InputView();
    static OutputView outputView = new OutputView();
    static CalculateNumOfTicket calculateNumOfTickets = new CalculateNumOfTicket();
    static SuccessNum successNum = new SuccessNum();
    static Tickets tickets = new Tickets();

    //티켓 구매
    public void purchaseTickets(){
        int purchasePrice=inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);

        numOfTickets= calculateNumOfTickets.getNumOfTickets();
        outputView.printNumOfTickets(numOfTickets);
    }

    //티켓 구매
    public void purchasePassivityPurchase(){
        int purchasePrice=inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
        int passivityPurchaseNum =inputView.requestPassivityPurchase();
        List <String> passivityNums = inputView.requestPassivityNum(passivityPurchaseNum);
//        for (String passivityNum : passivityNums) {
//            System.out.println(passivityNum);
//        }
    }

    //티켓들 생성
    public void settingTickets(){
        tickets.createTickets(numOfTickets);
        String lottoTicketsStr = tickets.getLottoTicketsStr();
        outputView.printTickets(lottoTicketsStr);
    }

    //당첨 번호 생성
    public void settingSuccessNum(){
        String successNumStr= inputView.requestSuccessNum();
        successNum.changeSuccessNumStrtoArr(successNumStr);
    }

    //보너스볼 생성
    public void settingBunusBall(){
        int bonusBall = inputView.requestBonusBall();
        successNum.decisionBonusBall(bonusBall);
    }

    //당첨 통계
    public void processStatistics(){
        /*
        successNum.settingStatistics(lottoTickets) 후에  successNum.resultStatisticsController()를 호출하고 있는데,
            통계를 다 계산해놓은 뒤에 다시 재초기화를 하는 과정이 문제! (구체적인 메서드는 successNum.statisticsInit();)
         */

        outputView.printStatistics();
        List<Ticket> lottoTickets = tickets.getLottoTickets();

        successNum.statisticsInit();

        // 정상동작
        successNum.settingStatistics(lottoTickets);

        successNum.resultStatisticsController();

        int purchasePrice= calculateNumOfTickets.getPurchasePrice();
        successNum.calculateProfit(purchasePrice);
        successNum.settingResultProfit();
    }

    //당첨 통계 출력
    public void printsettingStatistics(){
        List<String> resultStatistics = successNum.getResultStatistics();
        outputView.printMatchCount(resultStatistics);
        String resultProfit=successNum.settingResultProfit();
        outputView.printProfit(resultProfit);
    }


    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.purchasePassivityPurchase();
//        lottoController.purchaseTickets();
//        lottoController.settingTickets();
//        lottoController.settingSuccessNum();
//        lottoController.settingBunusBall();
//        lottoController.processStatistics();
//        lottoController.printsettingStatistics();
    }
}
