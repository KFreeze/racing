package com.github.hornta.racing.commands;

import se.hornta.commando.ICommandHandler;
import se.hornta.messenger.MessageManager;
import com.github.hornta.racing.MessageKey;
import com.github.hornta.racing.RacingManager;
import com.github.hornta.racing.Util;
import com.github.hornta.racing.enums.RaceStatType;
import com.github.hornta.racing.objects.Race;
import com.github.hornta.racing.objects.RacePlayerStatistic;

import org.bukkit.command.CommandSender;

import java.util.Locale;
import java.util.Set;

public class CommandTop extends RacingCommand implements ICommandHandler {
  public CommandTop(RacingManager racingManager) {
    super(racingManager);
  }

  @Override
  public void handle(CommandSender commandSender, String[] args, int typedArgs) {
    Race race = racingManager.getRace(args[0]);
    RaceStatType statType = RaceStatType.valueOf(args[1].toUpperCase(Locale.ENGLISH));
    int laps = Integer.parseInt(args[2]);
    
    sendTopMessage(commandSender, race, laps, statType);
  }

  static public void sendTopMessage(CommandSender target, Race race, int laps, RaceStatType statType) {
    MessageManager.setValue("type", statType.getFormattedStat(laps));
    MessageManager.setValue("race_name", race.getName());
    MessageManager.sendMessage(target, MessageKey.RACE_TOP_HEADER);
  
    Set<RacePlayerStatistic> results = race.getResults(statType, laps);
    int i = 1;
    for(RacePlayerStatistic result : results) {
      Util.setTimeUnitValues();
      String value = result.getStatValue(statType, laps);
      MessageManager.setValue("position", i++);
      MessageManager.setValue("value", value);
      MessageManager.setValue("player_name", result.getPlayerName());
      MessageManager.sendMessage(target, MessageKey.RACE_TOP_ITEM);
  
      if (i == 10) {
        break;
      }
    }
  
    for(int k = i; k <= 10; k++) {
      MessageManager.setValue("position", k);
      MessageManager.sendMessage(target, MessageKey.RACE_TOP_ITEM_NONE);
    }
  }
}

