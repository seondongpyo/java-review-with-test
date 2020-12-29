package collection.set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

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

    @Test
    @DisplayName("TreeSet 클래스 ceiling() - 지정된 객체와 같은 객체를 반환")
    void ceiling() {
        TreeSet<Integer> treeSet = new TreeSet<>();
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
        TreeSet<Integer> treeSet = new TreeSet<>();
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
        Integer theLeast = treeSet.floor(5); // 2) 5가 없으므로, 4보다 작은 값(2, 3) 중 가장 가까운 값인 3을 반환
        Integer notMatched = treeSet.floor(1); // 3) 1보다 같거나 작은 값이 둘 다 없으므로 null 반환

        assertThat(matched).isEqualTo(7);
        assertThat(theLeast).isEqualTo(3);
        assertThat(notMatched).isNull();
    }

    @Test
    @DisplayName("TreeSet 클래스 descendingSet() - 저장된 요소들을 역순으로 정렬하여 반환")
    void descendingSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(1);
        treeSet.add(6);

        // (1, 2, 6, 7, 9) -> (9, 7, 6, 2, 1)
        NavigableSet<Integer> descendingSet = treeSet.descendingSet();

        assertThat(descendingSet).containsExactly(9, 7, 6, 2, 1);
    }
}
