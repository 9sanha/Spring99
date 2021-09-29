package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//application 직접 설정 및 구성
//실제 동작에 필요한 구현 객체를 생성
public class AppConfig {


    public MemberService memberService(){
        return new MemberServiceImpl((MemoryMemberRepository) memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),new FixDiscountPolicy());

    }

    public DiscountPolicy discountPolicy(){
        return  new RateDiscountPolicy();
    }
}
