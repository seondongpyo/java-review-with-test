package decisionmaking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SwitchTest {

    @Test
    @DisplayName("조건문 - switch")
    void switchCase() {
        int score = 95;
        String grade;

        switch (score / 10) {
            case 10:
                grade = "S";
                break;
            case 9:
                grade = "A";
                break;
            case 8:
                grade = "B";
                break;
            case 7:
                grade = "C";
                break;
            case 6:
                grade = "D";
                break;
            default:
                grade = "F";
        }

        assertThat(grade).isEqualTo("A");
    }
}
