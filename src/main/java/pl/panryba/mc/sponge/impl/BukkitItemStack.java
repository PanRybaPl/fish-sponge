package pl.panryba.mc.sponge.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;
import pl.panryba.mc.sponge.interfaces.FishItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BukkitItemStack implements FishItemStack {
    private final ItemStack stack;

    public BukkitItemStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean isEmpty() {
        return this.stack == null || this.stack.getType() == Material.AIR;
    }

    @Override
    public List<FishItemEnchantment> getValidEnchantments() {
        List<FishItemEnchantment> valid = new ArrayList<>();
        if(this.isEmpty()) {
            return valid;
        }

        Map<Enchantment, Integer> alreadyApplied = this.stack.getEnchantments();

        for (Enchantment enchantment : Enchantment.values()) {
            if (!enchantment.canEnchantItem(this.stack))
                continue;

            boolean isValid = true;

            for (Enchantment alreadyAppliedEnchantment : alreadyApplied.keySet()) {
                if (alreadyAppliedEnchantment.equals(enchantment) || alreadyAppliedEnchantment.conflictsWith(enchantment)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                valid.add(new BukkitItemEnchantment(this.stack, enchantment));
            }
        }

        return valid;
    }

}
