package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<LottoTicket> tickets = new ArrayList<>();
    private List<String> ticketsList = new ArrayList<>();
    private String ticketsStr = new String();

    public LottoTickets() {
    }

    public LottoTickets(List<LottoTicket> tickets, List<String> ticketsList, String ticketsStr) {
        this.tickets = tickets;
        this.ticketsList = ticketsList;
        this.ticketsStr = ticketsStr;
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public List<String> getTicketsList() {
        return ticketsList;
    }

    public String getTicketsStr() {
        return ticketsStr;
    }

    //티켓리스트 생성하기
    public void createTicketsList(LottoTicket lottoTicket) {
        tickets.add(lottoTicket);
        ticketsList = tickets.stream()
                .map(LottoTicket::getTicketStr)
                .collect(Collectors.toList());
    }

    //티켓들을 스트링으로 변경하기
    public void changeTicketsListToStr() {
        ticketsStr = tickets.stream()
                .map(LottoTicket::getTicketStr)
                .collect(Collectors.joining("\n"));
    }

    //티켓들 생성하기
    public void createTickets(int NumOfTickets, List<String> passivityNums) {
        for (int i = 0; i < passivityNums.size(); i++) {
            LottoTicket lottoTicket = new LottoTicket(passivityNums.get(i));
            createTicketsList(lottoTicket);
        }
        for (int i = passivityNums.size(); i < NumOfTickets; i++) {
            LottoTicket lottoTicket = new LottoTicket();
            createTicketsList(lottoTicket);
        }
        changeTicketsListToStr();
    }
}