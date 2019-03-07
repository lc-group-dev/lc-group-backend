package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.Upvote;
import org.whu.cs.repository.UpvoteRepository;

import java.util.List;

@Service
public class UpvoteService {
    @Autowired
    private UpvoteRepository upvoteRepository;

//    public List<Upvote> getUpvoteListByFromAndDate(String from, String date) {
//        return upvoteRepository.findByToMemberIdAndgmt_createContains(from, date);
//    }
//
//    public Integer getUpvoteCountByFromAndDate(String from, String date) {
//        return upvoteRepository.countByToMemberIdAndgmt_createContains(from, date);
//    }

}
