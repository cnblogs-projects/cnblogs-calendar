package com.example.calendar.controller;

import com.example.calendar.common.CalendarDate;
import com.example.calendar.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author RWM
 * @date 2018/11/16
 */
@RestController
public class CalendarController {

    @GetMapping("/calendar")
    public List<CalendarDate<List<String>>> calendarDates(@RequestParam Integer year, @RequestParam Integer month) {
        Function<String, Optional<List<String>>> function = day -> {
            List<String> datas = new ArrayList<>();
            datas.add(day); // DB: datas = tableMapper.findByDay(day);
            return Optional.of(datas);
        };
        return DateUtils.calendar(year, month, function);
    }
}
