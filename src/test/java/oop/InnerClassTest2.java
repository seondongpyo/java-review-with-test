package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InnerClassTest2 {

    @Test
    @DisplayName("내부 클래스의 제어자와 접근성")
    void innerClassModifier() {
        assertThat(instanceInner.instanceVariant).isEqualTo(10);
        assertThat(StaticInner.staticVariant).isEqualTo(20);
        assertThat(InnerClassTest2.staticMethod()).isEqualTo(30);
        assertThat(instanceMethod()).isEqualTo(200);
        assertThat(method()).isEqualTo(100);
    }

    private int outerVariable = 10;
    static int outerStaticVariable = 20;

    class InstanceInner {
        int instanceVariant = outerVariable; // 외부 클래스의 private 멤버도 접근 가능
    }

    static class StaticInner {
        static int staticVariant = outerStaticVariable;
    }

    InstanceInner instanceInner = new InstanceInner(); // 인스턴스 멤버 간에는 서로 직접 접근 가능
    static StaticInner staticInner = new StaticInner(); // static 멤버 간에는 서로 직접 접근 가능

    static int staticMethod() {
        StaticInner staticInner = new StaticInner();

//        InstanceInner instanceInner = new InstanceInner(); // static 멤버는 인스턴스 멤버에 직접 접근 불가능
        InnerClassTest2 outer = new InnerClassTest2();
        InstanceInner instanceInner = outer.new InstanceInner(); // 인스턴스 클래스는 외부 클래스를 먼저 생성해야만 생성 가능

        return StaticInner.staticVariant + instanceInner.instanceVariant;
    }

    int instanceMethod() {
        StaticInner staticInner = new StaticInner(); // 인스턴스 멤버에서는 static 멤버도 접근 가능
        InstanceInner instanceInner = new InstanceInner();

        return StaticInner.staticVariant * instanceInner.instanceVariant;
    }

    int method() {
        int localVariable = 40;

        class LocalInner {
            int localInnerVariable1 = 30;
            int localInnerVariable2 = outerVariable;
            int localInnerVariable3 = outerStaticVariable;

            int getSumOfInnerVariables() {
                return localInnerVariable1 + localInnerVariable2 + localInnerVariable3;
            }
        }

        LocalInner localInner = new LocalInner(); // 지역 클래스는 외부에서는 접근 불가능

        return localVariable + localInner.getSumOfInnerVariables();
    }
}
