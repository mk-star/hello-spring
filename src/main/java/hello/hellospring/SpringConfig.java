package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MySQLMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//자바 코드로 직접 스프링 빈 등록
@Configuration //spring이 뜰 때 configuration 읽고
public class SpringConfig {
    @Bean //스프링빈에 등록. 스프링빈에 등록된 memberRepository를 MemberService에 넣어줌
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    //스프링빈에 등록
    @Bean
    public MySQLMemberRepository memberRepository() {
        return new MySQLMemberRepository();
    }
}
