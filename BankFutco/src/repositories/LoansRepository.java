package repositories;

import model.Loans;
import java.time.LocalDate;
import java.util.*;

public class LoansRepository {

    private final Map<String, Loans> data = new HashMap<>();

    private String generateKey(String accountNumber, LocalDate date) {
        return accountNumber + "-" + date.toString();
    }

    public void save(Loans loan) {
        data.put(generateKey(loan.getAccountNumber(), loan.getDate()), loan);
    }

    public Optional<Loans> findById(String accountNumber, LocalDate date) {
        return Optional.ofNullable(data.get(generateKey(accountNumber, date)));
    }

    public List<Loans> findAll() {
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
