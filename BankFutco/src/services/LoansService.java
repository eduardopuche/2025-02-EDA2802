package services;

import model.Loans;
import java.time.LocalDate;
import java.util.*;

public class LoansService {

    private static final Map<String, Loans> loans = new HashMap<>();

    public void save(Loans loan) {
        String key = loan.getAccountNumber() + "-" + loan.getDate();
        loans.put(key, loan);
    }

    public Optional<Loans> findById(String accountNumber, LocalDate date) {
        String key = accountNumber + "-" + date;
        return Optional.ofNullable(loans.get(key));
    }

    public List<Loans> findAll() {
        return new ArrayList<>(loans.values());
    }

    public boolean deleteById(String accountNumber, LocalDate date) {
        String key = accountNumber + "-" + date;
        return loans.remove(key) != null;
    }
}
