package net.mcreator.sobelow.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.sobelow.block.WitheriteBlock;
import net.mcreator.sobelow.SoBelowModElements;

import java.util.Map;

@SoBelowModElements.ModElement.Tag
public class WitheriteUpdateTickProcedure extends SoBelowModElements.ModElement {
	public WitheriteUpdateTickProcedure(SoBelowModElements instance) {
		super(instance, 20);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WitheriteUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WitheriteUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WitheriteUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WitheriteUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)).isSolid())) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), WitheriteBlock.block.getDefaultState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) (x + (-1)), (int) y, (int) z)).isSolid())) {
			world.setBlockState(new BlockPos((int) (x + (-1)), (int) y, (int) z), WitheriteBlock.block.getDefaultState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), WitheriteBlock.block.getDefaultState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) (y + (-1)), (int) z)).isSolid())) {
			world.setBlockState(new BlockPos((int) x, (int) (y + (-1)), (int) z), WitheriteBlock.block.getDefaultState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))).isSolid())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), WitheriteBlock.block.getDefaultState(), 3);
		}
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + (-1)))).isSolid())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + (-1))), WitheriteBlock.block.getDefaultState(), 3);
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
	}
}
