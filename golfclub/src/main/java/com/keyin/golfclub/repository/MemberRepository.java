package com.keyin.golfclub.repository;

import com.keyin.golfclub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}