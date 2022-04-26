package baguchan.habitat_and_threat.item;

import baguchan.habitat_and_threat.client.render.item.WildFireShieldBWLR;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class WildFireShieldItem extends Item {
	public WildFireShieldItem(Item.Properties properties) {
		super(properties);
	}

	public UseAnim getUseAnimation(ItemStack p_43105_) {
		return UseAnim.BLOCK;
	}

	public int getUseDuration(ItemStack p_43107_) {
		return 72000;
	}

	public InteractionResultHolder<ItemStack> use(Level p_43099_, Player p_43100_, InteractionHand p_43101_) {
		ItemStack itemstack = p_43100_.getItemInHand(p_43101_);
		p_43100_.startUsingItem(p_43101_);
		return InteractionResultHolder.consume(itemstack);
	}

	public boolean isValidRepairItem(ItemStack p_43091_, ItemStack p_43092_) {
		return super.isValidRepairItem(p_43091_, p_43092_);
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

	/* ******************** FORGE START ******************** */

	@Override
	public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
		return net.minecraftforge.common.ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
	}
}
