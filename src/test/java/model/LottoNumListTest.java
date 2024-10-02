package model;

import lotto.model.LottoNumList;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoNumListTest {
    @Test
    void 랜덤로또번호_리스트가_잘_생성되는_지_확인(){
        //Given
        LottoNumList lottoNumList = new LottoNumList();

        //When
        List<Integer> choose6Num = lottoNumList.createLottoNumList();

        //Then
        Assertions.assertThat(choose6Num.size()).isEqualTo(6);
    }

    @Test
    void 수동로또번호_리스트가_잘_생성되는_지_확인(){
        //Given
        LottoNumList lottoNumList = new LottoNumList();
        String passivityNum = "3, 5, 8, 6, 7, 2";
        List<Integer> tmp = Arrays.asList(2, 3, 5, 6, 7, 8);

        SoftAssertions softly = new SoftAssertions();

        //When
        List<Integer> chooseList = lottoNumList.createPassivityLottoNumList(passivityNum);

        //Then
        softly.assertThat(chooseList.size()).isEqualTo(6);
        softly.assertThat(chooseList).isEqualTo(tmp);
    }
}
