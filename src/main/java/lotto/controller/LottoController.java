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

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculateNumOfTickets = CalculateNumOfTickets.createCalculateNumOfTickets();
        this.lottoTickets = new LottoTickets();
        this.winningTicket = new WinningTicket();
        this.resultStatistics = new ResultStatistics();
    }

    public void settingPurchasePrice() {
        int purchasePrice = inputView.requestPurchasePrice();
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
    }

    public void createPassivityTickets() {
        int numOfTickets = calculateNumOfTickets.getNumOfTickets();
        int numOfPassivityTickets = inputView.requestNumOfPassivityTickets();
        List<String> passivityNums = inputView.requestPassivityNum(numOfPassivityTickets);
        String lottoTicketsStr = lottoTickets.createTickets(numOfTickets, passivityNums);
        int numOfRandomTickets = numOfTickets - numOfPassivityTickets;
        outputView.printTickets(lottoTicketsStr, numOfPassivityTickets, numOfRandomTickets);
    }

    public void settingSuccessNum() {
        String successNumStr = inputView.requestSuccessNum();
        winningTicket.changeNumStrToArr(successNumStr);
    }

    public void settingBunusBall() {
        int bonusBall = inputView.requestBonusBall();
        winningTicket.decisionBonusBall(bonusBall);
    }

    public void statisticsPreprocessing() {
        resultStatistics.statisticsInit();
    }

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

    public void printSettingStatistics() {
        outputView.printStatistics();
        List<String> resultStatisticsList = resultStatistics.getResultStatisticsList();
        outputView.printMatchCount(resultStatisticsList);
        String resultProfit = resultStatistics.settingResultProfit();
        outputView.printProfit(resultProfit);
    }
}
