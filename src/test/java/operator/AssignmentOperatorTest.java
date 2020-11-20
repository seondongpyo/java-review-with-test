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
        assertThat(intValue *= 10).isEqualTo(200);
        assertThat(intValue /= 5).isEqualTo(40);
        assertThat(intValue %= 3).isEqualTo(1);
    }

}
