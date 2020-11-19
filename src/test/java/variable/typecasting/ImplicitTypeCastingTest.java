package variable.typecasting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImplicitTypeCastingTest{

    @Test
    @DisplayName("묵시적 형변환 - byte to short")
    void castByteToShort() {
        byte byteValue = 127;
        short shortValue = byteValue;

        assertThat(shortValue).isEqualTo((short) 127);
    }

    @Test
    @DisplayName("묵시적 형변환 - short to int")
    void castShortToInt() {
        short shortValue = 32767;
        int intValue = shortValue;

        assertThat(intValue).isEqualTo(32767);
    }

    @Test
    @DisplayName("묵시적 형변환 - char to int")
    void castCharToInt() {
        char charValue = 'C';
        int intValue = charValue;

        assertThat(intValue).isEqualTo(67);
    }

    @Test
    @DisplayName("묵시적 형변환 - int to long")
    void castIntToLong() {
        int intValue = 2147483647;
        long longValue = intValue;

        assertThat(longValue).isEqualTo(2147483647L);
    }

    @Test
    @DisplayName("묵시적 형변환 - long to float")
    void castLongToFloat() {
        long longValue = 314L;
        float floatValue = longValue;

        assertThat(floatValue).isEqualTo(314.0f);
    }

    @Test
    @DisplayName("묵시적 형변환 - float to double")
    void castFloatToDouble() {
        float floatValue = 3.14f;
        double doubleValue = floatValue;

        assertThat(doubleValue).isGreaterThanOrEqualTo(3.14);
    }
}
