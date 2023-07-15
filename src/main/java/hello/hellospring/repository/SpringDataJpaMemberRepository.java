package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface를 받을때는 extends를 사용한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    //JPQL select m from Member m where m.name= ?
    @Override
    Optional<Member> findByName(String name);
}
