package net.wangjifeng.enums;

/**
 * @author: WJF
 * @date: 2020/5/24
 * @description: OpsType
 */

public enum OpsType {

    LEFT("left"),
    RIGHT("right")

    ;

    private String idea;

    OpsType(String idea) {
        this.idea = idea;
    }
}
