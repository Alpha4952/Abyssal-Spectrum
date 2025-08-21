package alpheta.abyssal_spectrum.item.custom;

import alpheta.abyssal_spectrum.item.ModItems;
import blue.endless.jankson.annotation.Nullable;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;
import java.util.function.Consumer;

public final class GeoArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GeoArmorItem(RegistryEntry<ArmorMaterial> armorMaterial, ArmorItem.Type type, Settings properties) {
        super(armorMaterial, type, properties);
    }

    // Create our armor model/renderer for Fabric and return it
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <T extends LivingEntity> BipedEntityModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable BipedEntityModel<T> original) {
                if(this.renderer == null)
                    this.renderer = new AbyssalSteelArmorRenderer();

                return this.renderer;
            }
        });
    }

    // Let's add our animation controller
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            // Apply our generic idle animation.
            // Whether it plays or not is decided down below.
            state.getController().setAnimation(DefaultAnimations.IDLE);

            // Let's gather some data from the state to use below
            // This is the entity that is currently wearing/holding the item
            Entity entity = state.getData(DataTickets.ENTITY);

            // We'll just have ArmorStands always animate, so we can return here
            if (entity instanceof ArmorStandEntity)
                return PlayState.CONTINUE;

            // For this example, we only want the animation to play if the entity is wearing all pieces of the armor
            // Let's collect the armor pieces the entity is currently wearing
            Set<Item> wornArmor = new ObjectOpenHashSet<>();

            if (entity instanceof LivingEntity living) {
                for (ItemStack stack : living.getArmorItems()) {
                    // We can stop immediately if any of the slots are empty
                    if (stack.isEmpty())
                        return PlayState.STOP;

                    wornArmor.add(stack.getItem());
                }
            }

            // Check each of the pieces match our set
            boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                    ModItems.abyssal_steel_boots,
                    ModItems.abyssal_steel_leggings,
                    ModItems.abyssal_steel_chestplate,
                    ModItems.abyssal_steel_helmet));

            // Play the animation if the full set is being worn, otherwise stop
            return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
        }));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}