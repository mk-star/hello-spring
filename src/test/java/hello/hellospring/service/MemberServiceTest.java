package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MySQLMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MySQLMemberRepository memberRepository;
    @BeforeEach //test 실행 전에
    public void beforeEach() {
        memberRepository = new MySQLMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given 기반 데이터
        Member member = new Member();
        member.setName("spring");
        //when 조건
        Long saveId = memberService.join(member);
        //then 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); //이미 있는 데이터냐
    }
    @Test
    public void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //validate어쩌구에서 예외 발생
        memberService.join(member1);
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
    }

    @Test
    void findOne() {
    }
}