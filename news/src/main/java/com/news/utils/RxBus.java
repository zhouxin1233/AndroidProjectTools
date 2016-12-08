package com.news.utils;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subscribers.SerializedSubscriber;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class RxBus {
    private static volatile RxBus sRxBus;
    private final FlowableProcessor<Object> mBus;
    private RxBus() {
        mBus=PublishProcessor.create().toSerialized();
    }
    public static RxBus getInstance(){
        if (sRxBus == null) {
            synchronized (RxBus.class) {
                if (sRxBus == null) {
                    sRxBus = new RxBus();
                }
            }
        }
        return sRxBus;
    }

    /**
     * 发送消息
     * @param o
     */
    public void post(Object o){
        new SerializedSubscriber<>(mBus).onNext(o);
    }
    /**
     * 确定接收消息的类型  根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
    /**
     * 判断是否有订阅者
     * @return
     */
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

}
