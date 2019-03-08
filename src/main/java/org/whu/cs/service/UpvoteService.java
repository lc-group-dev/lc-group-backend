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

    public List<Upvote> getUpvoteListByFromAndDate(String to, String date) {
        return upvoteRepository.findByToMemberIdAndDate(to, date);
    }

    public Integer getUpvoteCountByFromAndDate(String to, String date) {
        return upvoteRepository.countByToMemberIdAndDate(to, date);
    }

}
