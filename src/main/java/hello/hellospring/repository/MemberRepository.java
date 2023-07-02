package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장
    Optional<Member> findById(Long id); //optional은 null 반환할 때 쓰임
    Optional<Member> findByName(String name);
    List<Member> findAll(); //findAll 모든 회원 리스트 반환
}
