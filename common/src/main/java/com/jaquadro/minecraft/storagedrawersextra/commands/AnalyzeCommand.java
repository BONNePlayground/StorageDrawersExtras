package com.jaquadro.minecraft.storagedrawersextra.commands;


import com.jaquadro.minecraft.storagedrawersextra.block.VariantRegistry;
import com.jaquadro.minecraft.storagedrawersextra.platform.Services;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


public class AnalyzeCommand
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        if (!Services.PLATFORM.isDevelopmentEnvironment()) return;

        dispatcher.register(
            Commands.literal("sde").then(
                Commands.literal("analyze").
                    then(Commands.argument("missing", BoolArgumentType.bool()).
                        then(Commands.argument("name", StringArgumentType.string()).
                            suggests(AnalyzeCommand::suggestModIds).
                            executes(AnalyzeCommand::run)).
                        executes(AnalyzeCommand::runNoArgs)))
        );
    }


    private static int runNoArgs(CommandContext<CommandSourceStack> context)
    {
        boolean missing = BoolArgumentType.getBool(context, "missing");
        printOutTable(null, missing);
        return Command.SINGLE_SUCCESS;
    }


    private static int run(CommandContext<CommandSourceStack> context)
    {
        String name = StringArgumentType.getString(context, "name");
        boolean missing = BoolArgumentType.getBool(context, "missing");

        printOutTable(name, missing);

        return Command.SINGLE_SUCCESS;
    }


    private static CompletableFuture<Suggestions> suggestModIds(
        CommandContext<CommandSourceStack> context, SuggestionsBuilder builder)
    {
        for (String mod : Services.PLATFORM.getLoadedMods())
        {
            if (mod.toLowerCase().startsWith(builder.getRemainingLowerCase()))
            {
                builder.suggest(mod);
            }
        }
        return builder.buildFuture();
    }


    private static void printOutTable(String modId, boolean missing)
    {
        Set<ResourceLocation> drawersImplemented = new HashSet<>();

        for (VariantRegistry value : VariantRegistry.values())
        {
            drawersImplemented.add(value.getPlankResource());
        }


        List<ResourceLocation> data = BuiltInRegistries.BLOCK.stream().
            filter(block -> block.defaultBlockState().is(BlockTags.PLANKS)).
            map(Block::builtInRegistryHolder).
            map(Holder.Reference::key).
            map(ResourceKey::location).
            filter(location -> location.getNamespace().equals(modId) ||
                modId == null && !location.getNamespace().equals("minecraft")).
            filter(location -> !missing || !drawersImplemented.contains(location)).
            toList();


        int max = data.stream().mapToInt(res -> res.toString().length()).max().getAsInt();

        String format = "%-" + (max + 4) + "s %-10s%n";
        System.out.printf(format, "Plank", "Missing");
        System.out.println("-".repeat(max + 14));

        data.forEach(res -> System.out.printf(format, res, drawersImplemented.contains(res)));
    }
}