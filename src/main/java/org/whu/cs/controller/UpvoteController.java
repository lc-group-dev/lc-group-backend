package org.whu.cs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whu.cs.bean.Upvote;
import org.whu.cs.service.UpvoteService;

import java.util.List;

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
    @ApiOperation(value = "用户某日获赞详情", notes = "")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "toMemberId", value = "被赞用户id", required = true, dataType = "Long"),
                    @ApiImplicitParam(name = "date", value = "日期，格式yyyy-MM-dd", required = true, dataType = "String")
            }
    )
    @GetMapping(value = "/votedList")
    @ResponseBody
    public List<Upvote> getUpvoteListByFromAndDate(Long toMemberId, String date) {
        return upvoteService.getUpvoteListByFromAndDate(toMemberId, date);
    }

    /**
     * Gets upvote count by from and date.
     *
     * @param toMemberId the from
     * @param date       the date
     * @return the upvote count by from and date
     */
    @ApiOperation(value = "用户某日获赞总数", notes = "")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "toMemberId", value = "被赞用户id", required = true, dataType = "Long"),
                    @ApiImplicitParam(name = "date", value = "日期，格式yyyy-MM-dd", required = true, dataType = "String")
            }
    )
    @GetMapping(value = "/votedCount")
    @ResponseBody
    public Integer getUpvoteCountByFromAndDate(Long toMemberId, String date) {
        return upvoteService.getUpvoteCountByFromAndDate(toMemberId, date);
    }
}
