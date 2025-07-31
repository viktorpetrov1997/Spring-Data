package bg.softuni.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToObject
{
    public static void main(String[] args) throws JsonProcessingException
    {
        String json = "{\n" +
                "  \"firstName\" : \"Ivan\",\n" +
                "  \"lastName\" : \"Stoyanov\",\n" +
                "  \"age\" : 34,\n" +
                "  \"married\" : true\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();

        PersonDTO person = objectMapper.readValue(json, PersonDTO.class);

        System.out.println(person);
    }
}
