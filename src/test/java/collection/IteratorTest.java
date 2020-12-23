package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
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

    @Test
    @DisplayName("Map 인터페이스와 Iterator")
    void mapIterator() {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, "홍길동");
        map.put(2, "김길동");

        // Map 인터페이스를 구현한 컬렉션 클래스는 key와 value를 하나의 쌍으로 가지기 때문에 iterator()를 직접 호출할 수는 없다
        // 대신 keySet() 또는 entrySet()과 같이 key와 value를 각각 따로 Set의 형태로 얻어온 후 iterator()를 호출할 수 있다

        Set<Integer> keySet = map.keySet(); // Map에 들어있는 모든 키들을 반환
        Collection<Object> values = map.values(); // Map에 들어있는 모든 값들을 반환

        Iterator<Integer> keyIterator = keySet.iterator();
        Iterator<Object> valueIterator = values.iterator();

        assertThat(keyIterator.next()).isEqualTo(1);
        assertThat(keyIterator.next()).isEqualTo(2);
        assertThat(valueIterator.next()).isEqualTo("홍길동");
        assertThat(valueIterator.next()).isEqualTo("김길동");
    }
}
