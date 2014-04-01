package com.mycompany.template.repositories;

import com.mycompany.template.beans.SomeBean;
import com.mycompany.template.repositories.custom.SomeBeanRepositoryCustom;
import org.elasticsearch.repositories.Repository;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 3/26/13
 * Time: 2:27 PM
  */
public interface AnotherSomeBeanRepository extends ElasticsearchCrudRepository<SomeBean, String>{

    @Query("{\"title\" : \"?0\"}")
    List<SomeBean> findByTitle(String title);
}
