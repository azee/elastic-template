package com.mycompany.template.repositories.custom;

import com.mycompany.template.beans.SomeBean;
import org.springframework.data.domain.Page;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 16.11.12
 * Time: 11:24
 *
 * Custom methods for SomeBean
 */
public interface SomeBeanRepositoryCustom {
    public Page<SomeBean> findByTitle(String title);
}
