package bg.softuni.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ObjectToJsonDemo
{
    public static void main(String[] args)
    {
        AddressDTO address = new AddressDTO("Bulgaria", "Sofia");

        PersonDTO person = new PersonDTO("Georgi",
                                            "Ivanov",
                                                24,
                                            true,
                                                    Date.from(Instant.now()),
                                                    address);

        person.setFriendsList(List.of("Ivan", "Pesho", "Mitko"));

        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation() // Shows only fields with annotation @Expose
                .setDateFormat("dd-MM-YYYY")
                .serializeNulls() // Shows null values
                .create();

        String json = gsonBuilder.toJson(person);

        System.out.println(json);

        PersonDTO secondPerson = new PersonDTO("Desislava",
                "Topuzakova",
                27,
                false,
                Date.from(Instant.now()),
                address);

        secondPerson.setFriendsList(List.of("Niki", "Tedi", "Marti"));

        List<PersonDTO> peopleList = new ArrayList<>();
        peopleList.add(person);
        peopleList.add(secondPerson);

        String jsonList = gsonBuilder.toJson(peopleList);

        System.out.println(jsonList);
    }
}
