package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumList {
    private List<Integer> numInit = new ArrayList<>();
    private List<Integer> randomList;
    private List<Integer> passivityList;

    //1-45번호 세팅
    public void setting1to45() {
        for (int i = 1; i <= 45; i++) {
            numInit.add(i);
        }
    }

    //랜덤번호 6개 고르기
    public void chooseRandomNum() {
        Collections.shuffle(numInit);
        randomList = numInit.subList(0, 6);
    }

    //오름차순으로 정렬하기
    public void sortNum(List<Integer> numList) {
        Collections.sort(numList);
    }

    //랜덤로또 생성하기
    public List<Integer> createRandomList() {
        randomList = new ArrayList<>();
        setting1to45();
        chooseRandomNum();
        sortNum(randomList);
        return randomList;
    }

    //수동로또 생성하기
    public List<Integer> createPassivityList(String passivityNum) {
        passivityList = new ArrayList<>();
        String[] chooseNumArr = passivityNum.split(", ");
        for (String num : chooseNumArr) {
            passivityList.add(Integer.parseInt(num));
        }
        sortNum(passivityList);
        return passivityList;
    }
}
