package lotto.controller;

import lotto.model.CalculateNumOfTickets;
import lotto.model.WinningTicket;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    static InputView inputView = new InputView();
    static OutputView outputView = new OutputView();
    static CalculateNumOfTickets calculateNumOfTickets = new CalculateNumOfTickets();
    static LottoTickets tickets = new LottoTickets();
    static WinningTicket successNum = new WinningTicket();

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
        tickets.createTickets(numOfTickets, passivityNums);
    }

    //티켓들 출력
    public void outputTickets() {
        String lottoTicketsStr = tickets.getTicketsStr();
        this.numOfRandomTickets = numOfTickets - numOfPassivityTickets;
        outputView.printTickets(lottoTicketsStr, numOfPassivityTickets, numOfRandomTickets);
    }

    //당첨번호 생성
    public void settingSuccessNum() {
        String successNumStr = inputView.requestSuccessNum();
        successNum.changeNumStrToArr(successNumStr);
    }

    //보너스볼 생성
    public void settingBunusBall() {
        int bonusBall = inputView.requestBonusBall();
        successNum.decisionBonusBall(bonusBall);
    }

    //당첨 통계
    public void processStatistics() {
        outputView.printStatistics();
        List<LottoTicket> lottoTickets = tickets.getTickets();

        successNum.statisticsInit();
        successNum.settingStatistics(lottoTickets);
        successNum.resultStatisticsController();

        int purchasePrice = calculateNumOfTickets.getPurchasePrice();
        successNum.calculateProfit(purchasePrice);
        successNum.settingResultProfit();
    }

    //당첨 통계 출력
    public void printsettingStatistics() {
        List<String> resultStatistics = successNum.getResultStatistics();
        outputView.printMatchCount(resultStatistics);
        String resultProfit = successNum.settingResultProfit();
        outputView.printProfit(resultProfit);
    }


    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.settingPurchasePrice();
        lottoController.purchasePassivityTickets();
        lottoController.outputTickets();
        lottoController.settingSuccessNum();
        lottoController.settingBunusBall();
        //lottoController.processStatistics();
        //lottoController.printsettingStatistics();
    }
}
