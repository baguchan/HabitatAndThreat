package baguchan.habitat_and_threat.item;

import baguchan.habitat_and_threat.client.render.item.WildFireShieldBWLR;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class RawWildFireShieldItem extends Item {
	public RawWildFireShieldItem(Properties properties) {
		super(properties);
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return new WildFireShieldBWLR();
			}
		});
	}
}
