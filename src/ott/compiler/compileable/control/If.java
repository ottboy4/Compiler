package ott.compiler.compileable.control;

import ott.compiler.compileable.*;
import ott.compiler.compileable.code.*;

import java.util.*;
import static ott.compiler.compileable.Helper.*;

public class If implements Compilable {

    private Compilable elseif;
    private String endLabel;

    private Condition condition;
    private CodeBlock code;
    private String endIf;

    @Override
    public void parse(CompilableInfo info) {
        pullToken(info.tokens, "if");
        endIf = info.generateLabel();
        pullToken(info.tokens, "(");
        condition = new Condition(endIf);
        condition.parse(info);
        pullToken(info.tokens, ")");

        code = new CodeBlock();
        code.parse(info);

        if (isNextValue(info.tokens, "else")) {
            endLabel = info.generateLabel();
            elseif = isElseIf(info.tokens) ? new ElseIf(endLabel) : new Else();
            elseif.parse(info);
        }
    }

    @Override
    public void secondParse(Map<String, Integer> functions) {
        condition.secondParse(functions);
        code.secondParse(functions);
        if (elseif != null)
            elseif.secondParse(functions);
    }

    @Override
    public void generate(StringBuilder builder) {
        condition.generate(builder);
        code.generate(builder);

        if (elseif != null) {
            appendLine(builder, "B ", endLabel);
            appendLine(builder, endIf, ":");
            elseif.generate(builder);
            appendLine(builder, endLabel, ":");
        } else {
            appendLine(builder, endIf, ":");
        }
    }


}
