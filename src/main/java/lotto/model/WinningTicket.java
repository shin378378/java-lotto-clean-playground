package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class WinningTicket {
    private int bonusBall;
    private List<Integer> successList = new ArrayList<>();

    public WinningTicket() {}

    public WinningTicket(List<Integer> successList) {
        this.successList = successList;
    }

    //보너스볼 숫자 정하기
    public void decisionBonusBall(Integer bonusBall){
        this.bonusBall=bonusBall;
    }

    //당첨번호 배열로 변환
    public void changeNumStrToArr(String successNumStr){
        String[] successNumArr = successNumStr.split(",");
        for(String num : successNumArr){
            successList.add(Integer.parseInt(num.trim()));
        }
    }

    public List<Integer> getSuccessList() {
        return successList;
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
