package com.example.testenum.service;

import com.example.testenum.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    private Member normalStatusMember;
    private Member dormantStatusMember;
    private Member withdrawalStatusMember;

    @Before
    public void init() {
        normalStatusMember = new Member.MemberBuilder("minjae", "이민재", MemberStatus.NORMAL)
                .build();
        dormantStatusMember = new Member.MemberBuilder("chanpong", "김찬호", MemberStatus.DORMANT)
                .build();
        withdrawalStatusMember = new Member.MemberBuilder("ssunwoo", "박선우", MemberStatus.WITHDRAWAL)
                .build();
    }

    @Test
    public void 무조건_상태_변경_테스트() {
        Member normalMemberResult;
        normalMemberResult = memberService.updateStatus(normalStatusMember, MemberStatus.NORMAL);
        assertThat(normalMemberResult.getStatus()).as("회원 상태 일반 -> 일반").as("회원 상태 %s", normalMemberResult.getStatus())
                .isEqualTo(MemberStatus.NORMAL);

        normalStatusMember.setStatus(MemberStatus.NORMAL); // 상태값 리셋
        normalMemberResult = memberService.updateStatus(normalStatusMember, MemberStatus.DORMANT);
        assertThat(normalMemberResult.getStatus()).as("회원 상태 일반 -> 휴면").as("회원 상태 %s", normalMemberResult.getStatus())
                .isEqualTo(MemberStatus.DORMANT);

        normalStatusMember.setStatus(MemberStatus.NORMAL); // 상태값 리셋
        normalMemberResult = memberService.updateStatus(normalStatusMember, MemberStatus.WITHDRAWAL);
        assertThat(normalMemberResult.getStatus()).as("회원 상태 일반 -> 탈퇴").as("회원 상태 %s", normalMemberResult.getStatus())
                .isEqualTo(MemberStatus.WITHDRAWAL);

        Member dormantMemberResult;
        dormantMemberResult = memberService.updateStatus(dormantStatusMember, MemberStatus.NORMAL);
        assertThat(dormantMemberResult.getStatus()).as("회원 상태 휴면 -> 일반").as("회원 상태 %s", dormantMemberResult.getStatus())
                .isEqualTo(MemberStatus.NORMAL);

        dormantMemberResult.setStatus(MemberStatus.DORMANT); // 상태값 리셋
        dormantMemberResult = memberService.updateStatus(dormantStatusMember, MemberStatus.DORMANT);
        assertThat(dormantMemberResult.getStatus()).as("회원 상태 휴면 -> 휴면").as("회원 상태 %s", dormantMemberResult.getStatus())
                .isEqualTo(MemberStatus.DORMANT);

        dormantMemberResult.setStatus(MemberStatus.DORMANT); // 상태값 리셋
        dormantMemberResult = memberService.updateStatus(dormantStatusMember, MemberStatus.WITHDRAWAL);
        assertThat(dormantMemberResult.getStatus()).as("회원 상태 휴면 -> 탈퇴").as("회원 상태 %s", dormantMemberResult.getStatus())
                .isEqualTo(MemberStatus.WITHDRAWAL);

        Member withdrawalMemberResult;
        withdrawalMemberResult = memberService.updateStatus(withdrawalStatusMember, MemberStatus.NORMAL);
        assertThat(withdrawalMemberResult.getStatus()).as("회원 상태 탈퇴 -> 일반").as("회원 상태 %s", withdrawalMemberResult.getStatus())
                .isEqualTo(MemberStatus.NORMAL);

        withdrawalStatusMember.setStatus(MemberStatus.WITHDRAWAL); // 상태값 리셋
        withdrawalMemberResult = memberService.updateStatus(withdrawalStatusMember, MemberStatus.DORMANT);
        assertThat(withdrawalMemberResult.getStatus()).as("회원 상태 탈퇴 -> 휴면").as("회원 상태 %s", withdrawalMemberResult.getStatus())
                .isEqualTo(MemberStatus.DORMANT);

        withdrawalStatusMember.setStatus(MemberStatus.WITHDRAWAL); // 상태값 리셋
        withdrawalMemberResult = memberService.updateStatus(withdrawalStatusMember, MemberStatus.WITHDRAWAL);
        assertThat(withdrawalMemberResult.getStatus()).as("회원 상태 탈퇴 -> 탈퇴").as("회원 상태 %s", withdrawalMemberResult.getStatus())
                .isEqualTo(MemberStatus.WITHDRAWAL);
    }

    @Test
    public void 상태_변경_테스트() {

        Member normalMemberResult;

        Throwable thrown = catchThrowable(() -> memberService.updateStatus1(normalStatusMember, MemberStatus.NORMAL));

        assertThat(thrown).as("회원 상태 일반 -> 일반")
                .isInstanceOf(RuntimeException.class)
                .hasMessage("같은 상태로 변경할 수 없습니다.");

        normalStatusMember.setStatus(MemberStatus.NORMAL); // 상태값 리셋
        normalMemberResult = memberService.updateStatus1(normalStatusMember, MemberStatus.DORMANT);
        assertThat(normalMemberResult.getStatus()).as("회원 상태 일반 -> 휴면")
                .isEqualTo(MemberStatus.DORMANT);

        normalStatusMember.setStatus(MemberStatus.NORMAL); // 상태값 리셋
        normalMemberResult = memberService.updateStatus1(normalStatusMember, MemberStatus.WITHDRAWAL);
        assertThat(normalMemberResult.getStatus()).as("회원 상태 일반 -> 탈퇴")
                .isEqualTo(MemberStatus.WITHDRAWAL);

        Member dormantMemberResult;
        dormantMemberResult = memberService.updateStatus1(dormantStatusMember, MemberStatus.NORMAL);
        assertThat(dormantMemberResult.getStatus()).as("회원 상태 휴면 -> 일반")
                .isEqualTo(MemberStatus.NORMAL);

        dormantMemberResult.setStatus(MemberStatus.DORMANT); // 상태값 리셋

        Throwable thrown2 = catchThrowable(() -> memberService.updateStatus1(dormantStatusMember, MemberStatus.DORMANT));

        assertThat(thrown2).as("회원 상태 휴면 -> 휴면")
                .isInstanceOf(RuntimeException.class)
                .hasMessage("같은 상태로 변경할 수 없습니다.");

        dormantMemberResult.setStatus(MemberStatus.DORMANT); // 상태값 리셋
        thrown2 = catchThrowable(() -> memberService.updateStatus1(dormantStatusMember, MemberStatus.WITHDRAWAL));
        assertThat(thrown2).as("회원 상태 휴면 -> 탈퇴")
                .isInstanceOf(RuntimeException.class)
                .hasMessage("휴면회원은 탈퇴 상태로 변경 불가능");

        Throwable thrown3 = catchThrowable(() -> memberService.updateStatus1(withdrawalStatusMember, MemberStatus.NORMAL));
        assertThat(thrown3).as("회원 상태 탈퇴 -> 일반")
                .isInstanceOf(RuntimeException.class)
                .hasMessage("탈퇴회원은 어떤 상태로든 변경 불가능");

        withdrawalStatusMember.setStatus(MemberStatus.WITHDRAWAL); // 상태값 리셋
        thrown3 = catchThrowable(() -> memberService.updateStatus1(withdrawalStatusMember, MemberStatus.DORMANT));
        assertThat(thrown3).as("회원 상태 탈퇴 -> 휴면")
                .isInstanceOf(RuntimeException.class)
                .hasMessage("탈퇴회원은 어떤 상태로든 변경 불가능");

        withdrawalStatusMember.setStatus(MemberStatus.WITHDRAWAL); // 상태값 리셋
        thrown3 = catchThrowable(() -> memberService.updateStatus1(withdrawalStatusMember, MemberStatus.WITHDRAWAL));
        assertThat(thrown3).as("회원 상태 탈퇴 -> 탈퇴")
                .isInstanceOf(RuntimeException.class)
                .hasMessage("같은 상태로 변경할 수 없습니다.");
    }

    @Test
    public void 발전한_회원_상태_변경_테스트() {
        AdvencedMember member = new AdvencedMember.MemberBuilder("minjae", "이민재", AdvencedMemberStatus.NORMAL)
                .build();
        AdvencedMember dormantMember = new AdvencedMember.MemberBuilder("chanpong", "김찬호", AdvencedMemberStatus.DORMANT)
                .build();
        AdvencedMember withdrawalMember = new AdvencedMember.MemberBuilder("ssunwoo", "박선우", AdvencedMemberStatus.WITHDRAWAL)
                .build();

        // 일반회원
        Throwable thrown1 = catchThrowable(() -> memberService.updateStatus(member, AdvencedMemberStatus.NORMAL));
        assertThat(thrown1).as("회원 상태 일반 -> 일반")
                .isInstanceOf(MemberStatusSwitchException.class);

        AdvencedMember memberResult;
        member.setStatus(AdvencedMemberStatus.NORMAL); // 상태 초기화
        memberResult = memberService.updateStatus(member, AdvencedMemberStatus.DORMANT);
        assertThat(memberResult.getStatus()).as("회원 사태 일반 -> 휴면")
                .isEqualTo(AdvencedMemberStatus.DORMANT);

        member.setStatus(AdvencedMemberStatus.NORMAL); // 상태 초기화
        memberResult = memberService.updateStatus(member, AdvencedMemberStatus.WITHDRAWAL);
        assertThat(memberResult.getStatus()).as("회원 사태 일반 -> 탈퇴")
                .isEqualTo(AdvencedMemberStatus.WITHDRAWAL);

        // 휴면회원
        AdvencedMember domantMemberResult;
        domantMemberResult = memberService.updateStatus(dormantMember, AdvencedMemberStatus.NORMAL);
        assertThat(domantMemberResult.getStatus()).as("회원 사태 휴면 -> 일반")
                .isEqualTo(AdvencedMemberStatus.NORMAL);

        dormantMember.setStatus(AdvencedMemberStatus.DORMANT); // 상태 초기화
        Throwable thrown21 = catchThrowable(() -> memberService.updateStatus(dormantMember, AdvencedMemberStatus.DORMANT));
        assertThat(thrown21).as("회원 상태 휴면 -> 휴면")
                .isInstanceOf(MemberStatusSwitchException.class);

        dormantMember.setStatus(AdvencedMemberStatus.DORMANT); // 상태 초기화
        Throwable thrown22 = catchThrowable(() -> memberService.updateStatus(dormantMember, AdvencedMemberStatus.WITHDRAWAL));
        assertThat(thrown22).as("회원 사태 휴면 -> 탈퇴")
                .isInstanceOf(MemberStatusSwitchException.class);

        // 탈퇴회원
        Throwable thrown31 = catchThrowable(() -> memberService.updateStatus(withdrawalMember, AdvencedMemberStatus.NORMAL));
        assertThat(thrown31).as("회원 상태 탈퇴 -> 일반")
                .isInstanceOf(MemberStatusSwitchException.class);

        withdrawalMember.setStatus(AdvencedMemberStatus.WITHDRAWAL); // 상태 초기화
        Throwable thrown32 = catchThrowable(() -> memberService.updateStatus(withdrawalMember, AdvencedMemberStatus.DORMANT));
        assertThat(thrown32).as("회원 상태 탈퇴 -> 휴면")
                .isInstanceOf(MemberStatusSwitchException.class);

        withdrawalMember.setStatus(AdvencedMemberStatus.WITHDRAWAL); // 상태 초기화
        Throwable thrown33 = catchThrowable(() -> memberService.updateStatus(withdrawalMember, AdvencedMemberStatus.WITHDRAWAL));
        assertThat(thrown33).as("회원 상태 탈퇴 -> 탈퇴")
                .isInstanceOf(MemberStatusSwitchException.class);
    }
}