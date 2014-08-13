package pl.panryba.mc.sponge.interfaces;

import java.util.List;
import java.util.Map;

public interface EnchantSelector {
    public Map<FishItemEnchantment, Integer> select(List<FishItemEnchantment> valid, FishItemStack item);
}
