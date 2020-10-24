package net.mcreator.sobelow.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.sobelow.SoBelowModElements;

import java.util.Map;

@SoBelowModElements.ModElement.Tag
public class BelowmerangBulletHitsBlockProcedure extends SoBelowModElements.ModElement {
	public BelowmerangBulletHitsBlockProcedure(SoBelowModElements instance) {
		super(instance, 22);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BelowmerangBulletHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BelowmerangBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BelowmerangBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BelowmerangBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BelowmerangBulletHitsBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)).isSolid())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock()));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) z), false);
		}
		if ((world.getBlockState(new BlockPos((int) (x + (-1)), (int) y, (int) z)).isSolid())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (new ItemStack((world.getBlockState(new BlockPos((int) (x + (-1)), (int) y, (int) z))).getBlock()));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			world.destroyBlock(new BlockPos((int) (x + (-1)), (int) y, (int) z), false);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock()));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) (y + (-1)), (int) z)).isSolid())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y + (-1)), (int) z))).getBlock()));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			world.destroyBlock(new BlockPos((int) x, (int) (y + (-1)), (int) z), false);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))).isSolid())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), false);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + (-1)))).isSolid())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + (-1))))).getBlock()));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + (-1))), false);
		}
	}
}
