package stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setExtractBareNamePropertyMethods;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamTest {

    @Test
    @DisplayName("스트림의 특징 - 데이터 소스를 변경하지 않는다")
    void stream_1() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(4);
        numbers.add(1);
        numbers.add(3);

        Stream<Integer> stream = numbers.stream(); // 스트림을 생성
        List<Integer> sortedList = stream.sorted().collect(Collectors.toList()); // 정렬된 결과를 컬렉션에 담아서 반환

        assertThat(numbers).containsExactly(5, 2, 4, 1, 3); // 기존 리스트를 변경하지 않는다
        assertThat(sortedList).containsExactly(1, 2, 3, 4, 5);
    }
    
    @Test
    @DisplayName("스트림의 특징 - 스트림은 일회용이다")
    void stream_2() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(1);

        Stream<Integer> stream = numbers.stream(); // 스트림 생성
        List<Integer> sortedList = stream.sorted().collect(Collectors.toList()); // 스트림을 한 번 사용

        assertThrows(IllegalStateException.class, stream::count); // 한 번 사용한 스트림은 닫혀서 재사용이 불가능하다
        assertThat(sortedList).containsExactly(1, 2, 3);
    }
    
    @Test
    @DisplayName("스트림의 특징 - 작업을 내부 반복으로 처리한다")
    void stream_3() {
        String[] strArr = {"a", "b", "c", "d", "e"};
        List<String> strList = new ArrayList<>();

        Arrays.stream(strArr).forEach(s -> strList.add(s + s));

        assertThat(strList).containsExactly("aa", "bb", "cc", "dd", "ee");
    }
}
