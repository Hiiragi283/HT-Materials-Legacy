package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.HTAddon;
import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import io.github.hiiragi283.material.api.shape.HTShapePredicate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

public class HMCommonProxy implements HTLoader {

    private static final Logger LOGGER = LogManager.getLogger(HMReference.MOD_NAME + "/Proxy");

    private static final Collection<HTMaterialsAddon> cache = new HashSet<>();

    //    Construct    //

    @Override
    public void onConstruct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(HMEventHandler.class);
    }

    //    Pre Init    //

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        //Collect HTMaterialsAddon
        event.getAsmData().getAll(HTAddon.class.getCanonicalName()).forEach(data -> {
            try {
                cache.add(Class.forName(data.getClassName()).asSubclass(HTMaterialsAddon.class).getConstructor().newInstance());
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                LOGGER.catching(e);
            }
        });
        //Register HTShape
        registerShapeKey();
        modifyShapePredicate();
        createShape();
    }

    //    Pre Init - HTShape    //

    private static final HTObjectKeySet<HTShapeKey> shapeKeySet = HTObjectKeySet.create();

    private static void registerShapeKey() {
        cache.forEach(addon -> addon.registerShapeKey(shapeKeySet));
    }

    private static final HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> predicateMap = HTNonNullMap.create(key -> new HTShapePredicate.Builder());

    private static void modifyShapePredicate() {
        cache.forEach(addon -> addon.modifyShapePredicate(predicateMap));
    }

    private static void createShape() {
        shapeKeySet.forEach(key -> {
            var predicate = predicateMap.getOrCreate(key).build();
            HTShape.create(key, predicate);
        });
    }

    //    Pre Init - HTMaterial    //

    public static class Client extends HMCommonProxy {

    }

}