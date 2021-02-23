package net.wangjifeng.producer.constants;

/**
 * @author: WJF
 * @date: 2020/7/10
 * @description: Constants
 */

public class Constants {

    public static final String EMPTY_STRING = "";

    /**
     * 使用这个路由键，两个队列都会接收到消息 --> topic.bind.* , topic.bind.#
     */
    public static final String routingKeyOneTopic = "topic.bind.one";

    /**
     * 使用这个路由键，只要绑定这个路由键'topic.bind.#'的队列都会接收到消息 --> topic.bind.#
     */
    public static final String routingKeyTwoTopic = "topic.bind.one.two";

    /**
     * 使用这个路由键，路由键完全匹配绑定键的队列接收到消息，队列1接收此路由键的消息
     */
    public static final String routingKeyOneDirect = "direct.bind.one";

    /**
     * 使用这个路由键，路由键完全匹配绑定键的队列接收到消息，队列2接收此路由键的消息
     */
    public static final String routingKeyTwoDirect = "direct.bind.two";

}
