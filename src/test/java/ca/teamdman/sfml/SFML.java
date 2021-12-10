package ca.teamdman.sfml;

import ca.teamdman.sfml.ast.ASTBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

public class SFML {
    @Test
    public void Test() {
        var input = """
                    NAME "NAME TEST WITH \\"ESCAPED STR\\" INGS"
                    WORLD
                        input_chests
                        output_chests
                        altar
                    END
                    PROGRAM
                        EVERY 20 TICKS DO
                            INPUT FROM input_chests
                            OUTPUT TO altar
                        END
                    END
                """;
        var lexer   = new SFMLLexer(CharStreams.fromString(input));
        var tokens  = new CommonTokenStream(lexer);
        var parser  = new SFMLParser(tokens);
        var builder = new ASTBuilder();

        //        parser.addErrorListener(new ConsoleErrorListener());
        var context = parser.start();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new RuntimeException("syn error");
        }

        var start = builder.visitStart(context);
        System.out.println("Good!");
    }

    @Test
    public void TestBad() {
        var input = """
                    NAME "NAME TEST WITH \\"ESCAPED STR\\" INGS"
                    WORLD
                        input_chests
                        output_chests
                        altar
                    END
                """;
        var lexer   = new SFMLLexer(CharStreams.fromString(input));
        var tokens  = new CommonTokenStream(lexer);
        var parser  = new SFMLParser(tokens);
        var builder = new ASTBuilder();

        //        parser.addErrorListener(new ConsoleErrorListener());
        var context = parser.start();
        var start   = builder.visitStart(context);

        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new RuntimeException("syn error");
        }

        System.out.println("Good!");
    }
}
