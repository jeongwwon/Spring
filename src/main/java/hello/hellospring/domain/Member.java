package hello.hellospring.domain;

import javax.persistence.*;

//JPA가 관리하는 엔티티

@Entity
public class Member {
    //id = pk 값, db가 알아서 생성해주는 값 IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
