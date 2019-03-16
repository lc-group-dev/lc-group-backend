package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.whu.cs.bean.CheckDayInfo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface CheckDayInfoRepository extends JpaRepository<CheckDayInfo, String> {

    List<CheckDayInfo> findByDate(String date);

    List<CheckDayInfo> findByDateAndIsChecked(String date, int isChecked);

    Integer countByDateAndIsChecked(String date, int isChecked);
    Integer countByDate(String date);

    List<CheckDayInfo> findByUsername(String userName);
}
