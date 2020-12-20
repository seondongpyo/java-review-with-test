package collection.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayListTest {

    @Test
    @DisplayName("ArrayList 예제")
    void arrayList() {
        ArrayList<Integer> intList1 = new ArrayList<>();
        intList1.add(5);
        intList1.add(2);
        intList1.add(4);
        intList1.add(1);
        intList1.add(3);
        intList1.add(0);

        ArrayList<Integer> intList2 = new ArrayList<>(intList1); // 주어진 컬렉션이 저장된 ArrayList 생성
        assertThat(intList1).hasSameSizeAs(intList2);
        assertThat(intList1).isEqualTo(intList2);

        Collections.sort(intList1); // Collections.sort() : list 오름차순 정렬
        assertThat(intList1.get(0)).isEqualTo(0).isNotEqualTo(intList2.get(0));
        assertThat(intList1).isNotEqualTo(intList2);
        
        /*
            << ArrayList의 데이터 추가와 삭제 >>
            - ArrayList의 요소를 삭제하는 경우, 삭제할 객체의 바로 아래에 있는 데이터를 한 칸씩 위로 복사해서
              삭제할 객체를 덮어쓰는 방식으로 처리함
            - 삭제할 객체가 마지막 데이터라면, 복사할 필요 없이 단순히 null로 변경해주면 됨
            - ArrayList에 객체를 순차적으로 저장할 때와, 마지막에 저장된 요소부터 삭제하면
              데이터를 옮기지 않아도 되기 때문에 작업 시간이 짧지만,
              중간에 객체를 추가하거나 중간에 위치한 객체를 삭제하는 경우
              다른 데이터의 위치를 옮겨줘야 하기 때문에 데이터가 많을수록 작업 시간이 오래 걸림
         */
        
        intList1.add(3, 6);
        assertThat(intList1).containsExactly(0, 1, 2, 6, 3, 4, 5);

        intList1.remove(2);
        assertThat(intList1).containsExactly(0, 1, 6, 3, 4, 5);
    }
}
