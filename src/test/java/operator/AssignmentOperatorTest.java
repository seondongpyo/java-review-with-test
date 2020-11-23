package operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssignmentOperatorTest {

    @Test
    @DisplayName("대입 연산자")
    void assignmentOperator() {
        int intValue = 10;

        assertThat(intValue).isEqualTo(10);
        assertThat(intValue += 10).isEqualTo(20);
        assertThat(intValue -= 5).isEqualTo(15);
        assertThat(intValue *= 10).isEqualTo(150);
        assertThat(intValue /= 5).isEqualTo(30);
        assertThat(intValue %= 4).isEqualTo(2);
        assertThat(intValue).isEqualTo(2);
    }

}
