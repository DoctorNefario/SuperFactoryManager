package ca.teamdman.sfm.common.item;

import ca.teamdman.sfm.client.ClientStuff;
import ca.teamdman.sfm.common.Constants;
import ca.teamdman.sfm.common.blockentity.ManagerBlockEntity;
import ca.teamdman.sfm.common.cablenetwork.CableNetwork;
import ca.teamdman.sfm.common.cablenetwork.CableNetworkManager;
import ca.teamdman.sfm.common.program.LabelPositionHolder;
import ca.teamdman.sfm.common.registry.SFMItems;
import ca.teamdman.sfm.common.util.SFMUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class LabelGunItem extends Item {
    public LabelGunItem() {
        super(new Properties().stacksTo(1).tab(SFMItems.TAB));
    }

    public static void setActiveLabel(ItemStack gun, String label) {
        if (label.isEmpty()) return;
        LabelPositionHolder.from(gun).addReferencedLabel(label).save(gun);
        gun.getOrCreateTag().putString("sfm:active_label", label);
    }

    public static String getActiveLabel(ItemStack stack) {
        //noinspection DataFlowIssue
        return !stack.hasTag() ? "" : stack.getTag().getString("sfm:active_label");
    }

    public static String getNextLabel(ItemStack gun, int change) {
        var labels = LabelPositionHolder.from(gun).get().keySet().stream().sorted(Comparator.naturalOrder()).toList();
        if (labels.isEmpty()) return "";
        var currentLabel = getActiveLabel(gun);

        int currentLabelIndex = 0;
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).equals(currentLabel)) {
                currentLabelIndex = i;
                break;
            }
        }

        int nextLabelIndex = currentLabelIndex + change;
        // ensure going negative wraps around
        nextLabelIndex = ((nextLabelIndex % labels.size()) + labels.size()) % labels.size();

        return labels.get(nextLabelIndex);
    }

    @Override
    public InteractionResult onItemUseFirst(
            ItemStack gun, UseOnContext ctx
    ) {
        var level = ctx.getLevel();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        var gunLabels = LabelPositionHolder.from(gun);
        var pos = ctx.getClickedPos();

        if (level.getBlockEntity(pos) instanceof ManagerBlockEntity manager) {
            manager.getDisk().ifPresent(disk -> {
                Player player = ctx.getPlayer();
                if (player != null && player.isShiftKeyDown()) {
                    // copy labels from disk to gun and also add referenced labels from scripts
                    var diskLabels = LabelPositionHolder.from(disk);
                    manager.getReferencedLabels().forEach(diskLabels::addReferencedLabel);
                    diskLabels.save(gun);
                    player.sendSystemMessage(Constants.LocalizationKeys.LABEL_GUN_CHAT_PULLED.getComponent());
                } else {
                    // copy labels from gun to disk
                    gunLabels.save(disk);
                    manager.rebuildProgramAndUpdateDisk();
                    manager.setChanged();
                    if (player != null) {
                        player.sendSystemMessage(Constants.LocalizationKeys.LABEL_GUN_CHAT_PUSHED.getComponent());
                    }
                }
            });
            return InteractionResult.CONSUME;
        }

        var label = getActiveLabel(gun);
        if (label.isEmpty()) return InteractionResult.SUCCESS;


        if (Screen.hasControlDown()) {
            // find all connected inventories of the same block type and toggle the label on all of them
            // if any of them don't have it, apply it, otherwise strip from all
            Set<BlockPos> cablePositions = CableNetworkManager
                    .getNetworksForLevel(level)
                    .flatMap(CableNetwork::getCablePositions)
                    .collect(Collectors.toSet());
            Block targetBlock = level.getBlockState(pos).getBlock();
            List<BlockPos> positions = SFMUtils.getRecursiveStream((current, nextQueue, results) -> {
                results.accept(current);
                for (var d : Direction.values()) {
                    var offset = current.offset(d.getNormal());
                    if (level.getBlockState(offset).getBlock() == targetBlock) {
                        // this is the block we are looking for
                        // ensure it is also adjacent to a cable
                        if (Arrays
                                .stream(Direction.values())
                                .anyMatch(d2 -> cablePositions.contains(offset.offset(d2.getNormal())))) {
                            nextQueue.accept(offset);
                        }
                    }
                }
            }, pos).toList();

            var existing = new HashSet<>(gunLabels.getPositions(label));
            boolean anyMissing = positions.stream().anyMatch(p -> !existing.contains(p));
            if (anyMissing) {
                gunLabels.addAll(label, positions);
            } else {
                positions.forEach(p -> gunLabels.remove(label, p));
            }
        } else {
            if (ctx.getPlayer() != null && ctx.getPlayer().isShiftKeyDown())
                gunLabels.remove(pos);
            else
                gunLabels.toggle(label, pos);
        }

        gunLabels.save(gun);
        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(
            ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag detail
    ) {
        lines.add(Constants.LocalizationKeys.LABEL_GUN_ITEM_TOOLTIP_1.getComponent().withStyle(ChatFormatting.GRAY));
        lines.add(Constants.LocalizationKeys.LABEL_GUN_ITEM_TOOLTIP_2.getComponent().withStyle(ChatFormatting.GRAY));
        lines.add(Constants.LocalizationKeys.LABEL_GUN_ITEM_TOOLTIP_3.getComponent().withStyle(ChatFormatting.GRAY));
        lines.addAll(LabelPositionHolder.from(stack).asHoverText());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {
        var stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            ClientStuff.showLabelGunScreen(stack, hand);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public Component getName(ItemStack stack) {
        var name = getActiveLabel(stack);
        if (name.isEmpty()) return super.getName(stack);
        return Constants.LocalizationKeys.LABEL_GUN_ITEM_NAME_WITH_LABEL
                .getComponent(name)
                .withStyle(ChatFormatting.AQUA);
    }
}
