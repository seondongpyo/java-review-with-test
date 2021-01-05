package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaTest {

    /*
        << 람다식(Lambda Expression) >>
        - 메서드를 하나의 '식(expression)'으로 표현한 것
        - 함수를 간략하면서도 명확하게 표현할 수 있게 해준다
        - 클래스, 인스턴스를 새로 생성하지 않고도 람다식 자체만으로 메서드의 역할을 대신할 수 있다
        - 메서드의 매개변수로 전달되거나 메서드의 결과로 반환될 수 있다 -> 메서드를 변수처럼 다루는 것이 가능하다
     */

    /*
        << 람다식 작성하기 >>
        - 메서드에서 이름과 반환타입을 제거한다
        - 매개변수 선언부와 몸통({}) 사이에 '->'를 추가한다
        - 반환값이 있는 메서드의 경우, return 대신 '식'으로 대신할 수 있으며,
          '식'의 연산결과가 자동적으로 반환값이 된다('문장'이 아닌 '식'이므로 끝에 세미콜론 ';'을 붙이지 않는다)
        - 람다식에 선언된 매개변수의 타입은 추론이 가능한 경우는 생략할 수 있는데, 대부분의 경우에 생략 가능하다
        - 선언된 매개변수가 하나뿐인 경우에는 소괄호 '()'를 생략할 수 있으나, 매개변수의 타입이 있으면 괄호를 생략할 수 없다
        - 괄호 안의 문장이 하나일 때는 중괄호 '{}'를 생략할 수 있으며, 문장의 끝에 세미콜론;을 붙이지 않는다
          그러나, 괄호 안의 문장이 return 문일 경우 중괄호 '{}'를 생략할 수 없다
     */

    @Test
    @DisplayName("람다식 작성하기 - 두 숫자를 더한 값 구하기")
    void lambda() {
        // 람다식 미사용
//        Calculator calculator = new Calculator() {
//            @Override
//            public int add(int a, int b) {
//                return a + b;
//            }
//        };

        // 람다식 사용
        Calculator calculator = (a, b) -> a + b;

        int addValue = calculator.add(10, 20);

        assertThat(addValue).isEqualTo(30);
    }

    @Test
    @DisplayName("람다식 작성하기 - 주사위 굴리기")
    void diceRolling() {
        Dice dice = () -> {
            return (int) (Math.random() * 6 + 1); // 'return'과 '{}' 생략 가능
        };

        int number = dice.roll();

        assertThat(number).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(6);
    }

    interface Calculator {
        int add(int a, int b);
    }

    interface Dice {
        int roll();
    }
    
}
