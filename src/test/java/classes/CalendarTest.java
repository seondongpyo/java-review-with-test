package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
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

    @Test
    @DisplayName("Calendar 클래스 - 예제 2 (두 날짜 간의 차이 구하기)")
    void calendarExercise2() {
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date1.set(2020, Calendar.DECEMBER, 17); // 2020년 12월 17일
        date2.set(2020, Calendar.DECEMBER, 24); // 2020년 12월 24일

        long date1TimeInMillis = date1.getTimeInMillis();
        long date2TimeInMillis = date2.getTimeInMillis();
        long differenceInMillis = (date2TimeInMillis - date1TimeInMillis); // 두 날짜간의 차이를 밀리초 단위로 계산
        long differenceInSeconds = differenceInMillis / 1000; // 초 단위로 계산
        long differenceInDays = differenceInSeconds / (24 * 60 * 60); // 일 단위로 계산

        assertThat(differenceInSeconds).isEqualTo(604800); // 7일 * 24시간 * 60분 * 60초 = 604800
        assertThat(differenceInDays).isEqualTo(7); // 7일 차이
    }

    @Test
    @DisplayName("Calendar 클래스 - 예제 3 (두 시간 사이의 차이 구하기)")
    void calendarExercise3() {
        Calendar time1 = Calendar.getInstance();
        time1.set(Calendar.HOUR_OF_DAY, 11);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 50); // 11:30:50

        Calendar time2 = Calendar.getInstance();
        time2.set(Calendar.HOUR_OF_DAY, 22);
        time2.set(Calendar.MINUTE, 40);
        time2.set(Calendar.SECOND, 50); // 22:40:50

        long time1InMillis = time1.getTimeInMillis();
        long time2InMillis = time2.getTimeInMillis();
        long differenceInMillis = time2InMillis - time1InMillis;
        long differenceInSeconds = differenceInMillis / 1000; // 초 단위
        long differenceInHours = (differenceInMillis / 1000) / 3600; // 시 단위
        long differenceInMinutes = ((differenceInMillis / 1000) % 3600) / 60; // 분 단위

        assertThat(differenceInSeconds).isEqualTo(40200L);
        assertThat(differenceInHours).isEqualTo(11L);
        assertThat(differenceInMinutes).isEqualTo(10L);
    }

    @Test
    @DisplayName("Calendar 클래스 - 예제 4 (날짜 필드 연산)")
    void calendarExercise4() {
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.DECEMBER, 31); // 2020년 12월 31일

        // add() : 다른 날짜 필드에 영향을 미친다
        date.add(Calendar.DATE, 1); // 1일 후 : 2021년 1월 1일
        assertThat(date.get(Calendar.YEAR)).isEqualTo(2021);
        assertThat(date.get(Calendar.MONTH)).isEqualTo(Calendar.JANUARY);
        assertThat(date.get(Calendar.DATE)).isEqualTo(1);
        
        date.add(Calendar.MONTH, -5); // 5개월 전 : 2020년 8월 1일
        assertThat(date.get(Calendar.MONTH)).isEqualTo(Calendar.AUGUST);
        
        // roll() : 다른 날짜 필드에 영향을 미치지 않는다
        // 단, '일'이 말일(end of month)일 떄 roll()을 통해 '월'을 변경하면 '일'에 영향을 미칠 수 있다
        date.roll(Calendar.DATE, 31); // 31일 후(roll) : 2020년 8월 1일 (9월 X)
        assertThat(date.get(Calendar.MONTH)).isEqualTo(Calendar.AUGUST).isNotEqualTo(Calendar.SEPTEMBER);
        assertThat(date.get(Calendar.DATE)).isEqualTo(1);

        date.set(Calendar.DATE, 31); // 2020년 8월 1일에서 말일(31일)로 변경
        date.roll(Calendar.MONTH, 1); // 2020년 8월에서 2020년 9월로 변경하면?
        assertThat(date.get(Calendar.MONTH)).isEqualTo(Calendar.SEPTEMBER).isNotEqualTo(Calendar.AUGUST);
        assertThat(date.get(Calendar.DATE)).isEqualTo(30).isNotEqualTo(31); // 2020년 9월은 말일이 30일이다
    }

    @Test
    @DisplayName("Date와 Calendar간의 변환 - Calendar to Date")
    void calendarToDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.DECEMBER, 18);
        Date date = new Date(calendar.getTimeInMillis()); // new Date(long date)

        // Date 클래스의 대부분의 메서드는 deprecated
        assertThat(date.getYear() + 1900).isEqualTo(2020); // As of JDK version 1.1, replaced by Calendar.get(Calendar.YEAR) - 1900.
        assertThat(date.getMonth()).isEqualTo(11); // Calendar와 마찬가지로 0부터 1월 시작
        assertThat(date.getDate()).isEqualTo(18);
    }

    @Test
    @DisplayName("Date와 Calendar간의 변환 - Date to Calendar")
    void dateToCalendar() {
        Date date = new Date();
        date.setYear(2020 - 1900); // 연도는 1900년을 기준으로 생성 (2020 입력 시 3920년이 됨)
        date.setMonth(11);
        date.setDate(18);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        assertThat(calendar.get(Calendar.YEAR)).isEqualTo(2020);
        assertThat(calendar.get(Calendar.MONTH)).isEqualTo(Calendar.DECEMBER);
        assertThat(calendar.get(Calendar.DATE)).isEqualTo(18);
    }
}
