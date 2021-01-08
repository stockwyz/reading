package com.edu.reading.common.tree.config.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.edu.reading.common.tree.config.TreeConfig;

@Component
@Qualifier("DeptEmpTreeConfig")
@ConfigurationProperties(prefix = "tree.dept.emp")
public class DeptEmpTreeConfig extends TreeConfig {

}

