package net.smelly.seedgetter;

import net.minecraftforge.fml.common.Mod;
import net.smelly.seedgetter.decryption.HashedSeed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SeedGetter.MOD_ID)
public final class SeedGetter {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "seed_getter";
    private static HashedSeed hashedSeed = null;

    public SeedGetter() {}

    public static void setHashedSeed(long seed) {
        hashedSeed = new HashedSeed(seed);
    }

    public static void resetHashedSeed() {
        hashedSeed = null;
    }
}
