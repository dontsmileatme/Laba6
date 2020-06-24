package Tools;

import Human.HumanBeing;

import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Создание экземпляра класса-человека (вручную и из скрипта).
 */
public class HumanCreation {
    /**
     * @return объект человека
     */
    public HumanBeing create() {
        System.out.println("Вы запустили процесс создания человека!");
        HumanBeing human = new HumanBeing();
        HumanBeing.Coordinates coordinates = human.new Coordinates();
        HumanBeing.Car car = human.new Car();
        human.setCreationDate(ZonedDateTime.now());
        this.setNameForHuman(human);
        this.setCoordinateXForCoordinates(coordinates);
        this.setCoordinateYForCoordinates(coordinates);
        human.setCoordinates(coordinates);
        this.setRealHeroForHuman(human);
        this.setHasToothpickForHuman(human);
        this.setImpactSpeedForHuman(human);
        this.setMinutesOfWaitingForHuman(human);
        this.setWeaponTypeForHuman(human);
        this.setMoodForHuman(human);
        System.out.println("Вы хотите заполнить поле \"Car\"?");
        System.out.println("Нажмите \"Enter\", если да, или введите \"no\", если не хотите.");
        System.out.print("$ ");
        String answer = new Scanner(System.in).nextLine();
        if (!answer.equals("no")) {
            this.setNameForCar(car);
            this.setCoolForCar(car);
            human.setCar(car);
        }
        return human;
    }

    /**
     * @return объект человека
     */
    public HumanBeing createFromFile(String[] params) {
        System.out.println("Вы запустили процесс создания человека из скрипта!");
        HumanBeing human = new HumanBeing();
        HumanBeing.Coordinates coordinates = human.new Coordinates();
        HumanBeing.Car car = human.new Car();
        human.setCreationDate(ZonedDateTime.now());
        this.setNameForHumanFromFile(human, params[0]);
        this.setCoordinateXForCoordinatesFromFile(coordinates, params[1]);
        this.setCoordinateYForCoordinatesFromFile(coordinates, params[2]);
        human.setCoordinates(coordinates);
        this.setRealHeroForHumanFromFile(human, params[3]);
        this.setToothPickForHumanFromFile(human, params[4]);
        this.setImpactSpeedForHumanFromFile(human, params[5]);
        this.setMinutesOfWaitingForHumanFromFile(human, params[6]);
        this.setWeaponTypeForHumanFromFile(human, params[7]);
        this.setMoodForHumanFromFile(human, params[8]);
        if (!(params[9].isEmpty() && params[10].isEmpty())) {
            this.setNameForCarFromFile(car, params[9]);
            this.setCoolForCarFromFile(car, params[10]);
            human.setCar(car);
        }
        return human;
    }

    public void setNameForHuman(HumanBeing human) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя человека: ");
            System.out.print("$ ");
            String name = scanner.nextLine();
            if (!(name.equals("") || name == null)) human.setName(name);
            else {
                System.out.println("Значение должно быть типа \"String\".");
                this.setNameForHuman(human);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Значение должно быть типа \"String\".");
            this.setNameForHuman(human);
        }
    }

    public void setCoordinateXForCoordinates(HumanBeing.Coordinates coordinates) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите координату \"x\":");
            System.out.print("$ ");
            String x = scanner.nextLine();
            if (!(x.equals("") || x == null) && Double.parseDouble(x) > -928)
                coordinates.setX(Double.parseDouble(x));
            else {
                System.out.println("Значение должно быть типа \"Double\" и больше -928.");
                this.setCoordinateXForCoordinates(coordinates);
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println("Значение должно быть типа \"Double\" и больше -928.");
            this.setCoordinateXForCoordinates(coordinates);
        }
    }

    public void setCoordinateYForCoordinates(HumanBeing.Coordinates coordinates) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите координату \"y\":");
            System.out.print("$ ");
            String y = scanner.nextLine();
            if (!(y.equals("") || y == null) && Long.parseLong(y) > -556) coordinates.setY(Long.parseLong(y));
            else {
                System.out.println("Значение должно быть типа \"Long\" и больше -556.");
                this.setCoordinateYForCoordinates(coordinates);
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println("Значение должно быть типа \"Long\" и больше -556.");
            this.setCoordinateYForCoordinates(coordinates);
        }
    }

    public void setRealHeroForHuman(HumanBeing human) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите, является ли человек настоящим героем (true / false):");
        System.out.print("$ ");
        String isRealHero = scanner.nextLine();
        if (isRealHero.equals("true") || isRealHero.equals("false"))
            human.setRealHero(Boolean.parseBoolean(isRealHero));
        else {
            System.out.println("Значение должно быть типа \"boolean\".");
            this.setRealHeroForHuman(human);
        }
    }

    public void setHasToothpickForHuman(HumanBeing human) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите, есть ли у человека зубочистка (true / false):");
        System.out.print("$ ");
        String hasToothPick = scanner.nextLine();
        if (hasToothPick.equals("true") || hasToothPick.equals("false"))
            human.setHasToothpick(Boolean.parseBoolean(hasToothPick));
        else {
            System.out.println("Значение должно быть типа \"boolean\".");
            this.setHasToothpickForHuman(human);
        }
    }

    public void setImpactSpeedForHuman(HumanBeing human) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите скорость удара человека: ");
            System.out.print("$ ");
            String impactSpeed = scanner.nextLine();
            if (!(impactSpeed.equals("") || impactSpeed.equals(null)) && ((Long.parseLong(impactSpeed)) >= 0) && ((Long.parseLong(impactSpeed)) <= 383))
                human.setImpactSpeed(Long.parseLong(impactSpeed));
            else {
                System.out.println("Значение должно быть типа \"long\", неотрицательным и не больше 383.");
                this.setImpactSpeedForHuman(human);
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println("Значение должно быть типа \"long\", неотрицательным и не больше 383.");
            this.setImpactSpeedForHuman(human);
        }
    }

    public void setMinutesOfWaitingForHuman(HumanBeing human) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите время ожидания человека: ");
            System.out.print("$ ");
            String minutesOfWaiting = scanner.nextLine();
            if (!(minutesOfWaiting.equals("") || minutesOfWaiting.equals(null)) && Double.parseDouble(minutesOfWaiting) > 0)
                human.setMinutesOfWaiting(Double.parseDouble(minutesOfWaiting));
            else {
                System.out.println("Значение должно быть типа \"Double\" и неотрицательным.");
                this.setMinutesOfWaitingForHuman(human);
            }
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println("Значение должно быть типа \"Double\" и неотрицательным.");
            this.setMinutesOfWaitingForHuman(human);
        }
    }

    public void setWeaponTypeForHuman(HumanBeing human) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите тип оружия, находящегося у человека (HAMMER, AXE, RIFLE, KNIFE, BAT):");
            System.out.print("$ ");
            String weaponType = scanner.nextLine();
            if (weaponType.equals("KNIFE") || weaponType.equals("HAMMER") || weaponType.equals("BAT") || weaponType.equals("RIFLE") || weaponType.equals("AXE"))
                human.setWeaponType(HumanBeing.WeaponType.valueOf(weaponType));
            else {
                System.out.println("Значение должно соответствовать перечислинным типам.");
                this.setWeaponTypeForHuman(human);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Значение должно соответствовать перечислинным типам.");
            this.setWeaponTypeForHuman(human);
        }
    }

    public void setMoodForHuman(HumanBeing human) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите настроение человека: (SADNESS, LONGING, APATHY, SORROW):");
            System.out.print("$ ");
            String mood = scanner.nextLine();
            if (mood.equals("SADNESS") || mood.equals("SORROW") || mood.equals("LONGING") || mood.equals("APATHY"))
                human.setMood(HumanBeing.Mood.valueOf(mood));
            else {
                System.out.println("Значение должно соответствовать перечислинным типам.");
                this.setMoodForHuman(human);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Значение должно соответствовать перечислинным типам.");
            this.setMoodForHuman(human);
        }
    }

    public void setNameForCar(HumanBeing.Car car) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название машины, принадлежащей человеку: ");
            System.out.print("$ ");
            String nameCar = scanner.nextLine();
            if (!(nameCar.equals("") || nameCar.equals(null))) car.setCarName(nameCar);
            else {
                System.out.println("Значение должно быть типа \"String\".");
                this.setNameForCar(car);
            }
        } catch (InputMismatchException e) {
            System.out.println("Значение должно быть типа \"String\".");
            this.setNameForCar(car);
        }
    }

    public void setCoolForCar(HumanBeing.Car car) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите, крутая ли машина у человека (true / false): ");
        System.out.print("$ ");
        String coolCar = scanner.nextLine();
        if (coolCar.equals("true") || coolCar.equals("false")) car.setCarCool(Boolean.parseBoolean(coolCar));
        else {
            System.out.println("Значение должно быть типа \"boolean\".");
            this.setCoolForCar(car);
        }
    }

    public void setNameForHumanFromFile(HumanBeing human, String name) {
        try {
            if (!(name.equals("") || name.equals(null))) human.setName(name);
            else {
                System.out.println("Значение поля \"name\" должно быть типа \"String\". ");
                this.setNameForHuman(human);
            }
        } catch (InputMismatchException e) {
            System.out.println("Значение поля \"name\" должно быть типа \"String\".");
            this.setNameForHuman(human);
        }
    }

    public void setCoordinateXForCoordinatesFromFile(HumanBeing.Coordinates coordinates, String xs) {
        try {
            double x = Double.parseDouble(xs);
            if (x > -928) coordinates.setX(x);
            else {
                System.out.println("Значение должно быть больше -928.");
                this.setCoordinateXForCoordinates(coordinates);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение поля \"x\" должно быть типа \"double\".");
            this.setCoordinateXForCoordinates(coordinates);
        }
    }

    public void setCoordinateYForCoordinatesFromFile(HumanBeing.Coordinates coordinates, String ys) {
        try {
            long y = Long.parseLong(ys);
            if (y > -556) coordinates.setY(y);
            else {
                System.out.println("Значение должно быть больше -556.");
                this.setCoordinateYForCoordinates(coordinates);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение поля \"y\" должно быть типа \"long\".");
            this.setCoordinateYForCoordinates(coordinates);
        }
    }

    public void setRealHeroForHumanFromFile(HumanBeing human, String isRealHero) {
        if (!isRealHero.equals("") && (isRealHero.equals("true") || isRealHero.equals("false")))
            human.setRealHero(Boolean.parseBoolean(isRealHero));
        else {
            System.out.println("Значение поля \"Является ли человек настоящим героем\" должно быть типа \"boolean\".");
            this.setRealHeroForHuman(human);
        }
    }

    public void setToothPickForHumanFromFile(HumanBeing human, String hasToothPick) {
        if (!hasToothPick.equals("") && (hasToothPick.equals("true") || hasToothPick.equals("false")))
            human.setHasToothpick(Boolean.parseBoolean(hasToothPick));
        else {
            System.out.println("Значение поля \"Есть ли у челика зубочистка\" должно быть типа \"boolean\".");
            this.setHasToothpickForHuman(human);
        }
    }

    public void setImpactSpeedForHumanFromFile(HumanBeing human, String impactSpeed) {
        try {
            long impactSpeedn = Long.parseLong(impactSpeed);
            if (!(impactSpeed.equals("") || impactSpeed == null) && (impactSpeedn >= 0) && (impactSpeedn <= 383))
                human.setImpactSpeed(impactSpeedn);
            else {
                System.out.println("Значение поля \"Скорость удара\" должно быть типа \"long\", неотрицательным и не больше 383.");
                this.setImpactSpeedForHuman(human);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение поля \"Скорость удара\" должно быть типа \"long\", неотрицательным и не больше 383.");
            this.setImpactSpeedForHuman(human);
        }
    }

    public void setMinutesOfWaitingForHumanFromFile(HumanBeing human, String minutesOfWaiting) {
        try {
            Double minutesOfWaitingn = Double.parseDouble(minutesOfWaiting);
            if (!(minutesOfWaiting.equals("") || minutesOfWaiting == null) && (minutesOfWaitingn >= 0)) {
                human.setMinutesOfWaiting(minutesOfWaitingn);
            } else {
                System.out.println("Значение поля \"Время ожидания\" должно быть типа \"double\" и неотрицательным.");
                this.setMinutesOfWaitingForHuman(human);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение поля \"Время ожидания\" должно быть типа \"double\" и неотрицательным.");
            this.setMinutesOfWaitingForHuman(human);
        }
    }

    public void setWeaponTypeForHumanFromFile(HumanBeing human, String weaponType) {
        try {
            if (weaponType.toUpperCase().equals("KNIFE") || weaponType.toUpperCase().equals("HAMMER") || weaponType.toUpperCase().equals("BAT") || weaponType.toUpperCase().equals("RIFLE") || weaponType.toUpperCase().equals("AXE"))
                human.setWeaponType(HumanBeing.WeaponType.valueOf(weaponType));
            else {
                System.out.println("Значение поля \"Тип оружия\" должно соответствовать перечислинным типам (KNIFE, HAMMER, BAT, RIFLE, AXE).");
                this.setWeaponTypeForHuman(human);
            }
        } catch (InputMismatchException e) {
            System.out.println("Значение поля \"Тип оружия\" должно соответствовать перечислинным типам (KNIFE, HAMMER, BAT, RIFLE, AXE).");
            this.setWeaponTypeForHuman(human);
        }
    }

    public void setMoodForHumanFromFile(HumanBeing human, String mood) {
        try {
            if (mood.toUpperCase().equals("SADNESS") || mood.toUpperCase().equals("SORROW") || mood.toUpperCase().equals("LONGING") || mood.toUpperCase().equals("APATHY"))
                human.setMood(HumanBeing.Mood.valueOf(mood));
            else {
                System.out.println("Значение поля \"Настроение\" должно соответствовать перечислинным типам (SADNESS, SORROW, LONGING, APATHY).");
                this.setMoodForHuman(human);
            }
        } catch (InputMismatchException e) {
            System.out.println("Значение поля \"Настроение\" должно соответствовать перечислинным типам (SADNESS, SORROW, LONGING, APATHY).");
            this.setMoodForHuman(human);
        }
    }

    public void setNameForCarFromFile(HumanBeing.Car car, String nameCar) {
        try {
            if (!(nameCar.equals("") || nameCar == null)) car.setCarName(nameCar);
            else {
                System.out.println("Значение должно быть типа \"String\".");
                this.setNameForCar(car);
            }
        } catch (InputMismatchException e) {
            System.out.println("Значение должно быть типа \"String\".");
            this.setNameForCar(car);
        }
    }

    public void setCoolForCarFromFile(HumanBeing.Car car, String coolCar) {
        try {
            if (!(coolCar.equals("") || coolCar == null) && (coolCar.equals("true") || coolCar.equals("false")))
                car.setCarCool(Boolean.parseBoolean(coolCar));
            else {
                System.out.println("Значение поля \"Крутая ли машина\" должно быть типа \"boolean\". ");
                this.setCoolForCar(car);
            }
        } catch (InputMismatchException e) {
            System.out.println("Значение поля \"Крутая ли машина\" должно быть типа \"boolean\". ");
            this.setCoolForCar(car);
        }
    }

}


