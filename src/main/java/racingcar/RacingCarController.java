package racingcar;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RandomDigitSupplier;
import racingcar.domain.RandomMoveStrategy;
import racingcar.domain.TrialCount;
import java.util.List;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private RacingCarController() {
    }

    public static void run() {
        Cars cars = inputCars();
        TrialCount trialCount = inputTrialCount();

        OutputView.printResultTitle();
        trialCount.repeat(() -> progressRacing(cars));

        List<Car> winners = cars.findWinner();
        OutputView.printWinners(winners);
    }

    private static Cars inputCars() {
        try {
            List<String> carNames = InputView.inputNames();
            return Cars.of(carNames, new RandomMoveStrategy(new RandomDigitSupplier()));
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return inputCars();
        }
    }

    private static TrialCount inputTrialCount() {
        try {
            int trialAmount = InputView.inputTryCount();
            return new TrialCount(trialAmount);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return inputTrialCount();
        }
    }

    private static void progressRacing(Cars cars) {
        cars.move();
        OutputView.printProgress(cars.getCarList());
    }
}