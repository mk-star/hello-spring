package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MySQLMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service //spring이 올라올 때 스프링 컨테이너에 memberService를 등록해줌
public class MemberService {
    private final MySQLMemberRepository memberRepository;
    //@Autowired //등록하면서 생성자 호출. Autowired하면 너는 memberRepository가 필요하구나. 하고 넣어줌
    public MemberService(MySQLMemberRepository memberRepository) { //생성자 주입
        this.memberRepository = memberRepository;
    }//외부에서 memberRepository 넣어줌->DI라고 함...

    /**회원가입
    */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); //ifPresent=member의 값이 있으면
    }

    //전체회원조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
