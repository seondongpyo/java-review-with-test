package stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
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

    @Test
    @DisplayName("스트림의 최종연산 - collect(Collectors.toList())")
    void stream_collect_toList() {
        Stream<Student> studentStream = getStudentStream();

        // collect(Collectors.toList()) : 스트림을 컬렉션으로 변환
        List<String> studentNameList
                = studentStream.map(Student::getName).collect(Collectors.toList());

        assertThat(studentNameList).hasSize(5);
        assertThat(studentNameList).containsExactly("홍길동", "김길동", "박길동", "이길동", "최길동");
    }

    @Test
    @DisplayName("스트림의 최종연산 - collect(Collectors.toCollection())")
    void stream_collect_toCollection() {
        Stream<Student> studentStream = getStudentStream();

        // collect(Collectors.toCollection()) : 스트림을 특정 컬렉션으로 변환
        HashSet<Integer> studentScoreSet
                = studentStream.map(Student::getScore).collect(Collectors.toCollection(HashSet::new));

        assertThat(studentScoreSet).contains(87, 72, 54, 78, 66);
    }

    @Test
    @DisplayName("스트림의 최종연산 - collect(Collectors.toMap())")
    void stream_collect_toMap() {
        Stream<Student> studentStream = getStudentStream();

        // collect(Collectors.toMap())
        // : 스트림을 Map으로 변환하되, Map은 키와 값의 쌍으로 저장해야 하므로 키와 값으로 사용할 값을 지정해야 한다
        Map<String, Integer> studentMap
                = studentStream.collect(Collectors.toMap(Student::getName, Student::getScore));

        assertThat(studentMap).containsOnlyKeys("홍길동", "김길동", "박길동", "이길동", "최길동");
        assertThat(studentMap).containsValues(87, 72, 54, 78, 66);
    }

    @Test
    @DisplayName("스트림의 최종연산 - toArray")
    void stream_toArray() {
        Stream<Student> studentStream = getStudentStream();

        // toArray() : 스트림에 저장된 요소들을 배열로 변환, 단 해당 타입의 생성자 참조를 매개변수로 전달해야 함
        Student[] studentArray = studentStream.toArray(Student[]::new);

        assertThat(studentArray).hasSize(5);
        assertThat(studentArray).allMatch(student -> student.getName().endsWith("길동"));
        assertThat(studentArray).allMatch(student -> student.getScore() < 90);
    }

    @Test
    @DisplayName("스트림의 통계 - counting, summingInt, maxBy, summarizingInt")
    void stream_statistics() {
        Stream<Student> studentStream1 = getStudentStream();
        Stream<Student> studentStream2 = getStudentStream();
        Stream<Student> studentStream3 = getStudentStream();
        Stream<Student> studentStream4 = getStudentStream();
        Stream<Student> studentStream5 = getStudentStream();
        Stream<Student> studentStream6 = getStudentStream();
        Stream<Student> studentStream7 = getStudentStream();
        Stream<Student> studentStream8 = getStudentStream();

        // Collectors.counting() : count()와 동일
        Long count1 = studentStream1.collect(Collectors.counting());
        Long count2 = studentStream2.count();

        // Collectors.summingInt() : mapToInt().sum()과 동일
        Integer score1 = studentStream3.collect(Collectors.summingInt(Student::getScore));
        Integer score2 = studentStream4.mapToInt(Student::getScore).sum();

        // Collectors.maxBy() : max()와 동일
        Optional<Student> student1 = studentStream5.collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore)));
        Optional<Student> student2 = studentStream6.max(Comparator.comparingInt(Student::getScore));

        // Collectors.summarizingInt() : mapToInt().summaryStatistics()와 동일
        IntSummaryStatistics statistics1 = studentStream7.collect(Collectors.summarizingInt(Student::getScore));
        IntSummaryStatistics statistics2 = studentStream8.mapToInt(Student::getScore).summaryStatistics();

        assertThat(count1).isEqualTo(count2).isEqualTo(5L);
        assertThat(score1).isEqualTo(score2).isEqualTo(357);
        assertThat(student1.isPresent()).isTrue();
        assertThat(student2.isPresent()).isTrue();
        assertThat(student1.get().getName()).isEqualTo(student2.get().getName());
        assertThat(statistics1.getMin()).isEqualTo(statistics2.getMin());
        assertThat(statistics1.getMax()).isEqualTo(statistics2.getMax());
        assertThat(statistics1.getSum()).isEqualTo(statistics2.getSum());
        assertThat(statistics1.getAverage()).isEqualTo(statistics2.getAverage());
        assertThat(statistics1.getCount()).isEqualTo(statistics2.getCount());
    }

    @Test
    @DisplayName("스트림을 리듀싱 - reducing")
    void stream_reducing() {
        Stream<Student> studentStream1 = getStudentStream();
        Stream<Student> studentStream2 = getStudentStream();

        // Collectors.reducing() : map().reduce()와 동일
        Integer totalScore1 = studentStream1.map(Student::getScore).reduce(0, Integer::sum);
        Integer totalScore2 = studentStream2.collect(Collectors.reducing(0, Student::getScore, Integer::sum));

        assertThat(totalScore1).isEqualTo(totalScore2).isEqualTo(357);
    }

    @Test
    @DisplayName("스트림을 문자열로 결합 - joining")
    void stream_joining() {
        Stream<Student> studentStream1 = getStudentStream();
        Stream<Student> studentStream2 = getStudentStream();
        Stream<Student> studentStream3 = getStudentStream();

        // Collectors.joining() : 문자열 스트림의 모든 요소를 하나의 문자열로 연결해서 반환
        // 구분자, 접두사, 접미사도 지정 가능
        String studentNames1 = studentStream1.map(Student::getName).collect(Collectors.joining());
        String studentNames2 = studentStream2.map(Student::getName)
                                    .collect(Collectors.joining("|")); // 구분자 지정
        String studentNames3 = studentStream3.map(Student::getName)
                                    .collect(Collectors.joining(" ", "<<", ">>"));// 구분자, 접두사, 접미사 지정

        assertThat(studentNames1).isEqualTo("홍길동김길동박길동이길동최길동");
        assertThat(studentNames2).isEqualTo("홍길동|김길동|박길동|이길동|최길동");
        assertThat(studentNames3).isEqualTo("<<홍길동 김길동 박길동 이길동 최길동>>");
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
