package com.mycompany.template.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by azee on 4/2/14.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "someBean")
@Document(collection = "template_some_bean")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "somebean")
public class SomeBean extends BaseSomeBean {
}
