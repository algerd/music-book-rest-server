
package ru.javafx.repository.operators;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public class OperatorUtils {
    
    final static Logger logger = LoggerFactory.getLogger(OperatorUtils.class);
    
    public static void registerNumberOperators(NumberPath<? extends Number> numberPath, QuerydslBindings bindings) {
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

    public static void registerStringOperators(StringPath stringPath, QuerydslBindings bindings) {
        for(StringOperator operator : StringOperator.values()) {
            String pathName = stringPath.getMetadata().getParent().getMetadata().getName() + "." + stringPath.getMetadata().getName();          
            
            //http://localhost:8080/api/artists?artist.nameContainsIgnoreCase=metallica (name.containsIgnoreCase("metallica"))
            bindings.bind(stringPath).as(pathName + operator).first((path, value) -> {               
                logger.info("{}={}", path.toString(), value);
                return operator.toPredicate(path, value);
            }); 
            
            //http://localhost:8080/api/artists?artist.name.containsignorecase=metallica (name.containsIgnoreCase("metallica"))
            bindings.bind(stringPath).as(pathName + "." + operator.toString().toLowerCase()).first((path, value) -> {               
                logger.info("{}={}", path.toString(), value);
                return operator.toPredicate(path, value);
            });
            
        }
    }

}
