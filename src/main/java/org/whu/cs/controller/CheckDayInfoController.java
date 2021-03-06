package org.whu.cs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.bean.GroupContantValue;
import org.whu.cs.service.CheckDayInfoService;
import org.whu.cs.util.AjaxJson;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Check day info controller.
 */
@Controller
@RequestMapping(value = "/api/checkDayInfo")
public class CheckDayInfoController {
    @Autowired
    private CheckDayInfoService checkDayInfoService;

    /**
     * Check day infos list.
     *
     * @param date the date
     * @return the list
     */
    @ApiOperation(value = "获取全部用户打卡信息", notes = "每日打卡排行榜")
    @ApiImplicitParam(paramType = "query", name = "date", value = "日期，格式yyyy-MM-dd", required = true, dataType = "String")
    @GetMapping(value = "/day")
    @ResponseBody
    public List<CheckDayInfo> checkDayInfos(@RequestParam String date) {
        return checkDayInfoService.checkDayInfos(date);
    }


    @ApiOperation(value = "获取小组打卡排行榜", notes = "solveQuestionRank解决问题总数的排行榜" +
            "continueCheckDayRank连续打卡天数排行榜" +
            "currentCheckDayNumRank打卡总数排行榜")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userName", value = "当前用户名", required = true, dataType = "String"),
            }
    )
    @PostMapping(value = "/checkRank")
    @ResponseBody
    public Map<Object, Object> checkRankByDate(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        String userName = "   ";
        return checkDayInfoService.totalRankList(userName);
    }

    /**
     * Check ratio map.
     *
     * @param date the date
     * @return the map
     * @throws ParseException the parse exception
     */
    @ApiOperation(value = "获取小组打卡率数据", notes = "小组打卡曲线：从起始日期到现在的所有打卡数据")
    @ApiImplicitParam(paramType = "query", name = "date", value = "当天日期，格式yyyy-MM-dd", required = true, dataType = "String")
    @GetMapping(value = "/checkRatioList")
    @ResponseBody
    public Map<String, Double> checkRatio(@RequestParam String date) throws ParseException {

        String start = GroupContantValue.getStart_of_date();
        Map<String, Double> result = new HashMap<>();
        List<String> days = checkDayInfoService.getBetweenDates(start, date);
        if (days != null && days.size() > 0) {
            for (String day : days) {
                result.put(day, checkDayInfoService.checkRatio(day));
            }
        }

        return result;
    }

    /**
     * Create check day info.
     *
     * @param checkDayInfo the check day info
     * @return the check day info
     */
    @ApiIgnore
    @PostMapping(value = "/create")
    @ResponseBody
    public CheckDayInfo create(@RequestBody CheckDayInfo checkDayInfo) {
        checkDayInfoService.create(checkDayInfo);
        return checkDayInfo;
    }

    @ApiOperation(value = "获取爬虫数据写入CheckDayInfo表", notes = "传入CheckDayInfo对象")
    @PostMapping(value = "/putDataToCheckDayInfo")
    @ResponseBody
    public AjaxJson putDataToCheckDayInfo(@RequestBody @ApiParam(value = "爬虫数据 打卡信息") CheckDayInfo checkDayInfo) {
        if (checkDayInfo == null) {
            return AjaxJson.error("爬虫数据为空，请重试");
        } else if (checkDayInfoService.saveToDB(checkDayInfo)) {
            return AjaxJson.success("写入打卡数据成功");
        } else {
            return AjaxJson.error("写入数据失败");
        }
    }

    /**
     * Today summary map.
     *
     * @param date the date
     * @return the map
     */
    @ApiOperation(value = "获取用户打卡统计信息", notes = "统计信息：时间、人数、打卡人数、打卡率")
    @ApiImplicitParam(paramType = "query", name = "date", value = "日期，格式yyyy-MM-dd", required = true, dataType = "String")
    @GetMapping(value = "/summary")
    @ResponseBody
    public Map<Object, Object> todaySummary(@RequestParam String date) {
        if (date == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
            date = df.format(new Date());
        }
        Map<Object, Object> summary = new HashMap<>();
        summary.put("date", date);
        Integer totalUserCount = checkDayInfoService.getTotalUserCount(date);
        Integer checkedCount = checkDayInfoService.getCheckedCount(date);
        double ratio = (double) checkedCount / totalUserCount;
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(1);//设置保留几位小数
        summary.put("totalUserCount", totalUserCount);
        summary.put("checkedCount", checkedCount);
        summary.put("checkRatio", format.format(ratio));
        return summary;
    }

}
