package model;

import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsTest {

    @Test
    void 티켓리스트_생성_확인하기() {
        //Given
        List<LottoTicket> tickets = new ArrayList<>();
        List<String> ticketsList = new ArrayList<>();
        String ticketsStr = new String();
        LottoTickets lottoTickets = new LottoTickets(tickets, ticketsList, ticketsStr);
        LottoTicket lottoTicket = new LottoTicket();

        //When
        lottoTickets.createTicketsList(lottoTicket);
        ticketsList = lottoTickets.getTicketsList();

        //Then
        Assertions.assertThat(ticketsList.size()).isEqualTo(1);
    }
}
