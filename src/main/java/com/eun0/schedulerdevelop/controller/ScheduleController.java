package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.schedule.ScheduleCreateRequest;
import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleUpdateRequest;
import com.eun0.schedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // CREATE
    @PostMapping("")
    public ScheduleResponse createSchedule(@RequestBody @Valid ScheduleCreateRequest requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // READ
    @GetMapping("/{scheduleId}")
    public ScheduleResponse readScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.readScheduleById(scheduleId);
    }

    // READ AS PAGE SIZE
    @GetMapping("")
    public Page<SchedulePagingResponse> readSchedulesAsPageSize(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return scheduleService.readSchedulesAsPageSize(page, size);
    }

    // UPDATE
    @PutMapping("/{scheduleId}")
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleUpdateRequest requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto);
    }

    // DELETE
    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }
}
