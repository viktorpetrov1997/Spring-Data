package bg.softuni.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJSON
{
    public static void main(String[] args) throws JsonProcessingException
    {
        PersonDTO person = new PersonDTO("Ivan", "Stoyanov", 34, true);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(person);

        System.out.println(json);
    }
}
