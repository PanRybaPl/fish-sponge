package pl.panryba.mc.sponge.interfaces;

import java.util.List;

public interface FishItemStack {
    boolean isEmpty();
    List<FishItemEnchantment> getValidEnchantments();
}
