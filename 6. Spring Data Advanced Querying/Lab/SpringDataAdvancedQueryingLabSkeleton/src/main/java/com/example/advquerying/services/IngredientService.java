package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IngredientService
{
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAutocompleteOptions(String firstLetter)
    {
        return ingredientRepository.findByNameStartingWith(firstLetter);
    }

    public List<Ingredient> findAll(String[] names)
    {
        List<String> input = Arrays.asList(names);

        return ingredientRepository.findByNameInOrderByPrice(input);
    }

    @Transactional
    public void delete(String name)
    {
        ingredientRepository.deleteByName(name);
    }

    public void updatePrice()
    {
        ingredientRepository.updatePriceByPercent();
    }

    public void updatePriceByGivenNamesAndPercent(double percent, String[] names)
    {
        List<String> input = Arrays.asList(names);

        ingredientRepository.updatePriceByName(percent, input);
    }
}
