package collection.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    
    @Test
    @DisplayName("LinkedList 클래스의 메서드")
    void linkedListMethods() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.addFirst(0); // 주어진 객체를 맨 앞에 추가 (Inserts the specified element at the beginning of this list.)
        assertThat(linkedList).containsExactly(0, 1, 2, 3);

        linkedList.offerFirst(-1); // 주어진 객체를 첫 번째 요소로 추가 (Inserts the specified element at the front of this list.)
        assertThat(linkedList).containsExactly(-1, 0, 1, 2, 3);

        linkedList.addLast(4); // 주어진 객체를 마지막 요소로 추가 (Appends the specified element to the end of this list. This method is equivalent to add.)
        assertThat(linkedList).containsExactly(-1, 0, 1, 2, 3, 4);

        linkedList.offer(5); // 주어진 객체를 마지막 요소로 추가 (Adds the specified element as the tail (last element) of this list.)
        assertThat(linkedList).containsExactly(-1, 0, 1, 2, 3, 4, 5);

        assertThat(linkedList.element()).isEqualTo(-1); // 첫 번째 요소 반환 (Retrieves, but does not remove, the head (first element) of this list.)
        assertThat(linkedList.getFirst()).isEqualTo(-1); // 첫 번째 요소 반환 (Returns the first element in this list.)
        assertThat(linkedList.getLast()).isEqualTo(5); // 마지막 요소 반환 (Returns the last element in this list.)
        assertThat(linkedList.peek()).isEqualTo(-1); // 첫 번째 요소 반환 (Retrieves, but does not remove, the head (first element) of this list.)
        assertThat(linkedList.peekFirst()).isEqualTo(-1); // 첫 번째 요소 반환 (Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.)
        assertThat(linkedList.peekLast()).isEqualTo(5); // 마지막 요소 반환 (Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.)

        assertThat(linkedList.pop()).isEqualTo(-1); // 첫 번째 요소를 반환하고 LinkedList에서 제거 (Pops an element from the stack represented by this list. In other words, removes and returns the first element of this list. This method is equivalent to removeFirst().)
        assertThat(linkedList).containsExactly(0, 1, 2, 3, 4, 5);

        assertThat(linkedList.poll()).isEqualTo(0); // 첫 번째 요소를 반환하고 LinkedList에서 제거 (Retrieves and removes the head (first element) of this list.)
        assertThat(linkedList).containsExactly(1, 2, 3, 4, 5);

        assertThat(linkedList.pollFirst()).isEqualTo(1); // 첫 번째 요소를 반환하고 LinkedList에서 제거 (Retrieves and removes the first element of this list, or returns null if this list is empty.)
        assertThat(linkedList).containsExactly(2, 3, 4, 5);

        assertThat(linkedList.pollLast()).isEqualTo(5); // 마지막 요소를 반환하고 LinkedList에서 제거 (Retrieves and removes the last element of this list, or returns null if this list is empty.)
        assertThat(linkedList).containsExactly(2, 3, 4);

        LinkedList<Integer> emptyLinkedList = new LinkedList<>(linkedList);
        emptyLinkedList.removeAll(linkedList); // LinkedList 비우기 (size : 0)
        assertThat(emptyLinkedList).hasSize(0);
        
        // LinkedList 크기가 0이라면 다음 메서드들은 호출 시 null을 반환한다
        assertThat(emptyLinkedList.peek()).isNull();
        assertThat(emptyLinkedList.peekFirst()).isNull();
        assertThat(emptyLinkedList.peekLast()).isNull();
        assertThat(emptyLinkedList.poll()).isNull();
        assertThat(emptyLinkedList.pollFirst()).isNull();
        assertThat(emptyLinkedList.pollLast()).isNull();
        
        // LinkedList 크기가 0이라면 다음 메서드들은 호출 시 예외가 발생한다
        assertThrows(NoSuchElementException.class, () -> { emptyLinkedList.getFirst(); });
        assertThrows(NoSuchElementException.class, () -> { emptyLinkedList.getLast(); });
        assertThrows(NoSuchElementException.class, () -> { emptyLinkedList.pop(); });
        assertThrows(NoSuchElementException.class, () -> { emptyLinkedList.element(); });
    }

    @Test
    @DisplayName("ArrayList vs. LinkedList - 읽기(접근 시간)는 ArrayList가 더 빠르다")
    void arrayListAndLinkedListSearchTime() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 10000000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        
        // 읽기(접근 시간) : ArrayList가 LinkedList보다 빠르다
        // - 배열은 각 요소들이 연속적으로 메모리상에 존재하기 때문에
        //   간단한 계산만으로 원하는 요소의 주소를 얻어서 저장된 데이터를 곧바로 읽어올 수 있다
        // - LinkedList는 불연속적으로 위치한 각 요소들이 서로 연결된 것이라
        //   처음부터 n번째 데이터까지 차례대로 따라가야만 원하는 값을 얻을 수 있다
        long arrayListStart = System.currentTimeMillis();
        arrayList.get(700000);
        long arrayListEnd = System.currentTimeMillis();
        long arrayListSearchTime = arrayListEnd - arrayListStart;

        long linkedListStart = System.currentTimeMillis();
        linkedList.get(700000);
        long linkedListEnd = System.currentTimeMillis();
        long linkedListSearchTime = linkedListEnd - linkedListStart;
        
        assertThat(arrayListSearchTime).isLessThan(linkedListSearchTime);
    }

    @Test
    @DisplayName("ArrayList vs. LinkedList - 추가 및 삭제는 LinkedList가 더 빠르다")
    void arrayListAndLinkedListAddDeleteTime1() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 10000000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // 추가 및 삭제 : LinkedList가 ArrayList보다 빠르다 (단, 순차적인 추가 및 삭제는 ArrayList가 더 빠름)
        long startArrayListAdd = System.currentTimeMillis();
        arrayList.add(700000, 99999);
        long endArrayListAdd = System.currentTimeMillis();
        long arrayListAddTime = endArrayListAdd - startArrayListAdd;

        long startLinkedListAdd = System.currentTimeMillis();
        linkedList.add(700000, 99999);
        long endLinkedListAdd = System.currentTimeMillis();
        long linkedListAddTime = endLinkedListAdd - startLinkedListAdd;

        long startArrayListRemove = System.currentTimeMillis();
        arrayList.remove(700000);
        long endArrayListRemove = System.currentTimeMillis();
        long arrayListRemoveTime = endArrayListRemove - startArrayListRemove;

        long startLinkedListRemove = System.currentTimeMillis();
        linkedList.remove(700000);
        long endLinkedListRemove = System.currentTimeMillis();
        long linkedListRemoveTime = endLinkedListRemove - startLinkedListRemove;

        assertThat(linkedListAddTime).isLessThan(arrayListAddTime);
        assertThat(linkedListRemoveTime).isLessThan(arrayListRemoveTime);
    }
}
