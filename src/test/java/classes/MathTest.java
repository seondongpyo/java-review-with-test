package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathTest {

    @Test
    @DisplayName("Math 클래스 - 반올림, 올림, 버림")
    void math() {
        double positiveDouble = 31.41592;
        double positiveHalfDouble = 1.5;
        double negativeDouble = -10.6578;
        double negativeHalfDouble = -1.5;

        // Math.round() : 가장 가까운 정수로 반올림
        assertThat(Math.round(positiveDouble)).isEqualTo(31).isInstanceOf(Long.class); // 반올림 (양수, long 반환)
        assertThat(Math.round(negativeDouble)).isEqualTo(-11).isInstanceOf(Long.class); // 반올림 (음수, long 반환)
        assertThat(Math.round(positiveHalfDouble)).isEqualTo(2);
        assertThat(Math.round(negativeHalfDouble)).isEqualTo(-1);

        // Math.rint() : 가장 가까운 정수로 반올림하되, 두 정수의 정가운데 있는 값은 짝수 정수를 반환
        assertThat(Math.rint(positiveDouble)).isEqualTo(31.0).isInstanceOf(Double.class); // 반올림 (양수, double 반환)
        assertThat(Math.rint(negativeDouble)).isEqualTo(-11.0).isInstanceOf(Double.class); // 반올림 (음수, double 반환)
        assertThat(Math.rint(positiveHalfDouble)).isEqualTo(2.0);
        assertThat(Math.rint(negativeHalfDouble)).isEqualTo(-2.0);

        // Math.ceil() : 올림, 원래의 숫자보다 큰 값을 반환
        assertThat(Math.ceil(positiveDouble)).isEqualTo(32.0); // 올림 (양수)
        assertThat(Math.ceil(negativeDouble)).isEqualTo(-10.0); // 올림 (음수)

        // Math.floor() : 버림, 원래의 숫자보다 작은 값을 반환
        assertThat(Math.floor(positiveDouble)).isEqualTo(31.0); // 버림 (양수)
        assertThat(Math.floor(negativeDouble)).isEqualTo(-11.0); // 버림 (음수)
    }

    @Test
    @DisplayName("Math 클래스 - 절대값, 최소값, 최대값, n제곱, 제곱근")
    void mathMethods() {
        assertThat(Math.abs(-15.1234)).isEqualTo(15.1234); // 절대값
        assertThat(Math.min(-5, -10)).isEqualTo(-10); // 최소값
        assertThat(Math.max(10, 20)).isEqualTo(20); // 최대값
        assertThat(Math.pow(3, 4)).isEqualTo(81); // n제곱
        assertThat(Math.sqrt(16)).isEqualTo(4); // 제곱근
    }

    @Test
    @DisplayName("Math 클래스 - 난수 (random)")
    void mathRandom() {
        // Math.random() : 0.0 ~ 1.0 사이에 속하는 임의의 double 값을 반환 (1.0은 포함되지 않음)
        // 0.0 <= Math.random() < 1.0
        double randomDouble = Math.random();

        // Q. 1부터 99까지의 정수 중 하나의 난수를 구하려면?
        // 1) 양변에 99을 곱한다 -> 0.0 <= Math.random() * 99 < 99.0
        // 2) 양변을 int로 형변환한다 -> 0 <= (int)(Math.random() * 99) < 99
        // 3) 양변에 1을 더한다 -> 1 <= (int)(Math.random() * 99) + 1 < 100
        int randomInt = (int) (Math.random() * 99) + 1;

        assertThat(randomDouble).isGreaterThanOrEqualTo(0.0).isLessThan(1.0);
        assertThat(randomInt).isGreaterThanOrEqualTo(1).isLessThan(100);
    }
}
