package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class MethodReferenceTest {

    @Test
    @DisplayName("메서드 참조")
    void methodReference() {
        // 문자열을 정수로 변환하는 람다식
//        Function<String, Integer> fn = s -> Integer.parseInt(s);

        // 람다식이 하나의 메서드만 호출하는 경우, '메서드 참조'라는 방법으로 람다식을 간략히 할 수 있다
        // 메서드 참조 : '클래스이름::메서드이름' 또는 '참조변수::메서드이름'
        Function<String, Integer> fn = Integer::parseInt;

        assertThat(fn.apply("10")).isEqualTo(10);
    }

    @Test
    @DisplayName("생성자의 메서드 참조")
    void methodReferenceConstructor() {
        // 생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다
//        Supplier<NewClass1> s = () -> new NewClass1(); // 람다식
        Supplier<NewClass1> supplier = NewClass1::new; // 메서드 참조

        // 매개변수가 있는 생성자일 경우, 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 된다
//        Function<String, NewClass2> function = s -> new NewClass2(s); // 람다식
        Function<String, NewClass2> function = NewClass2::new; // 메서드 참조
        NewClass2 nc = function.apply("홍길동");

        assertThat(supplier.get()).isInstanceOf(NewClass1.class);
        assertThat(nc.name).isEqualTo("홍길동");
    }

    static class NewClass1 {}
    static class NewClass2 {
        String name;

        public NewClass2(String name) {
            this.name = name;
        }
    }
}
