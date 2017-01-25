
package ru.javafx.repository.operators;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;

public enum NumberOperator {
    
    GT("Gt") {
        @Override
        public BooleanExpression toPredicate(NumberPath<Integer> path, Integer value) {
            return path.gt(value);
        }       
    };
    
    public abstract BooleanExpression toPredicate(NumberPath<Integer> path, Integer value);
    
    private final String operator;

    private NumberOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
    
}
