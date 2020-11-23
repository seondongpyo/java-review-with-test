package decisionmaking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IfTest {

    @Test
    @DisplayName("조건문 - if ~ else")
    void if_else() {
        int age = 20;
        String person;

        if (age > 19) {
            person = "성인";
        } else {
            person = "청소년";
        }

        assertThat(person).isEqualTo("성인");
    }

    @Test
    @DisplayName("삼항 연산자를 이용한 조건문")
    void if_else_by_ternary_operator() {
        int age = 20;
        String person = (age > 19) ? "성인" : "청소년";

        assertThat(person).isEqualTo("성인");
    }

    @Test
    @DisplayName("조건문 - if ~ else if ~ else")
    void if_elseif_else() {
        int score = 85;
        String grade;

        if (score == 100) {
            grade = "S";
        } else if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        assertThat(grade).isEqualTo("B");
    }
}
