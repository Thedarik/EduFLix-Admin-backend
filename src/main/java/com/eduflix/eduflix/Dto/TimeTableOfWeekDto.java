package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableOfWeekDto {
    private Date classTime;
    private Classes classes;
}
