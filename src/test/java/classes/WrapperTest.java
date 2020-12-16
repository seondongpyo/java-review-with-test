package classes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WrapperTest {

    @Test
    @DisplayName("래퍼 클래스 (Wrapper class)")
    void wrapper() {
        /*
            << Wrapper 클래스 >>
            - 프로그램에 따라 기본 타입의 데이터를 객체로 취급해야 하는 경우가 있다
              예를 들어, 메소드의 인수로 객체 타입만이 요구되면, 기본 타입의 데이터를 그대로 사용할 수 없다
              이 때에는 기본 타입의 데이터를 먼저 객체로 변환한 후 작업을 수행해야 한다
            - 이렇게 8개의 기본 타입에 해당하는 데이터를 객체로 포장해 주는 클래스를 래퍼 클래스(Wrapper class)라고 한다
              Wrapper 클래스는 각각의 타입에 해당하는 데이터(또는 문자열)를 인수로 전달받아, 해당 값을 가지는 객체로 만들어 준다
              이러한 래퍼 클래스는 모두 java.lang 패키지에 포함되어 제공된다
            - 이 때 주의해야 할 점은 생성자의 매개변수로 문자열을 제공할 때, 각 자료형에 알맞은 문자열을 사용해야 한다
              그렇지 않을 경우 예외가 발생한다
         */

        Boolean bool = new Boolean(true);
        Character c = new Character('A');
        Byte b = new Byte((byte) 10);
        Short s = new Short((short) 20);
        Integer i = new Integer(100);
        Long l = new Long(200);
        Float f = new Float(3.14f);
        Double d = new Double(5.789);

        assertThat(bool).isTrue();
        assertThat(c).isEqualTo('A');
        assertThat(b).isEqualTo((byte) 10);
        assertThat(s).isEqualTo((short) 20);
        assertThat(i).isEqualTo(100);
        assertThat(l).isEqualTo(200L);
        assertThat(f).isEqualTo(3.14f);
        assertThat(d).isEqualTo(5.789);

        // 자료형에 맞지 않은 문자열을 전달할 경우 예외 발생
        assertThrows(NumberFormatException.class, () -> {
            Byte byteValue = new Byte("200"); // byte 범위를 벗어나는 값을 전달할 경우
            Integer intValue = new Integer("1.0"); // 정수가 아닌 실수 값을 전달할 경우
        });
    }

    @Test
    @DisplayName("래퍼 클래스 예제")
    void wrapperExercise() {
        Integer integer1 = new Integer(100);
        Integer integer2 = new Integer(100);
        Integer integer3 = new Integer(200);
        Integer integer4 = new Integer(50);

        assertThat(integer1 == integer2).isFalse(); // 주소 값을 비교
        assertThat(integer1.equals(integer2)).isTrue(); // 객체가 가지고 있는 값을 비교

        // compareTo() : 매개변수보다 값이 작으면 -1, 같으면 0, 크면 0을 반환
        /*
            public int compareTo(Integer anotherInteger) {
                return compare(this.value, anotherInteger.value);
            }

            public static int compare(int x, int y) {
                return x < y ? -1 : (x == y ? 0 : 1);
            }
         */
        assertThat(integer1.compareTo(integer3)).isEqualTo(-1);
        assertThat(integer1.compareTo(integer2)).isZero();
        assertThat(integer1.compareTo(integer4)).isEqualTo(1);
    }
}
