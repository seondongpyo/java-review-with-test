package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThat;

class DecimalFormatTest {

    @Test
    @DisplayName("형식화 클래스 - DecimalFormat")
    void decimalFormat() {
        double number = 1234567.89;
        DecimalFormat decimal = new DecimalFormat("0"); // 10진수 (또는 #)
        DecimalFormat point = new DecimalFormat("#.####"); // 소수점
        DecimalFormat negative = new DecimalFormat("-#.#"); // 음수 부호
        DecimalFormat unit = new DecimalFormat("#,###.##"); // 단위 구분자
        DecimalFormat quotient = new DecimalFormat("#.########E0"); // 지수 기호
        DecimalFormat pattern = new DecimalFormat("#,###.##+;#.###.##-"); // 패턴 구분자
        DecimalFormat percent = new DecimalFormat("#.#%"); // 퍼센트
        DecimalFormat perMill = new DecimalFormat("#.#\u2030"); // 퍼밀 (퍼센트 * 10)
        DecimalFormat currency = new DecimalFormat("\u00A4 #,###"); // 통화
        DecimalFormat escape = new DecimalFormat("'#'#,###"); // escape 문자

        assertThat(decimal.format(number)).isEqualTo("1234568");
        assertThat(point.format(number)).isEqualTo("1234567.89"); // 소수점 자릿수는 추가되지 않는다 (!= 1234567.8900)
        assertThat(negative.format(number)).isEqualTo("-1234567.9");
        assertThat(unit.format(number)).isEqualTo("1,234,567.89");
        assertThat(quotient.format(number)).isEqualTo("1.23456789E6");
        assertThat(pattern.format(number)).isEqualTo("1,234,567.89+"); // 음수면 1,234,567.89-
        assertThat(percent.format(number)).isEqualTo("123456789%");
        assertThat(perMill.format(number)).isEqualTo("1234567890‰");
        assertThat(currency.format(number)).isEqualTo("₩ 1,234,568");
        assertThat(escape.format(number)).isEqualTo("#1,234,568");
    }
}
