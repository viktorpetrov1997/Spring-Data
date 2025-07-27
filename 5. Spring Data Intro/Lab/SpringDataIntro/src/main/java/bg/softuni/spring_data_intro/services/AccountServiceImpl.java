package bg.softuni.spring_data_intro.services;

import bg.softuni.spring_data_intro.entities.Account;
import bg.softuni.spring_data_intro.entities.User;
import bg.softuni.spring_data_intro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl
{
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public void withdrawMoney(BigDecimal amount, long id)
    {
        Optional<Account> accountId = accountRepository.findById(id);

        if(accountId.isEmpty())
        {
            throw new RuntimeException("Missing Account");
        }

        Account account = accountId.get();

        if(account.getBalance().compareTo(amount) < 0)
        {
            throw new RuntimeException("Insufficient Funds");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        accountRepository.save(account);
    }

    public void transferMoney(BigDecimal amount, User user, long accountId)
    {
        Account account = getAccountOrThrow(accountId);

        if(amount.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new RuntimeException("Cannot transfer negative amounts");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        accountRepository.save(account);
    }

    // Helper method we use in the transferMoney method. We can also use it in the withdrawMoney method.
    private Account getAccountOrThrow(long id)
    {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Missing Account"));
    }
}
