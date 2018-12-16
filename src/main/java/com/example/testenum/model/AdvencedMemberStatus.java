package com.example.testenum.model;

import java.util.Arrays;
import java.util.List;

public enum AdvencedMemberStatus {
    NORMAL("01", Arrays.asList("02", "03")),
    DORMANT("02", Arrays.asList("01")),
    WITHDRAWAL("03", Arrays.asList());

    private String code;
    private List<String> switchableStatusList;

    AdvencedMemberStatus(String code, List<String> asList) {
        this.code = code;
        this.switchableStatusList = asList;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isStatusSwitchable(AdvencedMemberStatus status) {
        return switchableStatusList.contains(status.getCode());
    }
}
