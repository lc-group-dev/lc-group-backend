package org.whu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whu.cs.bean.Upvote;
import org.whu.cs.service.UpvoteService;

import java.util.List;

@Controller
@RequestMapping(value = "/upvote")
public class UpvoteController {
    @Autowired
    private UpvoteService upvoteService;

//    @GetMapping(value = "/list")
//    @ResponseBody
//    public List<Upvote> getUpvoteListByFromAndDate(String from, String date) {
//        return upvoteService.getUpvoteListByFromAndDate(from, date);
//    }
//
//    @GetMapping(value = "/count")
//    @ResponseBody
//    public Integer getUpvoteCountByFromAndDate(String from, String date) {
//        return upvoteService.getUpvoteCountByFromAndDate(from, date);
//    }
}
