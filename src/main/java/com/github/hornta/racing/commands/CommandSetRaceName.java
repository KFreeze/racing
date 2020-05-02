package com.github.hornta.racing.commands;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.racing.RacingManager;
import com.github.hornta.racing.enums.RaceState;
import com.github.hornta.racing.events.RaceChangeNameEvent;
import com.github.hornta.racing.MessageKey;
import com.github.hornta.messenger.MessageManager;
import com.github.hornta.racing.objects.Race;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CommandSetRaceName extends RacingCommand implements ICommandHandler {
  public CommandSetRaceName(RacingManager racingManager) {
    super(racingManager);
  }

  @Override
  public void handle(CommandSender commandSender, String[] args, int typedArgs) {
    Race race = racingManager.getRace(args[0]);

    if(race.getState() != RaceState.UNDER_CONSTRUCTION) {
      MessageManager.setValue("race_name", race.getName());
      MessageManager.sendMessage(commandSender, MessageKey.EDIT_NO_EDIT_MODE);
      return;
    }

    String oldName = race.getName();
    race.setName(args[1]);

    racingManager.updateRace(race, () -> {
      Bukkit.getPluginManager().callEvent(new RaceChangeNameEvent(race, oldName));
      MessageManager.setValue("old_name", oldName);
      MessageManager.setValue("new_name", race.getName());
      MessageManager.sendMessage(commandSender, MessageKey.CHANGE_RACE_NAME_SUCCESS);
    });
  }
}
