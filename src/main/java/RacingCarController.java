import domain.Car;
import domain.TrialCount;
import java.util.List;
import java.util.Random;
import view.InputView;
import view.OutputView;

public class RacingCarController {

    public static void run() {
        List<String> carNames = InputView.inputNames();
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .toList();

        int trialAmount = InputView.inputTryCount();
        TrialCount trialCount = new TrialCount(trialAmount);
        trialCount.repeat(() -> progressRacing(cars));
        OutputView.printResultTitle();

        List<Car> winners = findWinners(cars);
        OutputView.printWinners(winners);
    }

    private static void progressRacing(List<Car> cars) {
        cars.forEach(RacingCarController::moveRandomly);
        OutputView.printProgress(cars);
    }

    private static void moveRandomly(Car car) {
        Random random = new Random();
        int randomInt = Math.abs(random.nextInt() % 10);
        car.move(randomInt >= 4);
    }

    private static List<Car> findWinners(List<Car> cars) {
        int maxPosition = cars.stream().mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .toList();
    }
}