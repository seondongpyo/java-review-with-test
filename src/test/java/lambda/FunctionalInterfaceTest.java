package lambda;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalInterfaceTest {

    @Test
    @DisplayName("함수형 인터페이스 - 람다식을 다루기 위한 인터페이스")
    void functionalInterface() {
        /*
            << 함수형 인터페이스 >>
            - 람다식도 실제로는 익명 객체이고, 함수형 인터페이스를 구현한 익명 객체의 메서드와
              람다식의 매개변수의 타입, 개수 그리고 반환값이 일치하기 때문에
              익명 객체를 람다식으로 대체할 수 있다
            - 단, 함수형 인터페이스에는 오직 하나의 추상 메서드만 정의되어 있어야 한다는 제약이 있다
              그래야 람다식과 인터페이스의 메서드가 1:1로 연결될 수 있기 때문이다
         */
        
        // 함수형 인터페이스를 구현한 익명 클래스의 객체 생성
        NewFunction function1 = new NewFunction() {
            @Override
            public int max(int a, int b) {
                return Math.max(a, b);
            }
        };

        // 익명 객체는 다음 람다식으로 대체 가능
        NewFunction function2 = (int a, int b) -> Math.max(a, b);

        // 익명 객체의 메서드를 호출
        int value1 = function1.max(10, 20);
        int value2 = function2.max(20, 30);

        assertThat(value1).isEqualTo(20);
        assertThat(value2).isEqualTo(30);
    }

    // 함수형 인터페이스 정의
    @FunctionalInterface
    interface NewFunction {
        int max(int a, int b);
//        int min(int a, int b); //  함수형 인터페이스에는 하나의 추상 메서드만 정의되어 있어야 한다
    }
}
