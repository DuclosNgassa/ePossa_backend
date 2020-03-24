package com.kmerconsulting.epossa.mapper;

import com.kmerconsulting.epossa.model.BasisDTO;
import com.kmerconsulting.epossa.model.BasisEntity;

interface Mapper<T extends BasisEntity, S extends BasisDTO> {

    T mapToBasisEntity(S s);

    S mapToBasisDTO(T t);
}
