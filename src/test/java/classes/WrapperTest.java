package classes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    @DisplayName("래퍼 클래스 비교 (==, equals)")
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

    @Test
    @DisplayName("문자열을 숫자로 변환하기")
    void convertStringToNumber() {
        assertThat((byte) 10).isEqualTo(Byte.parseByte("10")).isEqualTo(Byte.valueOf("10"));
        assertThat((short) 200).isEqualTo(Short.parseShort("200")).isEqualTo(Short.valueOf("200"));
        assertThat(10000).isEqualTo(Integer.parseInt("10000")).isEqualTo(Integer.valueOf("10000"));
        assertThat(20000L).isEqualTo(Long.parseLong("20000")).isEqualTo(Long.valueOf("20000"));
        assertThat(3.14f).isEqualTo(Float.parseFloat("3.14")).isEqualTo(Float.valueOf("3.14"));
        assertThat(10.1523).isEqualTo(Double.parseDouble("10.1523")).isEqualTo(Double.valueOf("10.1523"));

        // 다른 진법 숫자로 변환하기
        assertThat(Integer.parseInt("100", 2)).isEqualTo(4); // 2진법, 100(2) = 4
        assertThat(Integer.parseInt("100", 8)).isEqualTo(64); // 8진법, 100(8) = 64
        assertThat(Integer.parseInt("100", 16)).isEqualTo(256); // 16진법, 100(16) = 256

        assertThat(Integer.parseInt("FF", 16)).isEqualTo(255); // 16진법에서는 A ~ F 사이의 문자도 허용
        assertThatThrownBy(() -> {
            Integer.parseInt("FF"); // 단, 진법을 생략할 경우 10진수로 간주하므로 예외 발생
        }).isInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("오토박싱(AutoBoxing)과 오토언박싱(AutoUnboxing)")
    void autoboxingUnboxing() {
        /*
            << 오토박싱(AutoBoxing)과 오토언박싱(AutoUnboxing) >>
            - 오토박싱 : 기본형 값을 래퍼 클래스의 객체로 자동 변환해주는 것
            - 언박싱 : 래퍼 클래스 객체를 기본형 값으로 자동 변환해주는 것

            - JDK 1.5부터 박싱과 언박싱이 필요한 상황에서 자바 컴파일러가 이를 자동으로 처리해주기 때문에
              기본형과 참조형 간의 연산이 가능해졌다 (컴파일러가 자동으로 변환하는 코드를 추가해준다)

            - 오토박싱을 이용하면 new 키워드를 사용하지 않고도 자동으로 래퍼 클래스 인스턴스를 생성할 수 있으며,
              반대로 오토언박싱을 이용하여 인스턴스에 저장된 값을 바로 참조할 수 있다

            - 래퍼 클래스의 비교 연산도 오토언박싱을 통해 가능해지지만, 인스턴스에 저장된 값의 동등 여부 판단은
              비교 연산자인 동등 연산자(==)를 사용해서는 안 되며, equals() 메소드를 사용해야만 한다
              래퍼 클래스도 객체이므로 동등 연산자(==)를 사용하게 되면, 두 인스턴스의 값을 비교하는 것이 아니라 두 인스턴스의 주소값을 비교하게 된다
              그러므로 인스턴스에 저장된 값의 동등 여부를 정확히 판단하려면 equals() 메소드를 사용해야만 한다
         */
        int i = 100; // 기본형
        Integer integer = new Integer(200); // 참조형

        List<Integer> integerList = new ArrayList<>();
        integerList.add(300); // 오토박싱. 300 → new Integer(300)
        int intValue = integerList.get(0); // 오토언박싱. new Integer(300) → 300

        assertThat(i + integer).isEqualTo(300); // 기본형과 참조형의 연산 (JDK 1.5~)
        assertThat(intValue).isEqualTo(300);
    }
}