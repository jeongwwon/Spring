package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
//통합 테스트
@SpringBootTest
@Transactional
//DB데이터 테스트 끝나면 롤백
class MemberServiceIntegrationTest {
    //test코드는 생성자 필요 없이 Autowired 로 생성한다
    @Autowired  MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test

    void 회원가입() {
        //given
        Member member =new Member();
        member.setName("spring");

        //when
        Long saveId= memberService.join(member);

        //then
        Member findMember= memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());



    }

    @Test
    void findMembers() {
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when

       memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findOne() {
    }
}