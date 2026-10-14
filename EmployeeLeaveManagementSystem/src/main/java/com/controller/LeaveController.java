
package com.controller;

import com.model.LeaveApplication;
import com.service.LeaveService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveApplication applyLeave(
            @Valid @RequestBody LeaveApplication application) {
        return leaveService.applyLeave(application);
    }

    @GetMapping("/{employeeId}")
    public LeaveApplication getLeaveByEmployeeId(
            @PathVariable int employeeId) {
        return leaveService.getLeaveByEmployeeId(employeeId);
    }

    @GetMapping
    public List<LeaveApplication> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @DeleteMapping("/{employeeId}")
    public String deleteLeave(@PathVariable int employeeId) {
        return leaveService.deleteLeave(employeeId);
    }
}
