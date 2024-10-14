package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNums {
    private List<Integer> randomNumbers;
    private List<Integer> passivityNumbers;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    //1-45번호 세팅
    private List<Integer> setting1to45() {
        List<Integer> availableNumbers = new ArrayList<>();
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
            availableNumbers.add(i);
        }
        return availableNumbers;
    }

    //랜덤번호 6개 고르기
    private void chooseRandomNum(List<Integer> availableNumbers) {
        Collections.shuffle(availableNumbers);
        randomNumbers = availableNumbers.subList(0, 6);
    }

    //예외 - 수동로또의 크기가 6이 아닌 경우
    private void passivityListSizeException() {
        if (passivityNumbers.size() != 6) {
            throw new IllegalArgumentException("리스트의 크기가 6이어야 합니다. 현재 크기: " + passivityNumbers.size());
        }
    }

    //오름차순으로 정렬하기
    private void sortNum(List<Integer> numList) {
        Collections.sort(numList);
    }

    //랜덤로또 생성하기
    public List<Integer> createRandomList() {
        randomNumbers = new ArrayList<>();
        List<Integer> availableNumbers = setting1to45();
        chooseRandomNum(availableNumbers);
        sortNum(randomNumbers);
        return randomNumbers;
    }

    //수동로또 생성하기
    public List<Integer> createPassivityList(String passivityNum) {
        passivityNumbers = new ArrayList<>();
        String[] chooseNumArr = passivityNum.split(",");
        for (String num : chooseNumArr) {
            passivityNumbers.add(Integer.parseInt(num.trim()));
        }
        passivityListSizeException();
        sortNum(passivityNumbers);
        return passivityNumbers;
    }
}
