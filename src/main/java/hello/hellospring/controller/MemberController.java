package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //스프링 컨테이너라는 통에 memberController 객체를 생성해서 넣어주고 관리.
public class MemberController {
    private MemberService memberService; //생성자 주입. 매번 new로 하지 말고..
//    @Autowired private MemberService memberService; ->필드에 autowired=필드주입
//    @Autowired //객체 생성될 때 생성자 호출. 스프링이 컨테이너 안에 있는 memberService을 갖다다 연결시킴. membercontroller가 생성될 때 스프링 빈에 등록되어 있는 객체를 스프링이 넣어줌->DI
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }->생성자 호출 요즘
    @Autowired //setter 주입
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect: /";
    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
