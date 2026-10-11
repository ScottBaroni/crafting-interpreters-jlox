# jlox

My implementation of **jlox**, the Java tree-walk interpreter for the Lox language, built while following
[*Crafting Interpreters*](https://craftinginterpreters.com/) by Robert Nystrom. This is coursework for CS4080
(Concepts of Programming Languages).

Almost all of the code comes from the book. Any original work is in the challenge branches listed below.
The book's own repository, [munificent/craftinginterpreters](https://github.com/munificent/craftinginterpreters),
has the full source and license, so check there for licensing terms.

## How the repo is organized

- **`main`** follows the book chapter by chapter, with no challenge solutions. Each chapter is one commit and has a tag
  (`ch04`, `ch05`, ...) marking the code as it stands at the end of that chapter.
- **`chNN-challenges`** branches hold my work on that chapter's challenge problems. Each one starts from that chapter's
  tag, so the code matches the state the challenges assume. One commit per challenge that needed code.

## Project layout

```
java/com/craftinginterpreters/lox/   interpreter source
tests/chNN/                          small .lox programs used to test each chapter
```

## Build and run

Requires a JDK (I used 24, but any recent one should work).

```
javac -d build java/com/craftinginterpreters/lox/*.java

# run a script
java -cp build com.craftinginterpreters.lox.Lox tests/ch04/hello.lox

# or start the REPL (no arguments)
java -cp build com.craftinginterpreters.lox.Lox
```

In IntelliJ, mark `java/` as a Sources Root, run `Lox.main()`, and put a script path in the run
configuration's program arguments.

## Challenge branches

| Chapter | Topic | Branch | Status                       |
|---------|-------|--------|------------------------------|
| 4 | Scanning | `ch04-challenges` | Done (nested block comments) |
| 5 | Representing Code | `ch05-challenges` | Done (RPN printer)           |
| 6 | Parsing Expressions | `ch06-challenges` | Planned                      |
| 7 | Evaluating Expressions | `ch07-challenges` | Planned                      |
| 8 | Statements and State | `ch08-challenges` | Planned                      |
| 9 | Control Flow | `ch09-challenges` | Planned                      |

Design-only challenges (written answers, no code) are not in this repo.

## Credit

*Crafting Interpreters* © Robert Nystrom. Read it free at <https://craftinginterpreters.com/>.

Scott Baroni