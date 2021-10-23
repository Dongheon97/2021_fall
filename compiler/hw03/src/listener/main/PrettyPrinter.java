package listener.main;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import generated.*;

public class PrettyPrinter {
    public static void main(String[] args) throws Exception
    {
        CharStream codeCharStream = CharStreams.fromFileName("test.c");
        MiniCLexer lexer = new MiniCLexer(codeCharStream);
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        MiniCParser parser = new MiniCParser( tokens );
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new MiniCPrintListener(), tree);
    }
}
