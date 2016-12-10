package com.news.utils;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class RxBusHelper {
    /**
     * 发布消息
     * @param o
     */
    public static void post(Object o) {
        RxBus.getInstance().post(o);
    }

    /**
     * 接收消息,并在主线程处理
     * @param aClass
     * @param disposables 用于存放消息
     * @param listener
     * @param <T>
     */
//    public static <T> void doOnMainThread(Class<T> aClass, CompositeDisposable disposables, OnEventListener<T> listener) {
//        disposables.add(RxBus.getInstance().toFlowable(aClass).observeOn(AndroidSchedulers.mainThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS))));
//    }
//
//    public static <T> void doOnMainThread(Class<T> aClass, OnEventListener<T> listener) {
//        RxBus.getInstance().toFlowable(aClass).observeOn(AndroidSchedulers.mainThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS)));
//    }
//
//    /**
//     * 接收消息,并在子线程处理
//     *
//     * @param aClass
//     * @param disposables
//     * @param listener
//     * @param <T>
//     */
//    public static <T> void doOnChildThread(Class<T> aClass, CompositeDisposable disposables, OnEventListener<T> listener) {
//        disposables.add(RxBus.getInstance().toFlowable(aClass).subscribeOn(Schedulers.newThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS))));
//    }
//
//    public static <T> void doOnChildThread(Class<T> aClass, OnEventListener<T> listener) {
//        RxBus.getInstance().toFlowable(aClass).subscribeOn(Schedulers.newThread()).subscribe(listener::onEvent, throwable -> listener.onError(new ErrorBean(ErrorCode.ERROR_CODE_RXBUS, ErrorCode.ERROR_DESC_RXBUS)));
//    }
//
//    public interface OnEventListener<T> {
//        void onEvent(T t);
//
//        void onError(ErrorBean errorBean);
//    }
}
