package collection.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {
    
    @Test
    @DisplayName("LinkedList 예제")
    void linkedList() {
        /*
            << 배열 vs. LinkedList >>
            - 배열은 가장 기본적인 형태의 자료구조로, 구조가 간단하며 사용하기 쉽고 데이터 접근 시간이 가장 빠르지만,
              '1) 크기를 변경할 수 없으며', '2) 비순차적인 데이터의 추가 또는 삭제 시 많은 시간이 걸린다'는 단점을 가지고 있다
            - LinkedList는 이러한 배열의 단점을 보완하기 위해 고안된 자료구조로,
              배열은 모든 데이터가 연속적으로 존재하지만 LinkedList는 불연속적으로 존재하는 데이터를 서로 연결한 형태로 구성되어 있다
            - LinkedList의 각 요소(node)는 자신과 연결된 다음 요소에 대한 참조(주소값)와 데이터로 구성되어 있다
         */

        LinkedList<Integer> intLinkedList = new LinkedList<>();
        intLinkedList.add(0);
        intLinkedList.add(1);
        intLinkedList.add(2);
        intLinkedList.add(3);
        intLinkedList.add(4);

        /*
            << LinkedList의 데이터 추가와 삭제 >>
            - 새로운 데이터 추가 시 새로운 요소를 생성한 다음 
              추가하고자 하는 위치의 이전 요소의 참조를 새로운 요소에 대한 참조로 변경해주고,
              새로운 요소가 그 다음 요소를 참조하도록 변경하면 되므로 처리 속도가 매우 빠르다
            - 데이터 삭제 시 삭제하고자 하는 요소의 이전 요소가, 삭제하고자 하는 요소의 다음 요소를 참조하도록 변경하면 된다
              즉, 하나의 참조만 변경하면 삭제가 이루어지므로
              배열처럼 데이터를 이동하기 위해 복사하는 과정이 없기 때문에 처리 속도가 매우 빠르다
         */

        intLinkedList.add(2, 5);
        assertThat(intLinkedList).containsExactly(0, 1, 5, 2, 3, 4);

        intLinkedList.remove(3);
        assertThat(intLinkedList).containsExactly(0, 1, 5, 3, 4);
    }
}
