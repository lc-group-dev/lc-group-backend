package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.whu.cs.bean.Upvote;

import java.util.List;

public interface UpvoteRepository extends JpaRepository<Upvote, String> {

//    @Query("SELECT upvoteid, click_time, from_member_id, to_member_id, gmt_create, status FROM upvote where from_member_id = 1? and   DATE_FORMAT(gmt_create,'%Y%m%d')= 2?")
//    List<Upvote> findByToMemberIdAndgmt_createContains(String from_id, String date);
//
//    Integer countByToMemberIdAndgmt_createContains(String from_id, String date);

}
