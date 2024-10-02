package model;

import lotto.model.WinningTicket;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SuccessNumTest {
    List<Integer> successLotto = Arrays.asList(1, 2, 3, 4, 5, 6);
    int bonusBall = 8;

    @Test
    void 티켓일치숫자_개수_세기(){
        //Given
        WinningTicket successNum = new WinningTicket();
        List<Integer> lottoTicket1 = Arrays.asList(1, 3, 5, 6, 7, 8);
        List<Integer> lottoTicket2 = Arrays.asList(1, 4, 3, 2, 5, 7);
        final int BONUSBALL_KEY=7;
        SoftAssertions softly = new SoftAssertions();

        //When & Then
        softly.assertThat(4).isEqualTo(successNum.ticketMatchRate(lottoTicket1));
        softly.assertThat(BONUSBALL_KEY).isEqualTo(successNum.ticketMatchRate(lottoTicket2));
        softly.assertAll();
    }
}
