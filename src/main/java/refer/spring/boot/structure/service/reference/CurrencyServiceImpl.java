package refer.spring.boot.structure.service.reference;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refer.spring.boot.structure.domain.reference.Currency;
import refer.spring.boot.structure.repository.reference.CurrencyRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> findCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> findCurrency(String code) {
        Objects.requireNonNull(code);

        return currencyRepository.findByCodeIgnoreCase(code);
    }

    @Override
    public Currency saveCurrency(Currency currency) {
        Objects.requireNonNull(currency);

        Currency result = new Currency();

        String code = currency.getCode();
        if (code != null) {
            result.setCode(code);
        }

        String name = currency.getName();
        if (name != null) {
            result.setName(name);
        }

        return currencyRepository.save(result);
    }

    @Override
    public Currency updateCurrency(String code, Currency currency) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(currency);

        Currency found = findCurrency(code).orElse(null);
        if (found == null) {
            Currency result = new Currency();
            result.setCode(code);
            result.setName(currency.getName());

            return saveCurrency(result);
        }

        String name = currency.getName();
        if (name != null && !name.equals(found.getName())) {
            found.setName(name);
        }

        return currencyRepository.save(found);
    }

    @Override
    public Optional<Currency> deleteCurrency(String code) {
        Objects.requireNonNull(code);

        Currency found = findCurrency(code).orElse(null);
        if (found == null) {
            return Optional.empty();
        }

        currencyRepository.delete(found);

        return Optional.of(found);
    }
}
