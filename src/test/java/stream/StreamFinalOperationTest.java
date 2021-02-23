package stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class StreamFinalOperationTest {

    Stream<Student> getStudentStream() {
        return Stream.of(
                new Student("홍길동", 87, true, 1),
                new Student("김길동", 72, true, 1),
                new Student("박길동", 54, true, 2),
                new Student("이길동", 78, true, 2),
                new Student("최길동", 66, true, 3)
        );
    }
    
    Stream<Student> getNewStudentStream() {
        return Stream.of(
                new Student("홍길동", 87, true, 1),
                new Student("김길동", 72, true, 1),
                new Student("박길동", 54, true, 2),
                new Student("이길동", 78, true, 2),
                new Student("최길동", 66, true, 3),
                new Student("김영미", 97, false, 1),
                new Student("이영미", 84, false, 1),
                new Student("박영미", 71, false, 2),
                new Student("최영미", 68, false, 2),
                new Student("한영미", 55, false, 2),
                new Student("남영미", 43, false, 3),
                new Student("주영미", 91, false, 3)
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
                                    .collect(Collectors.joining(" ", "<<", ">>")); // 구분자, 접두사, 접미사 지정

        assertThat(studentNames1).isEqualTo("홍길동김길동박길동이길동최길동");
        assertThat(studentNames2).isEqualTo("홍길동|김길동|박길동|이길동|최길동");
        assertThat(studentNames3).isEqualTo("<<홍길동 김길동 박길동 이길동 최길동>>");
    }

    @Test
    @DisplayName("스트림의 분할 - partitioningBy 1")
    void stream_partitioningBy_1() {
        Stream<Student> studentStream = getNewStudentStream();

        // Collectors.partitionBy() : 스트림의 요소를 전달된 Predicate에 따라 분할
        // 기본 분할 - 학생들을 성별로 분할
        Map<Boolean, List<Student>> studentByGender
                = studentStream.collect(Collectors.partitioningBy(Student::isMale));

        List<Student> maleStudent = studentByGender.get(true);
        List<Student> femaleStudent = studentByGender.get(false);

        assertThat(maleStudent).hasSize(5);
        assertThat(femaleStudent).hasSize(7);
    }
    
    @Test
    @DisplayName("스트림의 분할 - partitioningBy 2")
    void stream_partitioningBy_2() {
        Stream<Student> studentStream = getNewStudentStream();

        // 기본 분할 + 통계 정보 : 성별로 분할된 학생의 수를 계산
        Map<Boolean, Long> studentNumberByGender
                = studentStream.collect(Collectors.partitioningBy(Student::isMale, Collectors.counting()));

        Long maleStudentNumber = studentNumberByGender.get(true); // 남학생의 수
        Long femaleStudentNumber = studentNumberByGender.get(false); // 여학생의 수

        assertThat(maleStudentNumber).isEqualTo(5);
        assertThat(femaleStudentNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("스트림의 분할 - partitioningBy 3")
    void student_partitioningBy_3() {
        Stream<Student> studentStream = getNewStudentStream();

        // 기본 분할 + 통계 정보 : 성별별 1등 구하기
        Map<Boolean, Optional<Student>> topScorerByGender
                = studentStream.collect(Collectors.partitioningBy(Student::isMale,
                                        Collectors.maxBy(Comparator.comparingInt(Student::getScore))));

        Optional<Student> topMaleScorer = topScorerByGender.get(true);
        Optional<Student> topFemaleScorer = topScorerByGender.get(false);

        assertThat(topMaleScorer.isPresent()).isTrue();
        assertThat(topFemaleScorer.isPresent()).isTrue();
        assertThat(topMaleScorer.get().getName()).isEqualTo("홍길동");
        assertThat(topMaleScorer.get().getScore()).isEqualTo(87);
        assertThat(topFemaleScorer.get().getName()).isEqualTo("김영미");
        assertThat(topFemaleScorer.get().getScore()).isEqualTo(97);
    }
    
    @Test
    @DisplayName("스트림의 분할 - partitioningBy 4")
    void stream_partitioningBy_4() {
        Stream<Student> studentStream = getNewStudentStream();

        // 60점 미만인 학생들을 구하기
        Map<Boolean, Map<Boolean, List<Student>>> failedStudentByGender
                = studentStream.collect(Collectors.partitioningBy(Student::isMale,
                                        Collectors.partitioningBy(student -> student.getScore() < 60)));

        List<Student> failedMaleStudents = failedStudentByGender.get(true).get(true);
        List<Student> failedFemaleStudents = failedStudentByGender.get(false).get(true);
        String failedMaleStudentNames = failedMaleStudents.stream().map(Student::getName).collect(Collectors.joining(","));
        String failedFemaleStudentNames = failedFemaleStudents.stream().map(Student::getName).collect(Collectors.joining(","));
        
        assertThat(failedMaleStudents).hasSize(1);
        assertThat(failedFemaleStudents).hasSize(2);
        assertThat(failedMaleStudentNames).isEqualTo("박길동");
        assertThat(failedFemaleStudentNames).isEqualTo("한영미,남영미");
    }

    @Test
    @DisplayName("스트림의 그룹화 - groupingBy 1")
    void stream_groupingBy_1() {
        Stream<Student> studentStream = getNewStudentStream();

        // Collectors.groupingBy() : 스트림의 요소를 전달된 Function에 따라 그룹화
        // 학생들을 학년별로 그룹화
        Map<Integer, List<Student>> studentsByYear 
                = studentStream.collect(Collectors.groupingBy(Student::getYear, Collectors.toList())); // Collectors.toList()는 생략 가능

        List<Student> firstYearStudents = studentsByYear.get(1);
        List<Student> secondYearStudents = studentsByYear.get(2);
        List<Student> thirdYearStudents = studentsByYear.get(3);
        List<String> firstYearStudentNames = firstYearStudents.stream().map(Student::getName).collect(Collectors.toList());
        List<String> secondYearStudentNames = secondYearStudents.stream().map(Student::getName).collect(Collectors.toList());
        List<String> thirdYearStudentNames = thirdYearStudents.stream().map(Student::getName).collect(Collectors.toList());

        assertThat(firstYearStudents).hasSize(4);
        assertThat(firstYearStudentNames).contains("홍길동", "김길동", "김영미", "이영미");
        assertThat(secondYearStudents).hasSize(5);
        assertThat(secondYearStudentNames).contains("박길동", "이길동", "박영미", "최영미", "한영미");
        assertThat(thirdYearStudents).hasSize(3);
        assertThat(thirdYearStudentNames).contains("최길동", "남영미", "주영미");
    }

    @Test
    @DisplayName("스트림의 그룹화 - groupingBy 2")
    void stream_groupingBy_2() {
        Stream<Student> studentStream = getNewStudentStream();

        // 학생들을 성적별로 그룹화
        Map<Student.Level, Long> studentByLevel = studentStream.collect(Collectors.groupingBy(student -> {
            if (student.getScore() >= 90) {
                return Student.Level.HIGH;
            } else if (student.getScore() >= 70) {
                return Student.Level.MID;
            } else {
                return Student.Level.LOW;
            }
        }, Collectors.counting()));

        Long highLevelStudentNumber = studentByLevel.get(Student.Level.HIGH);
        Long midLevelStudentNumber = studentByLevel.get(Student.Level.MID);
        Long lowLevelStudentNumber = studentByLevel.get(Student.Level.LOW);

        assertThat(highLevelStudentNumber).isEqualTo(2);
        assertThat(midLevelStudentNumber).isEqualTo(5);
        assertThat(lowLevelStudentNumber).isEqualTo(5);
    }

    @Test
    @DisplayName("스트림의 그룹화 - groupingBy 3")
    void stream_groupingBy_3() {
        Stream<Student> studentStream = getNewStudentStream();

        // 학년별로 성적이 가장 우수한 학생 구하기
        Map<Integer, Student> topStudentByYear
                = studentStream.collect(Collectors.groupingBy(Student::getYear,
                                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Student::getScore)),
                                                                                        Optional::get)));

        String firstYearTopScorer = topStudentByYear.get(1).getName();
        String secondYearTopScorer = topStudentByYear.get(2).getName();
        String thirdYearTopScorer = topStudentByYear.get(3).getName();

        assertThat(firstYearTopScorer).isEqualTo("김영미");
        assertThat(secondYearTopScorer).isEqualTo("이길동");
        assertThat(thirdYearTopScorer).isEqualTo("주영미");
    }

    static class Student {
        String name;
        int score;
        boolean isMale;
        int year;

        public Student(String name, int score, boolean isMale, int year) {
            this.name = name;
            this.score = score;
            this.isMale = isMale;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public boolean isMale() {
            return isMale;
        }

        public int getYear() {
            return year;
        }

        enum Level {
            HIGH, MID, LOW
        }
    }
}
