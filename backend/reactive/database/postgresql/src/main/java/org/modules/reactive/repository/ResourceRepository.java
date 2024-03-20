package org.modules.reactive.repository;

import org.modules.reactive.entity.Resource;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * The type ResourceRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface ResourceRepository extends R2dbcRepository<Resource, Long> {

}
