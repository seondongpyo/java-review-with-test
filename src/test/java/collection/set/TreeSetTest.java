package collection.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class TreeSetTest {

    /*
        << TreeSet >>
        - 이진 검색 트리(binary search tree)라는 자료구조의 형태로 데이터를 저장하는 컬렉션 클래스
        - 정렬, 검색, 범위 검색(range search)에 높은 성능을 보이는 자료구조
        - 이진 검색 트리의 성능을 향상시킨 '레드-블랙 트리(Red-Black tree)'로 구현되어 있다
        - Set 인터페이스를 구현했으므로 중복 데이터를 허용하지 않으며, 정렬된 위치에 저장하므로 저장순서를 유지하지도 않는다
        - 이진 트리(binary)는 LinkedList처럼 여러 개의 노드(node)가 서로 연결된 구조
          각 노드에 최대 2개의 노드를 연결할 수 있으며, '루트(root)'라고 불리는 하나의 노드에서부터 시작하여 확장해 나갈 수 있다
        - 위아래로 연결된 두 노드를 '부모-자식' 관계에 있다고 하며 윗노드를 부모 노드, 아랫노드를 자식 노드라고 한다
          '부모-자식' 관계는 상대적인 것이며, 하나의 부모 노드는 최대 두 개의 자식 노드와 연결될 수 있다

        << 이진 탐색 트리 (binary search tree) >>
        - 부모 노드의 왼쪽에는 부모 노드의 값보다 작은 값의 자식 노드를, 오른쪽에는 큰 값의 자식 노드를 저장하는 이진 트리
        - 모든 노드는 최대 두 개의 자식 노드를 가질 수 있다
        - 순차적으로 저장하지 않으므로 노드의 추가 및 삭제에 시간이 걸린다
        - 검색(범위 검색)과 정렬에 유리하다
        - 중복된 값을 저장하지 못한다
        - 왼쪽 마지막 값에서부터 오른쪽 값까지 값을 '왼쪽 노드 -> 부모 노드 -> 오른쪽 노드' 순으로 읽어오면
          오름차순으로 정렬된 순서를 얻을 수 있다
     */

    private TreeSet<Integer> treeSet;

    @BeforeEach
    void initTreeSet() {
        treeSet = new TreeSet<>();
    }

    @Test
    @DisplayName("TreeSet 클래스 ceiling() - 지정된 객체와 같은 객체를 반환")
    void ceiling() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(1);
        treeSet.add(6);

        /*
            Returns the least key greater than or equal to the given key, or null if there is no such key.
            -> 지정된 객체와 같거나 혹은 큰 값 중 가장 가까운 값의 객체를 반환하는데, 그런 객체가 없다면 null을 반환
         */

        // 현재 TreeSet : 1, 2, 6, 7, 9
        Integer matched = treeSet.ceiling(2); // 1) 같은 값인 2를 반환
        Integer theLeast = treeSet.ceiling(3); // 2) 3이 없으므로, 3보다 큰 값(6, 7, 9) 중 가장 가까운 값인 6을 반환
        Integer notMatched = treeSet.ceiling(10); // 3) 10보다 같거나 큰 값이 둘 다 없으므로 null 반환

        assertThat(matched).isEqualTo(2);
        assertThat(theLeast).isEqualTo(6);
        assertThat(notMatched).isNull();
    }

    @Test
    @DisplayName("TreeSet 클래스 floor() - 지정된 객체와 같은 객체를 반환")
    void floor() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(3);
        treeSet.add(6);

        /*
            Returns the greatest key less than or equal to the given key, or null if there is no such key.
            -> 지정된 객체와 같거나 혹은 작은 값 중 가장 가까운 값의 객체를 반환하는데, 그런 객체가 없다면 null을 반환
         */

        // 현재 TreeSet : 2, 3, 6, 7, 9
        Integer matched = treeSet.floor(7); // 1) 같은 값인 7를 반환
        Integer theLeast = treeSet.floor(5); // 2) 5가 없으므로, 5보다 작은 값(2, 3) 중 가장 가까운 값인 3을 반환
        Integer notMatched = treeSet.floor(1); // 3) 1보다 같거나 작은 값이 둘 다 없으므로 null 반환

        assertThat(matched).isEqualTo(7);
        assertThat(theLeast).isEqualTo(3);
        assertThat(notMatched).isNull();
    }

    @Test
    @DisplayName("TreeSet 클래스 descendingSet() - 저장된 요소들을 역순으로 정렬하여 반환")
    void descendingSet() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(1);
        treeSet.add(6);

        // (1, 2, 6, 7, 9) -> (9, 7, 6, 2, 1)
        NavigableSet<Integer> descendingSet = treeSet.descendingSet();

        assertThat(descendingSet).containsExactly(9, 7, 6, 2, 1);
    }

    @Test
    @DisplayName("TreeSet 클래스 headSet() - 지정된 객체보다 작은 값의 객체들을 반환")
    void headSet() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(1);
        treeSet.add(6);

        /*
            public SortedSet<E> headSet(E toElement) {
                return headSet(toElement, false);
            }

            -> headSet()은 inclusive 기본값이 false
         */

        SortedSet<Integer> sortedSet = treeSet.headSet(7); // 7보다 작은 값들을 반환 (1, 2, 6)
        NavigableSet<Integer> navigableSet = treeSet.headSet(7, true); // inclusive가 true면 같은 값도 포함

        assertThat(sortedSet).hasSize(3);
        assertThat(sortedSet).containsExactly(1, 2, 6);
        assertThat(navigableSet).hasSize(4);
        assertThat(navigableSet).containsExactly(1, 2, 6, 7);
    }

    @Test
    @DisplayName("TreeSet 클래스 tailSet() - 지정된 객체와 같거나 큰 값의 객체들을 반환")
    void tailSet() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(1);
        treeSet.add(6);

        /*
            public SortedSet<E> tailSet(E fromElement) {
                return tailSet(fromElement, true);
            }

            -> tailSet()은 inclusive 기본값이 true
         */

        SortedSet<Integer> sortedSet = treeSet.tailSet(6); // 6보다 크거나 큰 값들을 반환 (6, 7, 9)
        NavigableSet<Integer> navigableSet = treeSet.tailSet(6, false); // inclusive가 false면 같은 값을 미포함

        assertThat(sortedSet).hasSize(3);
        assertThat(sortedSet).containsExactly(6, 7, 9);
        assertThat(navigableSet).hasSize(2);
        assertThat(navigableSet).containsExactly(7, 9);
    }

    @Test
    @DisplayName("TreeSet 클래스 higher() - 지정된 객체보다 큰 값을 가진 객체 중 제일 가까운 값의 객체를 반환, 없으면 null")
    void higher() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(1);
        treeSet.add(6);

        Integer higher = treeSet.higher(3); // 3보다 큰 값을 가진 객체(6, 7, 9) 중 제일 가까운 값인 6을 반환
        Integer notMatched = treeSet.higher(10);// 10보다 큰 값을 가진 객체는 없으므로 null 반환

        assertThat(higher).isEqualTo(6);
        assertThat(notMatched).isNull();
    }

    @Test
    @DisplayName("TreeSet 클래스 lower() - 지정된 객체보다 작은 값을 가진 객체 중 제일 가까운 값의 객체를 반환, 없으면 null")
    void lower() {
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(3);
        treeSet.add(6);

        Integer higher = treeSet.lower(7); // 7보다 작은 값을 가진 객체(2, 3, 6) 중 제일 가까운 값인 6을 반환
        Integer notMatched = treeSet.lower(1);// 1보다 작은 값을 가진 객체는 없으므로 null 반환

        assertThat(higher).isEqualTo(6);
        assertThat(notMatched).isNull();
    }
    
    @Test
    @DisplayName("TreeSet 클래스 subSet() - 범위 검색의 결과를 반환 (끝 범위는 포함되지 않음)")
    void subSet() {
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(4);
        treeSet.add(7);
        treeSet.add(9);
        treeSet.add(10);

        SortedSet<Integer> sortedSet = treeSet.subSet(3, 10); // 3부터 10 사이의 결과를 반환 (10은 포함되지 않음)

        assertThat(sortedSet).hasSize(4);
        assertThat(sortedSet).containsExactly(3, 4, 7, 9);
    }
    
    @Test
    @DisplayName("TreeSet 클래스 subSet() - inclusive에 따라 범위 값의 포함 유무를 결정할 수 있다")
    void subSet_inclusive() {
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(4);
        treeSet.add(7);
        treeSet.add(9);
        treeSet.add(10);

        // 3부터 10 사이의 범위 검색 결과를 반환하되, 3은 포함하지 않고 10은 포함한다
        NavigableSet<Integer> navigableSet = treeSet.subSet(3, false, 10, true);

        assertThat(navigableSet).hasSize(4);
        assertThat(navigableSet).containsExactly(4, 7, 9, 10);
    }
}
