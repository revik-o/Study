package io.hacken.task.database.dao.transaction;

import io.hacken.task.database.dao.AbstractDaoImpl;
import io.hacken.task.database.dao.transaction.search.AcceptedTransactionComplexSearch;
import io.hacken.task.database.model.AcceptedTransaction;
import io.hacken.task.database.repository.AcceptedTransactionRepositoryI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static io.hacken.task.configuration.DefaultValues.DEFAULT_PAGE_LENGTH;
import static io.hacken.task.database.dao.transaction.SearchParam.build;
import static io.hacken.task.database.dao.transaction.SearchParam.buildParamSequence;

@Service
public class AcceptedTransactionDao extends AbstractDaoImpl<AcceptedTransaction> {

    private final EntityManager entityManager;
    private final AcceptedTransactionRepositoryI repository;

    @Autowired
    private AcceptedTransactionDao(AcceptedTransactionRepositoryI repository,
                                   EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    protected JpaRepository<AcceptedTransaction, Long> getRepository() {
        return this.repository;
    }

    public Page<AcceptedTransaction> findByTo(String value, Pageable pageable) {
        return this.repository.findBySentToContaining(value, pageable);
    }

    public Page<AcceptedTransaction> findByFrom(String value, Pageable pageable) {
        return this.repository.findBySentFromContaining(value, pageable);
    }

    public Page<AcceptedTransaction> findByGas(String value, Pageable pageable) {
        return this.repository.findByGasContaining(value, pageable);
    }

    public Page<AcceptedTransaction> findByGasPrice(String value, Pageable pageable) {
        return this.repository.findByGasPriceContaining(value, pageable);
    }

    public Page<AcceptedTransaction> findByTransactionHash(String value, Pageable pageable) {
        return this.repository.findByTransactionHashContaining(value, pageable);
    }

    public Page<AcceptedTransaction> findByDate(LocalDateTime value, Pageable pageable) {
        return this.repository.findByDateBetween(value, LocalDateTime.now(), pageable);
    }

    public Page<AcceptedTransaction> complexSearch(AcceptedTransactionComplexSearch search) {
        var paramSequence = buildParamSequence(
                build("table.sentTo LIKE :sentTo ", "sentTo", search.to()),
                build("table.sentFrom LIKE :sentFrom ", "sentFrom", search.from()),
                build("table.gas LIKE :gas ", "gas", search.gas()),
                build("table.gasPrice LIKE :gasPrice ", "gasPrice", search.gasPrice()),
                build("table.transactionHash LIKE :transactionHash ", "transactionHash", search.transactionHash()),
                build("table.date BETWEEN :dateFrom AND :dateEnd ", query -> {
                    query.setParameter("dateFrom", search.date());
                    query.setParameter("dateEnd", LocalDateTime.now());
                }, search.date())
        );

        var paginationJpqlBuilder = new StringBuilder("SELECT count(table) FROM AcceptedTransaction table ");
        var jpqlBuilder = new StringBuilder("SELECT table FROM AcceptedTransaction table ");

        if (paramSequence.length > 0) {
            paginationJpqlBuilder.append("WHERE ");
            jpqlBuilder.append("WHERE ");

            for (int i = 0; i < paramSequence.length - 1; i++) {
                var param = paramSequence[i];
                paginationJpqlBuilder.append(param.jpqlQuery());
                jpqlBuilder.append(param.jpqlQuery());
                paginationJpqlBuilder.append("AND ");
                jpqlBuilder.append("AND ");
            }

            paginationJpqlBuilder.append(paramSequence[paramSequence.length - 1].jpqlQuery());
            jpqlBuilder.append(paramSequence[paramSequence.length - 1].jpqlQuery());
        }

        jpqlBuilder.append("GROUP BY table.id ORDER BY table.id DESC");
        var query = this.entityManager.createQuery(jpqlBuilder.toString(), AcceptedTransaction.class);
        var countQuery = this.entityManager.createQuery(paginationJpqlBuilder.toString(), Long.class);

        for (var param : paramSequence) {
            param.merge(query);
            param.merge(countQuery);
        }

        var result = query.setFirstResult(search.page() * DEFAULT_PAGE_LENGTH)
                .setMaxResults(DEFAULT_PAGE_LENGTH)
                .getResultList();
        return result.isEmpty() ? Page.empty() : new PageImpl<>(result,
                PageRequest.of(search.page(), DEFAULT_PAGE_LENGTH), countQuery.getSingleResult());
    }
}

record SearchParam(String jpqlQuery, Consumer<Query> merging) {

    public void merge(Query queryPtr) {
        this.merging.accept(queryPtr);
    }

    public static SearchParam build(String jpqlQuery, Consumer<Query> merging, Object... values) {
        for (var value : values) {
            if (value == null) {
                return null;
            }
        }

        return new SearchParam(jpqlQuery, merging);
    }

    public static SearchParam build(String jpqlQuery, String jpqlKey, String value) {
        return build(jpqlQuery, query -> query.setParameter(jpqlKey, "%" + value + "%"), value);
    }

    public static SearchParam[] buildParamSequence(SearchParam... params) {
        return Arrays.stream(params).filter(Objects::nonNull).toArray(SearchParam[]::new);
    }
}