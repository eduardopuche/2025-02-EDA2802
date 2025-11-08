package services;

import model.Balance;
import java.time.LocalDate;
import java.util.*;

public class BalanceService {

    private static final Map<String, Balance> balances = new HashMap<>();

    public void save(Balance balance) {
        
        String key = balance.getAccountNumber() + "-" + balance.getDate();
        balances.put(key, balance);
    }

    public Optional<Balance> findById(String accountNumber, LocalDate date) {
        String key = accountNumber + "-" + date;
        return Optional.ofNullable(balances.get(key));
    }

    public List<Balance> findAll() {
        return new ArrayList<>(balances.values());
    }

    public boolean deleteById(String accountNumber, LocalDate date) {
        String key = accountNumber + "-" + date;
        return balances.remove(key) != null;
    }

    public static Map<String, Balance> getBalances() {
        return balances;
    }

    public Balance get(Object key) {
        return balances.get(key);
    }
}
