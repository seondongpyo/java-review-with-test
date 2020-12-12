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
}