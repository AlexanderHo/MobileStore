package com.mobileshop.group8.validator;

import com.mobileshop.group8.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class ProductValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price","NotEmpty");
        if(String.valueOf(product.getPrice() ) != null){
            try {
                product.getPrice();
            }catch (NumberFormatException e){
                errors.rejectValue("price", "NotNumber");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity","NotEmpty");
        if(String.valueOf(product.getQuantity()) !=null){
            try {
               product.getQuantity();
            }catch (NumberFormatException e){
                errors.rejectValue("quantity", "NotNumber");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category","NotEmpty");
        if(product.getImage().isEmpty())
            errors.rejectValue("img","File");
    }
}
