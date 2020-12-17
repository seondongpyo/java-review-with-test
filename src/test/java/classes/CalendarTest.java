package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarTest {
    
    @Test
    @DisplayName("Calendar 클래스 - getInstance()")
    void calendarGetInstance() {
        /*
            << Calendar.getInstance() >>
            - 추상 클래스인 Calendar 클래스를 구현한 클래스의 인스턴스를 반환한다
            - 태국의 경우에는 BuddhistCalendar 클래스의 인스턴스를,
              그 외에는 GregorianCalendar 클래스의 인스턴스를 반환한다
         */
        Calendar calendar = Calendar.getInstance();

        assertThat(calendar).isInstanceOf(GregorianCalendar.class);
    }

    @Test
    @DisplayName("Calendar 클래스 - 예제 1")
    void calendarExercise1() {
        Calendar current = Calendar.getInstance(); // 기본적으로 현재 시스템의 날짜와 시간으로 설정된다
        Calendar future = new GregorianCalendar(2099, Calendar.DECEMBER, 31); // 특정 날짜로 설정
        Calendar now = new GregorianCalendar(2020, Calendar.DECEMBER, 17); // 2020년 12월 17일은 12월 3째주 목요일이다

        int currentYear = current.get(Calendar.YEAR);
        int currentMonth = current.get(Calendar.MONTH);
        int currentDate = current.get(Calendar.DATE);
        int futureYear = future.get(Calendar.YEAR);
        int futureMonth = future.get(Calendar.MONTH); // Calendar.MONTH : 0(1월), 1(2월), 2(3월), ... , 11(12월)
        int futureDate = future.get(Calendar.DAY_OF_MONTH);// DATE와 DAY_OF_MONTH는 같다
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH);
        int nowDate = now.get(Calendar.DATE); // 2020년 12월 중 일 수
        int nowWeekOfMonth = now.get(Calendar.WEEK_OF_MONTH); // 이 달의 몇째 주?
        int nowWeekOfYear = now.get(Calendar.WEEK_OF_YEAR); // 2020년 중 몇째 주?
        int nowDayOfYear = now.get(Calendar.DAY_OF_YEAR); // 전체 365일 중 352일
        int nowDayOfWeek = now.get(Calendar.DAY_OF_WEEK); // 요일 : 1(일), 2(월), 3(화), ... , 7(토)
        int nowDayOfWeekInMonth = now.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 이 달의 몇 번째 요일

        assertThat(currentYear).isLessThan(futureYear);
        assertThat(currentMonth).isLessThanOrEqualTo(futureMonth);
        assertThat(currentDate).isLessThanOrEqualTo(futureDate);
        assertThat(nowYear).isEqualTo(2020);
        assertThat(nowMonth).isEqualTo(11); // <주의> 12월은 11 (0: 1월, 1: 2월, ...)
        assertThat(nowDate).isEqualTo(17);
        assertThat(nowWeekOfMonth).isEqualTo(3);
        assertThat(nowWeekOfYear).isEqualTo(51);
        assertThat(nowDayOfYear).isEqualTo(352);
        assertThat(nowDayOfWeek).isEqualTo(5).isEqualTo(Calendar.THURSDAY);
        assertThat(nowDayOfWeekInMonth).isEqualTo(3);
    }
}
