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
}
