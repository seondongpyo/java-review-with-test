package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitOperatorTest {

    @Test
    @DisplayName("비트 단위 연산자")
    void bitwiseOperator() {
        // & : 이진수로 표현했을 때 양쪽의 값이 모두 1이면 1을, 그 외에는 0을 반환함 (비트 AND 연산)
        // | : 이진수로 표현했을 때 한쪽이라도 값이 1이면 1을, 그 외에는 0을 반환함 (비트 OR 연산)
        // ^ : 이진수로 표현했을 때 서로의 값이 다를 때는 1을, 같을 떄는 0을 반환함 (비트 NOR 연산)
        printLog("3       = " + getBinaryStringWithBitFormat(3)); // 0011
        printLog("5       = " + getBinaryStringWithBitFormat(5)); // 0101
        printLog("3 & 5   = " + getBinaryStringWithBitFormat(3 & 5)); // 0001 => 1
        printLog("3 | 5   = " + getBinaryStringWithBitFormat(3 | 5)); // 0111 => 7
        printLog("3 ^ 5   = " + getBinaryStringWithBitFormat(3 ^ 5)); // 0110 => 6
        assertThat(3 & 5).isEqualTo(1);
        assertThat(3 | 5).isEqualTo(7);
        assertThat(3 ^ 5).isEqualTo(6);
    }

    @Test
    @DisplayName("<< 비트 쉬프트 연산자")
    void signedLeftShiftOperator() {
        // x << n : x * 2^n (부호에 상관없이 왼쪽으로 이동시키며 빈 칸을 0으로 채움)
        printLog("8       = " + getBinaryStringWithBitFormat(8));        // 00001000
        printLog("8 << 1  = " + getBinaryStringWithBitFormat(8 << 1));   // 00010000 => 16 (= 8 * 2^1)
        printLog("8 << 2  = " + getBinaryStringWithBitFormat(8 << 2));   // 00100000 => 32 (= 8 * 2^2)
        printLog("8 << 3  = " + getBinaryStringWithBitFormat(8 << 3));   // 01000000 => 64 (= 8 * 2^3)
        assertThat(8 << 1).isEqualTo(16);
        assertThat(8 << 2).isEqualTo(32);
        assertThat(8 << 3).isEqualTo(64);
    }

    @Test
    @DisplayName(">> 비트 쉬프트 연산자")
    void signedRightShiftOperator() {
        // x >> n : x / 2^n
        // 양수인 경우 빈 칸을 0으로, 음수인 경우 부호를 유지하기 위해 빈 칸을 1로 채움
        printLog("8       = " + getBinaryStringWithBitFormat(8));        // 00001000
        printLog("8 >> 1  = " + getBinaryStringWithBitFormat(8 >> 1));   // 00000100 => 4 (= 8 / 2^1)
        printLog("8 >> 2  = " + getBinaryStringWithBitFormat(8 >> 2));   // 00000010 => 2 (= 8 / 2^2)
        printLog("8 >> 3  = " + getBinaryStringWithBitFormat(8 >> 3));   // 00000001 => 1 (= 8 / 2^3)
        // 8       = |00000000 00000000 00000000 00001000|
        //   >> 3  = |   00000 00000000 00000000 00000001|000
        // 8 >> 3  = |00000000 00000000 00000000 00000001|
        //            +++                                 ---
        //     (빈 칸을 0으로 채움)                  (오른쪽으로 밀려서 버려짐)
        assertThat(8 >> 1).isEqualTo(4);
        assertThat(8 >> 2).isEqualTo(2);
        assertThat(8 >> 3).isEqualTo(1);

        printLog("-8       = " + getBinaryStringWithBitFormat(-8));        // 11111111 11111111 11111111 11111000
        printLog("-8 >> 1  = " + getBinaryStringWithBitFormat(-8 >> 1));   // 11111111 11111111 11111111 11111100
        printLog("-8 >> 2  = " + getBinaryStringWithBitFormat(-8 >> 1));   // 11111111 11111111 11111111 11111110
        printLog("-8 >> 3  = " + getBinaryStringWithBitFormat(-8 >> 1));   // 11111111 11111111 11111111 11111111
        // -8       = |11111111 11111111 11111111 11111000|
        //    >> 3  = |   11111 11111111 11111111 11111111|100
        // -8 >> 3  = |11111111 11111111 11111111 11111111|
        //             +++                                 ---
        //  (부호를 유지하기 위해 1로 채움)             (오른쪽으로 밀려서 버려짐)
        assertThat(-8 >> 1).isEqualTo(-4);
        assertThat(-8 >> 2).isEqualTo(-2);
        assertThat(-8 >> 3).isEqualTo(-1);
    }

    @Test
    @DisplayName(">>> 비트 쉬프트 연산자")
    void unsignedRightShiftOperator() {
        // x >>> n : x * 2^n (부호에 상관없이 항상 빈 칸을 0으로 채움)
        // n개의 비트는 오른쪽으로 밀려서 버려지고, 맨 왼쪽에는 n개만큼 최상위 부호비트와 동일한 값으로 채워짐
        printLog("16        = " + getBinaryStringWithBitFormat(16));       // 00000000 00000000 00000000 00010000
        printLog("16 >>> 1  = " + getBinaryStringWithBitFormat(16 >>> 1)); // 00000000 00000000 00000000 00001000
        printLog("16 >>> 2  = " + getBinaryStringWithBitFormat(16 >>> 2)); // 00000000 00000000 00000000 00000100
        printLog("16 >>> 3  = " + getBinaryStringWithBitFormat(16 >>> 3)); // 00000000 00000000 00000000 00000010
        // 16       = |00000000 00000000 00000000 00010000|
        //    >>> 3 = |   00000 00000000 00000000 00000010|000
        // 16 >>> 3 = |00000000 00000000 00000000 00000010|
        //             +++                                 ---
        //   (부호에 상관없이 항상 0으로 채움)          (오른쪽으로 밀려서 버려짐)

        printLog("-16        = " + getBinaryStringWithBitFormat(-16));       // 11111111 11111111 11111111 11110000
        printLog("-16 >>> 1  = " + getBinaryStringWithBitFormat(-16 >>> 1)); // 01111111 11111111 11111111 11111000
        printLog("-16 >>> 2  = " + getBinaryStringWithBitFormat(-16 >>> 2)); // 00111111 11111111 11111111 11111100
        printLog("-16 >>> 3  = " + getBinaryStringWithBitFormat(-16 >>> 3)); // 00011111 11111111 11111111 11111110
        // -16       = |11111111 11111111 11111111 11110000|
        //     >>> 3 = |   11111 11111111 11111111 11111110|000
        // -16 >>> 3 = |00011111 11111111 11111111 11111110|
        //              +++                                 ---
        //   (부호에 상관없이 항상 0으로 채움)           (오른쪽으로 밀려서 버려짐)
    }

    private void printLog(String log) {
        System.out.println(log);
    }

    private String getBinaryStringWithBitFormat(int number) {
        StringBuilder builder = new StringBuilder();

        String leftPaddedBinaryString = leftPadBinaryStringByZero(number);
        for (int i = 0;  i < 32; i+= 8) {
            builder.append(leftPaddedBinaryString.substring(i, i + 8));
            builder.append(" ");
        }

        return builder.toString();
    }

    private String leftPadBinaryStringByZero(int number) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= Integer.numberOfLeadingZeros(number); i++) {
            builder.append(0);
        }
        builder.append(Integer.toBinaryString(number));

        return builder.toString();
    }

}
