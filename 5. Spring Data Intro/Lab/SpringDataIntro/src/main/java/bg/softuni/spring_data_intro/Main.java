package bg.softuni.spring_data_intro;

import bg.softuni.spring_data_intro.entities.User;
import bg.softuni.spring_data_intro.services.AccountServiceImpl;
import bg.softuni.spring_data_intro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class Main implements CommandLineRunner
{
    private final UserService userService;
    private final AccountServiceImpl accountService;

    @Autowired
    public Main(UserService userService, AccountServiceImpl accountService)
    {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        //accountService.withdrawMoney(BigDecimal.TEN, 1L);

        // User freeUser = new User("bankin", 22);

        // userService.registerUser(freeUser);

        Optional<User> user = userService.find("bankin");

        accountService.transferMoney(BigDecimal.ONE, user.get(), 1L);

        // System.out.println("Started from Spring!!!");

        // User pesho = new User("Pesho", 23);

        // userRepository.save(pesho);

        // Optional<User> user = userRepository.findByUsername("pesho");

        // List<User> allUsers = userRepository.findAll();
    }
}
