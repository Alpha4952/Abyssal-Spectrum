package alpheta.abyssal_spectrum.block.entity;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.block.ModBlocks;
import alpheta.abyssal_spectrum.block.entity.custom.NetheriteSmelterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<NetheriteSmelterBlockEntity> NETHERITE_SMELTER_BE;

    public static void registerBlockEntities() {
        NETHERITE_SMELTER_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(AbyssalSpectrum.MOD_ID, "netherite_smelter_be"),
                FabricBlockEntityTypeBuilder.create(NetheriteSmelterBlockEntity::new, ModBlocks.netherite_smelter).build()
        );

        AbyssalSpectrum.LOGGER.info("Registered Block Entities for " + AbyssalSpectrum.MOD_ID);
    }
}
