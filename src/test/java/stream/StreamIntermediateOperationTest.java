package stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

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
}
