//package hello.hellospring;
//
//import hello.hellospring.repository.MemberRepository;
//import hello.hellospring.repository.MemoryMemberRepository;
//import hello.hellospring.repository.MySQLMemberRepository;
//import hello.hellospring.service.MemberService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////자바 코드로 직접 스프링 빈 등록
//@Configuration //spring이 뜰 때 configuration 읽고
//public class SpringConfig {
//    @Bean //스프링빈에 등록. 스프링빈에 등록된 memberRepository를 MemberService에 넣어줌
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//    //스프링빈에 등록
//    @Bean
//    public MySQLMemberRepository memberRepository() {
//        return new MySQLMemberRepository();
//    }
//}

package hello.hellospring;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
// return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}