package refer.spring.boot.structure.controller.api;

import refer.spring.boot.structure.controller.api.reference.request.RequestCurrency;
import refer.spring.boot.structure.controller.api.reference.response.ResponseCurrency;
import refer.spring.boot.structure.domain.reference.Currency;

public final class Exchange {

    public static Currency mapToCurrency(RequestCurrency request) {
        Currency result = new Currency();
        result.setCode(request.getCode());
        result.setName(request.getName());

        return result;
    }

    public static ResponseCurrency mapToResponse(Currency currency) {
        if (currency == null) {
            return null;
        }

        ResponseCurrency result = new ResponseCurrency();
        result.setCode(currency.getCode());
        result.setName(currency.getName());

        return result;
    }
}
