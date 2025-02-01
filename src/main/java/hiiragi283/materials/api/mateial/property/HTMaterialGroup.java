package hiiragi283.materials.api.mateial.property;

import com.github.bsideup.jabel.Desugar;
import hiiragi283.materials.api.mateial.part.DefaultParts;
import hiiragi283.materials.api.mateial.part.HTPart;

import java.util.Arrays;
import java.util.List;

@Desugar
public record HTMaterialGroup(List<HTPart> parts) {

    public static final HTMaterialGroup DUST = new HTMaterialGroup(
            DefaultParts.DUST,
            DefaultParts.PLATE,
            DefaultParts.ROD
    );

    public static final HTMaterialGroup METAL = new HTMaterialGroup(
            DefaultParts.DUST,
            DefaultParts.GEAR,
            DefaultParts.INGOT,
            DefaultParts.NUGGET,
            DefaultParts.PLATE,
            DefaultParts.ROD
    );

    public static final HTMaterialGroup METAL_VANILLA = new HTMaterialGroup(
            DefaultParts.DUST,
            DefaultParts.GEAR,
            DefaultParts.PLATE,
            DefaultParts.ROD
    );

    public HTMaterialGroup(HTPart... parts) {
        this(Arrays.asList(parts));
    }

    public boolean contains(HTPart part) {
        return parts.contains(part);
    }
}
