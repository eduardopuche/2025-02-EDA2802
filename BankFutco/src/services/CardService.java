package services;

import model.Cards;
import repositories.CardRepository;

import java.util.List;
import java.util.Optional;

public class CardService {

    private final CardRepository repository = new CardRepository();

    public void save(Cards card) {
        repository.save(card);
    }

    public Optional<Cards> findById(String id) {
        return repository.findById(id);
    }

    public List<Cards> findAll() {
        return repository.findAll();
    }

    public boolean deleteById(String id) {
        return repository.deleteById(id);
    }
}
