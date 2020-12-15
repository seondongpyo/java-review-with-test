package classes;

import org.assertj.core.api.Assertions;
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
}
