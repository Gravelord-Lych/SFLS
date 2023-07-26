package lych.sfls.data;

import lych.sfls.SFLSMod;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

import static lych.sfls.item.ModItems.MATH_PAPER;
import static net.minecraft.data.ShapelessRecipeBuilder.shapeless;
import static net.minecraft.item.Items.*;

public class RecipeDataGen extends ForgeRecipeProvider {
    public RecipeDataGen(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        shapeless(MATH_PAPER, 4).requires(PAPER).requires(INK_SAC).requires(REDSTONE).unlockedBy("has_paper", has(Items.PAPER)).save(consumer);
    }

    public static InventoryChangeTrigger.Instance has(IItemProvider provider) {
        return RecipeProvider.has(provider);
    }

    public static InventoryChangeTrigger.Instance has(ITag<Item> tag) {
        return RecipeProvider.has(tag);
    }

    public static String stHas(String name) {
        return "has_" + name;
    }

    public static String stHasTwo(String name1, String name2) {
        return "has_" + name1 + "_and_" + name2;
    }

    @Override
    public String getName() {
        return String.format("Recipes: %s", SFLSMod.MOD_ID);
    }
}
