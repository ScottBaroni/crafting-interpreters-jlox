package com.craftinginterpreters.lox;

class RpnPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        // Post-order: left operand, right operand, then the operator.
        return expr.left.accept(this) + " " +
                expr.right.accept(this) + " " +
                expr.operator.lexeme;
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        // RPN has no parentheses. The tree structure already encodes
        // precedence, so a grouping just prints its inner expression.
        return expr.expression.accept(this);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return expr.right.accept(this) + " " + expr.operator.lexeme;
    }

    public static void main(String[] args) {
        // (1 + 2) * (4 - 3)
        Expr expression = new Expr.Binary(
                new Expr.Grouping(
                        new Expr.Binary(
                                new Expr.Literal(1.0),
                                new Token(TokenType.PLUS, "+", null, 1),
                                new Expr.Literal(2.0))),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Binary(
                                new Expr.Literal(4.0),
                                new Token(TokenType.MINUS, "-", null, 1),
                                new Expr.Literal(3.0))));

        System.out.println(new RpnPrinter().print(expression));
    }
}