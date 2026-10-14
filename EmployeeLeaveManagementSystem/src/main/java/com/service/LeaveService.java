
package com.service;

import com.model.LeaveApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveService {

    private final List<LeaveApplication> leaveApplications =
            new ArrayList<>();

    public LeaveApplication applyLeave(LeaveApplication application) {

        boolean exists = leaveApplications.stream()
                .anyMatch(leave ->
                        leave.getEmployeeId() == application.getEmployeeId());

        if (exists) {
            throw new IllegalArgumentException(
                    "Leave application already exists for employee ID: "
                            + application.getEmployeeId());
        }

        leaveApplications.add(application);
        return application;
    }

    public LeaveApplication getLeaveByEmployeeId(int employeeId) {

        return leaveApplications.stream()
                .filter(leave -> leave.getEmployeeId() == employeeId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Leave application not found for employee ID: "
                                + employeeId));
    }

    public List<LeaveApplication> getAllLeaves() {
        return new ArrayList<>(leaveApplications);
    }

    public String deleteLeave(int employeeId) {

        LeaveApplication application =
                getLeaveByEmployeeId(employeeId);

        leaveApplications.remove(application);

        return "Leave application deleted successfully for employee ID: "
                + employeeId;
    }
}
