package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryOperatorTest {

    @Test
    @DisplayName("이항연산자의 특징 - ① int보다 크기가 작은 타입은 int로 변환한다")
    void autoTypeCastingToInt() {
        byte byteValue = 127;
        short shortValue = 32767;
        char charValue = 'A';

        assertThat(byteValue + shortValue).isEqualTo(32894).isInstanceOf(Integer.class);
        assertThat(byteValue + charValue).isEqualTo(192).isInstanceOf(Integer.class);
        assertThat(shortValue + charValue).isEqualTo(32832).isInstanceOf(Integer.class);
    }

    @Test
    @DisplayName("이항연산자의 특징 - ② 피연산자 중 범위가 큰 타입으로 형변환된다")
    void autoTypeCastingToLargerType() {
        byte byteValue = 127;
        short shortValue = 32767;
        int intValue = 35;
        long longValue = 100000000000L;
        float floatValue = 3.14f;
        double doubleValue = 11.123456789;
        char charValue = 'A';

        assertThat(byteValue + intValue).isEqualTo(162).isInstanceOf(Integer.class);
        assertThat(shortValue + intValue).isEqualTo(32802).isInstanceOf(Integer.class);
        assertThat(charValue + intValue).isEqualTo(100).isInstanceOf(Integer.class);
        assertThat(longValue + intValue).isEqualTo(100000000035L).isInstanceOf(Long.class).isNotInstanceOf(Integer.class);
        assertThat(floatValue + intValue).isEqualTo(38.14f).isInstanceOf(Float.class).isNotInstanceOf(Integer.class);
        assertThat(floatValue + doubleValue).isEqualTo(14.263456893904175).isInstanceOf(Double.class).isNotInstanceOf(Float.class);
    }

    @Test
    @DisplayName("산술 연산자")
    void arithmeticOperator() {
        assertThat(6 + 2).isEqualTo(8);
        assertThat(6 - 2).isEqualTo(4);
        assertThat(6 * 2).isEqualTo(12);
        assertThat(6 / 2).isEqualTo(3);
        assertThat(6 % 2).isEqualTo(0);
    }

    @Test
    @DisplayName("비교(관계) 연산자")
    void relationalOperator() {
        assertThat(3 > 2).isTrue();
        assertThat(3 >= 2).isTrue();
        assertThat(3 < 2).isFalse();
        assertThat(3 <= 2).isFalse();
        assertThat(3 == 3).isTrue();
        assertThat(3 != 2).isTrue();
    }

    @Test
    @DisplayName("논리 연산자")
    void logicalOperator() {
        // 논리 곱 (&&)
        assertThat(true && true).isTrue();
        assertThat(true && false).isFalse();
        assertThat(false && true).isFalse();
        assertThat(false && false).isFalse();

        // 논리 합 (||)
        assertThat(true || true).isTrue();
        assertThat(true || false).isTrue();
        assertThat(false || true).isTrue();
        assertThat(false || false).isFalse();

        // 논리 부정 (!)
        assertThat(!(true && true)).isFalse();
        assertThat(!(true && false)).isTrue();
    }
}
