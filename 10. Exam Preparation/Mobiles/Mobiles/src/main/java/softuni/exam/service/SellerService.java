package softuni.exam.service;

import softuni.exam.models.entity.Seller;

import java.io.IOException;

// TODO: Implement all methods

public interface SellerService
{
    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException;

    Seller getReferenceById(Long id);
}
