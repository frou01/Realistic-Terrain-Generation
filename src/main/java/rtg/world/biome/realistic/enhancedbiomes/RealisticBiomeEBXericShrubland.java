package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBXericShrubland;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBXericShrubland;

public class RealisticBiomeEBXericShrubland extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBXericShrubland(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainEBXericShrubland(),
			new SurfaceEBXericShrubland(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Xeric Shrubland");
		this.biomeWeight = ConfigEB.weightEBXericShrubland;
	}
}
