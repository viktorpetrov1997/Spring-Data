package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleDTO;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService
{
    private final SaleRepository saleRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final SellerRepository sellerRepository;

    private static final String SALE_FILE_PATH = "src/main/resources/files/json/sales.json";

    public SaleServiceImpl(SaleRepository saleRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, SellerRepository sellerRepository)
    {
        this.saleRepository = saleRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported()
    {
        return saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException
    {
        return Files.readString(Path.of(SALE_FILE_PATH));
    }

    @Override
    public String importSales() throws IOException
    {
        SaleDTO[] saleDTOS = gson.fromJson(readSalesFileContent(), SaleDTO[].class);

        StringBuilder sb = new StringBuilder();
        for(SaleDTO saleDTO : saleDTOS)
        {
            Sale sale = createSale(saleDTO);
            if(sale == null)
            {
                sb.append(String.format("Invalid sale%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported sale with number %s%n", sale.getNumber()));
            }
        }

        return sb.toString();
    }

    private Sale createSale(SaleDTO saleDTO)
    {
        if(!validationUtil.isValid(saleDTO)) return null;

        Optional<Sale> saleByNumber = saleRepository.findSaleByNumber(saleDTO.getNumber());
        if(saleByNumber.isPresent())
        {
            return null;
        }

        try
        {
            Sale sale = modelMapper.map(saleDTO, Sale.class);
            saleRepository.save(sale);

            return sale;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
