package com.example.testenum.service;

import com.example.testenum.model.AdvencedMember;
import com.example.testenum.model.AdvencedMemberStatus;
import com.example.testenum.model.Member;
import com.example.testenum.model.MemberStatus;

public interface MemberService {
    /**
     * 아무 조건 없이 회원 상태 수정
     * @param member
     * @param status
     * @return
     */
    Member updateStatus(Member member, MemberStatus status);

    /**
     * 회원 상태 체크 후 수정
     * @param member
     * @param status
     * @return
     */
    Member updateStatusWithCheck(Member member, MemberStatus status);

    /**
     * Enum 클래스를 사용한 회원 상태 수정
     * @param member
     * @param status
     * @return
     */
    AdvencedMember updateStatusWithEnum(AdvencedMember member, AdvencedMemberStatus status);
}