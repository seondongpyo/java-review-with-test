package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModifierTest {

    @Test
    @DisplayName("접근 제어자")
    void accessModifier() {
        TestClass testClass = new TestClass();

        assertThat(testClass.privateVal).isEqualTo(10);
        assertThat(testClass.protectedVal).isEqualTo(20);
        assertThat(testClass.defaultVal).isEqualTo(30);
        assertThat(testClass.publicVal).isEqualTo(40);
    }

    @Test
    @DisplayName("static 제어자")
    void staticModifier() {
        assertThat(StaticTest.x).isEqualTo(200);
        assertThat(StaticTest.y).isEqualTo(100);
        assertThat(StaticTest.getMaxValue(10, 20)).isEqualTo(20);
    }

    @Test
    @DisplayName("final 제어자")
    void finalModifier() {
        FinalTest finalTest = new FinalTest();

        assertThat(finalTest.MAX_VALUE).isEqualTo(100);
        assertThat(finalTest.getMaxSize()).isEqualTo(100);
    }

    class TestClass {
        /*
            << 접근 제어자 >>
            - private : 같은 클래스 내에서만 접근 가능
            - default : 같은 패키지 내에서만 접근 가능
            - protected : 같은 패키지 내에서, 그리고 다른 패키지의 자손 클래스에서 접근 가능
            - public : 접근 제한 없음

            => public > protected > (default) > private
         */

        private int privateVal = 10; // private
        protected int protectedVal = 20; // protected
        int defaultVal = 30; // default (생략 가능)
        public int publicVal = 40; // public
    }
}

class StaticTest {

    // 클래스 변수
    static int x = 200;
    static int y = 100;
    static int[] array = new int[10];

    static { // 클래스 초기화 블록
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
    }

    // 클래스 메서드
    static int getMaxValue(int a, int b) {
        return Math.max(a, b);
    }
}

final class FinalTest { // 조상이 될 수 없는(상속할 수 없는) 클래스

    final int MAX_VALUE = 100; // 값을 변경할 수 없는(= 초기화를 단 한 번만 할 수 있는) 멤버 변수(상수)

    final int getMaxSize() { // 오버라이딩할 수 없는 메서드 (변경 불가)
        final int MAX_SIZE = MAX_VALUE; // 값을 변경할 수 없는 지역 변수(상수)

        return MAX_SIZE;
    }
}