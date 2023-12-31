package io.github.hiiragi283.material.compat;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.api.IGameObjectHandler;
import com.cleanroommc.groovyscript.api.Result;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.gameobjects.GameObjectHandlerManager;
import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.part.HTPart;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class HTGroovyPlugin implements GroovyPlugin {

    //    GroovyPlugin    //

    @NotNull
    @Override
    public String getModId() {
        return HMReference.MOD_ID;
    }

    @NotNull
    @Override
    public String getModName() {
        return HMReference.MOD_NAME;
    }

    @Override
    public void onCompatLoaded(GroovyContainer<?> container) {
        GameObjectHandlerManager.registerGameObjectHandler(HMReference.MOD_ID, "materialKey", HTGroovyPlugin::parseMaterialKey);
        GameObjectHandlerManager.registerGameObjectHandler(HMReference.MOD_ID, "part", HTGroovyPlugin::parsePart);
        GameObjectHandlerManager.registerGameObjectHandler(HMReference.MOD_ID, "shapeKey", IGameObjectHandler.wrapStringGetter(HTShapeKey::new));
    }

    @NotNull
    private static Result<HTMaterialKey> parseMaterialKey(String mainArg, Object... args) {
        if (args.length != 1 || !(args[0] instanceof Integer)) {
            return Result.error();
        }
        int index = (int) args[0];
        return Result.some(new HTMaterialKey(mainArg, index));
    }

    @NotNull
    private static Result<HTPart> parsePart(String mainArg, Object... args) {
        String[] split = mainArg.split(":");
        if (split.length != 2) {
            return Result.error();
        }
        HTShape shape = HTShape.getShapeOrNull(split[0]);
        if (shape == null) {
            return Result.error();
        }
        HTMaterial material = HTMaterial.getMaterialOrNull(split[1]);
        if (material == null) {
            return Result.error();
        }
        return Result.some(new HTPart(shape, material));
    }

}