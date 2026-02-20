package com.srots.service;

import com.srots.dto.PremiumResponseDTO;
import com.srots.model.Student;
import com.srots.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PremiumService {

    private final StudentRepository studentRepository;
    private final EmailService emailService;

    @Transactional
    public PremiumResponseDTO activatePremium(
            String studentId,
            com.srots.dto.PremiumSubscribeRequestDTO dto) {

        Student student = studentRepository.findByUserId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // ✅ Validate UTR
        if (dto.getUtr() == null || dto.getUtr().isBlank() || dto.getUtr().trim().length() < 10) {
            throw new RuntimeException("Invalid UTR. Must be at least 10 characters.");
        }

        LocalDate today = LocalDate.now();
        LocalDate newExpiry;

        // ✅ extend if already active
        if (student.isPremiumActive()
                && student.getPremiumExpiryDate() != null
                && student.getPremiumExpiryDate().isAfter(today)) {

            newExpiry = student.getPremiumExpiryDate().plusMonths(dto.getMonths());
        } else {
            newExpiry = today.plusMonths(dto.getMonths());
        }

        // 🔓 AUTO ACTIVATE AFTER RECHARGE
        student.setPremiumActive(true);
        student.setPremiumExpiryDate(newExpiry);
        student.setAccountStatus("ACTIVE");

        studentRepository.save(student);

        // 📧 send mail
        emailService.sendPremiumActivatedMail(student.getEmail(), newExpiry);

        return new PremiumResponseDTO(
                "Premium activated successfully",
                newExpiry);
    }
}
