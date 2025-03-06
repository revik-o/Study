package io.hacken.task.database.dao.transaction.search;

import io.hacken.task.database.dao.common.SearchOptionI;

import java.time.LocalDateTime;

import static io.hacken.task.database.dao.transaction.search.AcceptedTransactionComplexSearch.SEARCH_BY_DATE;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public record AcceptedTransactionSearchByDate(LocalDateTime value) implements SearchOptionI<LocalDateTime> {

    @Override
    public String getSearchBy() {
        return SEARCH_BY_DATE;
    }

    @Override
    public LocalDateTime getValue() {
        return this.value();
    }

    @Override
    public String getStringValue() {
        return ISO_DATE_TIME.format(this.value());
    }

    public static AcceptedTransactionSearchByDate findByDate(LocalDateTime value) {
        return new AcceptedTransactionSearchByDate(value);
    }
}
