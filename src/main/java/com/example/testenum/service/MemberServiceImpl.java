package com.example.testenum.service;

import com.example.testenum.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("memberService")
public class MemberServiceImpl implements MemberService {

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Member updateStatus(Member member, MemberStatus status) {

        member.setStatus(status);

        return member;
    }

    @Override
    public Member updateStatusWithCheck(Member member, MemberStatus status) {

        if (member.getStatus() == status) {
            log.error("같은 상태로 변경할 수 없습니다.");
            throw new RuntimeException("같은 상태로 변경할 수 없습니다.");
        }

        if (member.getStatus() == MemberStatus.NORMAL) {
            // 회왼상태가 정상이면 모든 상태로 변경 가능
            member.setStatus(status);

            return member;
        } else if (member.getStatus() == MemberStatus.DORMANT) {
            if (status == MemberStatus.NORMAL) {
                // 휴면회원은 정상 상태로 변경 가능
                member.setStatus(status);

                return member;
            } else {
                // 휴면회원은 탈퇴 상태로 변경 불가능
                log.error("휴면회원은 탈퇴 상태로 변경 불가능");
                throw new RuntimeException("휴면회원은 탈퇴 상태로 변경 불가능");
            }
        } else if (member.getStatus() == MemberStatus.WITHDRAWAL) {
            // 탈퇴회원은 어떤 상태로든 변경 불가능
            log.error("탈퇴회원은 어떤 상태로든 변경 불가능");
            throw new RuntimeException("탈퇴회원은 어떤 상태로든 변경 불가능");
        }

        log.error("정의되지 않은 상태 변경 조건");
        throw new RuntimeException("정의되지 않은 상태 변경 조건");
    }

    @Override
    public AdvencedMember updateStatusWithEnum(AdvencedMember member, AdvencedMemberStatus status) {

        if (member.getStatus() == status) {
            log.error("같은 상태로 변경할 수 없습니다.");
            throw new RuntimeException("같은 상태로 변경할 수 없습니다.");
        }

        if (!member.getStatus().isStatusSwitchable(status)) {
            log.error("변경할 수 있는 상태값이 아님");
            throw new MemberStatusSwitchException();
        }

        log.debug("상태 변경 {} -> {}", member.getStatus(), status);
        member.setStatus(status);

        return member;
    }


    private void test() {
        AdvencedMember member = new AdvencedMember.MemberBuilder("Gil-dong", "홍길동", AdvencedMemberStatus.NORMAL).build();

        //TODO: switch with IDE


        //TODO: foreach

    }
}
