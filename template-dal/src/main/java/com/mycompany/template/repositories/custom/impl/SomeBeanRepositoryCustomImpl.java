package com.mycompany.template.repositories.custom.impl;

import com.mycompany.template.beans.SomeBean;
import com.mycompany.template.repositories.custom.SomeBeanRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.elasticsearch.index.query.QueryBuilders.queryString;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 02.11.12
 * Time: 12:35
 *
 * Implementation of custom operation over SomeBean repository
 */
public class SomeBeanRepositoryCustomImpl implements SomeBeanRepositoryCustom {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public Page<SomeBean> findByTitle(String title) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryString(title).field("title"))
                .build();
        return elasticsearchTemplate.queryForPage(searchQuery, SomeBean.class);
    }
}
