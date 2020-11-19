package variable.typecasting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExplicitTypeCastingTest {

    @Test
    @DisplayName("명시적 형변환 - short to byte")
    void castShortToByte() {
        short shortValue = 127;
        byte byteValue = (byte) shortValue;

        assertThat(byteValue).isEqualTo((byte) 127);
    }

    @Test
    @DisplayName("명시적 형변환 - char to byte")
    void castCharToByte() {
        char charValue = 65;
        byte byteValue = (byte) charValue;

        assertThat(byteValue).isEqualTo((byte) 65);
    }

    @Test
    @DisplayName("명시적 형변환 - int to char")
    void castIntToChar() {
        int intValue = 65;
        char charValue = (char) intValue;

        assertThat(charValue).isEqualTo((char) 65);
        assertThat(charValue).isEqualTo('A');
        assertThat(charValue).isNotEqualTo("A");
    }

    @Test
    @DisplayName("명시적 형변환 - int to short")
    void castIntToShort() {
        int intValue = 1000;
        short shortValue = (short) intValue;

        assertThat(shortValue).isEqualTo((short) 1000);
    }

    @Test
    @DisplayName("명시적 형변환 - long to int")
    void castLongToInt() {
        long longValue = 2147483647L;
        int intValue = (int) longValue;

        assertThat(intValue).isEqualTo(2147483647);
    }

    @Test
    @DisplayName("명시적 형변환 - float to long")
    void castFloatToLong() {
        float floatValue = 3.402f;
        long longValue = (long) floatValue;

        assertThat(longValue).isEqualTo(3L);
    }

    @Test
    @DisplayName("명시적 형변환 - double to float")
    void castDoubleToFloat() {
        double doubleValue = 11.19;
        float floatValue = (float) doubleValue;

        assertThat(floatValue).isEqualTo(11.19f);
    }

    @Test
    @DisplayName("명시적 형변환 - double to int")
    void castDoubleToInt() {
        double doubleValue = 121.12345;
        int intValue = (int) doubleValue;

        assertThat(intValue).isEqualTo(121);
    }
}
