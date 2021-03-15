package iprwc.service;

import iprwc.api.Product;
import iprwc.api.ProductDirector;
import iprwc.api.ProductImage;
import iprwc.api.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

public class RegisterMapperService {
    private Jdbi jdbi;

    private final Class[] registerRowClasses = new Class[] {
            Product.class,
            ProductDirector.class,
            ProductImage.class,
            User.class
    };

    public RegisterMapperService(Jdbi jdbi) {this.jdbi = jdbi;}

    public Object registerMappersToModels() {
        for (Class registerRowClass : registerRowClasses) {
            jdbi.registerRowMapper(BeanMapper.factory(registerRowClass));
        }
        return null;
    }
}
