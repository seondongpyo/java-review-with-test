package classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("빈 문자열 (empty string)")
    void emptyString() {
        char[] charArray1 = new char[0];
        char[] charArray2 = {};
        String str1 = ""; // 빈 문자열로 초기화, 길이가 0인 char형 배열을 저장하고 있다
        String str2 = new String(charArray1);
        String str3 = new String("");

        assertThat(charArray1.length).isZero();
        assertThat(charArray2.length).isZero();
        assertThat(str1.length()).isZero();
        assertThat(str2.length()).isZero();
        assertThat(str3.length()).isZero();
    }

    @Test
    @DisplayName("(last)indexOf : 문자열 내에서 주어진 문자의 위치를 반환 (못 찾으면 -1 반환)")
    void indexOf() {
        // index :      0123456789
        // lastIndex :  9876543210
        String str =   "Hello Java";

        // indexOf : 문자열의 앞부터 시작 (왼쪽 -> 오른쪽)
        assertThat(str.indexOf("l")).isEqualTo(2); // 문자열의 처음부터 시작하여 첫 번째로 만나는 문자의 위치를 알려준다
        assertThat(str.indexOf("Hi")).isEqualTo(-1); // "Hi"라는 문자열은 포함되어 있지 않다
        assertThat(str.indexOf('a', 8)).isEqualTo(9); // 인덱스 8부터 시작하여 'a'라는 문자의 위치를 찾는다
        assertThat(str.indexOf("He", 2)).isEqualTo(-1); // 인덱스 2 이후에 "He"라는 문자열은 없다

        // lastIndexOf : 문자열의 뒤부터 시작 (오른쪽 -> 왼쪽)
        assertThat(str.lastIndexOf('l')).isEqualTo(3); // 문자열의 끝부터 시작하여 첫 번째로 만나는 문자의 위치를 알려준다
        assertThat(str.lastIndexOf("JavaScript")).isEqualTo(-1);
        assertThat(str.lastIndexOf('a', 8)).isEqualTo(7);
        assertThat(str.lastIndexOf("Java", 4)).isEqualTo(-1);
    }

    @Test
    @DisplayName("charAt : 문자열 내에서 지정된 위치에 있는 문자를 반환")
    void charAt() {
        // index :    0123456789
        String str = "Hello Java";

        assertThat(str.charAt(3) == 'l').isTrue();
        assertThat(str.charAt(5) == ' ').isTrue();
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            str.charAt(10); // 범위에 없는 인덱스의 문자의 반환을 요청 시 예외 발생
        });
    }

    @Test
    @DisplayName("concat : 문자열 이어 붙이기")
    void concat() {
        String str1 = "Hello";
        String str2 = "Java";

        assertThat(str1.concat(str2)).isEqualTo("HelloJava");
    }

    @Test
    @DisplayName("equalsIgnoreCase : 대소문자 구별 없이 문자열 비교하기")
    void equalsIgnoreCase() {
        String str1 = "hello java";
        String str2 = "HELLO JAVA";
        String str3 = "HeLlO jAvA";

        assertThat(str1.equalsIgnoreCase(str2)).isTrue();
        assertThat(str2.equalsIgnoreCase(str3)).isTrue();
        assertThat(str3.equalsIgnoreCase(str1)).isTrue();
        assertThat(str1).isEqualToIgnoringCase(str2);
        assertThat(str2).isEqualToIgnoringCase(str3);
        assertThat(str3).isEqualToIgnoringCase(str1);
    }

    @Test
    @DisplayName("startsWith & endsWith : 특정 문자열로 시작하거나 또는 끝나는지 확인")
    void startsEndsWith() {
        String str = "Hello Java";

        assertThat(str.startsWith("He")).isTrue();
        assertThat(str.endsWith("va")).isTrue();
        assertThat(str).startsWith("He");
        assertThat(str).endsWith("va");
    }

    @Test
    @DisplayName("replace(All) : 문자열 치환하기")
    void replace() {
        String str = "AABBCCBBCCAA";

        assertThat(str.replace('A', 'a')).isEqualTo("aaBBCCBBCCaa");
        assertThat(str.replace("B", "b")).isEqualTo("AAbbCCbbCCAA");
        assertThat(str.replaceAll("CC", "cc")).isEqualTo("AABBccBBccAA");
    }
}