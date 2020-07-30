package com.github.hornta.racing.hd_top_list.commands;

import se.hornta.commando.ICommandHandler;
import se.hornta.messenger.MessageManager;
import com.github.hornta.racing.MessageKey;
import com.github.hornta.racing.hd_top_list.HDTopList;
import com.github.hornta.racing.hd_top_list.HDTopListManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListHDTopList implements ICommandHandler {
  @Override
  public void handle(CommandSender commandSender, String[] args, int i) {
    MessageManager.sendMessage(commandSender, MessageKey.HD_TOP_LIST_LIST_HEADER);

    for(HDTopList topList : HDTopListManager.getTopLists()) {
      TextComponent tc = new TextComponent();

      MessageManager.setValue("top_list", topList.getName());
      ClickEvent teleoportClick = new ClickEvent(ClickEvent.Action.RUN_COMMAND, MessageManager.getMessage(MessageKey.HD_TOP_LIST_LIST_TELEPORT_CLICK));
      HoverEvent teleportHover = new HoverEvent(
        HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MessageManager.getMessage(MessageKey.HD_TOP_LIST_LIST_TELEPORT_HOVER)).create()
      );
      MessageManager.setValue("top_list", topList.getName());
      if(commandSender instanceof Player) {
        tc.addExtra(new ComponentBuilder(MessageManager.getMessage(MessageKey.HD_TOP_LIST_LIST_ITEM))
          .event(teleportHover)
          .event(teleoportClick).create()[0]);
      } else {
        tc.addExtra(MessageManager.getMessage(MessageKey.HD_TOP_LIST_LIST_ITEM));
      }

      MessageManager.setValue("stat", topList.getStatType().getFormattedStat(topList.getLaps()));
      MessageManager.setValue("race", topList.getRace().getName());
      tc.addExtra(MessageManager.getMessage(MessageKey.HD_TOP_LIST_LIST_ITEM_INFO));
      commandSender.spigot().sendMessage(tc);
    }
  }
}
