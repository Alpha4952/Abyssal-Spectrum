package alpheta.abyssal_spectrum.item;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.config.AbyssalSpectrumServerConfig;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTable {
    private static final Identifier ELDER_GUARDIAN = Identifier.of("minecraft", "entities/elder_guardian");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(ELDER_GUARDIAN.equals(key.getValue())) {
                LootPool.Builder guaranteed = LootPool.builder()
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .rolls(ConstantLootNumberProvider.create(AbyssalSpectrum.SERVER_CONFIG.ebonite_ingots_from_elder_guardian()))
                        .conditionally(RandomChanceLootCondition.builder(AbyssalSpectrum.SERVER_CONFIG.ebonite_ingots_drop_chance()))
                        .with(ItemEntry.builder(ModItems.ebonite_ingot));

                LootPool.Builder bonus1 = LootPool.builder()
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(AbyssalSpectrum.SERVER_CONFIG.ebonite_ingots_first_bonus()))
                        .with(ItemEntry.builder(ModItems.ebonite_ingot));

                LootPool.Builder bonus2 = LootPool.builder()
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(AbyssalSpectrum.SERVER_CONFIG.ebonite_ingots_second_bonus()))
                        .with(ItemEntry.builder(ModItems.ebonite_ingot));

                tableBuilder.pool(guaranteed.build());
                tableBuilder.pool(bonus1.build());
                tableBuilder.pool(bonus2.build());
            }
        });
    }
}
