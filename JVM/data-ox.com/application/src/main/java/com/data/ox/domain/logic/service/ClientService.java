package com.data.ox.domain.logic.service;

import com.data.ox.core.common.Page;
import com.data.ox.core.data.ClientData;
import com.data.ox.core.dto.request.CreateClientRequestDTO;
import com.data.ox.core.dto.request.UpdateClientRequestDTO;
import com.data.ox.core.dto.response.LightClientResponse;
import com.data.ox.domain.logic.converter.ClientConverter;
import com.data.ox.domain.logic.dao.ClientDaoI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientService {

    private final ClientDaoI storage;
    private final ClientConverter converter;

    public final LightClientResponse execute(CreateClientRequestDTO request) {
        if (request.getAddress() == null || request.getName() == null || request.getEmail() == null) {
            throw new RuntimeException("TODO"); // TODO
        }

        var savedEntity = this.storage.save(ClientData.builder()
                .address(request.getAddress())
                .email(request.getEmail())
                .name(request.getName())
                .build());

        return this.converter.convertToLight(savedEntity);
    }

    public final LightClientResponse execute(UpdateClientRequestDTO request) {
        boolean changed = false;
        var entity = this.storage.findById(request.getId());

        if (request.getName() != null && !entity.getName().equals(request.getName())) {
            changed = true;
            entity.setName(request.getName());
        }
        if (request.getEmail() != null && !entity.getEmail().equals(request.getEmail())) {
            changed = true;
            entity.setEmail(request.getEmail());
        }
        if (request.getAddress() != null && !entity.getAddress().equals(request.getAddress())) {
            changed = true;
            entity.setAddress(request.getAddress());
        }
        if (entity.isActive() != request.isActive()) {
            changed = true;
            entity.setActive(request.isActive());
        }
        if (changed) {
            entity = this.storage.save(entity);
        }

        return this.converter.convertToLight(entity);
    }

    public final Page<LightClientResponse> execute(int pageNumber, int limit) {
        var page = this.storage.findAll(pageNumber, limit);
        return new Page<>(page.getMaxSize(), page.getTotalPages(), this.converter.convertToLight(page.getCollection()));
    }

    public final LightClientResponse execute(long id) {
        return this.converter.convertToLight(this.storage.findById(id));
    }
}
