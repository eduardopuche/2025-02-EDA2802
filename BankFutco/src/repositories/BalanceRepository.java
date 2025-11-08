package repositories;

import model.Balance;
import java.time.LocalDate;
import java.util.*;

public class BalanceRepository {

    private final Map<String, Balance> data = new HashMap<>();

    private String generateKey(String accountNumber, LocalDate date) {
        return accountNumber + "-" + date.toString();
    }

    public void save(Balance balance) {
        data.put(generateKey(balance.getAccountNumber(), balance.getDate()), balance);
    }

    public Map<String, Balance> getData() {
        return data;
    }

    public Optional<Balance> findById(String accountNumber, LocalDate date) {
        return Optional.ofNullable(data.get(generateKey(accountNumber, date)));
    }

    public List<Balance> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean deleteById(String accountNumber, LocalDate date) {
        return data.remove(generateKey(accountNumber, date)) != null;
    }

    public boolean existsById(String accountNumber, LocalDate date) {
        return data.containsKey(generateKey(accountNumber, date));
    }

    public void clear() {
        data.clear();
    }
}
