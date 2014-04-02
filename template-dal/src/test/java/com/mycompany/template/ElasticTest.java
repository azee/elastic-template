package com.mycompany.template;

import com.mycompany.template.beans.SomeBean;
import com.mycompany.template.repositories.elastic.SomeBeanElasticRepository;
import com.mycompany.template.repositories.elastic.SomeBeanElasticRepositoryCustom;
import com.mycompany.template.repositories.mongo.SomeBeanRepository;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by azee on 4/1/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:domainContext.xml")
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ElasticTest {

    @Autowired
    SomeBeanElasticRepository someBeanElasticRepository;

    @Autowired
    SomeBeanElasticRepositoryCustom someBeanElasticRepositoryCustom;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    SomeBeanRepository someBeanRepository;

    @Autowired
    SomeBeanRepository anotherSomeBeanRepository;

    @Before
    public void tearDown(){
        someBeanElasticRepository.deleteAll();
        elasticsearchTemplate.deleteIndex(SomeBean.class);
    }

    @Test
    public void testSearchByTitle() throws IOException {
        SomeBean someBean = new SomeBean();
        someBean.setTitle("Title 1");
        someBeanElasticRepository.index(someBean);

        someBean = new SomeBean();
        someBean.setTitle("Title 1");
        someBeanElasticRepository.index(someBean);

        someBean = new SomeBean();
        someBean.setTitle("Other 2");
        someBeanElasticRepository.index(someBean);

        someBean = new SomeBean();
        someBean.setTitle("Another 3");
        someBeanElasticRepository.index(someBean);

        assertThat(someBeanElasticRepository.count(), is(4L));
        assertThat(someBeanElasticRepository.findByTitle("Title 1").size(), is(2));
        assertThat(someBeanElasticRepositoryCustom.findByTitle("Title 1").getContent().size(), is(2));
    }

}
