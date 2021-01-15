package lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalInterfaceCollectionTest {

    @Test
    @DisplayName("컬렉션 프레임워크와 함수형 인터페이스 - forEach")
    void forEach() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numbers.add(i);
        }

        List<Integer> tenFoldNumbers = new ArrayList<>();
        numbers.forEach(i -> tenFoldNumbers.add(i * 10)); // 각 요소에 10을 곱한 뒤 새로운 리스트에 저장

        assertThat(tenFoldNumbers).containsExactly(10, 20, 30, 40, 50);
    }

    @Test
    @DisplayName("컬렉션 프레임워크와 함수형 인터페이스 - removeIf")
    void removeIf() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        numbers.removeIf(i -> i % 2 == 0); // 리스트의 요소들 중에서 모든 짝수 제거

        assertThat(numbers).containsExactly(1, 3, 5, 7, 9);
    }

    @Test
    @DisplayName("컬렉션 프레임워크와 함수형 인터페이스 - replaceAll")
    void replaceAll() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numbers.add(i);
        }

        numbers.replaceAll(i -> i *= 10); // 각 요소를 기존 요소에 10을 곱한 값으로 대체

        assertThat(numbers).containsExactly(10, 20, 30, 40, 50);
    }
}
