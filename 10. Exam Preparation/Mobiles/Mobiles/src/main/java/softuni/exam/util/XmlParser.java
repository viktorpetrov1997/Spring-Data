package softuni.exam.util;

import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;

public interface XmlParser
{
    <T> T fromXml(String xml, Class<T> tClass) throws JAXBException, FileNotFoundException;
}
