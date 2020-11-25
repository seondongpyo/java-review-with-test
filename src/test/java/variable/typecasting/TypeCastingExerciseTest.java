package variable.typecasting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TypeCastingExerciseTest {

    @Test
    @DisplayName("형변환 예제")
    void typeCastingExercise() {
        assertThat("1" + "2").isEqualTo("12");
        assertThat(true + "").isEqualTo("true");
        assertThat('A' + 'B').isEqualTo(131); // 65 + 66
        assertThat('1' + 2).isEqualTo(51); // 49 + 2
        assertThat('1' + '2').isEqualTo(99); // 49 + 50
        assertThat('J' + "ava").isEqualTo("Java");
        assertThat(Byte.parseByte("10")).isEqualTo((byte) 10);
        assertThat(Short.parseShort("13017")).isEqualTo((short) 13017);
        assertThat(Integer.parseInt("123")).isEqualTo(123);
        assertThat(Long.parseLong("10000000")).isEqualTo(10000000L);
        assertThat(Float.parseFloat("3.141")).isEqualTo(3.141f);
        assertThat(Double.parseDouble("42.195")).isEqualTo(42.195);
    }
}
