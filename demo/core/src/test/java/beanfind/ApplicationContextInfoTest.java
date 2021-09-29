package beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.text.TabExpander;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String [] beanDefinitionName = ac.getBeanDefinitionNames();
        for (String s : beanDefinitionName) {
            Object bean = ac.getBean(s);
            System.out.println("s = " + s+"  been = "+bean);
            
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplcationAllBean(){
        String [] beanDefinitionName = ac.getBeanDefinitionNames();
        for (String s : beanDefinitionName) {
            BeanDefinition beanDefinition  = ac.getBeanDefinition(s);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(s);
                System.out.println("s = " + s+"  been = "+bean);

            }

        }
    }
}
