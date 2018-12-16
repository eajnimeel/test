package com.example.testenum.service;

import com.example.testenum.model.AdvencedMember;
import com.example.testenum.model.AdvencedMemberStatus;
import com.example.testenum.model.Member;
import com.example.testenum.model.MemberStatus;

public interface MemberService {
    Member updateStatus(Member member, MemberStatus status);
    Member updateStatus1(Member member, MemberStatus status);

    AdvencedMember updateStatus(AdvencedMember member, AdvencedMemberStatus status);
}