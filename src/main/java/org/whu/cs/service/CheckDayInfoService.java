package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.bean.GroupContantValue;
import org.whu.cs.repository.CheckDayInfoRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public CheckDayInfo create(CheckDayInfo checkDayInfo) {
        return checkDayInfoRepository.save(checkDayInfo);
    }

    public Integer getTotalUserCount(String date) {
        return checkDayInfoRepository.countByDate(date);
    }

    public Integer getCheckedCount(String check_date) {
        return checkDayInfoRepository.countByDateAndIsChecked(check_date, GroupContantValue.getChecked());
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
}
