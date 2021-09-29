package beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService=ac.getBean("memberService",MemberService.class);
        //검증 memberservice가 MemeberServiceImpl의 인스턴스면 성공
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService=ac.getBean(MemberService.class);
        //검증 memberservice가 MemeberServiceImpl의 인스턴스면 성공
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByType2(){ //스프링 컨테이너에 등록되어 있는 객체는 조회 가능
        MemberServiceImpl memberService=ac.getBean(MemberServiceImpl.class);
        //검증 memberservice가 MemeberServiceImpl의 인스턴스면 성공
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름을 조회 X")
    void findBeanByNameX(){ //스프링 컨테이너에 등록되어 있는 객체는 조회 가능
        //ac.getBean("xxxxx",MemberService.class);
        //이 코드 실행했을 때 에러가 나야 정상
        // MemberService xxxxx = ac.getBean("xxxxx",MemberService.class);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx",MemberService.class));

    }
}
