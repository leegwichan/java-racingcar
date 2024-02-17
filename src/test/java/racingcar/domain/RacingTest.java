package racingcar.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RacingTest {

    @DisplayName("가장 멀리간 차가 우승자가 된다.")
    @Test
    void findSingleWinnerTest() {
        MockCar carA = new MockCar(1);
        MockCar carB = new MockCar(2);
        List<Car> cars = List.of(carA, carB);
        Racing racing = new Racing(cars, () -> true);

        List<Car> winners = racing.findWinner();

        Assertions.assertThat(winners).containsExactlyInAnyOrder(carB);
    }

    @DisplayName("우승자가 여려명이 될 수 있다.")
    @Test
    void findMultipleWinnerTest() {
        MockCar carA = new MockCar(2);
        MockCar carB = new MockCar(2);
        MockCar carC = new MockCar(1);
        List<Car> cars = List.of(carA, carB, carC);
        Racing racing = new Racing(cars, () -> true);

        List<Car> winners = racing.findWinner();

        Assertions.assertThat(winners).containsExactlyInAnyOrder(carA, carB);
    }

    @DisplayName("전략에 따라 전진 유무가 결정된다.")
    @ParameterizedTest
    @CsvSource({"true, 1", "false, 0"})
    void moveTest(boolean isMove, int expectedPosition) {
        MockCar carA = new MockCar();
        List<Car> cars = List.of(carA);
        Racing racing = new Racing(cars, () -> isMove);

        racing.move();

        Assertions.assertThat(carA.getPosition()).isEqualTo(expectedPosition);
    }
}
