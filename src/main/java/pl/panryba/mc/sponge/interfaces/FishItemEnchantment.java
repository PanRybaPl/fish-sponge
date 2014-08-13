package pl.panryba.mc.sponge.interfaces;

public interface FishItemEnchantment {
    int getMaxLevel();
    int getStartLevel();
    void apply(int level);
}
