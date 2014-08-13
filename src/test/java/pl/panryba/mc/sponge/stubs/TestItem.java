package pl.panryba.mc.sponge.stubs;

import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;
import pl.panryba.mc.sponge.interfaces.FishItemStack;

import java.util.List;

public class TestItem implements FishItemStack {
    public List<FishItemEnchantment> enchantments;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public List<FishItemEnchantment> getValidEnchantments() {
        return enchantments;
    }
}