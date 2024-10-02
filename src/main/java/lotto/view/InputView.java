package lotto.view;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    //구입금액 입력 요청
    public int requestPurchasePrice(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    //당첨번호 입력 요청
    public String requestSuccessNum(){
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        scanner.nextLine();
        return scanner.nextLine();
    }

    //보너스볼 입력 요청
    public Integer requestBonusBall(){
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
