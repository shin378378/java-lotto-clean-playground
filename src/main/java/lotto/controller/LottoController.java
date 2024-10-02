package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    static InputView inputView = new InputView();
    static OutputView outputView = new OutputView();
    static CalculateNumOfTickets calculateNumOfTickets = new CalculateNumOfTickets();
    static LottoTickets lottoTickets = new LottoTickets();
    static WinningTicket winningTicket = new WinningTicket();
    static ResultStatistics resultStatistics = new ResultStatistics();

    private int numOfTickets;
    private int numOfPassivityTickets;
    private int numOfRandomTickets;

    //구매금액 정하기
    public void settingPurchasePrice() {
        int purchasePrice = inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
        numOfTickets = calculateNumOfTickets.getNumOfTickets();
    }

    //티켓 생성
    public void purchasePassivityTickets() {
        this.numOfPassivityTickets = inputView.requestPassivityPurchase();
        List<String> passivityNums = inputView.requestPassivityNum(numOfPassivityTickets);
        lottoTickets.createTickets(numOfTickets, passivityNums);
    }

    //티켓들 출력
    public void outputTickets() {
        String lottoTicketsStr = lottoTickets.getTicketsStr();
        this.numOfRandomTickets = numOfTickets - numOfPassivityTickets;
        outputView.printTickets(lottoTicketsStr, numOfPassivityTickets, numOfRandomTickets);
    }

    //당첨번호 생성
    public void settingSuccessNum() {
        String successNumStr = inputView.requestSuccessNum();
        winningTicket.changeNumStrToArr(successNumStr);
    }

    //보너스볼 생성
    public void settingBunusBall() {
        int bonusBall = inputView.requestBonusBall();
        winningTicket.decisionBonusBall(bonusBall);
    }

    //통계 전처리 작업
    public void statisticsPreprocessing(){
        resultStatistics.decisionPrice();
        resultStatistics.statisticsInit();
    }

    //당첨 통계
    public void processStatistics() {
        List<LottoTicket> tickets = lottoTickets.getTickets();
        List<Integer> successList = winningTicket.getSuccessList();
        int bonusBall = winningTicket.getBonusBall();
        resultStatistics.settingStatistics(tickets, successList ,bonusBall);
        int purchasePrice = calculateNumOfTickets.getPurchasePrice();
        resultStatistics.calculateProfit(purchasePrice);
        resultStatistics.settingResultProfit();
        resultStatistics.settingResultStatistics();
    }

    //당첨 통계 출력
    public void printsettingStatistics() {
        outputView.printStatistics();
        List <String> resultStatisticsList = resultStatistics.getResultStatisticsList();
        outputView.printMatchCount(resultStatisticsList);
        String resultProfit = resultStatistics.settingResultProfit();
        outputView.printProfit(resultProfit);
    }


    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.settingPurchasePrice();
        lottoController.purchasePassivityTickets();
        lottoController.outputTickets();
        lottoController.settingSuccessNum();
        lottoController.settingBunusBall();
        lottoController.statisticsPreprocessing();
        lottoController.processStatistics();
        lottoController.printsettingStatistics();
    }
}
