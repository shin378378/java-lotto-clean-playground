package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private CalculateNumOfTickets calculateNumOfTickets;
    private LottoTickets lottoTickets;
    private WinningTicket winningTicket;
    private ResultStatistics resultStatistics;

    private static int numOfPassivityTickets;
    private static int numOfRandomTickets;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculateNumOfTickets = CalculateNumOfTickets.createCalculateNumOfTickets();
        this.lottoTickets = new LottoTickets();
        this.winningTicket = new WinningTicket();
        this.resultStatistics = new ResultStatistics();
    }

    // 구매금액 정하기
    public void settingPurchasePrice() {
        int purchasePrice = inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
    }

    // 티켓 생성
    public void createPassivityTickets() {
        int numOfTickets = calculateNumOfTickets.getNumOfTickets();
        this.numOfPassivityTickets = inputView.requestNumOfPassivityTickets();
        List<String> passivityNums = inputView.requestPassivityNum(numOfPassivityTickets);
        lottoTickets.createTickets(numOfTickets, passivityNums);
    }

    // 티켓들 출력
    public void outputTickets() {
        int numOfTickets = calculateNumOfTickets.getNumOfTickets();
        String lottoTicketsStr = lottoTickets.getTicketsStr();
        this.numOfRandomTickets = numOfTickets - numOfPassivityTickets;
        outputView.printTickets(lottoTicketsStr, numOfPassivityTickets, numOfRandomTickets);
    }

    // 당첨번호 생성
    public void settingSuccessNum() {
        String successNumStr = inputView.requestSuccessNum();
        winningTicket.changeNumStrToArr(successNumStr);
    }

    // 보너스볼 생성
    public void settingBunusBall() {
        int bonusBall = inputView.requestBonusBall();
        winningTicket.decisionBonusBall(bonusBall);
    }

    // 통계 전처리 작업
    public void statisticsPreprocessing() {
        resultStatistics.statisticsInit();
    }

    // 당첨 통계
    public void processStatistics() {
        List<LottoTicket> tickets = lottoTickets.getTickets();
        List<Integer> successList = winningTicket.getSuccessList();
        int bonusBall = winningTicket.getBonusBall();
        resultStatistics.ticketMatchRate(tickets, successList, bonusBall);
        int purchasePrice = calculateNumOfTickets.getPurchasePrice();
        resultStatistics.calculateProfit(purchasePrice);
        resultStatistics.settingResultProfit();
        resultStatistics.settingResultStatistics();
    }

    // 당첨 통계 출력
    public void printSettingStatistics() {
        outputView.printStatistics();
        List<String> resultStatisticsList = resultStatistics.getResultStatisticsList();
        outputView.printMatchCount(resultStatisticsList);
        String resultProfit = resultStatistics.settingResultProfit();
        outputView.printProfit(resultProfit);
    }
}
