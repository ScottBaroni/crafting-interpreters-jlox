package com.craftinginterpreters.lox;

// Thrown by "break;" and caught by the nearest enclosing loop. A "for"
// loop is desugared into a "while", so this one catch covers both.
class BreakException extends RuntimeException {
    BreakException() {
        super(null, null, false, false);
    }
}