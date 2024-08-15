package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderSeriveImpl;
import hello.core.order.OrderService;

// 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
// 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결)해준다.
// appCinfig는 공연기획자다
// 구체 클래스를 appconfig가 직접 선택하고 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
// 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderSeriveImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy(); // 사용 영역의 코드를 변경하지않고 구성 영역의 코드변경만으로 앱 설정 변경 가능
        return new RateDiscountPolicy();
    }
}
