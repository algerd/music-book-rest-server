
package ru.javafx.repository.operators;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.data.querydsl.binding.MultiValueBinding;

public class StringMultiValueBinding implements MultiValueBinding<StringPath, String> {
    @Override
    public Predicate bind(StringPath path, Collection<? extends String> value) {
        Iterator<? extends String> it = value.iterator();
        String operator = it.next().trim().toLowerCase();
        if (it.hasNext()) {
            for (StringOperator stringOperator : StringOperator.values()) {
                if (operator.equals(stringOperator.toString())) {
                    return stringOperator.toPredicate(path, it.next());
                }
            }
            return path.containsIgnoreCase(it.next());
        } else {
            return path.eq(operator);
        }
    }  
}
