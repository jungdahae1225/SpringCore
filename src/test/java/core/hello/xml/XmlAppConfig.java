package core.hello.xml;

import core.hello.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppConfig {
    
    //AnnotationConfigApplicationContext 어노테이션 기반 스프링 컨테이너 대신 xml기반 스프링 컨테이너로 설정하는 방법
    //단, 요즘은 xml보다 그냥 어노테이션 기반 스프링 컨테이너를 많이 쓴다.
    @Test
    void xmlAppContext() {
        //appConfig.xml 파일을 참고하여 스프링 컨테이너를 구성한다.
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
