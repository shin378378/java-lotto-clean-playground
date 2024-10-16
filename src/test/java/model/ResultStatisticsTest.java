package model;

import lotto.model.LottoTicket;
import lotto.model.ResultStatistics;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultStatisticsTest {
    @Test
    void 당첨티켓과_티켓_일치율_계산() {
        //Given
        SoftAssertions softly = new SoftAssertions();
        ResultStatistics resultStatistics = new ResultStatistics();
        List<Integer> ticket1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> ticket2 = new ArrayList<>(Arrays.asList(2, 4, 1, 3, 5, 9));
        List<Integer> successList = new ArrayList<>(Arrays.asList(2, 3, 1, 4, 5, 7));
        int bonusBall=9;

        //When
        //int matchCount1 = resultStatistics.ticketMatchRate(ticket1,successList,7);
        //int matchCount2 = resultStatistics.ticketMatchRate(ticket2,successList,7);

        //Then
        //softly.assertThat(matchCount1).isEqualTo(5);
        //softly.assertThat(matchCount2).isEqualTo(7);
        softly.assertAll();
    }
}
