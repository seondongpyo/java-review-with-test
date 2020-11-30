package method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MethodParameterTest {

    @Test
    @DisplayName("기본형 매개변수 - 변수의 값을 읽기만 할 수 있다 (read only)")
    void primitiveTypeParameter() {
        Data data = new Data();
        data.number = 100;
        changeNumber(data.number); // 매개변수의 타입이 기본형일 경우, 값이 복사되어 전달됨

        assertThat(data.number).isEqualTo(100);
    }

    @Test
    @DisplayName("참조형 매개변수 - 변수의 값을 읽고 변경할 수 있다 (read & write)")
    void referenceTypeParameter() {
        Data data = new Data();
        changeData(data); // 매개변수의 타입이 참조형일 경우, 주소 값이 전달됨

        assertThat(data.number).isEqualTo(1000);
    }

    // 매개변수가 기본형인 메서드
    void changeNumber(int paramNumber) { // data.number의 값인 100이 number에 복사되어 전달됨
        paramNumber = 1000; // 원본 데이터의 값이 바뀐 게 아니라 매개변수로 전달된 number의 값이 변경됨
    } // changeNumber() 메서드가 종료되면서 매개변수 paramNumber는 스택에서 제거됨

    // 매개변수가 참조형인 메서드
    void changeData(Data paramData) { // 참조변수 data의 주소 값이 매개변수 paramData에 복사되어 전달됨
        paramData.number = 1000; // 참조변수 data와 매개변수 paramData는 같은 인스턴스를 가리킨다
    } // changeData() 메서드가 종료되면서 매개변수 paramData는 스택에서 제거됨

    private class Data {
        int number;
    }
}
