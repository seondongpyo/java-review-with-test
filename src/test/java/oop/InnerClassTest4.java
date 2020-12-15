package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InnerClassTest4 {

    @Test
    @DisplayName("내부 클래스의 제어자와 접근성 3")
    void innerClassModifier() {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();

        assertThat(inner.method()).isEqualTo(30);
        assertThat(inner.thisMethod()).isEqualTo(20);
        assertThat(inner.outerThisMethod()).isEqualTo(10);
    }

    class Outer {
        int value = 10; // Outer.this.value

        class Inner {
            int value = 20; // this.value

            int method() {
                int value = 30;
                return value;
            }

            int thisMethod() {
                return this.value;
            }

            int outerThisMethod() {
                return Outer.this.value;
            }
        }
    }
}
