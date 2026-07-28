package com.keyin.golfclub.repository;

import com.keyin.golfclub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByMemberNameContainingIgnoreCase(String memberName);

    List<Member> findByMembershipTypeIgnoreCase(String membershipType);

    List<Member> findByMemberPhoneNumber(String memberPhoneNumber);

    List<Member> findByTournamentsStartDate(LocalDate startDate);
}