package softuni.exam.service;

import java.io.IOException;

public interface SellerService
{
    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException;
}
