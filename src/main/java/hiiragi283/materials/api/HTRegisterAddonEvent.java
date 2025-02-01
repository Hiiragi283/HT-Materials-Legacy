package hiiragi283.materials.api;

import java.util.List;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.Event;

public class HTRegisterAddonEvent extends Event {

  private final List<HTMaterialsAddon> addons;

  public HTRegisterAddonEvent(List<HTMaterialsAddon> addons) {
    this.addons = addons;
  }

  public void register(HTMaterialsAddon... addons) {
    for (HTMaterialsAddon addon : addons) {
      if (Loader.isModLoaded(addon.getModId())) {
        this.addons.add(addon);
      }
    }
  }
}
