package listener.main;

import generated.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MiniCPrintListener extends MiniCBaseListener {
    ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
    private String new_line = "\n";
    private static final String dot = "....";
    private static int indent_count = 0;

    @Override
    public void exitProgram(MiniCParser.ProgramContext ctx) {
        String program = "";
        for (int i = 0; i < ctx.getChildCount(); i++) {
            newTexts.put(ctx, ctx.decl(i).getText());
            //ParseTree인 newText에 decl을 넣음
            program += newTexts.get(ctx.getChild(i));
            //ctx의 child에 들어갔다가 나오면서 출력
        }
        System.out.println(program);
        File file = new File(String.format("[HW3]201702052.c"));
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(program);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exitDecl(MiniCParser.DeclContext ctx) {
        String output = "";
        // decl rule 1 : declare variable
        if (ctx.getChild(0) == ctx.var_decl()){
            output += newTexts.get(ctx.var_decl());
        }
        // decl rule 2 : declare function
        else if(ctx.getChild(0) == ctx.fun_decl()){
            output += newTexts.get(ctx.fun_decl());
        }
        newTexts.put(ctx, output+ new_line);
    }

    @Override
    public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        // compare texts of index 2 in var_decl
        // init string
        String str = ctx.getChild(2).getText();
        String spec = newTexts.get(ctx.type_spec());
        String ident = ctx.IDENT().getText();
        String output = "";
        // var_decl rule 1 : declare int
        if(ctx.getChildCount() == 3) {
            output += spec+" "+ident+str;
        }
        // var_decl rule 2 : declare int & initialize
        else if(ctx.getChildCount() == 5){
            output += spec+" "+ident+" "+str+" "+ctx.LITERAL().getText()+ctx.getChild(4).getText();
        }
        // var_decl rule 3 : declare array
        else if(ctx.getChildCount() == 6){
            output += spec+" "+ident+str+ctx.LITERAL().getText()+ctx.getChild(4).getText()
                    +ctx.getChild(5).getText();
        }
        newTexts.put(ctx, output);
    }

    @Override
    public void exitType_spec(MiniCParser.Type_specContext ctx) {
        // type_spec rule 1
        if(ctx.VOID() != null){
            newTexts.put(ctx, ctx.VOID().getText());
        }
        // type_spec rule 2
        else{
            newTexts.put(ctx, ctx.INT().getText());
        }
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        // init String
        String output = "";
        output += newTexts.get(ctx.type_spec())+" "+ctx.getChild(1).getText()+ctx.getChild(2).getText()
                + newTexts.get(ctx.params()) + ctx.getChild(4).getText() + newTexts.get(ctx.compound_stmt());
        newTexts.put(ctx, output);
    }

    @Override
    public void exitParams(MiniCParser.ParamsContext ctx) {
        // init string
        String output = "";
        int count = ctx.param().size();
        // Only one parameter
        if(count == 1){
            output += newTexts.get(ctx.param(0));
        }
        // parameters exist more than 1
        else if(count > 1){
            output += newTexts.get(ctx.param(0));
            for(int i=1; i<count; i++){
                output += ", " + newTexts.get((ctx.param(i)));
            }
        }
        //  | VOID
        else{
            // VOID
            if(ctx.getChild(0) != null){
                output += ctx.getChild(0).getText();
            }
            // nothing
            else{
                // do nothing
            }
        }
        newTexts.put(ctx, output);
    }

    @Override
    public void exitParam(MiniCParser.ParamContext ctx) {
        // init string
        //String str = ctx.getChild(2).getText();
        String spec = newTexts.get(ctx.type_spec());
        String ident = ctx.IDENT().getText();
        String output = "";
        int len = ctx.getChildCount();

        // param rule 2
        if(len == 4){
            output += spec + " " +ident + ctx.getChild(2).getText() + ctx.getChild(3).getText();
        }
        // param rule 1
        else if(len == 2){
            output += spec +" "+ ident;
        }
        newTexts.put(ctx, output);
    }


    @Override public void exitStmt(MiniCParser.StmtContext ctx) {

    }

    @Override public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) { }

    //@Override public void enterWhile_stmt(MiniCParser.While_stmtContext ctx) { }

    @Override public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) { }

    @Override public void enterCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        // indentation depth + 1
        indent_count += 1;
    }

    @Override public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        // init local indentation
        String local_indent = "";

        for (int i=0; i<indent_count; i++){
            local_indent += dot;
        }
        // setting variables & output string
        int local_decl_count = ctx.local_decl().size();
        int stmt_count = ctx.stmt().size();
        String output = new_line +"{"+ new_line;

        // local_decl*
        for(int i=0; i<local_decl_count; i++){
            output += local_indent + newTexts.get(ctx.local_decl(i));

        }

        // stmt*

        output += "}" + new_line;
        newTexts.put(ctx, output);

        // indentation depth - 1
        indent_count -= 1;

    }

    @Override public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        // init set
        int len = ctx.getChildCount();
        String spec = newTexts.get(ctx.type_spec());
        String ident = ctx.getChild(1).getText();
        String output = "";

        // local_decl Rule 1
        if(len == 3){
            output += spec + " " + ident + ctx.getChild(2).getText();
        }
        // local_decl Rule 2 : =
        else if(len == 5){
            output += spec + " " + ident + " " + ctx.getChild(2).getText() + " "
                    + ctx.LITERAL().getText() + ctx.getChild(4).getText();
        }
        // local_decl Rule 3 : []
        else if(len == 6){
            output += spec + " " + ident + ctx.getChild(2).getText()
                    + ctx.LITERAL().getText() + ctx.getChild(4).getText() + ctx.getChild(5).getText();
        }
        newTexts.put(ctx, output+ new_line);
    }

    //@Override public void enterIf_stmt(MiniCParser.If_stmtContext ctx) { }

    @Override public void exitIf_stmt(MiniCParser.If_stmtContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */

    @Override public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */

    @Override public void exitExpr(MiniCParser.ExprContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */

    @Override public void exitArgs(MiniCParser.ArgsContext ctx) { }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */



}