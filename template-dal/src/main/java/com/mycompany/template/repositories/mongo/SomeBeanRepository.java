package com.mycompany.template.repositories.mongo;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.mycompany.template.beans.SomeBean;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 3/26/13
 * Time: 2:27 PM
  */
public interface SomeBeanRepository extends PagingAndSortingRepository<SomeBean, String>{
}
