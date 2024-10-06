package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 위치부터 // 지정하지 않으면, 1번 줄의 패키지부터 뒤짐
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // component scan에서 제외할 대상

)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository") // 수동 빈이 자동 빈보다 우선권을 가지고 bean에 등록됨
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
