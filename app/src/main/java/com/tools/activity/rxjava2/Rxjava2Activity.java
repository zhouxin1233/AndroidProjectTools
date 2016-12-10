package com.tools.activity.rxjava2;

import com.tools.R;
import com.tools.base.BaseActivity;
import com.tools.utils.LogUtil;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Activity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rxjava2);
    }

    @Override
    protected void initData() {
        //创建一个上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
                e.onNext(5);
            }
        }).subscribe(new Observer<Integer>() {
            Disposable mDisposable;
            private int i;
            @Override
            public void onSubscribe(Disposable d) {
                LogUtil.D("Rxjava2","subscrib"+d.isDisposed());
                mDisposable=d;
            }

            @Override
            public void onNext(Integer value) {
                LogUtil.D("Rxjava2",value+"");
                  LogUtil.D("Rxjava2","onNext-----Disposable : "+mDisposable.isDisposed());
                         i++;
                if (i==2){
                    mDisposable.dispose();
                     LogUtil.D("Rxjava2","onNext-----Disposable : "+mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.D("Rxjava2",e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtil.D("Rxjava2 + onComplete() ");

            }
        });


        //创建一个上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                LogUtil.D("上游线程"+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io())//上游线程 只可以指定一次
                .observeOn(AndroidSchedulers.mainThread())//下游线程,可以指定多次
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtil.D("下游线程 :" + Thread.currentThread().getName());
                        LogUtil.D(integer+"");
                    }
                });

        //map操作符
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                    e.onNext(1);
                    e.onNext(2);
                    e.onNext(3);
            }
        }, BackpressureStrategy.BUFFER).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return " This is result : "+integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtil.I("map",s);
            }
        });

        //flatMap操作符
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                    e.onNext(1);
                    e.onNext(2);
                    e.onNext(3);
            }
        },BackpressureStrategy.BUFFER).concatMap(new Function<Integer, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Integer integer) throws Exception {
                List<String> list=new ArrayList<String>();
                for (int i = 0; i < 4; i++) {
                    list.add("FlatMap操作符 This is result : "+integer);
                }
                return Flowable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe();
    }
}
/**
*上游可以发送无限个onNext, 下游也可以接收无限个onNext.
*当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
*当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
*上游可以不发送onComplete或onError.
*最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
*/