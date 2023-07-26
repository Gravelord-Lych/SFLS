package lych.sfls.util.data;

import com.google.gson.JsonObject;
import lych.sfls.world.chunkgen.ModChunkGenerators;
import net.minecraft.util.ResourceLocation;

public abstract class ChunkGeneratorBuilder implements IDataBuilder {
    private final String modid;
    private final String name;

    protected ChunkGeneratorBuilder(String modid, String name) {
        this.modid = modid;
        this.name = name;
    }

    @Override
    public JsonObject toJson() {
        JsonObject root = new JsonObject();
        addDetails(root);
        root.addProperty("type", getType().toString());
        return root;
    }

    protected abstract void addDetails(JsonObject root);

    protected abstract ResourceLocation getType();

    @Override
    public String getNamespace() {
        return modid;
    }

    @Override
    public String getPath() {
        return name;
    }

    public static Noise noise(String modid, String name) {
        return new Noise(modid, name);
    }

    public static Debug debug(String modid, String name) {
        return new Debug(modid, name);
    }

    public static Common common(String modid, String name, ResourceLocation type) {
        return new Common(modid, name, type);
    }

    public static FlatChunkGeneratorBuilder flat(String modid, String name) {
        return new FlatChunkGeneratorBuilder(modid, name);
    }

    public static class Noise extends ChunkGeneratorBuilder {
        private static final ResourceLocation NOISE = new ResourceLocation("noise");
        private JsonObject biomeSource;
        private long seed;
        private boolean noSeed;
        private ResourceLocation settings;
        private ResourceLocation type = NOISE;

        private Noise(String modid, String name) {
            super(modid, name);
        }

        public Noise biomeSource(BiomeProviderBuilder biomeSource) {
            this.biomeSource = biomeSource.toJson();
            return this;
        }

        public Noise biomeSource(JsonObject biomeSource) {
            this.biomeSource = biomeSource;
            return this;
        }

        public Noise seed(long seed) {
            this.seed = seed;
            return this;
        }

        public Noise noSeed() {
            this.noSeed = true;
            this.seed = 0;
            this.type = ModChunkGenerators.DYNAMIC_SEED_CHUNKGEN;
            return this;
        }

        public Noise settings(ResourceLocation settings) {
            this.settings = settings;
            return this;
        }

        @Override
        protected void addDetails(JsonObject root) {
            root.add("biome_source", biomeSource);
            if (!noSeed) {
                root.addProperty("seed", seed);
            }
            root.addProperty("settings", settings.toString());
        }

        @Override
        public ResourceLocation getType() {
            return type;
        }
    }

    public static class Debug extends ChunkGeneratorBuilder {
        private static final ResourceLocation DEBUG = new ResourceLocation("debug");

        public Debug(String modid, String name) {
            super(modid, name);
        }

        @Override
        protected void addDetails(JsonObject root) {}

        @Override
        public ResourceLocation getType() {
            return DEBUG;
        }
    }

    public static class Common extends ChunkGeneratorBuilder {
        private final ResourceLocation type;
        private JsonObject biomeSource;
        private long seed;
        private boolean noSeed;
        private ResourceLocation settings;

        private Common(String modid, String name, ResourceLocation type) {
            super(modid, name);
            this.type = type;
        }

        public Common biomeSource(BiomeProviderBuilder biomeSource) {
            this.biomeSource = biomeSource.toJson();
            return this;
        }

        public Common biomeSource(JsonObject biomeSource) {
            this.biomeSource = biomeSource;
            return this;
        }

        public Common seed(long seed) {
            this.seed = seed;
            return this;
        }

        public Common noSeed() {
            this.noSeed = true;
            this.seed = 0;
            return this;
        }

        public Common settings(ResourceLocation settings) {
            this.settings = settings;
            return this;
        }

        @Override
        protected void addDetails(JsonObject root) {
            root.add("biome_source", biomeSource);
            if (!noSeed) {
                root.addProperty("seed", seed);
            }
            root.addProperty("settings", settings.toString());
        }

        @Override
        public ResourceLocation getType() {
            return type;
        }
    }
}
