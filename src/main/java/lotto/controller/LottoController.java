package lotto.controller;

import lotto.model.CalculateNumOfTickets;
import lotto.model.SuccessNum;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    int numOfTickets;
    static InputView inputView = new InputView();
    static OutputView outputView = new OutputView();
    static CalculateNumOfTickets calculateNumOfTickets = new CalculateNumOfTickets();
    static SuccessNum successNum = new SuccessNum();
    static LottoTickets tickets = new LottoTickets();

    //구매금액 정하기
    public void settingPurchasePrice(){
        int purchasePrice=inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
        numOfTickets= calculateNumOfTickets.getNumOfTickets();
    }
    //티켓 구매
    public void purchaseTickets(){
        int purchasePrice=inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);

        int passivityPurchaseNum =inputView.requestPassivityPurchase();
        List <String> passivityNums = inputView.requestPassivityNum(passivityPurchaseNum);
        tickets.createTickets(numOfTickets,passivityNums);
    }

    //티켓들 생성
    public void settingTickets(){
        String lottoTicketsStr = tickets.getTicketsStr();
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
        outputView.printStatistics();
        List<LottoTicket> lottoTickets = tickets.getTickets();

        successNum.statisticsInit();
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

        lottoController.purchaseTickets();
        lottoController.settingTickets();
        //lottoController.settingSuccessNum();
        //lottoController.settingBunusBall();
        //lottoController.processStatistics();
        //lottoController.printsettingStatistics();
    }
}
