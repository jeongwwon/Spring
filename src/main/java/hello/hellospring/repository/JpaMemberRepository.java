package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;
    //jpa를 짜려면 무조건 Transaction 이 있어야 한다.
    //jpa 는 entityManager 라는것으로 모든것이 동작
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    //jpa 가 insert query 만들어서 db에 넣고 setId 까지 해준다.
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //jpql -> table이 아닌 객체를 대상으로 query를 날림
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) { List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
        return result.stream().findAny();
    }
}