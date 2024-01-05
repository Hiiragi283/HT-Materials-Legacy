package io.github.hiiragi283.material.compat.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.BracketHandler;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.zenscript.IBracketHandler;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.expression.ExpressionCallStatic;
import stanhebben.zenscript.expression.ExpressionString;
import stanhebben.zenscript.parser.Token;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.natives.IJavaMethod;

import java.util.List;

@SuppressWarnings("unused")
@BracketHandler
@ZenRegister
public class HTShapeKeyBracketHandler implements IBracketHandler {

    private static final IJavaMethod method = CraftTweakerAPI.getJavaMethod(
            HTShapeKeyBracketHandler.class,
            "getShapeKey",
            String.class
    );

    @NotNull
    public static HTShapeKey getShapeKey(String name) {
        return new HTShapeKey(name);
    }

    @Nullable
    @Override
    public IZenSymbol resolve(IEnvironmentGlobal environment, List<Token> tokens) {
        if (tokens.size() != 3) return null;
        else if (!tokens.get(0).getValue().equals("shapeKey")) return null;
        else if (!tokens.get(1).getValue().equals(":")) return null;
        else
            return position -> new ExpressionCallStatic(position, environment, method, new ExpressionString(position, tokens.get(2).getValue()));
    }

}