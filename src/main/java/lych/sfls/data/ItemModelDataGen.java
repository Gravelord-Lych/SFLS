package lych.sfls.data;

import lych.sfls.SFLSMod;
import lych.sfls.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

import static lych.sfls.data.ModDataGens.registryNameToString;

public class ItemModelDataGen extends ItemModelProvider {
    private static final ModelFile GENERATED = new ModelFile.UncheckedModelFile("item/generated");
    private static final ModelFile HANDHELD = new ModelFile.UncheckedModelFile("item/handheld");
    private static final ModelFile SPAWN_EGG = new ModelFile.UncheckedModelFile("item/template_spawn_egg");
    private static final String LAYER0 = "layer0";
    private static final String LAYER1 = "layer1";

    public ItemModelDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SFLSMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generated(ModItems.MATH_PAPER);
    }

    private void generated(Item item) {
        simple(registryNameToString(item), GENERATED, prefix(item));
    }

    private void handheld(Item item) {
        simple(registryNameToString(item), HANDHELD, prefix(item));
    }

    private void simple(String name, ModelFile model, ResourceLocation texture) {
        getBuilder(name).parent(model).texture(LAYER0, texture);
    }

    private void spawnEgg(Item item) {
        getBuilder(registryNameToString(item)).parent(SPAWN_EGG);
    }

//    private void segenBlockItem(BlockItem segen) {
//        blockItem(registryNameToString(segen), new ModelFile.UncheckedModelFile(BlockModelDataGen.prefix(Utils.getRegistryName(segen.getBlock()).getPath())));
//    }

//    private void blockItem(BlockItem item) {
//        blockItem(this, item);
//    }

//    public static void blockItem(ItemModelProvider provider, BlockItem item) {
//        blockItem(provider, registryNameToString(item), item.getBlock() instanceof WallBlock ? byWall((WallBlock) item.getBlock()) : byBlock(item.getBlock()));
//    }

    private void blockItem(String itemPath, ModelFile blockModel) {
        blockItem(this, itemPath, blockModel);
    }

    public static void blockItem(ItemModelProvider provider, String itemPath, ModelFile blockModel) {
        provider.getBuilder(itemPath).parent(blockModel);
    }

    static ResourceLocation prefix(Item item) {
        return prefix(item, "");
    }

    private static ResourceLocation prefix(Item item, String ex) {
        Objects.requireNonNull(item.getRegistryName(), "Registry name should be non-null");
        return SFLSMod.prefix("item/" + item.getRegistryName().getPath() + ex);
    }

//    private static ModelFile byBlock(Block block) {
//        return new ModelFile.UncheckedModelFile(BlockModelDataGen.prefix(block));
//    }
//
//    private static ModelFile byWall(WallBlock block) {
//        return new ModelFile.UncheckedModelFile(BlockModelDataGen.prefix(block) + "_inventory");
//    }
}
