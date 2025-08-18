package com.data.ox.infrastructure.dao;

import com.data.ox.core.common.Page;
import com.data.ox.core.data.OrderData;
import com.data.ox.domain.logic.dao.OrderDaoI;
import com.data.ox.infrastructure.converter.InfrastructureClientConverter;
import com.data.ox.infrastructure.converter.InfrastructureOrderConverter;
import com.data.ox.infrastructure.model.OrderModel;
import com.data.ox.infrastructure.repository.ClientRepository;
import com.data.ox.infrastructure.repository.OrderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDaoI {

    private final OrderRepository repository;
    private final ClientRepository clientRepository;
    private final InfrastructureOrderConverter converter;
    private final InfrastructureClientConverter clientConverter;

    @Override
    public Page<OrderData> findAll(int pageNumber, int limit) {
        var page = this.repository.findAll(PageRequest.of(pageNumber, limit));
        return new Page<>(limit, page.getTotalPages(), this.converter.convert(this.clientConverter, page.getContent()));
    }

    @Override
    public OrderData findById(long id) {
        return this.repository.findById(id)
                .map(item -> this.converter.convert(this.clientConverter, item))
                .orElse(null);
    }

    @Override
    public OrderData save(@NonNull OrderData entity) {
        if ((entity.getName() == null || entity.getName().isBlank())
                || entity.getSupplier() == null || entity.getConsumer() == null
                || entity.getEndProcessingDateTime() == null || entity.getStartProcessingDateTime() == null
                || entity.getAmount() == null) {
            throw new RuntimeException("TODO"); // TODO
        }

        var supplier = this.clientRepository.getReferenceById(entity.getSupplier().getId());
        var consumer = this.clientRepository.getReferenceById(entity.getConsumer().getId());

        return this.converter.convert(this.clientConverter, this.repository.save(OrderModel.builder()
                .supplier(supplier)
                .consumer(consumer)
                .name(entity.getName())
                .amount(entity.getAmount())
                .endProcessingDateTime(entity.getEndProcessingDateTime())
                .startProcessingDateTime(entity.getStartProcessingDateTime())
                .build()));
    }
}
