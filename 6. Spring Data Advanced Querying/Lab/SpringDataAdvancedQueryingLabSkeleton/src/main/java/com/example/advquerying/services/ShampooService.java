package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ShampooService
{
    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooService(ShampooRepository shampooRepository)
    {
        this.shampooRepository = shampooRepository;
    }

    public List<Shampoo> getShampooBySize(String inputSize)
    {
        Size size = Size.valueOf(inputSize);

        return shampooRepository.findBySize(size);
    }

    public List<Shampoo> getShampooBySizeOrLabel(String inputSize, int labelId)
    {
        Size size = Size.valueOf(inputSize);

        return shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    public List<Shampoo> getMoreExpensive(int price)
    {
        return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(price));
    }

    public int countCheaperThan(double price)
    {
        return shampooRepository.countByPriceLessThan(BigDecimal.valueOf(price));
    }

    public List<Shampoo> findByIngredients(String[] names)
    {
        List<String> ingredientNames = Arrays.asList(names);

        return shampooRepository.findByIngredientsNameInQuery(ingredientNames);
    }

    public List<Shampoo> findByIngredientCount(int count)
    {
        return shampooRepository.findByIngredientsCountLessThan(count);
    }
}
