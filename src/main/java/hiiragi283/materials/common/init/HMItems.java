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

    public static final @NotNull ItemPartMaterial INGOT = new ItemPartMaterial(DefaultParts.INGOT);

    public static final @NotNull ItemPartMaterial PLATE = new ItemPartMaterial(DefaultParts.PLATE);

    public static final @NotNull List<ItemPartMaterial> ITEMS = ImmutableList.<ItemPartMaterial>builder()
            .add(DUST)
            .add(INGOT)
            .add(PLATE)
            .build();
}
