package com.keyin.golfclub.controller;

import com.keyin.golfclub.model.Member;
import com.keyin.golfclub.repository.MemberRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/search/name/{name}")
    public List<Member> searchByName(@PathVariable String name) {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    @GetMapping("/search/type/{type}")
    public List<Member> searchByMembershipType(@PathVariable String type) {
        return memberRepository.findByMembershipTypeIgnoreCase(type);
    }

    @GetMapping("/search/phone/{phone}")
    public List<Member> searchByPhone(@PathVariable String phone) {
        return memberRepository.findByMemberPhoneNumber(phone);
    }

    @GetMapping("/search/tournament-date/{startDate}")
    public List<Member> searchByTournamentDate(@PathVariable LocalDate startDate) {
        return memberRepository.findByTournamentsStartDate(startDate);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {

        return memberRepository.findById(id)
                .map(member -> {
                    member.setMemberName(updatedMember.getMemberName());
                    member.setMemberAddress(updatedMember.getMemberAddress());
                    member.setMemberEmailAddress(updatedMember.getMemberEmailAddress());
                    member.setMemberPhoneNumber(updatedMember.getMemberPhoneNumber());
                    member.setMembershipStartDate(updatedMember.getMembershipStartDate());
                    member.setMembershipType(updatedMember.getMembershipType());

                    return memberRepository.save(member);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
    }
}
