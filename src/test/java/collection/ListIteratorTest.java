package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.assertj.core.api.Assertions.assertThat;

class ListIteratorTest {

    private List<Integer> list;

    @BeforeEach
    void initArrayList() {
        list = new ArrayList<>();
    }

    @Test
    @DisplayName("ListIterator 인터페이스 add() - 리스트에 새로운 요소 추가 (next() 호출 전)")
    void add_1() {
        /*
            Inserts the specified element into the list (optional operation).
            The element is inserted immediately before the element that would be returned by next(),
            if any, and after the element that would be returned by previous(), if any.
            (If the list contains no elements, the new element becomes the sole element on the list.)
            The new element is inserted before the implicit cursor

            -> (만약에 있다면) next() 호출 시 반환될 요소 앞 또는 previous() 호출 시 반환될 요소 뒤에 추가됨
         */
        list.add(1); // 요소 추가 후 리스트 : 1
        list.add(2); // 요소 추가 후 리스트 : 1 2

        ListIterator<Integer> listIterator = list.listIterator(); // next() 호출 시 반환될 값은 1

        // 새로운 요소 추가 시 리스트의 요소들 중 '1' 앞에 추가됨. 단, listIterator에는 추가되지 않음
        listIterator.add(3); // 요소 추가 후 리스트 : 3 1 2
        listIterator.add(4); // 요소 추가 후 리스트 : 3 4 1 2

        assertThat(list).containsExactly(3, 4, 1, 2).hasSize(4); // 리스트에는 새로운 요소가 추가됨
        assertThat(listIterator.next()).isEqualTo(1);
        assertThat(listIterator.next()).isEqualTo(2); // listIterator에는 요소가 추가되지 않음
    }

    @Test
    @DisplayName("ListIterator 인터페이스 add() - 리스트에 새로운 요소 추가 (next() 호출 후)")
    void add_2() {
        // | : 커서 위치
        list.add(1); // 요소 추가 후 리스트 : |1
        list.add(2); // 요소 추가 후 리스트 : |1 2

        ListIterator<Integer> listIterator = list.listIterator(); // next() 호출 시 반환될 값은 1
        assertThat(listIterator.next()).isEqualTo(1); // next()가 호출됨, 다음 next() 호출 시 반환될 값은 2
        // next() 호출 후 리스트 : 1 |2

        // add() : The new element is inserted before the implicit cursor
        // -> 새로운 요소는 커서 앞에 추가된다
        listIterator.add(3); // 요소 추가 후 리스트 : 1 3 |2

        assertThat(listIterator.next()).isEqualTo(2); // next()가 호출됨, 다음 next() 호출 시 반환될 값은 없음
        // next() 호출 후 리스트 : 1 3 2 |

        // 새로운 요소 추가
        listIterator.add(4); // 요소 추가 후 리스트 : 1 3 2 4 |
        listIterator.add(5); // 요소 추가 후 리스트 : 1 3 2 4 5 |

        assertThat(list).containsExactly(1, 3, 2, 4, 5).hasSize(5);
    }

}
