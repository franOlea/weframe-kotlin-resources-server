package org.weframe.kotlinresourcesserver;

import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RepositoryRestConfiguration  extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(final org.springframework.data.rest.core.config.RepositoryRestConfiguration config) {
    config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
    config.setDefaultPageSize(10);
  }
}
