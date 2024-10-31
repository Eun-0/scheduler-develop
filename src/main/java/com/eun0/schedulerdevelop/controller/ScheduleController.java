package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
import com.eun0.schedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("")
    public ScheduleResponse createSchedule(@RequestBody @Valid ScheduleRequest requestDto, @RequestParam Long userId) {
        return scheduleService.createSchedule(requestDto, userId);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponse readSchedule(@PathVariable Long scheduleId) {
        return scheduleService.readSchedule(scheduleId);
    }

    @GetMapping("")
    public Page<SchedulePagingResponse> readSchedulesAsPageSize(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return scheduleService.readSchedulesAsPageSize(page, size);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponse updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid ScheduleRequest requestDto,
            @RequestParam Long userId
    ) {
        return scheduleService.updateSchedule(scheduleId, requestDto, userId);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId, @RequestParam Long userId) {
        scheduleService.deleteSchedule(scheduleId, userId);
    }
}
