package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService
{
    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private static final String SELLER_FILE_PATH = "src/main/resources/files/json/sellers.json";

    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper)
    {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported()
    {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException
    {
        return Files.readString(Path.of(SELLER_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException
    {
        SellerDTO[] sellerDTOS = gson.fromJson(readSellersFromFile(), SellerDTO[].class);

        StringBuilder sb = new StringBuilder();
        for(SellerDTO sellerDTO : sellerDTOS)
        {
            Seller seller = createSeller(sellerDTO);
            if(seller == null)
            {
                sb.append(String.format("Invalid seller%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported seller %s %s%n", seller.getFirstName(), seller.getLastName()));
            }
        }

        return sb.toString();
    }

    private Seller createSeller(SellerDTO sellerDTO)
    {
        if(!validationUtil.isValid(sellerDTO)) return null;

        Optional<Seller> sellerByLastName = sellerRepository.findSellerByLastName(sellerDTO.getLastName());
        if(sellerByLastName.isPresent())
        {
            return null;
        }

        try
        {
            Seller seller = modelMapper.map(sellerDTO, Seller.class);
            sellerRepository.save(seller);

            return seller;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
