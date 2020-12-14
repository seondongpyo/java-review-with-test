package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringBufferTest {

    @Test
    @DisplayName("StringBuffer 클래스")
    void stringBuffer() {
        /*
            << StringBuffer 클래스 >>
            - 인스턴스를 생성할 때 지정된 문자열을 변경할 수 있다
            - 내부적으로 문자열 편집을 위한 버퍼를 가지고 있다
            - 인스턴스를 생성할 때, 적절한 길이의 char형 배열이 생성되고
              이 배열은 문자열을 저장하고 편집하기 위한 공간(buffer)으로 사용된다
            - 인스턴스 생성 시, 인스턴스에 저장될 문자열의 길이를 고려하여 충분히 여유있는 크기로 지정하는 것이 좋다
              버퍼의 크기를 지정해주지 않으면 16개의 문자를 저장할 수 있는 크기의 버퍼를 생성한다
         */

        StringBuffer sb1 = new StringBuffer(); // 버퍼의 크기를 지정하지 않으면 버퍼의 크기는 16이 된다
        /*
            public StringBuffer() {
                super(16);
            }
         */

        StringBuffer sb2 = new StringBuffer("str"); // 지정한 문자열의 길이보다 16이 더 크게 버퍼를 생성한다
        /*
            public StringBuffer(String str) {
                super(str.length() + 16);
                this.append(str);
            }
         */

        StringBuffer str = sb1.append("str"); // append() : 자신의 주소를 반환한다
        StringBuffer sb3 = sb2.append("str"); // sb2의 주소를 sb3에 저장
        sb3.append("str");

        assertThat(str.toString()).isEqualTo("str");
        assertThat(sb2.toString()).isEqualTo("strstrstr");
        assertThat(sb3.toString()).isEqualTo("strstrstr");
    }

    @Test
    @DisplayName("StringBuffer의 비교")
    void stringBufferComparison() {
        /*
            << StringBuffer의 비교 >>
            - String 클래스에서는 equals 메서드를 오버라이딩해서 문자열의 내용을 비교하도록 구현되어 있지만,
              StringBuffer 클래스는 equals 메서드를 오버라이딩하지 않기 때문에
              equals 메서드를 사용해도 등가비교연산자(==)로 비교한 것과 같은 결과가 나온다
            - 반면에 toString 메서드는 오버라이딩되어 있어서, toString 메서드를 호출하면
              StringBuffer 인스턴스가 담고 있는 문자열을 String으로 반환한다
              따라서 StringBuffer 인스턴스에 담긴 문자열을 비교하기 위해서는 toString 메서드를 호출하여
              String 인스턴스를 얻은 다음, 여기에 equals 메서드를 호출하여 비교해야 한다
         */
        StringBuffer sb1 = new StringBuffer("str");
        StringBuffer sb2 = new StringBuffer("str");
        assertThat(sb1 == sb2).isFalse(); // == 비교 (false)
        assertThat(sb1.equals(sb2)).isFalse(); // StringBuffer의 equals 비교 : == 비교와 동일 (false)

        String str1 = sb1.toString();
        String str2 = sb2.toString();
        assertThat(str1 == str2).isFalse(); // == 비교 (false)
        assertThat(str1.equals(str2)).isTrue(); // String의 equals 비교 (true)
    }

    @Test
    @DisplayName("StringBuffer 클래스의 메서드")
    void stringBufferMethods() {
        StringBuffer sb = new StringBuffer("stringBuffer");

        assertThat(sb.capacity()).isEqualTo(28); // 버퍼의 크기를 반환 (문자열 길이(12)보다 +16)
        assertThat(sb.charAt(1)).isEqualTo('t'); // 인덱스(1)에 해당하는 문자 반환
        assertThat(sb.delete(3, 5).toString()).isEqualTo("strgBuffer"); // 시작 위치(3)부터 끝 위치(5) 사이에 있는 문자를 제거 (단, 끝 위치는 포함 안 됨)
        assertThat(sb.deleteCharAt(5).toString()).isEqualTo("strgBffer"); // 인덱스(5)에 해당하는 문자 제거
        assertThat(sb.insert(3, "in").toString()).isEqualTo("stringBffer"); // 지정된 위치(3)에 두 번째 매개변수로 받은 값을 문자열로 변환하여 추가
        assertThat(sb.insert(7, 'u').toString()).isEqualTo("stringBuffer");
        assertThat(sb.insert(12, 123).toString()).isEqualTo("stringBuffer123");
        assertThat(sb.reverse().toString()).isEqualTo("321reffuBgnirts"); // 문자열 순서를 거꾸로 나열하기
        assertThat(sb.replace(0, 3, "456").toString()).isEqualTo("456reffuBgnirts"); // 지정된 범위의 문자들을 주어진 문자들로 변환
        assertThat(sb.substring(3, 8).toString()).isEqualTo("reffu"); // 지정된 범위 내의 문자열을 String으로 반환
        assertThat(sb.substring(8).toString()).isEqualTo("Bgnirts");
    }
}
