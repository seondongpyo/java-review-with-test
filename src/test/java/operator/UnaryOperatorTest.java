package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnaryOperatorTest {

    @Test
    @DisplayName("부호 연산자")
    void signOperator() {
        int intValue1 = 3;
        int intValue2 = -2;

        assertThat(intValue1).isPositive();
        assertThat(intValue2).isNegative();
        assertThat(intValue1 + intValue2).isPositive();
        assertThat(intValue1 * intValue2).isNegative();
    }

    @Test
    @DisplayName("증감 연산자")
    void incrementAndDecrementOperator() {
        int intValue = 0;

        assertThat(intValue++).isEqualTo(0);
        assertThat(intValue).isEqualTo(1);
        assertThat(++intValue).isEqualTo(2);
        assertThat(intValue).isEqualTo(2);

        assertThat(intValue--).isEqualTo(2);
        assertThat(intValue).isEqualTo(1);
        assertThat(--intValue).isEqualTo(0);
        assertThat(intValue).isEqualTo(0);
    }

    @Test
    @DisplayName("증감 연산자 예제1")
    void operatorExercise1() {
        int x = 10;
        int y = x-- + 5 + --x;

        assertThat(x).isEqualTo(8);
        assertThat(y).isEqualTo(23);
    }

    @Test
    @DisplayName("증감 연산자 예제2")
    void operatorExercise2() {
        int x = 6;
        int y = 6;
        System.out.println("x++ = " + x++); // 6 => (x = 7, y = 6)
        System.out.println("--y = " + --y); // 5 => (x = 7, y = 5)
        System.out.println("++x = " + ++x); // 8 => (x = 8, y = 5)
        System.out.println("y-- = " + y--); // 5 => (x = 8, y = 4)
        System.out.println("--y = " + --y); // 3 => (x = 8, y = 3)

        int z = x--;
        System.out.println("z = " + z); // 8 => (x = 7, y = 3, z = 8)

        assertThat(x).isEqualTo(7);
        assertThat(y).isEqualTo(3);
        assertThat(z).isEqualTo(8);
    }

    @Test
    @DisplayName("증감 연산자 예제3")
    void operatorExercise3() {
        int x = 3;
        System.out.println("x++ = " + x++); // 3 => (x = 4)
        System.out.println("++x = " + ++x); // 5 => (x = 5)

        int y = ++x; // 6 (x = 6, y = 6)
        int z = x--; // 6 (x = 5, y = 6, z = 6)

        if (++x >= 10) { // 6 (x = 6, y = 6, z = 6)
            System.out.println("x는 10 이상입니다."); // 출력 안 됨
        }

        assertThat(x).isEqualTo(6);
        assertThat(y).isEqualTo(6);
        assertThat(z).isEqualTo(6);
    }
}
