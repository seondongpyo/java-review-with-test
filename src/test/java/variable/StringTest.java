package variable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringTest {

    @Test
    @DisplayName("문자열 + '아무 타입' = 문자열")
    void stringPlusAnyTypeIsString() {
        byte byteValue = 10;
        short shortValue = 20;
        int intValue = 30;
        long longValue = 40;
        float floatValue = 3.14f;
        double doubleValue = 11.19;
        char charValue = 'A';
        String stringValue = "String";

        assertThat(stringValue + byteValue).isInstanceOf(String.class);
        assertThat(stringValue + shortValue).isInstanceOf(String.class);
        assertThat(stringValue + intValue).isInstanceOf(String.class);
        assertThat(stringValue + longValue).isInstanceOf(String.class);
        assertThat(stringValue + floatValue).isInstanceOf(String.class);
        assertThat(stringValue + doubleValue).isInstanceOf(String.class);
        assertThat(stringValue + charValue).isInstanceOf(String.class);
    }

    @Test
    @DisplayName("문자열의 생성 및 비교")
    void createNewString() {
        String stringValue1 = "string1"; // 리터럴로 생성
        String stringValue2 = new String("string1"); // 생성자로 생성
        String stringValue3 = new String("string2");
        String stringValue4 = stringValue3;

        // isEqualTo : 값 자체를 비교
        assertThat(stringValue1).isEqualTo(stringValue2); // "string1" == "string1"
        assertThat(stringValue1).isNotEqualTo(stringValue3); // "string1" != "string2"

        // isSameAs : 주소 값을 비교
        assertThat(stringValue1).isNotSameAs(stringValue2);
        assertThat(stringValue2).isNotSameAs(stringValue3);

        assertThat(stringValue3).isEqualTo(stringValue4);
        assertThat(stringValue3).isSameAs(stringValue4);
    }

    @Test
    @DisplayName("문자열은 대소문자를 구분한다")
    void stringIsCaseSensitive() {
        String mixedCase = "String is Case Sensitive";
        String upperCase = "STRING IS CASE SENSITIVE";
        String lowerCase = "string is case sensitive";

        assertThat(mixedCase).isNotEqualTo(upperCase).isNotEqualTo(lowerCase); // 문자열은 대소문자를 구분한다
        assertThat(upperCase).isUpperCase(); // upperCase는 대문자로 구성된 문자열
        assertThat(lowerCase).isLowerCase(); // lowerCase는 소문자로 구성된 문자열

        assertThat(mixedCase.toUpperCase()).isEqualTo(upperCase); // String.toUpperCase() : 문자열을 모두 대문자로 변환
        assertThat(mixedCase.toLowerCase()).isEqualTo(lowerCase); // String.toLowerCase() : 문자열을 모두 소문자로 변환

        assertThat(mixedCase.equalsIgnoreCase(upperCase)).isTrue(); // String.equalsIgnoreCase() : 대소문자 구분 없이 비교하기
        assertThat(mixedCase).isEqualToIgnoringCase(upperCase).isEqualToIgnoringCase(lowerCase); // 대소문자 구분 없이 비교하기
    }
}
