package lych.sfls.data.lang;

import lych.sfls.entity.ModEntities;
import lych.sfls.item.ModItems;
import lych.sfls.util.ModSoundEvents;
import net.minecraft.data.DataGenerator;

public class EnUsLDG extends BaseLanguageDataGen {
    public EnUsLDG(DataGenerator gen) {
        super(gen, "en_us");
    }

    @Override
    protected void addItems() {
        addItem(ModItems.MATH_PAPER, "Math Paper");
    }

    @Override
    protected void addEntities() {
        addEntityType(ModEntities.MATH_PAPER, "Math Paper");
    }

    @Override
    protected void addSubtitles() {
        addSubtitle(ModSoundEvents.MATH_PAPER_USE, "Math Paper used");
    }

    @Override
    protected void addMiscellaneous() {
        add(ITEM_GROUP, "SFLS");
    }
}
