package bg.softuni.jackson;

import bg.softuni.jackson.dtos.CustomerList;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Object to XML

        /*
        Product product = new Product("TV", "Sony");
        Order order = new Order(List.of(product, product), 10, BigDecimal.TEN);
        xmlMapper.writeValue(System.out, order);
        */

        // XML to Object

        CustomerList list = xmlMapper.readValue(System.in, CustomerList.class);

        System.out.println();

        System.out.println(list);
    }
}