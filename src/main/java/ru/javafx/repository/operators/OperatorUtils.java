
package ru.javafx.repository.operators;

import com.querydsl.core.types.dsl.NumberPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public class OperatorUtils {
    
    final static Logger logger = LoggerFactory.getLogger(OperatorUtils.class);
    
    public static void expressNumberOperators(NumberPath<Integer> numberPath, QuerydslBindings bindings) {
        for(NumberOperator operator : NumberOperator.values()) {
            String pathName = numberPath.getMetadata().getParent().getMetadata().getName() + "." + numberPath.getMetadata().getName();          
            
            //http://localhost:8080/api/artists?artist.ratingGt=5 (rating > 5)
            bindings.bind(numberPath).as(pathName + operator).first((path, value) -> {               
                logger.info("{}={}", path.toString(), value);
                return operator.toPredicate(path, value);
            }); 
            
            //http://localhost:8080/api/artists?artist.rating.gt=5 (rating > 5)
            bindings.bind(numberPath).as(pathName + "." + operator.toString().toLowerCase()).first((path, value) -> {               
                logger.info("{}={}", path.toString(), value);
                return operator.toPredicate(path, value);
            });
            
        }
    }    

}
