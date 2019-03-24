package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whu.cs.bean.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
    public Member findByUrl(String address);
    public boolean existsByUrl(String address);
    public List<Member> findByStatus(int status);

    Member findByUsername(String userName);
}
