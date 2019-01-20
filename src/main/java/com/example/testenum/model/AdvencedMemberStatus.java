package com.example.testenum.model;

import java.util.Arrays;
import java.util.List;

public enum AdvencedMemberStatus {
    NORMAL("01", Arrays.asList("02", "03")),
    DORMANT("02", Arrays.asList("01")),
    WITHDRAWAL("03", Arrays.asList());

    private String code;
    private List<String> switchableStatusCodeList;

    AdvencedMemberStatus(String code, List<String> switchableStatusCodeList) {
        this.code = code;
        this.switchableStatusCodeList = switchableStatusCodeList;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isStatusSwitchable(AdvencedMemberStatus status) {
        return switchableStatusCodeList.contains(status.getCode());
    }
}
