package silly.chemthunder.arcturus.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import silly.chemthunder.arcturus.Arcturus;
import silly.chemthunder.arcturus.item.*;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ArcturusItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //  Item WEAPON_RACK = create("weapon_rack", new WeaponRackItem(new Item.Settings()));

    Item DAMNED_BOOK = create("damned_book", new DamnedBookItem(new Item.Settings()
            .maxCount(1)
            .fireproof()
            .rarity(Rarity.UNCOMMON)
    ));

    Item HOLY_SHARD = create("holy_shard", new Item(new Item.Settings()
            .maxCount(1)
            .fireproof()
            .rarity(Rarity.UNCOMMON)
    ));

    Item ELDRITCH_SHACKLE = create("eldritch_shackle", new EldritchShackleItem(new Item.Settings()
            .maxCount(1)
            .fireproof()
            .rarity(Rarity.UNCOMMON)
    ));

    Item HERALDS_HORN = create("heralds_horn", new HeraldsHornItem(new Item.Settings()
            .maxCount(1)
            .fireproof()
            .rarity(Rarity.UNCOMMON)
    ));

    Item DECADENCE = create("decadence", new DecadenceItem(
            ToolMaterials.NETHERITE,
            3,
            -2.5f,

            new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
            .fireproof()
    ));

    Item SHATTERED_DECADENCE = create("shattered_decadence", new ShatteredDecadenceItem(new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    ));

    Item TATTERED_DAMNED_BOOK = create("tattered_damned_book", new TatteredDamnedBookItem(new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    ));

    static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, Arcturus.id(name));
        return item;
    }

    static void initialize() {
        ITEMS.forEach((item, id) -> Registry.register(Registries.ITEM, id, item));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ArcturusItems::addCombatEntries);
    }

    private static void addCombatEntries(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.addAfter(Items.TRIDENT, DAMNED_BOOK);
        fabricItemGroupEntries.addAfter(DAMNED_BOOK, ELDRITCH_SHACKLE);
        fabricItemGroupEntries.addAfter(ELDRITCH_SHACKLE, HERALDS_HORN);
        fabricItemGroupEntries.addAfter(ELDRITCH_SHACKLE, HOLY_SHARD);
        fabricItemGroupEntries.addAfter(HOLY_SHARD, DECADENCE);
        fabricItemGroupEntries.addAfter(DAMNED_BOOK, TATTERED_DAMNED_BOOK);
        fabricItemGroupEntries.addAfter(DECADENCE, SHATTERED_DECADENCE);
    }

    /*
    / todo
    / add custom item group
    / simplify existing items
    / revamp shackles
     */
}
