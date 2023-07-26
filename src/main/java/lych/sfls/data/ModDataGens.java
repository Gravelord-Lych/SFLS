package lych.sfls.data;

import lych.sfls.SFLSMod;
import lych.sfls.data.lang.EnUsLDG;
import lych.sfls.data.lang.ZhCnLDG;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = SFLSMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModDataGens {
    private ModDataGens() {}

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        addLangs(gen);
        gen.addProvider(new ItemModelDataGen(gen, helper));
        gen.addProvider(new RecipeDataGen(gen));
        gen.addProvider(new SoundDataGen(gen, helper));
    }

    private static void addLangs(DataGenerator gen) {
        gen.addProvider(new EnUsLDG(gen));
        gen.addProvider(new ZhCnLDG(gen));
    }

    public static String registryNameToString(ForgeRegistryEntry<?> entry) {
        Objects.requireNonNull(entry.getRegistryName(), "Registry name should be non-null");
        return entry.getRegistryName().toString();
    }
}