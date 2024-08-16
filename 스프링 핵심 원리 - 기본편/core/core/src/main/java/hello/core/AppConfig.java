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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; //@Configuration 어노테이션을 위한 import

// 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
// 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결)해준다.
// appCinfig는 공연기획자다
// 구체 클래스를 appconfig가 직접 선택하고 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
// 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.

@Configuration
// @스프링 컨테이너는 @Configuratoin이 붙은 appconfig 를 구성 정보롤 사용하고
// @Bean이 붙은 메소드르 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록
// applicationContext.getBean() 을 통해서 객체를 가져옴
// 기존에는 자바코드를 통해서 개발자가 직접 모든것을 했다면, 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고 스프링 컨테이너에서 스프링 빈을 찾아서 사용
// 코드가 약간 더 복잡해진 것 같은데, 스프링 컨테이너를 사용하면 어떤 장점이 있을까??
// ----------------------------------------------이거에 대한 해답은 앞으로의 강의를 들으면서,,

// 주의) 빈의 이름은 항상 다른 이름을 부여해야한다.
public class AppConfig {
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderSeriveImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy(); // 사용 영역의 코드를 변경하지않고 구성 영역의 코드변경만으로 앱 설정 변경 가능
        return new RateDiscountPolicy();
    }
}
