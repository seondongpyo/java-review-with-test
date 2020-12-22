package collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("ListIterator 인터페이스 remove() - 리스트에서 요소 삭제 (1)")
    void remove_1() {
        /*
            Removes from the list the last element that was returned by next() or previous() (optional operation).
            This call can only be made once per call to next or previous.
            It can be made only if add(E) has not been called after the last call to next or previous.

            -> next() 또는 previous() 호출에 의해 반환된 마지막 요소를 리스트에서 삭제
         */

        list.add(1);
        list.add(2);
        list.add(3);

        ListIterator<Integer> listIterator = list.listIterator();
        assertThat(listIterator.next()).isEqualTo(1);
        assertThat(listIterator.next()).isEqualTo(2);
        assertThat(listIterator.next()).isEqualTo(3);

        // remove() 호출 시, next()에 의해 마지막으로 반환된 요소(3)가 삭제된다
        listIterator.remove();

        assertThat(list).containsExactly(1, 2).hasSize(2);
    }

    @Test
    @DisplayName("ListIterator 인터페이스 remove() - 리스트에서 요소 삭제 (2)")
    void remove_2() {
        list.add(1);
        list.add(2);
        list.add(3);
        // 현재 리스트 : |1 2 3

        ListIterator<Integer> listIterator = list.listIterator();
        assertThat(listIterator.next()).isEqualTo(1); // 1 |2 3 (커서를 오른쪽으로 이동)
        assertThat(listIterator.next()).isEqualTo(2); // 1 2 |3 (커서를 오른쪽으로 이동)
        assertThat(listIterator.previous()).isEqualTo(2); // 1 |2 3 (커서를 왼쪽으로 이동)
        assertThat(listIterator.previous()).isEqualTo(1); // |1 2 3 (커서를 왼쪽으로 이동)

        // remove() 호출 시, next() 또는 previous()에 의해 마지막으로 반환된 값인 1이 삭제된다
        listIterator.remove();

        assertThat(list).containsExactly(2, 3).hasSize(2);
    }

    @Test
    @DisplayName("ListIterator 인터페이스 remove() - 리스트에서 요소 삭제 (3)")
    void remove_3() {
        list.add(1);
        list.add(2);
        // 현재 리스트 : |1 2

        /*
            if neither next nor previous have been called,
            or remove or add have been called after the last call to next or previous

            ->  1) next() 또는 previous()가 호출된 적이 없을 때, 또는
                2) next() 또는 previous()의 마지막 호출 이후 add()가 호출됐을 때 (remove()는 잘 모르겠음)
                IllegalStateException 예외 발생
         */
        ListIterator<Integer> listIterator = list.listIterator();

        // 1) next() 또는 previous()가 호출되기 전에 remove()를 호출하면 예외 발생
        assertThrows(IllegalStateException.class, () -> {
            listIterator.remove();
        });

        // 2) next() 또는 previous()의 마지막 호출 이후 add() 호출 시
        assertThat(listIterator.next()).isEqualTo(1); // 1 |2
        listIterator.remove(); // next() 호출에 의해 반환된 1 삭제 -> |2

        assertThat(listIterator.next()).isEqualTo(2); // 2 |
        listIterator.remove(); // next() 호출에 의해 반환된 2 삭제 -> |

        assertThrows(IllegalStateException.class, () -> {
            listIterator.remove();
        });
    }
}
