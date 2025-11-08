package repositories;

import model.Cards;
import java.util.*;

public class CardRepository {

    private final Map<String, Cards> data = new HashMap<>();

    public void save(Cards card) {
        data.put(card.getCardNumber(), card);
    }

    public Optional<Cards> findById(String cardNumber) {
        return Optional.ofNullable(data.get(cardNumber));
    }

    public List<Cards> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean deleteById(String cardNumber) {
        return data.remove(cardNumber) != null;
    }

    public boolean existsById(String cardNumber) {
        return data.containsKey(cardNumber);
    }

    public void clear() {
        data.clear();
    }
}
