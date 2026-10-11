package com.craftinginterpreters.lox;

class RpnPrinterTest {
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String testName, String expected, Expr expr) {
        String actual = new RpnPrinter().print(expr);
        if (actual.equals(expected)) {
            passed++;
            System.out.println("PASS: " + testName);
        } else {
            failed++;
            System.out.println("FAIL: " + testName);
            System.out.println("  expected: " + expected);
            System.out.println("  actual:   " + actual);
        }
    }

    private static Token tok(TokenType type, String lexeme) {
        return new Token(type, lexeme, null, 1);
    }

    public static void main(String[] args) {
        check("simple addition: 1 + 2",
                "1.0 2.0 +",
                new Expr.Binary(
                        new Expr.Literal(1.0), tok(TokenType.PLUS, "+"),
                        new Expr.Literal(2.0)));

        check("book example: (1 + 2) * (4 - 3)",
                "1.0 2.0 + 4.0 3.0 - *",
                new Expr.Binary(
                        new Expr.Grouping(new Expr.Binary(
                                new Expr.Literal(1.0), tok(TokenType.PLUS, "+"),
                                new Expr.Literal(2.0))),
                        tok(TokenType.STAR, "*"),
                        new Expr.Grouping(new Expr.Binary(
                                new Expr.Literal(4.0), tok(TokenType.MINUS, "-"),
                                new Expr.Literal(3.0)))));

        check("redundant nested grouping disappears",
                "5.0",
                new Expr.Grouping(new Expr.Grouping(new Expr.Literal(5.0))));

        check("unary negation: -3",
                "3.0 -",
                new Expr.Unary(tok(TokenType.MINUS, "-"), new Expr.Literal(3.0)));

        check("nil literal",
                "nil",
                new Expr.Literal(null));

        check("left-associative chain: 1 + 2 + 3",
                "1.0 2.0 + 3.0 +",
                new Expr.Binary(
                        new Expr.Binary(
                                new Expr.Literal(1.0), tok(TokenType.PLUS, "+"),
                                new Expr.Literal(2.0)),
                        tok(TokenType.PLUS, "+"),
                        new Expr.Literal(3.0)));

        check("right-grouped chain: 1 + (2 + 3)",
                "1.0 2.0 3.0 + +",
                new Expr.Binary(
                        new Expr.Literal(1.0), tok(TokenType.PLUS, "+"),
                        new Expr.Grouping(new Expr.Binary(
                                new Expr.Literal(2.0), tok(TokenType.PLUS, "+"),
                                new Expr.Literal(3.0)))));

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
    }
}