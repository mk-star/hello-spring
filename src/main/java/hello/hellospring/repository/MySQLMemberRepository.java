package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MySQLMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); //Member-이름, 나이 등 여러 정보 들어감
    //key-id, long 타입
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store.values();가 멤버임
    }
    public void clearStore() {
        store.clear();
    }
}
