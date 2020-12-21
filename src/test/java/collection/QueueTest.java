package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void initQueue() {
        queue = new LinkedList<>(); // Queue 인터페이스의 구현체인 LinkedList 사용
    }

    @Test
    @DisplayName("Queue 인터페이스 add() - Queue에 객체를 추가 (성공 시 true 반환, 저장 공간 부족 시 IllegalStateException 발생")
    void add() {
        /*
            Inserts the specified element into this queue
            if it is possible to do so immediately without violating capacity restrictions,
            returning true upon success and throwing an IllegalStateException
            if no space is currently available.
         */

        boolean isOneAdded = queue.add(1);
        queue.add(2);
        queue.add(3);

        assertThat(isOneAdded).isTrue();
        assertThat(queue).hasSize(3);
    }

    @Test
    @DisplayName("Queue 인터페이스 offer() - Queue에 객체를 추가 (성공 시 true, 실패 시 false 반환)")
    void offer() {
        /*
            Inserts the specified element into this queue
            if it is possible to do so immediately without violating capacity restrictions.
            When using a capacity-restricted queue,
            this method is generally preferable to add(E),
            which can fail to insert an element only by throwing an exception.

            -> 저장 실패 시 예외가 발생하는 add()보다 일반적으로 더 낫다
         */

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertThat(queue).hasSize(3);
    }

    @Test
    @DisplayName("Queue 인터페이스 element() - Queue에서 객체를 꺼내진 않고 반환 (Queue가 비어있으면 NoSuchElementException 예외 발생)")
    void element() {
        /*
            Retrieves, but does not remove, the head of this queue.
            This method differs from peek only in that
            it throws an exception if this queue is empty.

            -> peek()과의 차이점은, Queue가 비어있을 때 호출 시 NoSuchElementException 발생 여부
         */
        queue.offer(1);
        queue.offer(2);

        assertThat(queue.element()).isEqualTo(1);
        assertThat(queue.element()).isEqualTo(1).isNotEqualTo(2); // 객체를 꺼내진 않음
        assertThrows(NoSuchElementException.class, () -> {
            new LinkedList<>().element();
        });
    }

    @Test
    @DisplayName("Queue 인터페이스 peek() - Queue에서 객체를 꺼내진 않고 반환 (Queue가 비어있으면 null 반환)")
    void peek() {
        /*
            Retrieves, but does not remove,
            the head of this queue, or returns null if this queue is empty.
         */

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertThat(queue.peek()).isEqualTo(1);
        assertThat(queue.peek()).isEqualTo(1).isNotEqualTo(2); // 객체를 꺼내진 않음
        assertThat(new LinkedList<>().peek()).isEqualTo(null);
    }

    @Test
    @DisplayName("Queue 인터페이스 remove() - Queue에서 객체를 꺼내 반환 (Queue가 비어있으면 NoSuchElementException 예외 발생)")
    void remove() {
        /*
            Retrieves and removes the head of this queue.
            This method differs from poll() only in that
            it throws an exception if this queue is empty.

            -> poll()과의 차이점은, Queue가 비어있을 때 호출 시 NoSuchElementException 발생 여부
         */

        queue.offer(1);
        queue.offer(2);

        assertThat(queue.remove()).isEqualTo(1);
        assertThat(queue.remove()).isEqualTo(2);
        assertThrows(NoSuchElementException.class, () -> {
            new LinkedList<>().remove();
        });
    }

    @Test
    @DisplayName("Queue 인터페이스 poll() - Queue에서 객체를 꺼내 반환 (Queue가 비어있으면 null 반환)")
    void poll() {
        /*
            Retrieves and removes the head of this queue,
            or returns null if this queue is empty.
         */

        queue.offer(2);
        queue.offer(4);

        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(4);
        assertThat(queue.poll()).isNull();
    }
}
