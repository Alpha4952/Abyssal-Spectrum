package alpheta.abyssal_spectrum;

import alpheta.abyssal_spectrum.datagen.ModBlockTagProvider;
import alpheta.abyssal_spectrum.datagen.ModItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AbyssalSpectrumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        //pack.addProvider(ModItemTagProvider::new);
	}
}
