package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionTest {

    @Test
    @DisplayName("java.util.function 패키지 - Function<T, R>")
    void function() {
        /*
            << Function<T, R> >>
            - 일반적인 함수
            - 하나의 매개변수를 받아서 결과를 반환
            - T : Type, R : Return Type
         */

        /*
        // 문자열을 숫자로 바꾸는 함수
        Function<String, Integer> parseStringToInteger = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };
         */
        Function<String, Integer> parseStringToInteger = s -> Integer.parseInt(s);

        // 동일한 문자열을 더하여 반환하는 함수
        Function<String, String> doubleString = s -> s + s;

        assertThat(parseStringToInteger.apply("100")).isEqualTo(100);
        assertThat(doubleString.apply("test")).isEqualTo("testtest");
    }
}
