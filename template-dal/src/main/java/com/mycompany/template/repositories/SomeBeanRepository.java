package com.mycompany.template.repositories;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.mycompany.template.beans.SomeBean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 3/26/13
 * Time: 2:27 PM
  */
public interface SomeBeanRepository extends PagingAndSortingRepository<SomeBean, String>,
        ElasticsearchCrudRepository<SomeBean, String> {

    @Query("{\"title\" : \"?0\"}")
    List<SomeBean> findByTitle(String title);
}
