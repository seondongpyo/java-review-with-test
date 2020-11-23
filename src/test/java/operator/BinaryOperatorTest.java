package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryOperatorTest {

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
