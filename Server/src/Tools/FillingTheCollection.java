package Tools;

import Collection.HumanBeingCollection;
import Human.HumanBeing;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.PriorityQueue;

/***
 * Заполнение коллекции из JSON-файла.
 */

public class FillingTheCollection {
    /***
     *
     * @param filepath
     * @throws IOException
     */

    public static void fillTheCollection(String filepath) {
        try {
            PriorityQueue<HumanBeing> humans = new PriorityQueue<>();
            String data = IOModule.readFromFile(filepath);

            Type type = new TypeToken<ArrayList>() {
            }.getType();
            ArrayList<LinkedTreeMap> objects = new Gson().fromJson(data, type);

            for (int i = 0; i < objects.size(); i++) {
                LinkedTreeMap params = objects.get(i);
                HumanBeing human = new HumanBeing();
                human.setCreationDate(ZonedDateTime.now());


                if (((params.get("creationDate")) != null) && !((params.get("creationDate")).equals("")))
                    human.setCreationDate(ZonedDateTime.now());


                if (((params.get("name")) != null) && !((params.get("name")).equals("")))
                    human.setName((String) params.get("name"));
                else {
                    throw new IllegalArgumentException();
                }

                if (((params.get("id")) != null) && !((params.get("id")).equals(""))) {
                    human.setId((((Double) params.get("id")).longValue()));
                }

                human.setRealHero((boolean) params.get("realHero"));

                human.setHasToothpick((boolean) params.get("hasToothpick"));

                if ((((Double) params.get("impactSpeed")).longValue()) <= 383 && ((((Double) params.get("impactSpeed")).longValue()) >= 0))
                    human.setImpactSpeed(((Double) params.get("impactSpeed")).longValue());
                else {
                    throw new IllegalArgumentException();
                }

                if ((Double) params.get("minutesOfWaiting") >= 0)
                    human.setMinutesOfWaiting((Double) params.get("minutesOfWaiting"));
                else {
                    throw new IllegalArgumentException();
                }

                if (params.get("weaponType").equals("KNIFE") ||
                        params.get("weaponType").equals("HAMMER") ||
                        params.get("weaponType").equals("BAT") ||
                        params.get("weaponType").equals("RIFLE") ||
                        params.get("weaponType").equals("AXE"))
                    human.setWeaponType(HumanBeing.WeaponType.valueOf((String) params.get("weaponType")));
                else {
                    throw new IllegalArgumentException();
                }

                if (params.get("mood").equals("SADNESS") ||
                        params.get("mood").equals("SORROW") ||
                        params.get("mood").equals("LONGING") ||
                        params.get("mood").equals("APATHY"))
                    human.setMood(HumanBeing.Mood.valueOf((String) params.get("mood")));
                else {
                    throw new IllegalArgumentException();
                }

                LinkedTreeMap cars = (LinkedTreeMap) params.get("car");

                if (params.get("car") != null && !params.get("car").equals("")) {

                    HumanBeing.Car car = human.new Car();

                    if (((cars.get("name")) != null) && !((cars.get("name")).equals("")))
                        car.setCarName((String) cars.get("name"));
                    else {
                        throw new IllegalArgumentException();
                    }
                    car.setCarCool((Boolean) cars.get("cool"));

                    human.setCar(car);
                }


                HumanBeing.Coordinates coordinates = human.new Coordinates();

                LinkedTreeMap coords = (LinkedTreeMap) params.get("coordinates");

                if (((Double) coords.get("x")) > -928)
                    coordinates.setX((Double) coords.get("x"));
                else {
                    throw new IllegalArgumentException();
                }

                if (((Double) coords.get("y")).longValue() > -556)
                    coordinates.setY(((Double) coords.get("y")).longValue());
                else {
                    throw new IllegalArgumentException();
                }

                human.setCoordinates(coordinates);

                humans.add(human);
            }
            HumanBeingCollection.setCollection(humans);
            System.out.println("Коллекция успешно заполнена.");
        } catch (JsonSyntaxException | ClassCastException | IllegalArgumentException e) {
            System.out.println("В файле неверно указаны данные. Коллекция не заполнена.");
        } catch (NullPointerException e) {
            System.out.println("Невозможно загрузить коллекцию.");
        }
    }
}
