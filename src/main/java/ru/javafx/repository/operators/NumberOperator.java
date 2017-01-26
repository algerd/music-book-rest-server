
package ru.javafx.repository.operators;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;

public enum NumberOperator {
    
    // "="
    EQ("Eq") {
        @Override
        public BooleanExpression toPredicate(NumberPath path, Number value) {
            return path.eq(value);
        }       
    },
    // ">"
    GT("Gt") {
        @Override
        public BooleanExpression toPredicate(NumberPath path, Number value) {
            return path.gt(value);
        }       
    },
    // ">="
    GOE("Goe") {
        @Override
        public BooleanExpression toPredicate(NumberPath path, Number value) {
            return path.goe(value);
        }       
    },
    // "<"
    LT("Lt") {
        @Override
        public BooleanExpression toPredicate(NumberPath path, Number value) {
            return path.lt(value);
        }       
    },
    // "<="
    LOE("Loe") {
        @Override
        public BooleanExpression toPredicate(NumberPath path, Number value) {
            return path.loe(value);
        }       
    },
    // "!="
    NE("Ne") {
        @Override
        public BooleanExpression toPredicate(NumberPath path, Number value) {
            return path.ne(value);
        }       
    };
   
    public abstract BooleanExpression toPredicate(NumberPath path, Number value);
    
    private final String operator;

    private NumberOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
    
}
