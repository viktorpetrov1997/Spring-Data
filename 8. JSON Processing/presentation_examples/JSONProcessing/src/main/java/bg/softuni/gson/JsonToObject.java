package bg.softuni.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonToObject
{
    public static void main(String[] args)
    {
        String json = "{\n"
                + "  \"firstName\": \"Georgi\",\n"
                + "  \"lastName\": \"Ivanov\",\n"
                + "  \"age\": 24,\n"
                + "  \"birthday\": \"31-07-2025\",\n"
                + "  \"address\": {\n"
                + "    \"country\": \"Bulgaria\",\n"
                + "    \"city\": \"Sofia\"\n"
                + "  },\n"
                + "  \"friendsList\": [\n"
                + "    \"Ivan\",\n"
                + "    \"Pesho\",\n"
                + "    \"Mitko\"\n"
                + "  ]\n"
                + "}";

        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("dd-MM-YYYY")
                .serializeNulls()
                .create();

        PersonDTO person = gsonBuilder.fromJson(json, PersonDTO.class);

        System.out.println(person);

        String jsonObjects = "[\n" +
                "  {\n" +
                "    \"firstName\": \"Georgi\",\n" +
                "    \"lastName\": \"Ivanov\",\n" +
                "    \"age\": 24,\n" +
                "    \"birthday\": \"31-07-2025\",\n" +
                "    \"address\": {\n" +
                "      \"country\": \"Bulgaria\",\n" +
                "      \"city\": \"Sofia\"\n" +
                "    },\n" +
                "    \"friendsList\": [\n" +
                "      \"Ivan\",\n" +
                "      \"Pesho\",\n" +
                "      \"Mitko\"\n" +
                "    ]\n" + "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Desislava\",\n" +
                "    \"lastName\": \"Topuzakova\",\n" +
                "    \"age\": 27,\n" +
                "    \"birthday\": \"31-07-2025\",\n" +
                "    \"address\": {\n" +
                "      \"country\": \"Bulgaria\",\n" +
                "      \"city\": \"Sofia\"\n" +
                "    },\n" +
                "    \"friendsList\": [\n" +
                "      \"Niki\",\n" +
                "      \"Tedi\",\n" +
                "      \"Marti\"\n" +
                "    ]\n" +
                "  }\n" +
                "]\n" +
                "\n";

        PersonDTO[] people = gsonBuilder.fromJson(jsonObjects, PersonDTO[].class);

        System.out.println(people[0]);

        System.out.println(people[1]);
    }
}
