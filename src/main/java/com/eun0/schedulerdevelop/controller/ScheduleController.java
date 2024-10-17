package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
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
    public ScheduleResponse createSchedule(@RequestBody @Valid ScheduleRequest requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // READ
    @GetMapping("/{id}")
    public ScheduleResponse readScheduleById(@PathVariable("id") Long id) {
        return scheduleService.readScheduleById(id);
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
    @PutMapping("/{id}")
    public ScheduleResponse updateSchedule(@PathVariable("id") Long id, @RequestBody @Valid ScheduleRequest requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
    }
}
