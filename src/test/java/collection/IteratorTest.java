package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IteratorTest {

    private List<Integer> list;

    @BeforeEach
    void initArrayList() {
        list = new ArrayList<>();
    }

    @Test
    @DisplayName("Iterator 인터페이스 hasNext() - 읽어올 요소가 있는지 확인")
    void hasNext() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    @DisplayName("Iterator 인터페이스 next() - 다음 요소 읽어오기")
    void next() {
        list.add(1);
        list.add(2);

        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next(); // 읽어올 다음 요소가 없으면 NoSuchElementException 예외 발생
        });
    }
}
