package kaptainwutax.seedcrackerX.cracker.decorator;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;

import kaptainwutax.seedcrackerX.util.Log;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class GenericBlock extends Decorator<Decorator.Config, GenericBlock.Data> {

    public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
    		.add(MCVersion.v1_18, new Decorator.Config(3, 3));

    public GenericBlock(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "block";
    }

    @Override
    public boolean canStart(Data data, long structureSeed, ChunkRand rand) {
        if (this.getVersion().isNewerThan(MCVersion.v1_17_1)) return true;
        return false;
    }

    @Override
    public boolean canStart(GenericBlock.Data data, long structureSeed, WorldgenRandom rand) {
        return true;
    }

    @Override
    public boolean isValidDimension(Dimension dimension) {
        return dimension == Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return true; //thinks it all plains biome
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }


    public GenericBlock.Data at(int blockX, int blockY, int blockZ, Biome biome) {
        return new GenericBlock.Data(this, blockX, blockY, blockZ, biome);
    }

    public static class Data extends Decorator.Data<GenericBlock> {
        public final int offsetX;
        public final int blockY;
        public final int offsetZ;

        public Data(GenericBlock feature, int blockX, int blockY, int blockZ, Biome biome) {
            super(feature, blockX >> 4, blockZ >> 4, biome);
            this.offsetX = blockX & 15;
            this.blockY = blockY;
            this.offsetZ = blockZ & 15;
        }
    }

}
