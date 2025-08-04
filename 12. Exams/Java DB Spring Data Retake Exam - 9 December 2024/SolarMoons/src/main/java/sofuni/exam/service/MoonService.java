package sofuni.exam.service;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface MoonService
{
    boolean areImported();

    String readMoonsFileContent() throws IOException;

    String importMoons() throws IOException, JAXBException;

    String exportMoons();
}
