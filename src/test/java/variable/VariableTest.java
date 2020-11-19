package variable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VariableTest {

    private byte byteValue;
    private short shortValue;
    private int intValue;
    private long longValue;
    private float floatValue;
    private double doubleValue;
    private char charValue;
    private boolean booleanValue;
    private String stringValue;

    @Test
    @DisplayName("인스턴스 변수는 자동으로 기본값으로 초기화된다")
    void primitiveTypeMemberVariables() {
        assertThat(byteValue).isEqualTo((byte) 0);
        assertThat(shortValue).isEqualTo((short) 0);
        assertThat(intValue).isEqualTo(0);
        assertThat(longValue).isEqualTo(0L);
        assertThat(floatValue).isEqualTo(0.0f);
        assertThat(doubleValue).isEqualTo(0.0d);
        assertThat(charValue).isEqualTo('\u0000');
        assertThat(booleanValue).isEqualTo(false);
        assertThat(stringValue).isEqualTo(null);
    }

    @Test
    @DisplayName("기본형 타입 - byte")
    void primitiveTypeByte() {
        byte byteValue = 10;

        assertThat(byteValue).isEqualTo((byte) 10);
    }

    @Test
    @DisplayName("기본형 타입 - short")
    void primitiveShortType() {
        Short shortValue = 10;

        assertThat(shortValue).isEqualTo((short) 10);
    }

    @Test
    @DisplayName("기본형 타입 - int")
    void primitiveIntType() {
        int intValue = 10000;

        assertThat(intValue).isEqualTo(10000);
    }

    @Test
    @DisplayName("기본형 타입 - long")
    void primitiveLongType() {
        long longValue = 1000000000L;

        assertThat(longValue).isEqualTo(1000000000L);
    }

    @Test
    @DisplayName("기본형 타입 - float")
    void primitiveFloatType() {
        float floatValue = 3.14f;

        assertThat(floatValue).isEqualTo(3.14f);
        assertThat(floatValue).isNotEqualTo(3.14); // 접미사 f를 생략하면 double 타입으로 자동 형변환
    }

    @Test
    @DisplayName("기본형 타입 - double")
    void primitiveDoubleType() {
        double doubleValue = 11.19421538;

        assertThat(doubleValue).isEqualTo(11.19421538); // 접미사 생략 가능
        assertThat(doubleValue).isEqualTo(11.19421538d);
    }

    @Test
    @DisplayName("기본형 타입 - boolean")
    void primitiveBooleanType() {
        boolean booleanTrue = true;
        boolean booleanFalse = false;

        assertThat(booleanTrue).isTrue();
        assertThat(booleanFalse).isFalse();
    }

    @Test
    @DisplayName("기본형 타입 - char")
    void primitiveCharType() {
        char charValue = 'A';

        assertThat(charValue).isEqualTo('A');
    }

    @Test
    @DisplayName("char와 String은 다른 타입이다")
    void charIsNotEqualToString() {
        char charValue = 'A';
        String stringValue = "A";

        assertThat(charValue).isNotEqualTo(stringValue);
    }

    @Test
    @DisplayName("참조형 타입 - String")
    void referenceStringType() {
        String stringValue = "This is String value";

        assertThat(stringValue).isEqualTo("This is String value");
    }

    @Test
    @DisplayName("String은 대소문자를 구분한다")
    void stringIsCaseSensitive() {
        String stringValue = "String is case sensitive";

        assertThat(stringValue).isNotEqualTo("string is case sensitive");
        assertThat(stringValue).isNotEqualTo("STRING IS CASE SENSITIVE");
    }

    @Test
    @DisplayName("자료형의 최소값과 최대값")
    void variableMinMaxValue() {
        assertThat(Byte.MIN_VALUE).isEqualTo((byte) -128);
        assertThat(Byte.MAX_VALUE).isEqualTo((byte) 127);
        assertThat(Short.MIN_VALUE).isEqualTo((short) -32768);
        assertThat(Short.MAX_VALUE).isEqualTo((short) 32767);
        assertThat(Integer.MIN_VALUE).isEqualTo(-2147483648);
        assertThat(Integer.MAX_VALUE).isEqualTo(2147483647); // 약 21억
        assertThat(Long.MIN_VALUE).isEqualTo(-9223372036854775808L);
        assertThat(Long.MAX_VALUE).isEqualTo(9223372036854775807L); // 약 922경
        assertThat(Float.MIN_VALUE).isEqualTo(1.4E-45f);
        assertThat(Float.MAX_VALUE).isEqualTo(3.4028235E38f);
        assertThat(Double.MIN_VALUE).isEqualTo(4.9E-324);
        assertThat(Double.MAX_VALUE).isEqualTo(1.7976931348623157E308);
    }
}
