package com.mycompany.template.repositories.elastic;

import com.mycompany.template.beans.SomeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.queryString;

/**
 * Created by azee on 4/1/14.
 */
@Service
public class SomeBeanElasticRepositoryCustom {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Page<SomeBean> findByTitle(String title){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryString(title).field("title"))
                .build();

        return elasticsearchTemplate.queryForPage(searchQuery, SomeBean.class);
    }
}
