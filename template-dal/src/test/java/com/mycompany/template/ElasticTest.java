package com.mycompany.template;

import com.mycompany.template.beans.SomeBean;
import com.mycompany.template.repositories.SomeBeanRepository;
import com.mycompany.template.rule.EmbeddedMongoRule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.*;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.elasticsearch.node.Node;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by azee on 4/1/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:domainContext.xml")
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ElasticTest {

    @ClassRule
    public static EmbeddedMongoRule embeddedMongoRule = new EmbeddedMongoRule(37017);

    private Node node;

    @Autowired
    SomeBeanRepository someBeanRepository;

    @Autowired
    SomeBeanRepository anotherSomeBeanRepository;

    String SERVICE_PATH = "http://127.0.0.1:9200/_river/" + new Date().getTime() + "/_meta";


    @Before
    public void setUp(){
        node = nodeBuilder().local(true).node();
        node.start();
    }

    @After
    public void tearDown(){
        node.stop();
    }

    @Test
    public void testSearchByTitle(){
        SomeBean someBean = new SomeBean();
        someBean.setTitle("Title 1");
        someBeanRepository.save(someBean);

        someBean = new SomeBean();
        someBean.setTitle("Title 1");
        someBeanRepository.save(someBean);

        someBean = new SomeBean();
        someBean.setTitle("Title 2");
        someBeanRepository.save(someBean);

        someBean = new SomeBean();
        someBean.setTitle("Title 3");
        someBeanRepository.save(someBean);

        String request = "{\"type\": \"mongodb\", \"mongodb\": {\"servers\": [{ \"host\": \"127.0.0.1\", \"port\": 37017 }"
            + "], \"options\": { \"secondary_read_preference\": true }, \"db\": \"test\", \"collection\": \"template_some_bean\""
        + "}, \"index\": { \"name\": \"somebean\", \"type\": \"template_some_bean\" } }";

        Client client = Client.create();
        WebResource webResource = client.resource(SERVICE_PATH);
        webResource.type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, request);

        assertThat(anotherSomeBeanRepository.findByTitle("Title 1").size(), is(2));
    }


}
