package stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamFinalOperationTest {

    Stream<Student> getStudentStream() {
        return Stream.of(
                new Student("홍길동", 87),
                new Student("김길동", 72),
                new Student("박길동", 54),
                new Student("이길동", 78),
                new Student("최길동", 66)
        );
    }

    @Test
    @DisplayName("스트림의 최종연산 - 조건검사 (allMatch)")
    void stream_allMatch() {
        Stream<Student> studentStream1 = getStudentStream();
        Stream<Student> studentStream2 = getStudentStream();

        // allMatch(Predicate p) - 모든 요소가 조건과 일치하면 true 반환
        boolean nobodyScoredOver90 = studentStream1.allMatch(student -> student.getScore() <= 90);
        boolean allStudentsHaveNameAsGilDong = studentStream2.allMatch(student -> student.name.contains("길동"));

        assertThat(nobodyScoredOver90).isTrue();
        assertThat(allStudentsHaveNameAsGilDong).isTrue();
    }

    @Test
    @DisplayName("스트림의 최종연산 - 조건검사 (anyMatch)")
    void stream_anyMatch() {
        Stream<Student> studentStream1 = getStudentStream();
        Stream<Student> studentStream2 = getStudentStream();

        // anyMatch(Predicate p) - 하나의 요소라도 조건과 일치하면 true 반환
        boolean someoneFailedTest = studentStream1.anyMatch(student -> student.getScore() < 60);
        boolean someoneHasLastNameOfPyo = studentStream2.anyMatch(student -> student.getName().startsWith("표"));

        assertThat(someoneFailedTest).isTrue();
        assertThat(someoneHasLastNameOfPyo).isFalse();
    }

    @Test
    @DisplayName("스트림의 최종연산 - 조건검사 (noneMatch)")
    void stream_noneMatch() {
        Stream<Student> studentStream = getStudentStream();

        // noneMatch(Predicate p) - 모든 요소가 불일치하면 true 반환
        boolean nobodyGetGradeA = studentStream.noneMatch(student -> student.getScore() > 90);

        assertThat(nobodyGetGradeA).isTrue();
    }

    static class Student {
        String name;
        int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}
