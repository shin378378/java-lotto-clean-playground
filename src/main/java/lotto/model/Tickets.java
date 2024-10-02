package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tickets {
    private List<Ticket> lottoTickets = new ArrayList<>();
    private List<String> lottoTicketsList = new ArrayList<>();
    private String lottoTicketsStr= new String();

    public List<Ticket> getLottoTickets() {
        return lottoTickets;
    }

    public List<String> getLottoTicketsList() {
        return lottoTicketsList;
    }

    public String getLottoTicketsStr() {
        return lottoTicketsStr;
    }

    //티켓들 생성
    public void createTickets(int NumOfTickets) {
        for (int i = 0; i < NumOfTickets; i++) {
            Ticket ticket = new Ticket();
            lottoTickets.add(ticket);
            lottoTicketsList = lottoTickets.stream()
                    .map(Ticket::getLottoTicketStr)
                    .collect(Collectors.toList());
            changeTicketsListToStr();
        }
    }

    //티켓들 스트링으로 변경
    public void changeTicketsListToStr(){
        lottoTicketsStr= lottoTickets.stream()
                .map(Ticket::getLottoTicketStr)
                .collect(Collectors.joining("\n"));
    }
}
