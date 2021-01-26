package stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StreamIntermediateOperationTest {

    @Test
    @DisplayName("스트림의 중간연산 - skip")
    void stream_skip() {
        IntStream intStream = IntStream.rangeClosed(1, 10);

        // skip(n) - 처음 n개의 요소를 건너뛴다
        IntStream skippedStream = intStream.skip(4); // 처음 4개의 요소를 건너뜀 (5 ~ 10)

        assertThat(skippedStream).hasSize(6).containsExactly(5, 6, 7, 8, 9, 10).doesNotContain(1, 2, 3, 4);
    }

    @Test
    @DisplayName("스트림의 중간연산 - limit")
    void stream_limit() {
        IntStream intStream = IntStream.rangeClosed(1, 10);
        
        // limit(maxSize) - 스트림의 요소를 맨 앞 요소로부터 maxSize개로 제한한다
        IntStream limitStream = intStream.limit(5); // 요소의 개수를 5개로 제한 (1 ~ 5)

        assertThat(limitStream).hasSize(5).containsExactly(1, 2, 3, 4, 5).doesNotContain(6, 7, 8, 9, 10);
    }

    @Test
    @DisplayName("스트림의 중간연산 - filter")
    void stream_filter() {
        IntStream intStream = IntStream.rangeClosed(1, 10);
        
        // filter(Predicate p) - 주어진 조건(p)에 맞지 않는 요소를 걸러낸다
        IntStream filteredStream = intStream.filter(i -> i % 2 == 0); // 홀수 요소들을 제외

        assertThat(filteredStream).hasSize(5).containsExactly(2, 4, 6, 8, 10);
    }

    @Test
    @DisplayName("스트림의 중간연산 - distinct")
    void stream_distinct() {
        IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5);

        // distinct() - 중복 요소를 제거한다
        IntStream distinctStream = intStream.distinct();

        assertThat(distinctStream).hasSize(5).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    @DisplayName("스트림의 중간연산 - sorted")
    void stream_sorted() {
        IntStream intStream = IntStream.of(1, 4, 3, 5, 2);
        Stream<String> stringStream = Stream.of("a", "c", "b", "C", "A", "B");

        // sorted() - 스트림 요소의 기본 정렬 기준으로 정렬함 (단, 요소가 Comparable을 구현한 클래스가 아니라면 예외 발생)
        IntStream sortedIntStream = intStream.sorted();
        Stream<String> sortedStringStream = stringStream.sorted();

        assertThat(sortedIntStream).containsExactly(1, 2, 3, 4, 5); // 오름차순 정렬
        assertThat(sortedStringStream).containsExactly("A", "B", "C", "a", "b", "c"); // 사전 순으로 정렬
    }

    @Test
    @DisplayName("스트림의 중간연산 - sorted(Comparator c)")
    void stream_sorted_comparable() {
        Stream<String> stringStream1 = Stream.of("aa", "cc", "bb", "CC", "AA", "BB");
        Stream<String> stringStream2 = Stream.of("aa", "cc", "bb", "CC", "AA", "BB");
        Stream<String> stringStream3 = Stream.of("ddd", "f", "zz", "eee", "aaaa");

        // sorted(Comparator c) - 스트림을 지정된 Comparator로 정렬
        Stream<String> reverseOrdered = stringStream1.sorted(Comparator.reverseOrder()); // 기본 정렬 역순
        Stream<String> caseInsensitiveOrdered = stringStream2.sorted(String.CASE_INSENSITIVE_ORDER); // 대소문자를 구분하지 않음
        Stream<String> orderedByLength = stringStream3.sorted(Comparator.comparing(String::length)); // 길이가 짧은 순서대로 정렬

        assertThat(reverseOrdered).containsExactly("cc", "bb", "aa", "CC", "BB", "AA");
        assertThat(caseInsensitiveOrdered).containsExactly("aa", "AA", "bb", "BB", "cc", "CC");
        assertThat(orderedByLength).containsExactly("f", "zz", "ddd", "eee", "aaaa");
    }

    @Test
    @DisplayName("스트림의 중간연산 - Comparator의 comparing()")
    void stream_comparator_comparing() {
        Stream<Student> studentStream = Stream.of(
                new Student("홍길동", 1, 100),
                new Student("백길동", 3, 190),
                new Student("박길동", 2, 160),
                new Student("천길동", 3, 110),
                new Student("김길동", 1, 140),
                new Student("최길동", 1, 120),
                new Student("황길동", 2, 150),
                new Student("이길동", 3, 180),
                new Student("노길동", 2, 200)
        );

        Stream<Student> sortedByBan = studentStream.sorted(Comparator.comparing(Student::getBan)); // 반별 정렬
        sortedByBan.limit(3).forEach(student -> assertThat(student.getBan()).isEqualTo(1));
    }

    @Test
    @DisplayName("스트림의 중간연산 - Comparator의 thenComparing()")
    void stream_comparator_thenComparing() {
        Student hong = new Student("홍길동", 1, 100);
        Student baek = new Student("백길동", 3, 190);
        Student park = new Student("박길동", 2, 160);
        Student cheon = new Student("천길동", 3, 110);
        Student kim = new Student("김길동", 1, 140);
        Student choi = new Student("최길동", 1, 120);
        Student hwang = new Student("황길동", 2, 150);
        Student lee = new Student("이길동", 3, 180);
        Student noh = new Student("노길동", 2, 200);

        Student[] students = {hong, baek, park, cheon, kim, choi, hwang, lee, noh};

        Stream<Student> studentStream = Stream.of(students);

        Stream<Student> sortedStudentStream
                = studentStream.sorted(Comparator.comparing(Student::getBan) // 반별 정렬
                                        .thenComparing(Student::getTotalScore)); // 총점 정렬
        
        // 반별 오름차순으로 먼저 정렬 후, 총점 오름차순으로 정렬
        // => 1반부터 3반 순으로 정렬하고, 각 반 내에서 성적이 낮은 순부터 높은 순으로 정렬함
        assertThat(sortedStudentStream).containsExactly(hong, choi, kim, hwang, park, noh, cheon, lee, baek);
    }

    @Test
    @DisplayName("스트림의 중간연산 - map")
    void stream_map() {
        Stream<File> fileStream = Stream.of(
            new File("/file/house1.jpg"),
            new File("/file/house2.jpg"),
            new File("/file/house3.jpg"),
            new File("/file/house4.jpg")
        );

        // map : 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야 하는 경우 사용
        Stream<String> filenameStream = fileStream.map(File::getName);
        List<String> filenameList = new ArrayList<>();
        
        // 파일의 이름만 뽑아서 리스트에 저장
        filenameStream.forEach(filenameList::add);

        assertThat(filenameList).hasSize(4);
        assertThat(filenameList).containsExactly("house1.jpg", "house2.jpg", "house3.jpg", "house4.jpg");
    }

    @Test
    @DisplayName("스트림의 중간연산 - peek")
    void stream_peek() {
        List<String> fileExtensionList = new ArrayList<>();
        Stream<File> fileStream = Stream.of(
                new File("/file/test.txt"),
                new File("/file/test.bak"),
                new File("/file/test.js"),
                new File("/file/test.html")
        );

        // peek : 연산과 연산 사이에 올바르게 처리되었는지 확인하고 싶은 경우
        Stream<String> fileExtensionStream
                = fileStream.map(File::getName)
                            .peek(s -> System.out.println("filename = " + s)) // 파일명 출력
                            .map(s -> s.substring(s.indexOf(".") + 1)) // 확장자만 추출
                            .peek(s -> System.out.println("extension = " + s)); // 확장자 출력

        // 파일의 확장자만 뽑아서 리스트에 저장
        fileExtensionStream.forEach(fileExtensionList::add);

        assertThat(fileExtensionList).hasSize(4);
        assertThat(fileExtensionList).containsExactly("txt", "bak", "js", "html");
    }

    static class Student implements Comparable<Student> {
        String name;
        int ban;
        int totalScore;

        public Student(String name, int ban, int totalScore) {
            this.name = name;
            this.ban = ban;
            this.totalScore = totalScore;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", ban=" + ban +
                    ", totalScore=" + totalScore +
                    '}';
        }

        // 총점 내림차순을 기본 정렬로 한다
        @Override
        public int compareTo(Student student) {
            return student.totalScore - this.totalScore;
        }

        public String getName() {
            return name;
        }

        public int getBan() {
            return ban;
        }

        public int getTotalScore() {
            return totalScore;
        }
    }
}
