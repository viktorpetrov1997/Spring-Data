package sofuni.exam.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface DiscovererService
{
    boolean areImported();

    String readDiscovererFileContent() throws IOException;

    String importDiscoverers() throws IOException;
}
