package org.weframe.kotlinresourcesserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.weframe.kotlinresourcesserver.product.backboard.Backboard;
import org.weframe.kotlinresourcesserver.product.frame.Frame;
import org.weframe.kotlinresourcesserver.product.frameglass.FrameGlass;
import org.weframe.kotlinresourcesserver.product.mat.Mat;
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatType;
import org.weframe.kotlinresourcesserver.product.picture.Picture;
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture;
import org.weframe.kotlinresourcesserver.purchase.Purchase;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

  private @Value("${database.driver.class.name}") String databaseDriverClassName;
  private @Value("${database.url}") String databaseUrl;
  private @Value("${database.user}") String databaseUser;
  private @Value("${database.password}") String databasePassword;

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(Frame.class);
    config.exposeIdsFor(Picture.class);
    config.exposeIdsFor(Backboard.class);
    config.exposeIdsFor(MatType.class);
    config.exposeIdsFor(Mat.class);
    config.exposeIdsFor(FrameGlass.class);
    config.exposeIdsFor(UserPicture.class);
    config.exposeIdsFor(Purchase.class);
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(databaseDriverClassName);
    dataSource.setUrl(databaseUrl);
    dataSource.setUsername(databaseUser);
    dataSource.setPassword(databasePassword);
    return dataSource;
  }

}