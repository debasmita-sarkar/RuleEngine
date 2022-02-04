package com.demo.rule.actions;
@FunctionalInterface
public interface IActionExecutor {
    String packageType=null;
     public boolean execute(String actionType);
}
