package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnaryOperatorTest {

    @Test
    @DisplayName("부호 연산자")
    void signOperator() {
        int intValue1 = 3;
        int intValue2 = -2;

        assertThat(intValue1).isPositive();
        assertThat(intValue2).isNegative();
        assertThat(intValue1 + intValue2).isPositive();
        assertThat(intValue1 + intValue2).isNegative();
    }

    @Test
    @DisplayName("증감 연산자")
    void incrementAndDecrementOperator() {
        int intValue = 0;

        assertThat(intValue++).isEqualTo(0);
        assertThat(intValue).isEqualTo(1);
        assertThat(++intValue).isEqualTo(2);
        assertThat(intValue).isEqualTo(2);

        assertThat(intValue--).isEqualTo(2);
        assertThat(intValue).isEqualTo(1);
        assertThat(--intValue).isEqualTo(0);
        assertThat(intValue).isEqualTo(0);
    }

}
