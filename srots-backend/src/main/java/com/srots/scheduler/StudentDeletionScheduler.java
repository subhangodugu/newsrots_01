package com.srots.scheduler;

import com.srots.model.Student;
import com.srots.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDeletionScheduler {

    private final StudentRepository studentRepository;

    @Scheduled(cron = "0 30 1 * * ?") // daily at 1:30 AM
    @Transactional
    public void deleteExpiredStudents() {
        // Logic: Delete students who are NOT premium active and whose expiry date was
        // more than 90 days ago
        // We need a method for this in repository or just fetch and filter

        // Using a simple fetch and filter for now, can be optimized later with a custom
        // query
        List<Student> students = studentRepository.findAll();
        LocalDate deletionThreshold = LocalDate.now().minusDays(90);

        for (Student s : students) {
            if (!s.isPremiumActive()
                    && s.getPremiumExpiryDate() != null
                    && s.getPremiumExpiryDate().isBefore(deletionThreshold)) {

                studentRepository.delete(s);
            }
        }
    }
}
