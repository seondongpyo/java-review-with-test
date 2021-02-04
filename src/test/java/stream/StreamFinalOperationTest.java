package stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;
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

    @Test
    @DisplayName("스트림의 최종연산 - reduce (1)")
    void stream_reduce_1() {
        String[] strArr = {"Inheritance", "Lambda", "Stream", "Optional"};

        // Stream<String[]> -> IntStream
        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);

        // reduce('초기값', '처리할 연산') - 스트림의 요소를 줄여나가면서 연산을 수행하고 최종 결과를 반환
        // 초기값은 a에 저장됨
        int result1 = intStream1.reduce(0, (a, b) -> a + 1); // 초기값 0부터 1씩 더하기
        int result2 = intStream2.reduce(0, (a, b) -> a + b); // 초기값 0부터 모든 스트림의 요소를 더하기

        assertThat(result1).isEqualTo(4); // 1 + 1 + 1 + 1
        assertThat(result2).isEqualTo(31); // 11 + 6 + 6 + 8
    }

    @Test
    @DisplayName("스트림의 최종연산 - reduce (2)")
    void stream_reduce_2() {
        String[] strArr = {"Byte", "Short", "Integer", "Long", "Float", "Double"};

        // Stream<String[]> -> IntStream
        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);

        OptionalInt maxValue = intStream1.reduce(Integer::max); // 최대값
        OptionalInt minValue = intStream2.reduce(Integer::min); // 최소값

        assertThat(maxValue).isPresent();
        assertThat(maxValue.getAsInt()).isEqualTo(7);
        assertThat(minValue).isPresent();
        assertThat(minValue.getAsInt()).isEqualTo(4);
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
