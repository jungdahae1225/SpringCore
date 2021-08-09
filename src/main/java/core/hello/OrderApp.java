package core.hello;

import core.hello.member.Grade;
import core.hello.member.Member;
import core.hello.member.MemberService;
import core.hello.order.Order;
import core.hello.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();//appConfig를 통해 memberService를 실행
//        OrderService orderService = appConfig.orderService();//appConfig를 통해 orderService를 실행

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class); //첫번쨰 인자는 가지고 올 메소드의 이름, 두번째 인자는 가지고 올 메소드의 메소드 타입
        OrderService orderService = applicationContext.getBean("orderService",OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
