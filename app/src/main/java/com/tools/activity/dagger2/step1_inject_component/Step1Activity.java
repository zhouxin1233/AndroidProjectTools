package com.tools.activity.dagger2.step1_inject_component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.R;
import com.tools.utils.ToastUtils;

import javax.inject.Inject;

public class Step1Activity extends AppCompatActivity {
    @Inject
    Pot mPot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        DaggerStep1ActivityComponent.create().inject(this);
        ToastUtils.show(getBaseContext(),mPot.show());
    }
}
/**知识点
 * @Inject 用于标记需要注入的依赖，或者标记用于提供依赖的方法。
 * 在Dagger 2中有3种不同的方式来提供依赖：
 * 构造器注入，@Inject标注在构造器上其实有两层意思。
 * ①告诉Dagger2可以使用这个构造器构建对象。如 Rose 类
 * ②注入构造器所需要的参数的依赖。 如 Pot 类，构造上的Rose会被注入。
 * 构造器注入的局限：如果有多个构造器，我们只能标注其中一个，无法标注多个。
 *
 * 属性注入
 * 如 MainActivity 类，标注在属性上。被标注的属性不能使用 private 修饰，否则无法注入。
 * 属性注入也是Dagger2中使用最多的一个注入方式。
 *
 *方法注入
 * @Component 则可以理解为注入器，在注入依赖的目标类 MainActivity 使用Component完成注入。
 * 使用接口定义，并且 @Component 注解。
 * Component中一般使用两种方式定义方法。
 * void inject(目标类 obj); Dagger2会从目标类开始查找@Inject注解，自动生成依赖注入的代码，调用inject可完成依赖的注入。
 *   Object getObj(); 如： Pot getPot();
 *   Dagger2会到Pot类中找被@Inject注解标注的构造器，自动生成提供Pot依赖的代码，这种方式一般为其他Component提供依赖。
 *
 **/

