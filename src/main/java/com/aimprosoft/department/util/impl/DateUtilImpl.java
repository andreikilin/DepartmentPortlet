package com.aimprosoft.department.util.impl;

import com.aimprosoft.department.util.DateUtil;
import org.springframework.stereotype.Component;

import java.text.DateFormatSymbols;
import java.util.*;

@Component("DateUtil")
public class DateUtilImpl implements DateUtil {
    @Override
    public List<String> getDayList() {
        List<String> dayList = new ArrayList<>(31);
        for(Integer i = 1; i<= 31; i++) {
            dayList.add(i.toString());
        }
        return dayList;
    }

    @Override
    public Map<String, String> getMonthMap() {
        String[] months = new DateFormatSymbols().getMonths();
        Map<String,String> monthMap = new LinkedHashMap<>(12);
        for(int i = 0; i < 12; i++) {
            monthMap.put(String.valueOf(1+i), months[i]);
        }
        return monthMap;
    }

    @Override
    public List<String> getYearList() {
        List<String> yearList = new ArrayList<>();
        for(Integer i = 1930 ; i<= Calendar.getInstance().get(Calendar.YEAR); i++ ) {
            yearList.add(i.toString());
        }
        return yearList;
    }
}