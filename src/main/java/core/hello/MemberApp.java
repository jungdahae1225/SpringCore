package core.hello;

import core.hello.member.Grade;
import core.hello.member.Member;
import core.hello.member.MemberService;
import core.hello.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); //appConfig를 통해 memberService를 실행
        
        /**
         * ApplicationContext는 스프링 컨테이너이다.
         * 이 스프링 컨테이너는 @Configuration이 붙은 구현체를 설정 정보를 구성하는 DI 컨테이너로 사용한다.
         * 여기서 @Bean이라고 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록 시켜 놓는다.
         * 이전에는 개발자가 필요한 객체를 Appconfig를 사용해서 직접 조회 했는데, 이제는 스프링 컨테이너에 등록된 빈을 찾으면 된다.
         * 스프링 빈은 applicationContext.getBean()메서드로 찾을 수 있다.
         **/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 이렇게 스프링 컨테이너를 가지고 오면 AppConfig 구현체도 스프링 빈에 등록 되어 의존성에 참여하게 된다
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class); //첫번쨰 인자는 가지고 올 메소드의 이름, 두번째 인자는 가지고 올 메소드의 메소드 타입
        

        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
