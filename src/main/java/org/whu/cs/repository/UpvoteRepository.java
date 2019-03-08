package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whu.cs.bean.Upvote;

import java.util.List;

public interface UpvoteRepository extends JpaRepository<Upvote, String> {

    List<Upvote> findByToMemberIdAndDate(String toMemberId, String date);

    Integer countByToMemberIdAndDate(String toMemberId, String date);
}