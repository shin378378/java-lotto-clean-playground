package lotto;

import lotto.controller.LottoController;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.settingPurchasePrice();
        lottoController.createPassivityTickets();
        lottoController.outputTickets();
        lottoController.settingSuccessNum();
        lottoController.settingBunusBall();
        lottoController.statisticsPreprocessing();
        lottoController.processStatistics();
        lottoController.printSettingStatistics();
    }
}