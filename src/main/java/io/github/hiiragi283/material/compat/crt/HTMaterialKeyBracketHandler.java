package io.github.hiiragi283.material.compat.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.BracketHandler;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.zenscript.IBracketHandler;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.expression.ExpressionCallStatic;
import stanhebben.zenscript.expression.ExpressionInt;
import stanhebben.zenscript.expression.ExpressionString;
import stanhebben.zenscript.parser.Token;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.ZenType;
import stanhebben.zenscript.type.natives.IJavaMethod;

import java.util.List;

@SuppressWarnings("unused")
@BracketHandler
@ZenRegister
public class HTMaterialKeyBracketHandler implements IBracketHandler {

    private static final IJavaMethod method = CraftTweakerAPI.getJavaMethod(
            HTMaterialKeyBracketHandler.class,
            "getMaterialKey",
            String.class,
            Integer.class
    );

    @NotNull
    public static HTMaterialKey getMaterialKey(String name, Integer index) {
        return new HTMaterialKey(name, index);
    }

    @Nullable
    @Override
    public IZenSymbol resolve(IEnvironmentGlobal environment, List<Token> tokens) {
        if (tokens.size() != 5) return null;
        else if (!tokens.get(0).getValue().equals("materialKey")) return null;
        else if (!tokens.get(1).getValue().equals(":")) return null;
        else if (!tokens.get(3).getValue().equals(":")) return null;
        else
            return position -> new ExpressionCallStatic(
                    position,
                    environment,
                    method,
                    new ExpressionString(position, tokens.get(2).getValue()),
                    new ExpressionInt(position, Long.parseLong(tokens.get(4).getValue()), ZenType.INT)
            );
    }

}