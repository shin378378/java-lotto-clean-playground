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

    //예외 - 수동로또의 크기가 6이 아닌 경우
    public void passivityListSizeException(){
        if (passivityList.size() != 6) {
            throw new IllegalArgumentException("리스트의 크기가 6이어야 합니다. 현재 크기: " + passivityList.size());
        }
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
        String[] chooseNumArr = passivityNum.split(",");
        for (String num : chooseNumArr) {
            passivityList.add(Integer.parseInt(num.trim()));
        }
        passivityListSizeException();
        sortNum(passivityList);
        return passivityList;
    }
}
