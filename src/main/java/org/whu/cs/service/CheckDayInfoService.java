package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.bean.GroupContantValue;
import org.whu.cs.repository.CheckDayInfoRepository;
import org.whu.cs.repository.MemberRepository;
import org.whu.cs.repository.UpvoteRepository;
import org.whu.cs.repository.WechatAppRepository;
import org.whu.cs.vo.RankVo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CheckDayInfoService {
    @Autowired
    private CheckDayInfoRepository checkDayInfoRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UpvoteRepository upvoteRepository;
    @Autowired
    private WechatAppRepository wechatAppRepository;

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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        String date = df.format(new Date());
        Map<Object, Object> ranMap = new HashMap<>();
        List<CheckDayInfo> checkDayInfoUser = checkDayInfoRepository.findByUsername(userName);
        List<CheckDayInfo> checkDayInfoList = checkDayInfoRepository.findAll();
        List<CheckDayInfo> todayCheckDayInfoList = checkDayInfoRepository.findByDate(date);
        if (checkDayInfoList == null || todayCheckDayInfoList == null) {
            return ranMap;
        }
        List<RankVo> rankVoList = new ArrayList<>();
//        填充rankvo
        fillRankVo(rankVoList, todayCheckDayInfoList);

        List<RankVo> solvedQuestion = rankForSolvedQuestion(rankVoList);
        List<RankVo> continueCheckDay = rankForContinueCheckDay(rankVoList);
        List<RankVo> currentCheckDayNum = rankForCurrentCheckDayNum(rankVoList);

        return ranMap;

    }

    private void fillRankVo(List<RankVo> rankVoList, List<CheckDayInfo> todayCheckDayInfoList) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
        String date = df.format(new Date());
        for (CheckDayInfo check : todayCheckDayInfoList) {
            RankVo rankVo = new RankVo();
            if (check.getUsername() != null) {
                rankVo.setUserName(check.getUsername());
                rankVo.setSolvedQuestion(check.getSolvedQuestion());
            }
        }
        for (RankVo rankVo : rankVoList) {
            rankVo.setCurrentCheckDayNum(checkDayInfoRepository.countCheckDayInfoByUsernameAndIsChecked(rankVo.getUserName(), 1));
            List<CheckDayInfo> checkDayInfoListUser = checkDayInfoRepository.findByUsername(rankVo.getUserName());
            if (checkDayInfoListUser == null) {
                continue;
            }
            rankVo.setContinueCheckDay(judgeCheckDay(checkDayInfoListUser));
            Long memberId = memberRepository.findByUsername(rankVo.getUserName()).getMemberId();
            if (memberId == null) {
                rankVo.setClickTime(0);
                rankVo.setAvatarUrl(null);
            }
            rankVo.setClickTime(upvoteRepository.countByToMemberIdAndDate(memberId, date));
            rankVo.setAvatarUrl(wechatAppRepository.findByMemberId(memberId).getAvatarUrl());
        }
    }

    //解决问题数
    private List<RankVo> rankForSolvedQuestion(List<RankVo> rankVoList) {
        if (rankVoList == null) {
            return null;
        }
        Collections.sort(rankVoList, new Comparator<RankVo>() {
            @Override
            public int compare(RankVo o1, RankVo o2) {
                return 0;
            }
        });
        return null;
    }

    //连续打卡天数
    private List<RankVo> rankForContinueCheckDay(List<RankVo> checkDayInfoList) {
        return null;
    }

    //打卡总天数
    private List<RankVo> rankForCurrentCheckDayNum(List<RankVo> checkDayInfoList) {
        return null;
    }
}
