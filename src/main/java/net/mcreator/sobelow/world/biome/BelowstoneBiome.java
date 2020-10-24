
package net.mcreator.sobelow.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.sobelow.block.MoltenRockBlock;
import net.mcreator.sobelow.block.DeepstoneBlock;
import net.mcreator.sobelow.SoBelowModElements;

import java.util.Set;
import java.util.Random;

@SoBelowModElements.ModElement.Tag
public class BelowstoneBiome extends SoBelowModElements.ModElement {
	@ObjectHolder("so_below:belowstone")
	public static final CustomBiome biome = null;
	public BelowstoneBiome(SoBelowModElements instance) {
		super(instance, 41);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.NETHER);
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(1f).scale(1.5f).temperature(0.5f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.NETHER).waterColor(-16777216).waterFogColor(-16777216)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(DeepstoneBlock.block.getDefaultState(),
							DeepstoneBlock.block.getDefaultState(), DeepstoneBlock.block.getDefaultState())));
			setRegistryName("belowstone");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.DEFAULT_FLOWER_CONFIG)
					.withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(4))));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG)
					.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new CustomTreeFeature()
					.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(MoltenRockBlock.block.getDefaultState()),
							new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()))).baseHeight(2)
									.setSapling((net.minecraftforge.common.IPlantable) Blocks.JUNGLE_SAPLING).build())
					.withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SILVERFISH, 15, 1, 15));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SKELETON, 15, 1, 15));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SKELETON_HORSE, 15, 1, 15));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.WITHER_SKELETON, 15, 1, 15));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(double posX, double posZ) {
			return -13421773;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor() {
			return -13421773;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -16777216;
		}
	}

	static class CustomTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig> {
		CustomTreeFeature() {
			super(BaseTreeFeatureConfig::deserialize);
		}

		@Override
		protected boolean place(IWorldGenerationReader worldgen, Random rand, BlockPos position, Set<BlockPos> changedBlocks,
				Set<BlockPos> changedBlocks2, MutableBoundingBox bbox, BaseTreeFeatureConfig conf) {
			if (!(worldgen instanceof IWorld))
				return false;
			IWorld world = (IWorld) worldgen;
			int height = rand.nextInt(5) + 2;
			boolean spawnTree = true;
			if (position.getY() >= 1 && position.getY() + height + 1 <= world.getHeight()) {
				for (int j = position.getY(); j <= position.getY() + 1 + height; j++) {
					int k = 1;
					if (j == position.getY())
						k = 0;
					if (j >= position.getY() + height - 1)
						k = 2;
					for (int px = position.getX() - k; px <= position.getX() + k && spawnTree; px++) {
						for (int pz = position.getZ() - k; pz <= position.getZ() + k && spawnTree; pz++) {
							if (j >= 0 && j < world.getHeight()) {
								if (!this.isReplaceable(world, new BlockPos(px, j, pz))) {
									spawnTree = false;
								}
							} else {
								spawnTree = false;
							}
						}
					}
				}
				if (!spawnTree) {
					return false;
				} else {
					Block ground = world.getBlockState(position.add(0, -1, 0)).getBlock();
					Block ground2 = world.getBlockState(position.add(0, -2, 0)).getBlock();
					if (!((ground == DeepstoneBlock.block.getDefaultState().getBlock() || ground == DeepstoneBlock.block.getDefaultState().getBlock())
							&& (ground2 == DeepstoneBlock.block.getDefaultState().getBlock()
									|| ground2 == DeepstoneBlock.block.getDefaultState().getBlock())))
						return false;
					BlockState state = world.getBlockState(position.down());
					if (position.getY() < world.getHeight() - height - 1) {
						setTreeBlockState(changedBlocks, world, position.down(), DeepstoneBlock.block.getDefaultState(), bbox);
						for (int genh = position.getY() - 3 + height; genh <= position.getY() + height; genh++) {
							int i4 = genh - (position.getY() + height);
							int j1 = (int) (1 - i4 * 0.5);
							for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
								for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
									int j2 = i2 - position.getZ();
									if (Math.abs(position.getX()) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
										BlockPos blockpos = new BlockPos(k1, genh, i2);
										state = world.getBlockState(blockpos);
										if (state.getBlock().isAir(state, world, blockpos) || state.getMaterial().blocksMovement()
												|| state.isIn(BlockTags.LEAVES) || state.getBlock() == Blocks.AIR.getDefaultState().getBlock()
												|| state.getBlock() == Blocks.AIR.getDefaultState().getBlock()) {
											setTreeBlockState(changedBlocks, world, blockpos, Blocks.AIR.getDefaultState(), bbox);
										}
									}
								}
							}
						}
						for (int genh = 0; genh < height; genh++) {
							BlockPos genhPos = position.up(genh);
							state = world.getBlockState(genhPos);
							setTreeBlockState(changedBlocks, world, genhPos, MoltenRockBlock.block.getDefaultState(), bbox);
							if (state.getBlock().isAir(state, world, genhPos) || state.getMaterial().blocksMovement() || state.isIn(BlockTags.LEAVES)
									|| state.getBlock() == Blocks.AIR.getDefaultState().getBlock()
									|| state.getBlock() == Blocks.AIR.getDefaultState().getBlock()) {
							}
						}
						if (rand.nextInt(4) == 0 && height > 5) {
							for (int hlevel = 0; hlevel < 2; hlevel++) {
								for (Direction Direction : Direction.Plane.HORIZONTAL) {
									if (rand.nextInt(4 - hlevel) == 0) {
										Direction dir = Direction.getOpposite();
										setTreeBlockState(changedBlocks, world, position.add(dir.getXOffset(), height - 5 + hlevel, dir.getZOffset()),
												Blocks.AIR.getDefaultState(), bbox);
									}
								}
							}
						}
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}

		private void addVines(IWorld world, BlockPos pos, Set<BlockPos> changedBlocks, MutableBoundingBox bbox) {
			setTreeBlockState(changedBlocks, world, pos, Blocks.AIR.getDefaultState(), bbox);
			int i = 5;
			for (BlockPos blockpos = pos.down(); world.isAirBlock(blockpos) && i > 0; --i) {
				setTreeBlockState(changedBlocks, world, blockpos, Blocks.AIR.getDefaultState(), bbox);
				blockpos = blockpos.down();
			}
		}

		private boolean canGrowInto(Block blockType) {
			return blockType.getDefaultState().getMaterial() == Material.AIR || blockType == MoltenRockBlock.block.getDefaultState().getBlock()
					|| blockType == Blocks.AIR.getDefaultState().getBlock() || blockType == DeepstoneBlock.block.getDefaultState().getBlock()
					|| blockType == DeepstoneBlock.block.getDefaultState().getBlock();
		}

		private boolean isReplaceable(IWorld world, BlockPos pos) {
			BlockState state = world.getBlockState(pos);
			return state.getBlock().isAir(state, world, pos) || canGrowInto(state.getBlock()) || !state.getMaterial().blocksMovement();
		}

		private void setTreeBlockState(Set<BlockPos> changedBlocks, IWorldWriter world, BlockPos pos, BlockState state, MutableBoundingBox mbb) {
			super.func_227217_a_(world, pos, state, mbb);
			changedBlocks.add(pos.toImmutable());
		}
	}
}
