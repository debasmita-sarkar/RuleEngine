package com.demo.rule.utility;

import com.demo.rule.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserCache {
    static Map<String, User> userMap = new HashMap<String, User>();

    public static void put(User user){
        userMap.put(user.getId(),user);
    }
    public static User get(String userID) {
        return userMap.get(userID);
    }

    @PostConstruct
    public void addTestUser(){
        User user1 = User.builder().id("user1").isMember(true).accountNumber("1234").agentID("agent1").memberActivationStatus(true).build();
        User agent = User.builder().id("agent1").isAgent(true).accountNumber("12345").build();
        userMap.put("user1",user1);
        userMap.put("agent1",agent);
    }

}
