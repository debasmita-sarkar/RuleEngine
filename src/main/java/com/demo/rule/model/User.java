package com.demo.rule.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    String id;
    String name;
    boolean isMember;
    boolean isAgent;
    boolean memberActivationStatus;
    String password;
    String accountNumber;
    String agentID;
}
