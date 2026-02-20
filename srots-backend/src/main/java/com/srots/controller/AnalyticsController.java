package com.srots.controller;

import com.srots.dto.analytics.AnalyticsOverviewDTO;
import com.srots.dto.analytics.SystemAnalyticsDTO;
import com.srots.model.User;
import com.srots.repository.UserRepository;
import com.srots.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final UserRepository userRepository;

    /**
     * GET /api/v1/analytics/overview
     * - ADMIN / SROTS_DEV → system-wide data
     * - CPH / STAFF → college-scoped data (from their JWT subject)
     */
    @GetMapping("/overview")
    @PreAuthorize("hasAnyRole('ADMIN', 'SROTS_DEV', 'CPH', 'STAFF')")
    public AnalyticsOverviewDTO getAnalytics(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // CPH and STAFF get college-scoped analytics
        if (user.getRole() == User.Role.CPH || user.getRole() == User.Role.STAFF) {
            String collegeId = user.getCollegeIdOnly();
            if (collegeId != null) {
                return analyticsService.getOverviewByCollege(collegeId);
            }
        }
        // ADMIN and SROTS_DEV get system-wide analytics
        return analyticsService.getOverview();
    }

    @GetMapping("/system")
    @PreAuthorize("hasAnyRole('ADMIN', 'SROTS_DEV')")
    public SystemAnalyticsDTO getSystemAnalytics() {
        return analyticsService.getSystemAnalytics();
    }
}
