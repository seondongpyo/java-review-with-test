package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringBuilderTest {

    @Test
    @DisplayName("StringBuilder 클래스")
    void stringBuilder() {
        /*
            << StringBuilder 클래스 >>
            - StringBuffer 클래스는 멀티쓰레드에 안전(thread safe)하도록 동기화되어 있기 때문에,
              멀티쓰레드로 작성된 프로그램이 아닌 경우 StringBuffer의 동기화는 불필요하게 성능만 떨어뜨린다
            - StringBuilder 클래스는 StringBuffer에서 쓰레드의 동기화만 뺀 클래스로,
              StringBuffer와 완전히 동일한 기능으로 작성되어 있다
            - 단, StringBuffer 클래스도 충분히 성능이 좋기 때문에 성능 향상이 반드시 필요한 경우를 제외하고는
              기존에 작성한 코드에서 StringBuffer를 StringBuilder로 굳이 바꿀 필요는 없다
         */

        StringBuilder sb1 = new StringBuilder("sb");
        StringBuilder sb2 = new StringBuilder("sb");
        assertThat(sb1 == sb2).isFalse(); // == 비교 (false)
        assertThat(sb1.equals(sb2)).isFalse(); // StringBuilder equals 비교 : == 비교와 동일 (false)

        String str1 = sb1.toString();
        String str2 = sb2.toString();
        assertThat(str1 == str2).isFalse(); // == 비교 (false)
        assertThat(str1.equals(str2)).isTrue(); // String의 equals 비교 (true)
    }

    @Test
    @DisplayName("StringBuilder 클래스의 메서드")
    void stringBufferMethods() {
        StringBuilder sb = new StringBuilder("StringBuilder");

        assertThat(sb.capacity()).isEqualTo(29); // 버퍼의 크기를 반환 (문자열 길이(13)보다 +16)
        assertThat(sb.charAt(6)).isEqualTo('B'); // 인덱스(6)에 해당하는 문자 반환
        assertThat(sb.delete(3, 6).toString()).isEqualTo("StrBuilder"); // 시작 위치(3)부터 끝 위치(6) 사이에 있는 문자를 제거 (단, 끝 위치는 포함 안 됨)
        assertThat(sb.deleteCharAt(5).toString()).isEqualTo("StrBulder"); // 인덱스(5)에 해당하는 문자 제거
        assertThat(sb.insert(3, "ing").toString()).isEqualTo("StringBulder"); // 지정된 위치(3)에 두 번째 매개변수로 받은 값을 문자열로 변환하여 추가
        assertThat(sb.insert(8, 'i').toString()).isEqualTo("StringBuilder");
        assertThat(sb.insert(13, 1234).toString()).isEqualTo("StringBuilder1234");
        assertThat(sb.reverse().toString()).isEqualTo("4321redliuBgnirtS"); // 문자열 순서를 거꾸로 나열하기
        assertThat(sb.replace(0, 4, "1234").toString()).isEqualTo("1234redliuBgnirtS"); // 지정된 범위의 문자들을 주어진 문자들로 변환
        assertThat(sb.substring(4, 11)).isEqualTo("redliuB"); // 지정된 범위 내의 문자열을 String으로 반환
        assertThat(sb.substring(11)).isEqualTo("gnirtS");
    }
}
