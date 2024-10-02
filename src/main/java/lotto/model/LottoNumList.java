package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumList {
    private List<Integer> lottoNumInit = new ArrayList<>();
    private List<Integer> choose6Num;
    private List<Integer> chooseList;

    //1-45번호 세팅
    public void setting1to45() {
        for (int i = 1; i <= 45; i++) {
            lottoNumInit.add(i);
        }
    }

    //번호 랜덤으로 6개 고르기
    public void getChoose6Num() {
        Collections.shuffle(lottoNumInit);
        choose6Num = lottoNumInit.subList(0, 6);
    }

    //번호 오름차순으로 정렬하기
    public void sortNum(List<Integer> numList) {
        Collections.sort(numList);
    }

    //랜덤 로또 번호 생성
    public List<Integer> createLottoNumList() {
        choose6Num = new ArrayList<>();
        setting1to45();
        getChoose6Num();
        sortNum(choose6Num);
        return choose6Num;
    }

    //수동 로또 번호 생성
    public List<Integer> createPassivityLottoNumList(String passivityNum) {
        chooseList = new ArrayList<>();
        String[] chooseNumArr = passivityNum.split(", ");
        for (String num : chooseNumArr) {
            chooseList.add(Integer.parseInt(num));
        }
        sortNum(chooseList);
        return chooseList;
    }
}
