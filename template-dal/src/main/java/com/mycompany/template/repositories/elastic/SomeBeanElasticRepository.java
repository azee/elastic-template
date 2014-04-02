package com.mycompany.template.repositories.elastic;

import com.mycompany.template.beans.SomeBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 3/26/13
 * Time: 2:27 PM
  */
public interface SomeBeanElasticRepository extends ElasticsearchRepository<SomeBean, String> {
    List<SomeBean> findByTitle(String title);
}
