package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InnerClassTest3 {

    @Test
    @DisplayName("내부 클래스의 제어자와 접근성 2")
    void innerClassModifier() {
        // 인스턴스 클래스(InstanceInner)의 인스턴스를 생성하려면, 외부 클래스의 인스턴스를 먼저 생성해야 한다
        NewOuter outer = new NewOuter();
        NewOuter.InstanceInner instanceInner = outer.new InstanceInner();
        assertThat(instanceInner.instanceVariable).isEqualTo(100);
        assertThat(outer.method()).isEqualTo(400);

        // static 내부 클래스의 인스턴스는 외부 클래스를 먼저 생성하지 않아도 된다
        NewOuter.StaticInner staticInner = new NewOuter.StaticInner();
        assertThat(staticInner.instanceVariable).isEqualTo(200);
        assertThat(NewOuter.StaticInner.staticVariable).isEqualTo(300);
    }
}

class NewOuter {

    class InstanceInner {
        int instanceVariable = 100;
    }

    static class StaticInner {
        int instanceVariable = 200;
        static int staticVariable = 300;
    }

    int method() {
        int localVariable = 400; // final 생략 가능 (JDK 1.8~)

        class LocalInner {
            final int instanceVariable = localVariable;
        }

        return new LocalInner().instanceVariable;
    }
}
