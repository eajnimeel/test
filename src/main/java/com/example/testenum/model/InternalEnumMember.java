package com.example.testenum.model;

public class InternalEnumMember {

    private String id;
    private String name;
    private InternalMemberStatus status;
    private Integer age;
    private String email;

    private InternalEnumMember(MemberBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.status = builder.status;
        this.age = builder.age;
        this.email = builder.email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InternalMemberStatus getStatus() {
        return status;
    }

    public void setStatus(InternalMemberStatus status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class MemberBuilder {
        String id;
        String name;
        InternalMemberStatus status;
        Integer age;
        String email;

        public MemberBuilder(String id, String name, InternalMemberStatus status) {
            this.id = id;
            this.name = name;
            this.status = status;
        }

        public MemberBuilder setAge(Integer age) {
            this.age = age;

            return this;
        }

        public MemberBuilder setEmail(String email) {
            this.email = email;

            return this;
        }

        public InternalEnumMember build() {
            return new InternalEnumMember(this);
        }
    }
}

enum InternalMemberStatus {
    NORMAL,
    DORMANT,
    WITHDRAWAL
}