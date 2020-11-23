package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperatorPriorityTest {

    @Test
    @DisplayName("연산자 우선순위 - 단항 > 이항")
    void unaryIsHigherThanBinary() {
        int a = 20;
        int b = 10;

        assertThat(a * -b).isEqualTo(-200); // 20 * -10 = -210
        assertThat(a * ++b).isEqualTo(220).isNotEqualTo(201); // 20 * 11 = 220

        // 단, 단항 후위 연산자의 경우 주의
        assertThat(a * b++).isEqualTo(220); // 20 * 11 = 220, 연산 후 b = 12
        assertThat(a * b).isEqualTo(240); // 20 * 12 = 240

        boolean x = true;
        boolean y = false;

        assertThat(x || y).isTrue();
        assertThat(!x || y).isFalse();
    }

    @Test
    @DisplayName("단항, 대입 연산자의 연산 진행방향은 오른쪽에서 왼쪽이다.")
    void unaryAndAssignmentOperatorProgressDirection() {
        int x;
        int y;
        x = y = 3;

        assertThat(x).isEqualTo(y).isEqualTo(3);
    }

    @Test
    @DisplayName("연산자 우선순위 - 곱셈, 나눗셈 > 덧셈, 뺄셈")
    void multiplicationDivisionIsHigherThanAdditionSubtraction() {
        assertThat(1 + 2 + 3 * 4 - 5).isEqualTo(10).isNotEqualTo(19);
        assertThat(2 * 3 + 4 + 6 / 5 - 7).isEqualTo(4);
    }

    @Test
    @DisplayName("연산자의 우선순위 - 괄호의 우선순위가 제일 높다")
    void roundBracketHasTheHighestPriority() {
        assertThat(2 * 3 * (4 + 5)).isEqualTo(54).isNotEqualTo(29);
        assertThat(2 * 4 - 6 / 2).isEqualTo(5).isNotEqualTo(1);
        assertThat("String" + (20 + 20)).isEqualTo("String40");
        assertThat("String" + 20 + 20).isEqualTo("String2020");
    }

    @Test
    @DisplayName("연산자 우선순위 - 비트 쉬프트 연산자는 덧셈 연산자보다 우선순위가 낮다")
    void bitShiftIsLowerThanAddition() {
        int a = 8;

        assertThat(a << 2 + 1).isEqualTo(64).isNotEqualTo(33);  // a << (2 + 1), not (a << 2) + 1
        assertThat((a << 2) + 1).isEqualTo(33);
    }

    @Test
    @DisplayName("연산자 우선순위 - 논리 연산 중 OR는 AND보다 우선순위가 낮다")
    void orIsLowerThanAnd() {
        int x = 4;

        assertThat(x < -1 || x > 3 && x < 5).isTrue(); // x < -1 || (x > 3 && x < 5) => false || true => true
    }
}
