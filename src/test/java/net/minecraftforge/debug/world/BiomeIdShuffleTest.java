/*
 * Minecraft Forge
 * Copyright (c) 2020.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.debug.world;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BiomeIdShuffleTest.MOD_ID)
public class BiomeIdShuffleTest {
    public static final boolean ENABLED = true;
    static final String MOD_ID = "biome_id_shuffle";
    public BiomeIdShuffleTest() {
        if (ENABLED) FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        // Try adding "first" here, before the others and see what happens!
        for (final String name : new String[] {"second", "third", "fourth"}) {
            final ResourceLocation id = new ResourceLocation(MOD_ID, name);
            final RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, id);
            final Biome biome = BiomeMaker.makeVoidBiome().setRegistryName(id);

            event.getRegistry().register(biome);
            for (final BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
                BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, 1000));
            }
        }
    }
}
