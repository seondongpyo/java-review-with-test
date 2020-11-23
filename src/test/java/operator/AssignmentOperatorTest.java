package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssignmentOperatorTest {

    @Test
    @DisplayName("대입 연산자")
    void assignmentOperator() {
        int value1 = 10;

        assertThat(value1).isEqualTo(10);
        assertThat(value1 += 10).isEqualTo(20);
        assertThat(value1 -= 5).isEqualTo(15);
        assertThat(value1 *= 10).isEqualTo(150);
        assertThat(value1 /= 5).isEqualTo(30);
        assertThat(value1 %= 4).isEqualTo(2);
        assertThat(value1).isEqualTo(2);

        int value2 = 8;
        assertThat(value2 <<= 2).isEqualTo(32); // 0000 1000 << 2 = 0010 0000
        assertThat(value2 >>= 3).isEqualTo(4); // 0010 0000 >> 3 = 0000 0100
        assertThat(value2 &= 7).isEqualTo(4); // 0100 & 0111 = 0100
        assertThat(value2 |= 11).isEqualTo(15);  // 0100 | 1011 = 1111
        assertThat(value2 ^= 2).isEqualTo(13); // 1111 ^ 0010 = 1101
        assertThat(value2 >>>= 2).isEqualTo(3); // 1101 >>> 2 = 0011
    }

}
