package com.project.library.repository;

import com.project.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    // Gasim imprumuturile active ale unui membru
    List<Loan> findByMemberIdAndStatus(Long memberId, String status);
}