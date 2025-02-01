package hiiragi283.materials.common.init;

import com.google.common.collect.ImmutableList;
import hiiragi283.materials.api.mateial.part.DefaultParts;
import hiiragi283.materials.common.item.ItemPartMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class HMItems {
    private HMItems() {
    }

    public static final @NotNull ItemPartMaterial DUST = new ItemPartMaterial(DefaultParts.DUST);

    public static final @NotNull ItemPartMaterial GEAR = new ItemPartMaterial(DefaultParts.GEAR);

    public static final @NotNull ItemPartMaterial INGOT = new ItemPartMaterial(DefaultParts.INGOT);

    public static final @NotNull ItemPartMaterial NUGGET = new ItemPartMaterial(DefaultParts.NUGGET);

    public static final @NotNull ItemPartMaterial PLATE = new ItemPartMaterial(DefaultParts.PLATE);

    public static final @NotNull ItemPartMaterial ROD = new ItemPartMaterial(DefaultParts.ROD);

    public static final @NotNull List<ItemPartMaterial> ITEMS = ImmutableList.<ItemPartMaterial>builder()
            .add(DUST)
            .add(GEAR)
            .add(INGOT)
            .add(NUGGET)
            .add(PLATE)
            .add(ROD)
            .build();
}
