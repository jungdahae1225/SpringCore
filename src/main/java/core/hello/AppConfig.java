package core.hello;

import core.hello.discount.FixDiscountPolicy;
import core.hello.member.MemberRepository;
import core.hello.member.MemberService;
import core.hello.member.MemberServiceImpl;
import core.hello.member.MemoryMemberRepository;
import core.hello.order.OrderService;
import core.hello.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
/**
 *AppConfig = DI 컨테이너 이다.
 **/

/***
 * 스프링 @Configuration의 기능 중 가장 중요한 기능은 "싱글톤을 유지"하도록 도와주는 것이다.
 * 즉, @Configuration를 달지 않아도 스프링 빈 등록 과정은 실행되나, 가장 첫번째 문제점은 싱글톤 유지가 안된다,,,,
 *
 * (예컨데, AppConfig의 자바 코드를 보면 분명히 각각 2번 new MemoryMemberRepository 호출해서 다른 인스턴스가 생성되어야 하는데
 * @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면
 * 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다. 덕분에 싱글톤이 보장되는 것이다.)
 * => 자세히는 강의 자료 81p~를 참고.
 ***/
@Configuration //설정 정보를 담는 컨테이너임을 알리는 annotation
public class AppConfig {

    @Bean //스프링 컨테이너에 등록; 등록 될 때 "메소드 이름"으로 등록된다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); //각각의 메소드에서 만들어 줘도 되지만 최대한 가시적으로 코딩하는게 좋다.
    }

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //저장소 연결해주기
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //할인 적용 정책을 여기서 설정해주기
    }

    /**
     * 이제 할인 정책을 적용할 때 이 discountPolicy() 메서드만 변경해주면된다.
     **/
    @Bean
    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }

}
