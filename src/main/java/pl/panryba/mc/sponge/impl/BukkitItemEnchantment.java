package pl.panryba.mc.sponge.impl;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;

public class BukkitItemEnchantment implements FishItemEnchantment {

    private final Enchantment e;
    private final ItemStack stack;

    public BukkitItemEnchantment(ItemStack stack, Enchantment e) {
        this.e = e;
        this.stack = stack;
    }

    public void apply(int level) {
        this.stack.addEnchantment(this.e, level);
    }

    @Override
    public int getMaxLevel() {
        return this.e.getMaxLevel();
    }

    @Override
    public int getStartLevel() {
        return this.e.getStartLevel();
    }
}
