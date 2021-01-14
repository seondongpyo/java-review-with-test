package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    @Test
    @DisplayName("java.util.function 패키지 - Supplier<T>")
    void supplier() {
        /*
            << Supplier<T> >>
            - 매개변수가 없고, 반환값만 있는 함수
         */

        // 무작위로 10보다 작은 숫자를 반환하는 함수
        Supplier<Integer> randomNumberMaker = () -> (int) (Math.random() * 10);

        assertThat(randomNumberMaker.get()).isLessThan(10);
    }

    @Test
    @DisplayName("java.util.function 패키지 - Consumer<T>")
    void consumer() {
        /*
            << Consumer<T> >>
            - 매개변수만 있고, 반환값이 없는 함수 (Supplier와 반대)
         */

        List<Integer> numbers = new ArrayList<>();

        // 전달한 매개변수의 10배에 해당하는 숫자를 리스트에 저장하는 함수
        Consumer<Integer> addTenfoldNumber = i -> numbers.add(i * 10);
        addTenfoldNumber.accept(1);
        addTenfoldNumber.accept(2);
        addTenfoldNumber.accept(3);

        assertThat(numbers).contains(10);
        assertThat(numbers).contains(20);
        assertThat(numbers).contains(30);
    }

    @Test
    @DisplayName("Predicate의 결합")
    void bindPredicate() {
        /*
            << Predicate의 결합 >>
            - 여러 조건식을 논리 연산자인 &&, ||, ! 등으로 연결해서 하나의 식을 구성할 수 있는 것처럼,
              여러 Predicate를 and(), or(), negate()로 연결해서 하나의 새로운 Predicate로 결합할 수 있다
         */
        Predicate<Integer> lessThan100 = i -> i < 100;
        Predicate<Integer> greaterThan50 = i -> i > 50;
        Predicate<Integer> lessThanOrEqualTo50 = greaterThan50.negate(); // negate(),  i <= 50
        Predicate<Integer> isBetween50And100 = greaterThan50.and(lessThan100); // and()
        Predicate<Integer> isLessThan50or100 = lessThanOrEqualTo50.or(lessThan100); // or()

        assertThat(lessThanOrEqualTo50.test(30)).isTrue();
        assertThat(isBetween50And100.test(30)).isFalse();
        assertThat(isBetween50And100.test(70)).isTrue();
        assertThat(isLessThan50or100.test(150)).isFalse();
    }
}
