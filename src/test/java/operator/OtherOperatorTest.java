package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OtherOperatorTest {

    @Test
    @DisplayName("삼항 연산자")
    void ternaryOperator() {
        int a = 20;
        int b = 19;
        int c = (a > b) ? a : b; // (조건식) ? '참'일 경우의 값 : '거짓'일 경우의 값
        int d = (a > ++b) ? a : b;
        double e = 0.3;

        assertThat(c).isEqualTo(a);
        assertThat(d).isEqualTo(b);
        assertThat(e < 0.5 ? 0 : 0.5).isEqualTo(0.0).isInstanceOf(Double.class).isNotInstanceOf(Integer.class);
    }

    @Test
    @DisplayName("instanceof 연산자")
    void instanceOfOperator() {
        A a = new A();
        B b = new B();

        assertThat(a).isInstanceOf(A.class);
        assertThat(a).isNotInstanceOf(B.class);
        assertThat(b).isInstanceOf(A.class);
        assertThat(b).isInstanceOf(B.class);

        assertThat(a instanceof A).isTrue();
        assertThat(a instanceof B).isFalse();
        assertThat(b instanceof A).isTrue();
        assertThat(b instanceof B).isTrue();
    }

    private class A {
    }

    private class B extends A {
    }

}
