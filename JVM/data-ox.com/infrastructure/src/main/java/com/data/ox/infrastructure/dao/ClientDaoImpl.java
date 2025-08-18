package com.data.ox.infrastructure.dao;

import com.data.ox.core.common.Page;
import com.data.ox.core.data.ClientData;
import com.data.ox.domain.logic.dao.ClientDaoI;
import com.data.ox.infrastructure.converter.InfrastructureClientConverter;
import com.data.ox.infrastructure.converter.InfrastructureOrderConverter;
import com.data.ox.infrastructure.model.ClientModel;
import com.data.ox.infrastructure.repository.ClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static org.springframework.data.domain.PageRequest.of;

@Repository
@RequiredArgsConstructor
public class ClientDaoImpl implements ClientDaoI {

    private final ClientRepository repository;
    private final InfrastructureClientConverter converter;
    private final InfrastructureOrderConverter orderConverter;

    @Override
    public Page<ClientData> findClient(String searchKey, int pageNumber, int limit) {
        var page = this.repository.findClient(searchKey, of(pageNumber, limit));
        return new Page<>(limit, page.getTotalPages(), this.converter.convert(this.orderConverter, page.getContent()));
    }

    @Override
    public Page<ClientData> findAll(int pageNumber, int limit) {
        var page = this.repository.findAll(of(pageNumber, limit));
        return new Page<>(limit, page.getTotalPages(), this.converter.convert(this.orderConverter, page.getContent()));
    }

    @Override
    public ClientData findById(long id) {
        return this.repository.findById(id)
                .map(clientModel -> this.converter.convert(this.orderConverter, clientModel))
                .orElse(null);
    }

    @Override
    public ClientData save(@NonNull ClientData entity) {
        if ((entity.getName() == null || entity.getName().isBlank())
                || (entity.getEmail() == null || entity.getEmail().isBlank()
                || (entity.getAddress() == null || entity.getAddress().isBlank()))) {
            throw new RuntimeException("TODO"); // TODO
        }

        return this.converter.convert(this.orderConverter, this.repository.save(entity.getId() == -1 ? ClientModel.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build() : null)); // TODO
    }
}
