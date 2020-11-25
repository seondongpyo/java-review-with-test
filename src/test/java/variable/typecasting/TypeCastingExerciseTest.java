package variable.typecasting;

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

    @Test
    @DisplayName("형변환 예제")
    void typeCastingExercise2() {
        int x = 2;
        int y = 5;
        char c = 'A';

        assertThat(1 + x << 33).isEqualTo(6);
        // 1) 덧셈 연산자가 쉬프트 연산자보다 우선순위가 높다. 즉, 1 + 2 << 33 = 3 << 33
        // 2) int는 32비트이므로 33번 쉬프트하지 않고 1번만 쉬프트한다. 즉, 3 << 33 = 3 << 1
        //  3 << 1 = 0110 << 1 => 6

        assertThat(y >= 5 || x < 0 && x > 2).isTrue(); // true || (false && false) = true || false = true
        assertThat(y += 10 - x++).isEqualTo(13); // 5 + 10 - 2 (연산 후 x = 3, y = 8)
        assertThat(x += 2).isEqualTo(5); // 연산 후 x = 5
        assertThat(!('A' <= c && c <= 'Z')).isFalse(); // !(true && true) = false
        assertThat('C' - c).isEqualTo(2); // int보다 작은 자료형은 int로 형변환 후 연산, 즉 67 - 65 = 2
        assertThat('5' - '0').isEqualTo(5); // 53 - 48 = 5
        assertThat(c + 1).isEqualTo(66); // 65 + 1 = 66
        assertThat(++c).isEqualTo('B'); // 연산 후 c = 'B'
        assertThat(c++).isEqualTo('B'); // 연산 후 c = 'C'
        assertThat(c).isEqualTo('C');
    }
}
