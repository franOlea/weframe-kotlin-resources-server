package org.weframe.kotlinresourcesserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.weframe.kotlinresourcesserver.product.backboard.Backboard;
import org.weframe.kotlinresourcesserver.product.frame.Frame;
import org.weframe.kotlinresourcesserver.product.frameglass.FrameGlass;
import org.weframe.kotlinresourcesserver.product.mat.Mat;
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatType;
import org.weframe.kotlinresourcesserver.product.picture.Picture;
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture;
import org.weframe.kotlinresourcesserver.purchase.Purchase;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
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
}