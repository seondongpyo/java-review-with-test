package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringTest {

    @Test
    @DisplayName("String 클래스 - 문자열의 비교")
    void stringComparison() {
        String str1 = "abc"; // 문자열 리터럴 "abc"의 주소가 str1에 저장됨
        String str2 = "abc"; // 문자열 리터럴 "abc"의 주소가 str2에 저장됨
        String str3 = new String("abc"); // 새로운 String 클래스의 인스턴스 생성
        String str4 = new String("abc"); // 새로운 String 클래스의 인스턴스 생성

        // == : 주소를 비교
        assertThat(str1 == str2).isTrue();
        assertThat(str3 == str4).isFalse();

        // equals : 문자열의 내용을 비교
        assertThat(str1.equals(str2)).isTrue();
        assertThat(str3.equals(str4)).isTrue();
    }

    @Test
    @DisplayName("문자열 리터럴 (String 리터럴)")
    void stringLiteral() {
        /*
            << 문자열 리터럴 >>
            - 자바 소스 파일에 포함된 모든 문자열 리터럴은 컴파일 시에 클래스 파일에 저장되는데,
              이 때 같은 내용의 문자열 리터럴은 한 번만 저장된다
            - 문자열 리터럴도 String 인스턴스이고, 한 번 생성하면 내용을 변경할 수 없으니
              하나의 인스턴스를 공유하면 되기 때문이다
            - 클래스 파일이 클래스 로더에 의해 메모리에 올라갈 때, 클래스 파일의 리터럴들이
              JVM 내에 있는 '상수 저장소(constant pool)'에 저장되는데
              이 때 "abcdef"와 같은 문자열 리터럴은 자동적으로 생성되어 저장된다
         */
        String str1 = "abcdef";
        String str2 = "abcdef";
        String str3 = "abcdef";

        assertThat(str1 == str2).isTrue();
        assertThat(str2 == str3).isTrue();
        assertThat(str1 == str3).isTrue();
    }
}