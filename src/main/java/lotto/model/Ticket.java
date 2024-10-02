package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
    private List<Integer> lottoTicket;
    private String lottoTicketStr;
    private LottoNumList lottoNumList = new LottoNumList();

    public Ticket() {
        this.lottoTicket = lottoNumList.createLottoNumList();
        changeNumListToStr();
    }

    public List<Integer> getLottoTicket() {
        return lottoTicket;
    }

    public String getLottoTicketStr() {
        return lottoTicketStr;
    }

    //번호 리스트를 스트링으로 바꾸기
    public void changeNumListToStr(){
        this.lottoTicketStr = lottoTicket.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ","[","]"));
    }
}
