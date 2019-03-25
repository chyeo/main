package seedu.address.logic.parser;

/**
 * Operators that can be used
 */
public enum Operator {
    /*
     We will use 99 for highest precedence, 0 for lowest precedence
     Order of precedence
        && > OR
    */
    // LEFT_BRACKET and RIGHT_BRACKET uses 1 because we handle it in our switch condition.
    LEFT_BRACKET(0),
    RIGHT_BRACKET(0),
    // Boolean OR. We use 11 to indicate lower precedence
    OR(11),
    // Boolean AND. We use 22 to indicate a higher precedence vs boolean OR
    // We increase more than 1 mainly for future references if there is any operators we need between them.
    AND(22);
    final int precedence;

    Operator(int precedence) {
        this.precedence = precedence;
    }
}
