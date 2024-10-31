package com.eun0.schedulerdevelop.controller;

import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
import com.eun0.schedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("")
    public ResponseEntity<ScheduleResponse> createSchedule(
            @RequestBody @Valid ScheduleRequest requestDto,
            @RequestParam Long userId) {
        ScheduleResponse responseDto = scheduleService.createSchedule(requestDto, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> readSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.readSchedule(scheduleId));
    }

    @GetMapping("")
    public ResponseEntity<Page<SchedulePagingResponse>> readSchedulesAsPageSize(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.readSchedulesAsPageSize(page, size));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid ScheduleRequest requestDto,
            @RequestParam Long userId
    ) {
        return ResponseEntity
                .status(HttpStatus.RESET_CONTENT)
                .body(scheduleService.updateSchedule(scheduleId, requestDto, userId));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam Long userId
    ) {
        scheduleService.deleteSchedule(scheduleId, userId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
