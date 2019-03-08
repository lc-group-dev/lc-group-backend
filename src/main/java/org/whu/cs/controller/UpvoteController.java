package org.whu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.Upvote;
import org.whu.cs.service.UpvoteService;

import java.util.List;
import java.util.Map;

/**
 * The type Upvote controller.
 */
@Controller
@RequestMapping(value = "/api/upvote")
public class UpvoteController {
    @Autowired
    private UpvoteService upvoteService;


    /**
     * 用户某日被赞信息
     *
     * @param toMemberId the from
     * @param date       the date
     * @return the upvote list by from and date
     */
    @GetMapping(value = "/votedList")
    @ResponseBody
    public List<Upvote> getUpvoteListByFromAndDate(String toMemberId, String date) {
        return upvoteService.getUpvoteListByFromAndDate(toMemberId, date);
    }

    /**
     * Gets upvote count by from and date.
     *
     * @param toMemberId the from
     * @param date       the date
     * @return the upvote count by from and date
     */
    @GetMapping(value = "/votedCount")
    @ResponseBody
    public Integer getUpvoteCountByFromAndDate(String toMemberId, String date) {
        return upvoteService.getUpvoteCountByFromAndDate(toMemberId, date);
    }
}
