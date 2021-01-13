package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

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

    @Test
    @DisplayName("java.util.function 패키지 - Predicate<T>")
    void predicate() {
        /*
            << Predicate<T> >>
            - 조건식을 표헌하는 데 사용됨
            - 매개변숳는 하나이며, 반환 타입은 boolean
         */

        /*
        // 문자열이 비어있는지 확인하는 함수
        Predicate<String> isEmptyStr = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() == 0;
            }
        };
         */
        Predicate<String> isEmptyString = s -> s.length() == 0; // 람다식으로 간소화
        String str1 = "";
        String str2 = "test";

        assertThat(isEmptyString.test(str1)).isTrue();
        assertThat(isEmptyString.test(str2)).isFalse();
    }
}
