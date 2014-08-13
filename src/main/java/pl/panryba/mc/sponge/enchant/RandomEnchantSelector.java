package pl.panryba.mc.sponge.enchant;

import pl.panryba.mc.sponge.interfaces.EnchantSelector;
import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;
import pl.panryba.mc.sponge.interfaces.FishItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomEnchantSelector implements EnchantSelector {
    @Override
    public Map<FishItemEnchantment, Integer> select(List<FishItemEnchantment> valid, FishItemStack item) {
        Map<FishItemEnchantment, Integer> values = new HashMap<>();

        Random randomGenerator = new Random();
        int toApplyIndex = randomGenerator.nextInt(valid.size());

        FishItemEnchantment toApply = valid.get(toApplyIndex);
        if(toApply == null) {
            return values;
        }

        int maxLevel = toApply.getMaxLevel() - toApply.getStartLevel() + 1;
        int level = randomGenerator.nextInt(maxLevel) + toApply.getStartLevel();

        values.put(toApply, level);
        return values;
    }
}
