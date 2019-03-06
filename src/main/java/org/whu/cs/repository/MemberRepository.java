package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whu.cs.bean.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
    public Member findByAddress(String address);
    public boolean existsByAddress(String address);
}
