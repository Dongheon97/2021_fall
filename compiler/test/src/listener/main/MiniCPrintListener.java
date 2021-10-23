package listener.main;

import generated.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MiniCPrintListener extends MiniCBaseListener {
    ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
    private String new_line = "\n";
    private static final String indent = "....";
    private static int indent_count = 0;

    // print indentation
    private String printDot(){
        String output = "";
        for(int i=0; i<indent_count; i++){
            output += "....";
        }
        return output;
    }
    // Check Binary Operation
    private boolean isBinaryOperation(MiniCParser.ExprContext ctx){
        // (check number of token) && (is middle token operator)
        return (ctx.getChildCount() == 3) && (ctx.getChild(1) != ctx.expr());
    }
    // Check Pre-Operation
    private boolean isPreOperation(MiniCParser.ExprContext ctx){
        // (check number of token) && (is first token operator)
        return (ctx.getChildCount() == 2) && (ctx.getChild(0) != ctx.expr());
    }

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
    public void enterFun_decl(MiniCParser.Fun_declContext ctx){
        //indent_count += 1;
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        // init String
        String output = "";
        output += newTexts.get(ctx.type_spec())+" "+ctx.getChild(1).getText()+ctx.getChild(2).getText()
                + newTexts.get(ctx.params()) + ctx.getChild(4).getText() +new_line+ newTexts.get(ctx.compound_stmt());
        newTexts.put(ctx, output);
        //indent_count -= 1;
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


    @Override
    public void exitStmt(MiniCParser.StmtContext ctx) {
        // init String
        String output = "";
            if(ctx.getChildCount() > 0){
                // stmt Rule 1 : expr_stmt
                if(ctx.expr_stmt() != null){
                    output += newTexts.get(ctx.expr_stmt());
                }
                // stmt Rule 2 : compound_stmt
                else if(ctx.compound_stmt() != null){
                    output += newTexts.get(ctx.compound_stmt());
                }
                // stmt Rule 3 : if_stmt
                else if(ctx.if_stmt() != null){
                    output += newTexts.get(ctx.if_stmt());
                }
                // stmt Rule 4 : while_stmt
                else if(ctx.while_stmt() != null){
                    output += newTexts.get(ctx.while_stmt());
                }
                // stmt Rule 5 : return_stmt
                else if(ctx.return_stmt() != null){
                    output += newTexts.get(ctx.return_stmt());
                }

            }
        newTexts.put(ctx, output);
    }

    @Override
    public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        // init
        String output = "";
        output += newTexts.get(ctx.expr()) + ";";
        newTexts.put(ctx, output+new_line);
    }

    @Override
    public void enterWhile_stmt(MiniCParser.While_stmtContext ctx) {
        // indentation depth + 1
        indent_count += 1;
    }

    @Override
    public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        // init
        String output ="";
        // indentation
//        indent_count -= 1;
//
//        output += ctx.getChild(0).getText();
//        indent_count += 1;



        output += ctx.WHILE().getText() + " " + ctx.getChild(1).getText()
                + newTexts.get(ctx.expr()) + ctx.getChild(3).getText() + new_line;

        output += newTexts.get(ctx.stmt());

        newTexts.put(ctx, output);
        indent_count -=1;
    }

    @Override
    public void enterCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        // indentation depth + 1
    }

    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        // local_decl pointer
        int local_count = 0;
        // stmt pointer
        int stmt_count =0;
        // '{'
        String output = printDot()+ctx.getChild(0).getText()+ new_line;
        indent_count +=1;

        // local_decl* stmt*
        for(int i=1; i<ctx.getChildCount()-1; i++){
            if(ctx.local_decl().contains(ctx.getChild(i))){
                // local_decl*
                output += printDot() + newTexts.get(ctx.local_decl(local_count));
                local_count += 1;
            }
            else if(ctx.stmt().contains(ctx.getChild(i))) {
                // stmt*
                output += printDot() + newTexts.get(ctx.stmt(stmt_count));
                stmt_count += 1;
            }
        }
        // indentation depth - 1
        indent_count -= 1;
        // }
        output += printDot()+ ctx.getChild(ctx.getChildCount()-1) + new_line;
        newTexts.put(ctx, output);


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

    @Override
    public void enterIf_stmt(MiniCParser.If_stmtContext ctx) {
        // indentation depth + 1
        indent_count += 1;
    }

    @Override
    public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        // init
        String output = ctx.IF().getText() + " " + ctx.getChild(1).getText()
                + newTexts.get(ctx.expr()) + ctx.getChild(3).getText() + new_line;
        String stmt_output = "";
        int len = ctx.getChildCount();
        int stmt_count = ctx.stmt().size();

        // if_stmt Rule 1
        if(len == 5){
            if(stmt_count == 1){
                output += indent + newTexts.get(ctx.stmt(0));
            }
            else if(stmt_count > 1){
                for(int i=0; i<stmt_count; i++){
                    stmt_output += indent + newTexts.get(ctx.stmt(i)) + new_line;
                }
                output += stmt_output;
            }
        }
        indent_count -= 1;
        newTexts.put(ctx, output);
    }


    @Override
    public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        // init
        String output = "";
        String rtn = ctx.RETURN().getText();

        int len = ctx.getChildCount();
        // return_stmt Rule 1
        if(len == 2){
            output += rtn + ctx.getChild(1).getText();
        }
        else if(len == 3){
            output += rtn  + " " + newTexts.get(ctx.expr())+ctx.getChild(2);
        }
        newTexts.put(ctx, output+new_line);
    }

    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        // init
        String output = "";
        String expr_output = "";
        int len = ctx.getChildCount();

        // LITERAL | IDENT
        if(len == 1) {
            output += ctx.getChild(0).getText();
        }
        else if(len == 2){
            // pre_operator expr
            if(isPreOperation(ctx)){
                output += ctx.getChild(0).getText() + newTexts.get(ctx.expr(0));
            }
        }
        else if(len == 3){
            // '(' expr ')' | IDENT '=' expr
            if(ctx.getChild(0).getText().equals("(")){
                output += ctx.getChild(0).getText() + newTexts.get(ctx.expr(0)) + ctx.getChild(2).getText();
            }
            // IDENT '=' expr
            else if(ctx.getChild(1).getText().equals("=")){
                output += ctx.getChild(0).getText() +" "+ ctx.getChild(1).getText()
                        +" "+ newTexts.get(ctx.expr(0));
            }
            // expr1 binary_operator expr2
            else if(isBinaryOperation(ctx)){
                output += newTexts.get(ctx.expr(0)) + " " + ctx.getChild(1).getText()
                        + " " + newTexts.get(ctx.expr(1));
            }
        }
        else if(len == 4){
            // IDENT '[' expr ']'
            if(ctx.getChild(1).getText().equals("[")){
                output += ctx.getChild(0).getText() + ctx.getChild(1).getText()
                        + newTexts.get(ctx.expr(0)) + ctx.getChild(3).getText();
            }
            // IDENT '(' args ')'
            else if(ctx.getChild(1).getText().equals("(")){
                output += ctx.getChild(0).getText() + ctx.getChild(1).getText()
                        + newTexts.get(ctx.args()) + ctx.getChild(3).getText();
            }
        }
        // IDENT '[' expr ']' '=' expr
        else if(len == 6){
            output += ctx.getChild(0).getText() + ctx.getChild(1).getText() + newTexts.get(ctx.expr(0))
                    + ctx.getChild(3).getText() +" "+ ctx.getChild(4).getText() +" "+ newTexts.get(ctx.expr(1));
        }
        newTexts.put(ctx, output);
    }

    @Override
    public void exitArgs(MiniCParser.ArgsContext ctx) {
        // init
        String output = "";
        int count = ctx.expr().size();

        output += newTexts.get(ctx.expr(0));
        // args Rule 1
        if(count > 0){
            //(', ' expr)*
            for(int i=1; i<count; i++){
                output += ", " + newTexts.get(ctx.expr(i));
            }
        }
        // args Rule 2
        else{
            // do nothing
        }

        newTexts.put(ctx, output);
    }


}