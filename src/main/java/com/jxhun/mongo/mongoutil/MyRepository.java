package com.jxhun.mongo.mongoutil;

import com.jxhun.mongo.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 继承PagingAndSortingRepository
 * @author Jxhun
 */
public interface MyRepository extends PagingAndSortingRepository<User, Long> {
}
