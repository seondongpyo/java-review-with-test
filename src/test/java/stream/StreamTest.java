package stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

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

    @Test
    @DisplayName("스트림 만들기 - 배열")
    void streamArray() {
        Stream<String> strStream = Stream.of("a", "b", "c"); // 가변 인자
        IntStream intStream = IntStream.of(1, 2, 3, 4);
        LongStream longStream = LongStream.of(10L, 20L, 30L, 40L, 50L);
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3);

        assertThat(strStream).hasSize(3).containsExactly("a", "b", "c");
        assertThat(intStream).hasSize(4).containsExactly(1, 2, 3, 4);
        assertThat(longStream).hasSize(5).containsExactly(10L, 20L, 30L, 40L, 50L);
        assertThat(doubleStream).hasSize(3).containsExactly(1.1, 2.2, 3.3);
    }
    
    @Test
    @DisplayName("스트림 만들기 - 임의의 수")
    void streamRandom() {
        List<Integer> integers = new ArrayList<>();
        List<Long> longs = new ArrayList<>();

        // Random 클래스에는 해당 타입의 난수들로 이루어진 스트림을 반환하는 인스턴스 메서드들이 포함되어 있다
        IntStream intStream = new Random().ints(); // 무한 스트림
        intStream.limit(5).forEach(integers::add); // 요소 개수를 5개로 제한

        LongStream longStream = new Random().longs(10); // 크기가 10인 난수 스트림을 반환
        longStream.forEach(longs::add);

        assertThat(integers).hasOnlyElementsOfType(Integer.class);
        assertThat(integers).hasSize(5);
        assertThat(longs).hasOnlyElementsOfType(Long.class);
        assertThat(longs).hasSize(10);
    }

    @Test
    @DisplayName("스트림 만들기 - 특정 범위의 정수")
    void streamRange() {
        IntStream intStream1 = IntStream.range(1, 5); // 1부터 5까지의 연속된 정수를 스트림으로 반환 (5는 미포함)
        IntStream intStream2 = IntStream.rangeClosed(6, 10); // 6부터 10까지의 연속된 정수를 스트림으로 반환 (10 포함)
        IntStream intStream3 = new Random().ints(5, 1, 5);// 1부터 5 사이의 난수를 발생시키는 스트림 반환

        assertThat(intStream1).hasSize(4).doesNotContain(5);
        assertThat(intStream2).hasSize(5).contains(10);
        intStream3.forEach(i -> assertThat(i).isLessThan(5));
    }
}
