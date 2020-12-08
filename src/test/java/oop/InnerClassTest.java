package oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InnerClassTest {

    /*
        << 내부 클래스 (inner class) >>
        * 클래스 안에 선언된 클래스
        * 특정 클래스 내에서만 주로 사용되는 클래스를 내부 클래스로 선언함
        * GUI 어플리케이션(AWT, Swing)의 이벤트 처리에 많이 사용됨

        << 내부 클래스의 장점 >>
        * 내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근할 수 있음
        * 서로 관련 있는 클래스를 논리적으로 묶어서 표현함으로써, 코드의 캡슐화를 증가시킴
        * 외부에서는 내부 클래스에 접근할 수 없으므로, 코드의 복잡성을 줄일 수 있음

        << 내부 클래스의 종류와 특징 >>
        * 내부 클래스의 종류는 변수의 선언 위치에 따른 종류와 동일함
        * 유효 범위와 성질도 변수와 유사하므로 비교해보면 이해하기 쉬움

            1) 인스턴스 클래스 (instance class)
                - 외부 클래스의 멤버변수 선언 위치에 선언하며, 외부 클래스의 인스턴스 멤버처럼 다루어진다
                - 주로 외부 클래스의 인스턴스 멤버들과 관련된 작업에 사용될 목적으로 선언된다

            2) 정적 클래스 (static class)
                - 외부 클래스의 멤버변수 선언 위치에 선언하며, 외부 클래스의 static 멤버처럼 다루어진다
                - 주로 외부 클래스의 static 멤버, 특히 static 메서드에서 사용될 목적으로 선언된다

            3) 지역 클래스 (local class)
                - 외부 클래스의 메서드나 초기화 블록 안에 선언하며, 선언된 영역 내부에서만 사용될 수 있다

            4) 익명 클래스 (anonymous class)
                - 클래스의 선언과 객체의 생성을 동시에 하는 이름 없는 클래스 (일회용)
     */

    @Test
    @DisplayName("내부 클래스")
    void innerClass() {
        Outer outer = new Outer();

        assertThat(Outer.Inner.innerConstantVariable).isEqualTo(20);
        assertThat(Outer.InstanceInner.instanceInnerVariable).isEqualTo(30);
        assertThat(outer.getInnerVariable()).isEqualTo(10);
        assertThat(outer.getLocalInnerVariable()).isEqualTo(40);
    }
}

class Outer {

    class Inner { // 인스턴스 클래스
        int innerVariable = 10;
        static final int innerConstantVariable = 20; // 상수(static final)는 허용
    }

    static class InstanceInner { // 정적(static) 클래스
        static int instanceInnerVariable = 30;
    }

    int getInnerVariable() {
        return new Inner().innerVariable;
    }

    int getLocalInnerVariable() { // 지역 클래스
        class LocalInner {
            int localInnerVariable = 40;
        }

        return new LocalInner().localInnerVariable;
    }
}
