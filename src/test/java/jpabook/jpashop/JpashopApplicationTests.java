package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JpashopApplicationTests {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("shin");
        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test()
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1= new Member();
        member1.setName("shin");

        Member member2= new Member();
        member2.setName("shin");
        //when
        memberService.join(member1);

        //then
        Assertions.assertThrows(IllegalStateException.class,() -> memberService.join(member2));

    }


}

