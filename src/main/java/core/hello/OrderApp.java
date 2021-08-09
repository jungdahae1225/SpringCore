package core.hello;

import core.hello.member.Grade;
import core.hello.member.Member;
import core.hello.member.MemberService;
import core.hello.order.Order;
import core.hello.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();//appConfig를 통해 memberService를 실행
        OrderService orderService = appConfig.orderService();//appConfig를 통해 orderService를 실행

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
