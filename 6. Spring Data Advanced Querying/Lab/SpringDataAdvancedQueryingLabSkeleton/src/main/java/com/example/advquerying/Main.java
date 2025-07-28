package com.example.advquerying;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main implements CommandLineRunner
{
    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    public Main(ShampooService shampooService, IngredientService ingredientService)
    {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);

        // 1. Select Shampoos by Size

        /*
        String inputSize = scanner.nextLine();

        List<Shampoo> shampooList = shampooService.getShampooBySize(inputSize);

        shampooList.forEach(System.out::println);
        */

        // 2. Select Shampoos by Size or Label

        /*
        String inputSize = scanner.nextLine();
        int labelId = Integer.parseInt(scanner.nextLine());

        List<Shampoo> shampooList = shampooService.getShampooBySizeOrLabel(inputSize, labelId);

        shampooList.forEach(System.out::println);
        */

        // 3. Select Shampoos by Price

        /*
        int price = Integer.parseInt(scanner.nextLine());

        List<Shampoo> shampooList = shampooService.getMoreExpensive(price);

        shampooList.forEach(System.out::println);
        */

        // 4. Select Ingredients by Name

        /*
        String firstLetter = scanner.nextLine();

        List<Ingredient> ingredientList = ingredientService.getAutocompleteOptions(firstLetter);

        ingredientList.forEach(System.out::println);
        */

        // 5. Select Ingredients by Names

        /*
        String[] names = scanner.nextLine().split(",");

        List<Ingredient> ingredientList = ingredientService.findAll(names);

        ingredientList.forEach(System.out::println);
        */

        // 6. Count Shampoos by Price

        /*
        double price = Double.parseDouble(scanner.nextLine());

        int count = shampooService.countCheaperThan(price);

        System.out.println(count);
        */

        // 7. Select Shampoos by Ingredients

        /*
        String[] names = scanner.nextLine().split("\\n");

        List<Shampoo> shampooList = shampooService.findByIngredients(names);

        shampooList.forEach(System.out::println);
        */

        // 8. Select Shampoos by Ingredients Count

        /*
        int count = Integer.parseInt(scanner.nextLine());

        List<Shampoo> shampooList = shampooService.findByIngredientCount(count);

        shampooList.forEach(System.out::println);
        */

        // 9. Delete Ingredients by Name

        /*
        String name = scanner.nextLine();

        ingredientService.delete(name);
        */

        // 10. Update Ingredients by Price

        // ingredientService.updatePrice();

        // 11. Update Ingredients by Names

        /*
        double percentage = Double.parseDouble(scanner.nextLine());

        String[] names = scanner.nextLine().split("\\n");

        ingredientService.updatePriceByGivenNamesAndPercent(percentage, names);
        */
    }
}
