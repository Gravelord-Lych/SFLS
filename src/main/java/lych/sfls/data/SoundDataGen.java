package lych.sfls.data;

import com.google.common.base.Preconditions;
import lych.sfls.SFLSMod;
import lych.sfls.util.DefaultValues;
import lych.sfls.util.ModSoundEvents;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static lych.sfls.SFLSMod.MOD_ID;

public class SoundDataGen extends SoundDefinitionsProvider {
    private static final String GENERIC_EXPLODE = "subtitles.entity.generic.explode";
    private static final String GENERIC_FOOTSTEPS = "subtitles.block.generic.footsteps";
    private static final String BOW_PATH = "random/bow";
    private static final ResourceLocation ETHEMOVE_PATH = SFLSMod.prefix("item/math_paper");

    public SoundDataGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        single(ModSoundEvents.MATH_PAPER_USE, "random/bow");
    }

    @Override
    public String getName() {
        return "Sounds: " + MOD_ID;
    }

    private void single(RegistryObject<SoundEvent> sound, String name) {
        single(sound, new SoundPair(name));
    }

    private void single(RegistryObject<SoundEvent> sound,  SoundPair pair) {
        single(sound, DefaultValues.dummyConsumer(), pair);
    }

    private void single(RegistryObject<SoundEvent> sound, Consumer<? super SoundDefinition> additionalOperations, SoundPair pair) {
        add(sound, Util.make(definition().with(sound(pair.name).volume(pair.volume).pitch(pair.pitch)).subtitle(makeSubtitle(sound)), additionalOperations::accept));
    }

    private void multiple(RegistryObject<SoundEvent> sound, String... names) {
        multiple(sound, DefaultValues.dummyConsumer(), names);
    }

    private void multiple(RegistryObject<SoundEvent> sound, Consumer<? super SoundDefinition> additionalOperations, String... names) {
        multiple(sound, additionalOperations, 1, names);
    }

    private void multiple(RegistryObject<SoundEvent> sound,  double volume, String... names) {
        multiple(sound, DefaultValues.dummyConsumer(), volume, names);
    }

    private void multiple(RegistryObject<SoundEvent> sound, Consumer<? super SoundDefinition> additionalOperations, double volume, String... names) {
        multiple(sound, additionalOperations, volume, 1, names);
    }

    private void multiple(RegistryObject<SoundEvent> sound,  double volume, double pitch, String... names) {
        multiple(sound, DefaultValues.dummyConsumer(), volume, pitch, names);
    }

    private void multiple(RegistryObject<SoundEvent> sound, Consumer<? super SoundDefinition> additionalOperations, double volume, double pitch, String... names) {
        multiple(sound, additionalOperations, Arrays.stream(names).map(name -> new SoundPair(name, volume, pitch)).toArray(SoundPair[]::new));
    }

    private void multiple(RegistryObject<SoundEvent> sound,  SoundPair... pairs) {
        multiple(sound, DefaultValues.dummyConsumer(), pairs);
    }

    private void multiple(RegistryObject<SoundEvent> sound, Consumer<? super SoundDefinition> additionalOperations, SoundPair... pairs) {
        Objects.requireNonNull(pairs);
        Preconditions.checkArgument(pairs.length > 1);
        SoundDefinition definition = definition().subtitle(makeSubtitle(sound));
        for (SoundPair pair : pairs) {
            definition.with(sound(pair.name).volume(pair.volume).pitch(pair.pitch));
        }
        additionalOperations.accept(definition);
        add(sound, definition);
    }

    private String[] paths(String type, int count) {
        return paths(type, 1, count);
    }

    private String[] paths(String type, int min, int max) {
        if (max == 1) {
            return new String[]{type};
        }
        List<String> paths = new ArrayList<>(max);
        for (int i = min; i <= max; i++) {
            paths.add(type + i);
        }
        return paths.toArray(new String[0]);
    }

    private void add(RegistryObject<SoundEvent> sound) {
        add(sound, 1);
    }

    private void add(RegistryObject<SoundEvent> sound, int count) {
        add(sound, count, DefaultValues.dummyConsumer());
    }

    private void add(RegistryObject<SoundEvent> sound, int count, Consumer<? super SoundDefinition> additionalOperations) {
        Preconditions.checkArgument(count > 0, "Count must be positive");
        SoundDefinition definition = definition();
        if (count > 1) {
            for (int i = 1; i <= count; i++) {
                definition.with(sound((sound.getId() + String.valueOf(i)).replace('.', '/')));
            }
        } else {
            definition.with(sound(sound.getId().toString().replace('.', '/')));
        }
        definition.subtitle(makeSubtitle(sound));
        additionalOperations.accept(definition);
        add(sound, definition);
    }

    private void redirect(RegistryObject<SoundEvent> sound, String redirectTarget) {
        redirect(sound, new ResourceLocation(redirectTarget));
    }

    private void redirect(RegistryObject<SoundEvent> sound, String redirectTarget, int count) {
        redirect(sound, new ResourceLocation(redirectTarget), count);
    }

    private void redirect(RegistryObject<SoundEvent> sound, ResourceLocation redirectTarget) {
        redirect(sound, redirectTarget, 1);
    }

    private void redirect(RegistryObject<SoundEvent> sound, ResourceLocation redirectTarget, int count) {
        SoundDefinition definition = definition().subtitle(makeSubtitle(sound));
        if (count > 1) {
            for (int i = 1; i <= count; i++) {
                definition.with(sound(redirectTarget + String.valueOf(i)));
            }
        } else {
            definition.with(sound(redirectTarget));
        }
        add(sound, definition);
    }

    private static String makeSubtitle(RegistryObject<SoundEvent> sound) {
        return "subtitles." + sound.getId();
    }

    protected static class SoundPair {
        public final String name;
        public final double volume;
        public final double pitch;

        public SoundPair(String name) {
            this(name, 1);
        }

        public SoundPair(String name, double volume) {
            this(name, volume, 1);
        }

        public SoundPair(String name, double volume, double pitch) {
            this.name = name;
            this.volume = volume;
            this.pitch = pitch;
        }
    }
}
