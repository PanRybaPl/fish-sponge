package pl.panryba.mc.sponge.stubs;

import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;

import static org.junit.Assert.assertTrue;

public class TestItemEnchantment implements FishItemEnchantment {
    private boolean applyCalled;
    private int maxLevel = 2;
    private int startLevel = 1;

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return startLevel;
    }

    @Override
    public void apply(int level) {
        applyCalled = true;
    }

    public void validate() {
        assertTrue(applyCalled);
    }
}