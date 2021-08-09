package core.hello;

import core.hello.discount.FixDiscountPolicy;
import core.hello.member.MemberRepository;
import core.hello.member.MemberService;
import core.hello.member.MemberServiceImpl;
import core.hello.member.MemoryMemberRepository;
import core.hello.order.OrderService;
import core.hello.order.OrderServiceImpl;

/**
 * 구현체들 간의 연결 만을 다루는 구현체.
 * = Application의 실제 동작에 필요한 구현 객체를 생성한다.
 * = 생성한 객체 인스턴스를 생성자 주입 방법으로 연결한다.
 * = 객체를 생성하고 연결하는 역할을 온전히 AppConfig가 담당하게 된다.
 * = 어떤 구현 객체를 주입할지는 오로지 이곳에서 결정한다.
 *
 * 이렇게 구성하는 방법이 스프링의 핵심 개념 중 하나인, DI(Dependency Injection) ; 의존관계 주입이라는 개념이다.
 */

//리팩터링)) 각 메소드가 최대한 자신의 역할이 가시적으로 드러나게, 단일 책임의 원칙을 최대한 따른다.
public class AppConfig {

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository(); //각각의 메소드에서 만들어 줘도 되지만 최대한 가시적으로 코딩하는게 좋다.
    }

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //저장소 연결해주기
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //할인 적용 정책을 여기서 설정해주기
    }

    /**
     * 이제 할인 정책을 적용할 때 이 discountPolicy() 메서드만 변경해주면된다.
     **/
    private FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }

}