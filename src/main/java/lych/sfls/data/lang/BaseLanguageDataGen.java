package lych.sfls.data.lang;

import lych.sfls.SFLSMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public abstract class BaseLanguageDataGen extends LanguageProvider {
    protected static final String ITEM_GROUP = "itemGroup.sfls";
    protected final String locale;

    public BaseLanguageDataGen(DataGenerator gen, String locale) {
        super(gen, SFLSMod.MOD_ID, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        addItems();
        addEntities();
        addSubtitles();
        addMiscellaneous();
    }

    protected abstract void addItems();

    protected abstract void addEntities();

    protected abstract void addSubtitles();

    protected abstract void addMiscellaneous();

    protected final void addItem(Item key, String name) {
        addItem(() -> key, name);
    }

    protected final void addEntityType(EntityType<?> key, String name) {
        addEntityType(() -> key, name);
    }

    protected final void addSubtitle(Supplier<SoundEvent> event, String name) {
        add(String.format("subtitles.%s:%s", SFLSMod.MOD_ID, event.get().getLocation().getPath()), name);
    }

    @Override
    public String getName() {
        return String.format("Languages: %s (%s)", SFLSMod.MOD_ID, locale);
    }
}
