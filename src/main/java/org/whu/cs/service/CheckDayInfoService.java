package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.bean.GroupContantValue;
import org.whu.cs.repository.CheckDayInfoRepository;
import org.whu.cs.vo.RankVo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CheckDayInfoService {
    @Autowired
    private CheckDayInfoRepository checkDayInfoRepository;

    public List<CheckDayInfo> checkDayInfos(String date) {
        return checkDayInfoRepository.findByDate(date);

    }

    // 打卡率
    public Double checkRatio(String date) {
        Integer totalUserCount = getTotalUserCount(date);
        Integer checkedCount = getCheckedCount(date);
        if (totalUserCount == 0) {
            return 0.0;
        }
        return (double) checkedCount / totalUserCount;
    }

    public Integer getTotalUserCount(String date) {
        return checkDayInfoRepository.countByDate(date);
    }

    public Integer getCheckedCount(String check_date) {
        return checkDayInfoRepository.countByDateAndIsChecked(check_date, GroupContantValue.getChecked());
    }

    public CheckDayInfo create(CheckDayInfo checkDayInfo) {
        return checkDayInfoRepository.save(checkDayInfo);
    }

    // 获取两个日期之间的所有日期（yyyy-MM-dd）
    public List<String> getBetweenDates(String head, String tail) throws ParseException {

        DateFormat formatter;

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = formatter.parse(head);
        Date end = formatter.parse(tail);

        List<String> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);

        while (begin.getTime() <= end.getTime()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String yyyyMMdd = sdf.format(tempStart.getTime());
            result.add(yyyyMMdd);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    //判断最近的连续打卡天数
    public int judgeCheckDay(List<CheckDayInfo> checkDayInfoList) {
        int count = 0;
        if (checkDayInfoList == null) {
            return -1;
        }
        int total = checkDayInfoList.size();
//        中断打卡判定为0
        if (checkDayInfoList.get(total).getIsChecked() == 0 && checkDayInfoList.get(total - 1).getIsChecked() == 0) {
            return 0;
        }
        for (int i = checkDayInfoList.size() - 1; i < 0; i--) {
            if (checkDayInfoList.get(i).getIsChecked() == 1) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public boolean saveToDB(CheckDayInfo checkDayInfo) {
        if (checkDayInfo == null) {
            return false;
        }
        checkDayInfoRepository.save(checkDayInfo);
        return true;
    }

    public Map<Object, Object> totalRankList(String userName) {
        Map<Object, Object> ranMap = new HashMap<>();
        List<CheckDayInfo> checkDayInfoList = checkDayInfoRepository.findByUserName(userName);
        if (checkDayInfoList == null) {
            return ranMap;
        }
        List<RankVo> solvedQuestion = rankForSolvedQuestion(checkDayInfoList);
        List<RankVo> continueCheckDay = rankForContinueCheckDay(checkDayInfoList);
        List<RankVo> currentCheckDayNum = rankForCurrentCheckDayNum(checkDayInfoList);

        return ranMap;

    }

    private List<RankVo> rankForSolvedQuestion(List<CheckDayInfo> checkDayInfoList) {
        return null;
    }

    private List<RankVo> rankForContinueCheckDay(List<CheckDayInfo> checkDayInfoList) {
        return null;
    }

    private List<RankVo> rankForCurrentCheckDayNum(List<CheckDayInfo> checkDayInfoList) {
        return null;
    }
}
